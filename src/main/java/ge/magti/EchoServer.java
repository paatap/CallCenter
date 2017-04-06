package ge.magti;

/**
 * Created by user on 3/7/17.
 */
import ge.magti.server.GreetingServiceImpl;
import ge.magti.server.functions;
import ge.magti.server.sets;

import java.io.IOException;
import java.util.*;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 * @ServerEndpoint gives the relative name for the end point
 * This will be accessed via ws://localhost:8080/EchoChamber/echo
 * Where "localhost" is the address of the host,
 * "EchoChamber" is the name of the package
 * and "echo" is the address to access this class from the server
 */
@ServerEndpoint("/message/{clientId}")
public class EchoServer {
    /**
     * @OnOpen allows us to intercept the creation of a new session.
     * The session class allows us to send data to the user.
     * In the method onOpen, we'll let the user know that the handshake was
     * successful.
     */
    @OnOpen
    public void onOpen(@PathParam("clientId") String clientId, Session session){
        System.out.println(session.getId() + " has opened a connection==============client=============="+clientId);



        try {
            session.getBasicRemote().sendText("Connection Established==========client==============="+clientId);
 String[] s2=clientId.split(",");
            addsession(s2[0],session, functions.str2int(s2[1]));
            //GreetingServiceImpl.callpause(s2[0]);
            //GreetingServiceImpl.callenable(s2[0],s2[1]);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    static HashMap<String,mysession> session2=new HashMap<String,mysession>();
    static HashMap<String,Integer> grprestadd=new HashMap<String,Integer>();


    static synchronized void addsession(String clientId, Session session,int grp){
        mysession ses=new mysession();
        ses.session=session;
        ses.grp=grp;
        session2.put(clientId,ses);
    }
    public static synchronized void removesession(String clientId){
        mysession ses=session2.get(clientId);
        GreetingServiceImpl.callpause(clientId,true,ses.uname);
        session2.remove(clientId);
    }
    static synchronized void removesession(Session session){
        for (Map.Entry entr:session2.entrySet()){
            mysession ses=(mysession)entr.getValue();
            if (session.equals(ses.session)){
                System.out.println("remove session=="+entr.getKey().toString());
                GreetingServiceImpl.callpause(entr.getKey().toString(),true,ses.uname);
                session2.remove(entr.getKey());
                break;
            }
        }
    }
    static synchronized void removesession(){
        ArrayList rems=new ArrayList();
        for (Map.Entry entr:session2.entrySet()){
            mysession ses = (mysession) entr.getValue();
            if (ses==null) {
                rems.add(entr.getKey());
            }else if (ses.session==null){
                rems.add(entr.getKey());
            }else if (!ses.session.isOpen()) {
                rems.add(entr.getKey());
            }else {

                String query="select oid from oprest where state=205 and statedop=0 and " +
                        " EXTRACT(EPOCH FROM now()-start_time)>"+sets.RestWarnTime;
                //System.out.println("kukukuku");
                ArrayList<String[]> res=functions.getResult(query,functions.isnewcc);

                for (int i=0;i<res.size();i++){
                    restwarn(res.get(i)[0]);
                    sendmessage(res.get(i)[0],"$restwarning");
                    System.out.println("warn20 "+res.get(i)[0]);

                }

                query="select oid from oprest where state=205 and statedop=0 and " +
                        " resttime-EXTRACT(EPOCH FROM now()-start_time)<0 ";
                //System.out.println("kukukuku");
                res=functions.getResult(query,functions.isnewcc);
                for (int i=0;i<res.size();i++){
                    restwarn(res.get(i)[0]);
                    sendmessage(res.get(i)[0],"$restwarning");
                    System.out.println("warn "+res.get(i)[0]);
                }
            }
        }
        for (Object o:rems){
            mysession ses=session2.get(o);
            GreetingServiceImpl.callpause(o.toString(),true,ses.uname);
            session2.remove(o);
        }
    }
    public static mysession getsession(String clientId){
        return session2.get(clientId);
    }
    public static mysession getsession_callid(String callid){
         for (Map.Entry entr:session2.entrySet()){

            if (entr.getValue()!=null) {
                mysession mses=(mysession)entr.getValue();
                if (mses==null) System.out.println("nulll");
                else System.out.println(mses.callid+"=="+callid);
                if (callid.equals(((mysession)entr.getValue()).callid))
                return (mysession)entr.getValue();
            }

        }
        return null;
    }
    public static String sendmessage_callid(String callid,String message){

        mysession mses=getsession_callid(callid);

        if (mses==null) return "session not enable";

        Session ses=mses.session;
        if (ses==null) return "session not enable";
        if (ses.isOpen()) {
            try {
                ses.getBasicRemote().sendText(message);
                return "ok";
            } catch (IOException e) {
                e.printStackTrace();
                return "session exception";
            }
        }else{
            //removesession(clientId);
            return "session closed_callid";
        }
    }
     public static String sendmessage(String clientId,String message){
        mysession mses=session2.get(clientId);
        if (mses==null) return "session not enable";
         Session ses=mses.session;
         if (ses==null) return "session not enable";
         if (ses.isOpen()) {
             try {
                 ses.getBasicRemote().sendText(message);
                 return "ok";
             } catch (IOException e) {
                 e.printStackTrace();
                 return "session exception";
             }
         }else{
             removesession(clientId);
             return "session closed";
         }
     }
    /**
     * When a user sends a message to the server, this method will intercept the message
     * and allow us to react to it. For now the message is read as a String.
     */
    @OnMessage
    public void onMessage(String message, Session session){
        System.out.println("Message from =================================" + session.getId() + ": " + message);
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * The user closes the connection.
     *
     * Note: you can't send messages to the client from this method
     */
    @OnClose
    public void onClose(Session session){
        System.out.println("Session " +session.getId()+" has ended");
        removesession(session);
    }


    public static String getops(String input){
        String mynumber=input.split("\t")[1];
        String ss="$getops\ntoall\t-100\nmobile\t-0\ngov\t-2\nmagtifix\t-3\nmagtisat\t-13\ntelemarket\t-6";
        for (Map.Entry entr:EchoServer.session2.entrySet()) {
            mysession ses = (mysession) entr.getValue();
            if (ses==null) {

            }else if (ses.session==null){

            }else if (!ses.session.isOpen()) {

            }else {
                if (!entr.getKey().toString().equals(mynumber))
                    ss+="\n"+ses.uname+"\t"+entr.getKey().toString();
            }
        }
        return ss;
    }
    public static String sendopmessage(String input){

     /*   try{
            Thread.sleep(3000);
        }catch(Exception e){}
        */

        System.out.println("sendopmessage="+input);
        String[] s2=input.split("\n");
        String[] s22=s2[0].split("\t");
   //     "sendmessage\t"+
     //                   CallCenter.callCenterInstance.mynumber+"\t"+CallCenter.callCenterInstance.uname+
       //                 "\t"+ops.getValue().toString()+"\n"+txt.getValue().toString());
        String ss="$sendopmessage\t"+s22[2]+"<<<<<<";
        for (int i=1;i<s2.length;i++) {
            if (i==1) ss+=s2[i];
            else ss+="\n"+s2[i];
        }

        if (s22[3].equals("-100")){
                    for (Map.Entry entr:EchoServer.session2.entrySet()) {
                        mysession ses = (mysession) entr.getValue();
                        if (ses!=null&&ses.session!=null&&ses.session.isOpen())
                try {
                    ses.session.getBasicRemote().sendText(ss);
                } catch (IOException e) {
                    //e.printStackTrace();
                }
                    }
        }else if (s22[3].startsWith("-")){
            int grpt=-functions.str2int(s22[3]);
                    for (Map.Entry entr:EchoServer.session2.entrySet()) {
                        mysession ses = (mysession) entr.getValue();
                        if (ses!=null&&ses.session!=null&&ses.session.isOpen())
                            if (ses.grp==grpt)
                                try {
                                    ses.session.getBasicRemote().sendText(ss);
                                } catch (IOException e) {
                                    //e.printStackTrace();
                                }
                    }
        }else {
            mysession ses = session2.get(s22[3]);
            if (ses!=null&&ses.session!=null&&ses.session.isOpen())
                try {
                    ses.session.getBasicRemote().sendText(ss);
                } catch (IOException e) {
                    //e.printStackTrace();
                }
        }

        return "$sendopmessageok";
    }
    static class Ops{
        public int   Operators=0;
        public int    Rest=0;
        public int  Ready=0;
        public int  Busy=0;
        public int   Conn=0;
        public int   Term=0;
    }
    public static String getserverinfo(){//synchronized ???
        StringBuffer ss=new StringBuffer("$getserverinfo\n");
        HashMap<Integer,Ops> grps= new HashMap<Integer,Ops>();
        for (Map.Entry entr:EchoServer.session2.entrySet()){
            mysession ses=(mysession)entr.getValue();

            if (ses==null) {

            }else if (ses.session==null){

            }else if (!ses.session.isOpen()) {

            }else {
                Ops grp1=grps.get(ses.grp);
                if (grp1==null){
                    grp1=new Ops();
                    grps.put(ses.grp,grp1);
                }


                if(ses.status==sets.REST) grp1.Rest++;
                if(ses.status==sets.READY) grp1.Ready++;
                if(ses.status==sets.BUSY) grp1.Busy++;
                if(ses.status==sets.CON) grp1.Conn++;
                if(ses.status==sets.TERMINATE) grp1.Term++;

                grp1.Operators++;
                long tt=(System.nanoTime() / 1000000 - ses.tim)/1000;
                String stt="";
                if (tt<60) stt=""+tt +" s";
                else stt=""+(tt/60)+" m "+(tt % 60) +" s";
                ss.append(functions.grp2grps(ses.grp)+" "+ses.grp+"\t" + functions.getstatus(ses.status)+"\t"+stt+"\t"+
                        ses.uname+"\t"+entr.getKey()+"\t" +ses.anumber+"\t" +ses.callid+"\n");
            }

        }
        ss.append("$stat\n");
        String   sgrps="";
        String   Operators="Operators";
        String    Rest="Rest";
        String  Ready="Ready";
        String  Busy="Busy";
        String   Conn="Connect";
        String   Term="Terminate";
        String   CanRest="CanRest";
        String   AddRes="AddRest";
        for (Map.Entry entr:grps.entrySet()){
            Ops grp1=(Ops)entr.getValue();
            int grp=functions.str2int(entr.getKey().toString());
            sgrps+="\t"+functions.grp2grps(grp)+" "+grp;
            Operators+="\t"+grp1.Operators;
            Rest+="\t"+grp1.Rest;
            Ready+="\t"+grp1.Ready;
            Busy+="\t"+grp1.Busy;
            Conn+="\t"+grp1.Conn;
            Term+="\t"+grp1.Term;

            Integer add=grprestadd.get(entr.getKey().toString());
            if (add==null) add=0;
            CanRest+="\t"+(grp1.Operators/10+1+add);
            AddRes+="\t"+add;
        }

        ss.append(""+sgrps+"\n"+Operators+"\n"+Rest+"\n"+Ready+"\n"+Busy+"\n"+Conn+"\n"+Term+"\n"+CanRest+"\n"+AddRes);
      //  System.out.println("===!"+ss.toString()+"!===");
        //    Operators
    //    RestOp
    //    ReadyOp
    //    RingingToOp
    //    Ringing
    //    MaxRest

        return ss.toString();
    }
    public static synchronized String getRest(String sgrp,String mynumber){
        System.out.println(sgrp+"=="+mynumber);
        int grp=functions.str2int(sgrp,-1);
        if (grp==-1) return "false";
        int ops=0;
        int rest=0;
        mysession myses=null;
        System.out.println("11111111111");
                for (Map.Entry entr:EchoServer.session2.entrySet()) {
                        mysession ses=(mysession)entr.getValue();


            if (ses==null) {

            }else if (ses.session==null){

            }else if (!ses.session.isOpen()) {

            }else {
                if (mynumber.equals(entr.getKey().toString()))
                    myses=ses;
                if (ses.grp==grp){
                    ops++;
                    if (ses.status==sets.REST) rest++;
                }
            }
                }

        Integer add=grprestadd.get(sgrp);
        if (add==null) add=0;
        System.out.println("======"+add+"=="+ops+"=="+rest);
                if (rest<ops/10+1+add){
                    System.out.println("22222222222222");
                    if (myses==null) return "false";
                    String srest = GreetingServiceImpl.reststart(mynumber);
                    System.out.println("33333333333333=="+srest);
                    if (srest.equals("false")) return "false";
                    myses.status=sets.REST;
                    return srest;
                }else return "false";

    }
    static Timer timer=new Timer();

    public static void starttimer (){
        TimerTask task;

        task = new TimerTask() {
            @Override
            public void run() {
                    removesession();
            }
        };
         timer.schedule(task, 0, 1000);
        System.out.println("start timer");
    };
    static void restwarn(String oid){
        String query=String.format("UPDATE oprest set statedop=%d WHERE oid='%s'",
                sets.RESTWARNING,oid);
        functions.execSql(query, functions.isnewcc);
    }
    public static String grpadd(String ss){
        System.out.println(ss);
        String[] s2=ss.split("\t");
        System.out.println(s2[1]+"==="+s2[2]);
        int add=functions.str2int0(s2[2]);
        grprestadd.put(s2[1],add);
        return "$say\tok";
    }
}

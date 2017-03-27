package ge.magti;

/**
 * Created by user on 3/7/17.
 */
import ge.magti.server.GreetingServiceImpl;
import ge.magti.server.functions;

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
            if (entr.getValue()==null) rems.add(entr.getKey());
            else{
                if (!((mysession)entr.getValue()).session.isOpen()){
                    rems.add(entr.getKey());
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

}
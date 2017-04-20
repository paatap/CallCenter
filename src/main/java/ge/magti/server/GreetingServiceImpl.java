package ge.magti.server;


import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.widgets.grid.ListGridField;
import ge.magti.EchoServer;
import ge.magti.client.CallCenter;
import ge.magti.client.GreetingService;
import ge.magti.mysession;
import ge.magti.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;


/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
    GreetingService {

  public String greetServer(String input) throws IllegalArgumentException {

    sets.init();



    // Verify that the input is valid.
    if (!FieldVerifier.isValidName(input)) {
      // If the input is not valid, throw an IllegalArgumentException back to
      // the client.
      throw new IllegalArgumentException(
          "Name must be at least 4 characters long");
    }

    //String serverInfo = getServletContext().getServerInfo();
    //String userAgent = getThreadLocalRequest().getHeader("User-Agent");

    // Escape data from the client to avoid cross-site script vulnerabilities.
  //  input = escapeHtml(input);
    //userAgent = escapeHtml(userAgent);
    if (input.startsWith("getserverinfo")) return EchoServer.getserverinfo();
    System.out.println("1111111111111111111111111111111111=="+input.substring(0,10));

    if (input.startsWith("getprobleminfo")) return getprobleminfo(input);//login2!!!!
    if (input.startsWith("login")) return login(input);
    if (input.startsWith("button")) return button(input);

    if (input.startsWith("savetxt")) return savetxt(input);
    if (input.startsWith("getops")) return EchoServer.getops(input);
    if (input.startsWith("get2ops")) return EchoServer.getops();
    if (input.startsWith("sendmessage")) return EchoServer.sendopmessage(input);
    if (input.startsWith("sendsms")) return sendsms(input);
    if (input.startsWith("grpadd")) return EchoServer.grpadd(input);
    if (input.startsWith("killopsession")) return EchoServer.killsession(input);
   // return "Hello, " + input + "!<br><br>I am running " + serverInfo
   //     + ".<br><br>It looks like you are using:<br>" + userAgent;
      return "unknown";
  }





  String savetxt(String input){
    String[] s2=input.split("\n");
    String[] s20=s2[0].split("\t");
    String uname=s20[1].replace(".","_").replace(" ","_");
    if (!uname.equals("")){
      StringBuffer ss=new StringBuffer("");
      for (int i=1;i<s2.length;i++){
        if (i==1)  ss.append(s2[i]);
        else  ss.append("\n"+s2[i]);
      }
      functions.str2file(ss.toString(),"/home/ccfiles/"+uname+".txt");
    }
    return "$savetxtok";
  }
  String button(String input){
    String[] s2=input.split("\t");
      String rest="?";

     if (s2[2].equals("rest")) {
         rest=EchoServer.getRest(s2[5], s2[3]);
         if (rest.equals("false")) {
             return "$button\trestno\t"+rest;
         }
     }




//            uname            dt    action            channel
//    CallCenter.callCenterInstance.sendgreet("button\t"+CallCenter.callCenterInstance.uname+"\t"+
//            click+"\t"+CallCenter.callCenterInstance.mynumber);

    String query="insert into oplog(uname,dt,action,channel) values ('"+s2[1]+"',NOW(),'"+s2[2]+"','"+s2[3]+"');";

functions.execSql(query,functions.isnewcc);

    if (s2[2].equals("end")) {
      query = "";
      String[] s22 = input.split("\n");
      String problems = "";
      String infos = "";
      int k = 0;String phonedescrip="";
      for (int i = 1; i < s22.length; i++) {
        if (s22[i].equals("\tproblems")) k = 0;
        else if (s22[i].equals("\tinfos")) k = 1;
        else if (s22[i].equals("\trequests")) k = 0;
        else if (s22[i].equals("\tphonedescrip")) k = 11;
        else {
          if (k == 0) {
            if (problems.equals("")) problems = s22[i];
            else problems += "," + s22[i];
          } else if (k == 1) {
            if (infos.equals("")) infos = s22[i];
            else infos += "," + s22[i];
          } else {
            if (phonedescrip.equals("")) phonedescrip=s22[i];
            else phonedescrip+="\n"+s22[i];
            System.out.println("*************************==="+s22[i]);
          }
        }
      }
      if (!problems.equals("") && !infos.equals(""))//4,3,
        query += "\ninsert into log2(callid,operat,problems,info,op_end) values ('" + s2[4] + "','SIP/" + s2[3] + "'," +
                "'{" + problems + "}','{" + infos + "}',now());";
      else if (!problems.equals(""))
        query += "\ninsert into log2(callid,operat,problems,op_end) values ('" + s2[4] + "','SIP/" + s2[3] + "'," +
                "'{" + problems + "}',now());";
      else if (!infos.equals(""))
        query += "\ninsert into log2(callid,operat,info,op_end) values ('" + s2[4] + "','SIP/" + s2[3] + "'," +
                "'{" + infos + "}',now());";

 System.out.println("*************************="+phonedescrip+"*="+s2[7]+"\n"+input);

      if (!phonedescrip.equals("")){
        phonedescrip=phonedescrip.replace("'","").replace("\"","");
        String number=s2[7].trim();
        if (number.length()>3&&!phonedescrip.equals(""))
        query += "select addphonedescrip('"+number+"','"+phonedescrip+"');";
      }
      System.out.println("*******************************===="+query);
      if (!query.equals("")) functions.execSql(query, functions.isnewcc);
    }
      int state=0;
      System.out.println("status====="+s2[2]+" "+s2[3]+" "+s2[6]);

      if (s2[2].equals("ready")){
        state=sets.READY;
      }else if (s2[2].equals("end")){
        state=sets.READY;
      }else if (s2[2].equals("busy")){
        state=sets.BUSY;
      }else if (s2[2].equals("rest")){
              //rest = this.reststart(s2[3]);
              state = sets.REST;
      }else if (s2[2].equals("restend")){rest=this.restend(s2[3]);
        state=sets.READY;
      }else if (s2[2].equals("terminate")){
        state=sets.TERMINATE;
      }

      if (state==sets.READY){
          callenable(s2[3],s2[6]);
      }else{
          callpause(s2[3],false,"");
      }
    System.out.println("sessssssssssssssssssss=="+s2[3]+"="+state);
    mysession ses=EchoServer.getsession(s2[3]);
    if (ses!=null){
      ses.status=state;ses.tim=System.nanoTime() / 1000000;
      if (state==sets.READY) {ses.callid="";ses.anumber="";}
        System.out.println("sess is not null");
    }


    return "$button\t"+s2[2]+"\t"+rest;

  }


  String login(String input){
    String[] s2=input.split("\t");





    String query="SELECT oid,optype,number,style FROM secrets where uname='"+s2[1]+"'and secret='"+s2[2]+"'";
      ArrayList<String[]> res = functions.getResult(query,functions.isnewcc);
    if (res.size()>0) {
        String res2;
   //     System.out.println("=========!"+res[0][2]+"!================");
        if (res.get(0)[2].startsWith("-")){
            res2="nophone";
           // System.out.println("=================nophone============");
        }else {

            query = "insert into oplog(uname,dt,action,channel) values ('" + s2[1] + "',NOW(),'login','" + res.get(0)[2] + "');";

            functions.execSql(query, functions.isnewcc);

            query = "select queue from pbx.member_control where number='" + res.get(0)[2] + "'";
            res2 = functions.getResult2(query, "\n", "\t", functions.isaster12).toString();
        }
      String warName = functions.getwarname(this);
//      if (s2[1].equals("sarchi")) {
      String setsmessagestring=sets.messagestring;
      if (!warName.equals("CallCenter")) {
        setsmessagestring=sets.messagestring.replace("CallCenter",warName);
        System.out.println(s2[1]+"==messagestring=="+sets.messagestring);
      }





      mysession ses=EchoServer.getsession(res.get(0)[2]);
      if (ses!=null){
        return "$login\terror\tSession is open!!!";
      }

        String rst=this.getRestOpTime(res.get(0)[2]);
     // System.out.println("111111111111111111111111111111111111");
        boolean debug=sets.debug;
        if (s2[1].equals("sarchi")) debug=true;
      return "$login\tok\t"+setsmessagestring+"\t"+debug+"\t"+res.get(0)[2]+"\t"+s2[1]+"\t"+res.get(0)[1]+"\t"+res.get(0)[0]+"\t"+CallCenter.ver+"\t"+res.get(0)[3]+"\n"+rst+"\n"+res2;
    }
    else return "$login\terror\tError user password !!!";
  }


   public static void callpause(String number,boolean logout,String uname){
      String query;
      if (logout)
           query="update pbx.member_control set pause='1',login='0' where number='"+number+"'";
      else query="update pbx.member_control set pause='1',login='1' where number='"+number+"'";
      functions.execSql(query,functions.isaster12);
      if (logout) {
         query = "insert into oplog(uname,dt,action,channel) values ('" + uname + "',NOW(),'logout','number');";
      }
   }
  public static void callenable(String number,String grps){
     //String grps=grp2grps(functions.str2int(grp));
    String query="update pbx.member_control set pause='1',login='1' where number='"+number+"'";
    System.out.println(query);
    functions.execSql(query,functions.isaster12);
    query="update pbx.member_control set pause='0' where number='"+number+"' and queue='"+grps+"'";
    System.out.println(query);
    functions.execSql(query,functions.isaster12);
  }


  String getprobleminfo(String input){
    String[] s2=input.split("\t");
    String vv=s2[1];
    String mygrp=s2[2];
      String optype=s2[5];
    StringBuffer  ss=new StringBuffer("");
    ss.append("$getprobleminfo\n$problem\n");

//    String query="SELECT problem.oid, problem.p_type,problem.grp,not(problem.grp="+mygrp+") as mygrp FROM problem where problem.grp in ("+vv+",100) ORDER BY mygrp,problem.p_type";

    String query="select problem.oid, problem.p_type,problem.grp,not(problem.grp="+mygrp+") as mygrp FROM problem where not(problem.grp=-10) ORDER BY mygrp,problem.p_type";
      ArrayList<String[]> res = functions.getResult(query,functions.isnewcc);

    for (int i=0;i<res.size();i++) {
      ss.append(""+res.get(i)[0]+"\t"+res.get(i)[1]+"\t"+res.get(i)[2]+"\n");
    }

    ss.append("$info\n");
//    query="SELECT info.oid, info.i_type,info.grp,not(info.grp="+mygrp+") as mygrp FROM info where info.grp in ("+vv+") ORDER BY mygrp,info.i_type";
   query="select info.oid, info.i_type,info.grp,not(info.grp="+mygrp+") as mygrp FROM info where not(info.grp=-10) ORDER BY mygrp,info.i_type";
    res = functions.getResult(query,functions.isnewcc);
    for (int i=0;i<res.size();i++) {
      ss.append(""+res.get(i)[0]+"\t"+res.get(i)[1]+"\t"+res.get(i)[2]+"\n");
    }
    ss.append("$savetxt\n");
    String uname=s2[3].replace(".","_").replace(" ","_");
    String txt=functions.file2str("/home/ccfiles/"+uname+".txt");
    ss.append(txt);


    ss.append("$shablon\n");
    txt=functions.file2str("/home/ccfiles/smsshablon.txt");
    ss.append(txt);

      ss.append("$reports\n");
//    query="SELECT info.oid, info.i_type,info.grp,not(info.grp="+mygrp+") as mygrp FROM info where info.grp in ("+vv+") ORDER BY mygrp,info.i_type";
      query="select name,title,dat from mydat where optype<='"+optype+"' and type=1 ORDER BY n";
      res = functions.getResult(query,functions.isnewcc);
      for (int i=0;i<res.size();i++) {
          ss.append(""+res.get(i)[0]+"\t"+res.get(i)[1]+"\t"+res.get(i)[2]+"\n");
      }

    mysession ses=EchoServer.getsession(s2[4]);
    if (ses!=null){
      ses.uname=s2[3];
      if (ses.status==0) {
          //ses.status=sets.LOGIN;
          ses.status=sets.READY;
          ses.tim=System.nanoTime() / 1000000;
      }
    }

   /* ss.append("$request\n");
    for (int i=0;i<13;i++) {
      ss.append(""+i+"\trequest"+i+"\n");
    }*/

   // System.out.println(ss);

    return ss.toString();
  }









  /////////////////////////////////////////////////////////////////////////////////////////////

  static String getRestOpTime(String oid)//oid=number
  {

      String ctmp=functions.getnow();
      String query=String.format("SELECT resttime,EXTRACT(EPOCH FROM now()-start_time)," +
              "state,statedop FROM oprest WHERE oid='%s' and rdate='%s'", oid,ctmp);

      ArrayList<String[]> res=functions.getResult(query,functions.isnewcc);


      if(res.size()==1)
      {

        //op[OpChan].reststarttime=atoi(PQgetvalue(res, 0, 1));
          if (res.get(0)[2]!=null&&res.get(0)[2].equals(""+sets.REST)){
             return res.get(0)[0]+"\t"+res.get(0)[1]+"\t"+res.get(0)[2]+"\t"+res.get(0)[3];
          }

        return res.get(0)[0];
      }  else
      {
        query=String.format("SELECT resttime  FROM oprest WHERE oid='%s'", oid);
        res=functions.getResult(query,functions.isnewcc);

        if(res.size()>=1) {
          query = String.format("UPDATE oprest set resttime='%d',rdate='%s',state=0,statedop=0 WHERE oid='%s'", sets.RestFullTime, ctmp, oid);
          functions.execSql(query, functions.isnewcc);
        }else {

          query = String.format("INSERT INTO oprest (oid,resttime,rdate,start_time,state,statedop) VALUES (%s,%d,'%s',now(),0,0)", oid, sets.RestFullTime, ctmp);
          functions.execSql(query, functions.isnewcc);
        }
        return ""+sets.RestFullTime;
      }

  }
  public static String reststart(String oid){
      String stt=getRestOpTime(oid).split("\t")[0];
      if (functions.str2int(stt,-1)<0) return "false";
             String query=String.format("UPDATE oprest set start_time=NOW(),state=%d,statedop=%d WHERE oid='%s'",
                 sets.REST,0,oid);
        functions.execSql(query, functions.isnewcc);
        return stt;
  }
  static String restend(String oid){
        String query=String.format("UPDATE oprest set state=%d,statedop=%d,resttime=resttime-EXTRACT(EPOCH FROM now()-start_time) WHERE oid='%s'",
                0,0,oid);

        functions.execSql(query, functions.isnewcc);
        return getRestOpTime(oid);
  }



/*
  void setRestOpTime(int oid,boolean startrest,int state,int statedop)//aaaaaaaaaaaaaa
  {

      if (startrest)
      {
        String query=String.format("UPDATE oprest set start_time=NOW(),state=%d,statedop=%d WHERE oid='%d'",
                 state,statedop,oid);
        functions.execSql(query, functions.isnewcc);

      }
      else
      {
        //int dt=getDBTime()-op[OpChan].reststarttime;
//   		sprintf(query,"UPDATE oprest set state=%d,statedop=%d,resttime=resttime-cast(extract(second from current_timestamp-start_time)+60*extract(minute from current_timestamp-start_time)+60*60*extract(hour from current_timestamp-start_time) as int) WHERE oid='%d'", op[OpChan].state,op[OpChan].statedop,op[OpChan].oid);
        String query=String.format("UPDATE oprest set state=%d,statedop=%d,resttime=resttime-EXTRACT(EPOCH FROM now()-start_time) WHERE oid='%d'",
                state,statedop,oid);

        functions.execSql(query, functions.isnewcc);
      }

    getRestOpTime(oid);
  }

*/



    //////////////////////////////////////////////////////////////////////////////////////////////
  String sendsms(String input){
    String[] s2=input.split("\n");
    String[] s22=s2[0].split("\t");
    String txt="";
    for (int i=1;i<s2.length;i++){
      if (i==1) txt=s2[i];else txt+="\n"+s2[i];
    }
    String ss= null;
    try {

      ss = java.net.URLEncoder.encode(txt,"UTF-8");

    if (ss.length()>170) ss=ss.substring(0,170);

String s12=java.net.URLEncoder.encode(s22[1]+"-"+s22[2]+"-","UTF-8");

String s223=java.net.URLEncoder.encode(s22[3],"UTF-8");


      String warName = new File(getServletContext().getRealPath("/")).getName();

   //   System.out.println("warName====="+warName);
    ss="/cgi-bin/sendsms?username=1&password=1&to=%2B995"+s223+"&from=110011"+
            "&dlr-url=http://192.168.27.30:8080/"+warName+"/mymessage?resp=%25d-"+
            s12+ss+"-%25p&dlr-mask=31&text="+ss+"&coding=2&charset=utf8";

  //    http://192.168.19.194:13013/cgi-bin/sendsms?username=2&password=2&from=123&to=%2B995595392929&
      // text=%E1%83%A2%E1%83%94%E1%83%A1%E1%83%A2%E1%83%98&coding=2&charset=utf8

///http://192.168.19.194:13013cgi-bin/sendsms?username=1&password=1&to=%2B995599283399&from=110011&dlr-url=http://192.168.27.30:8080/CallCentr/mymessage?resp=%25d-400-sarchi-fdvg+fvf-%25p&dlr-mask=31&text=fdvg+fvf&coding=2&charset=utf8
      System.out.println(ss);
      functions.mysocket(ss,"192.168.19.194",13013);




    } catch (Exception e) {
      System.out.println(ss+"===resperr");
    }
    return "ok";
  }





  /**
   * Escape an html string. Escaping data received from the client helps to
   * prevent cross-site script vulnerabilities.
   *
   * @param html the html string to escape
   * @return the escaped string
   */
  private String escapeHtml(String html) {
    if (html == null) {
      return null;
    }
    return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(
        ">", "&gt;");
  }
}

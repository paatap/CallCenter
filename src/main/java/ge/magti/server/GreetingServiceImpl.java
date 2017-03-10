package ge.magti.server;

import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.widgets.grid.ListGridField;
import ge.magti.client.GreetingService;
import ge.magti.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
    GreetingService {

  public String greetServer(String input) throws IllegalArgumentException {

    sets.init();

    System.out.println("1111111111111111111111111111111111");
    System.out.println("1111111111111111111111111111111111");
    System.out.println("1111111111111111111111111111111111");
    System.out.println("1111111111111111111111111111111111");
    System.out.println("1111111111111111111111111111111111");
    System.out.println("1111111111111111111111111111111111");
    // Verify that the input is valid.
    if (!FieldVerifier.isValidName(input)) {
      // If the input is not valid, throw an IllegalArgumentException back to
      // the client.
      throw new IllegalArgumentException(
          "Name must be at least 4 characters long");
    }

    String serverInfo = getServletContext().getServerInfo();
    String userAgent = getThreadLocalRequest().getHeader("User-Agent");

    // Escape data from the client to avoid cross-site script vulnerabilities.
    input = escapeHtml(input);
    userAgent = escapeHtml(userAgent);

    if (input.startsWith("getprobleminfo")) return getprobleminfo(input);
    if (input.startsWith("login")) return login(input);

    return "Hello, " + input + "!<br><br>I am running " + serverInfo
        + ".<br><br>It looks like you are using:<br>" + userAgent;
  }

  String login(String input){
    String[] s2=input.split("\t");
    String query="SELECT oid,optype,number FROM secrets where uname='"+s2[1]+"'and secret='"+s2[2]+"'";
    String[][] res = functions.getResult(query,functions.isnewcc);
    if (res.length>0) return "$login\tok";
    else return "$login\terror";
  }


  String getprobleminfo(String input){
    String[] s2=input.split("\t");
    String vv=s2[1];
    String mygrp=s2[2];
    StringBuffer  ss=new StringBuffer("");
    ss.append("$getprobleminfo\n$problem\n");


    String query="SELECT problem.oid, problem.p_type,problem.grp,not(problem.grp="+mygrp+") as mygrp FROM problem where problem.grp in ("+vv+",100) ORDER BY mygrp,problem.p_type";
    String[][] res = functions.getResult(query,functions.isnewcc);

    for (int i=0;i<res.length;i++) {
      ss.append(""+res[i][0]+"\t"+res[i][1]+"\t"+res[i][2]+"\n");
    }

    ss.append("$info\n");
    query="SELECT info.oid, info.i_type,info.grp,not(info.grp="+mygrp+") as mygrp FROM info where info.grp in ("+vv+") ORDER BY mygrp,info.i_type";
    res = functions.getResult(query,functions.isnewcc);
    for (int i=0;i<res.length;i++) {
      ss.append(""+res[i][0]+"\t"+res[i][1]+"\t"+res[i][2]+"\n");
    }

   /* ss.append("$request\n");
    for (int i=0;i<13;i++) {
      ss.append(""+i+"\trequest"+i+"\n");
    }*/

    System.out.println(ss);

    return ss.toString();
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

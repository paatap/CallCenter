package ge.magti;

import com.sun.xml.internal.ws.client.BindingProviderProperties;
import ge.magti.server.functions;
import ge.magti.server.gobs;
import ge.magti.server.sets;
import mypackage.BankPaymentFascadeBean;
import mypackage.BankPaymentService;
import mypackage.ExecuteQuery;

import java.io.IOException;
        import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.BindingProvider;

/**
 *
 * @author root
 */
public class cc_get_number extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("begin======================================");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        try  {

            /* TODO output your page here. You may use following sample code. */


            String ssreq=functions.printvars(request);



            String cc_command = request.getParameter("command");
            String anum ="";
            String bnum ="";
            if (cc_command==null){
                System.out.println("command=null!!!!!!!!");
            }else {
                System.out.println("command="+cc_command);
                if (cc_command.equals("1")) {

                    System.out.println("command=1");
                    String callid = request.getParameter("callid");
                    anum = request.getParameter("anum");


                    ExecuteQuery.Params ps=new ExecuteQuery.Params();

                    ExecuteQuery.Params.Entry en=new ExecuteQuery.Params.Entry();
                    if (anum.startsWith("0")) anum=anum.substring(1);
                    en.setKey("phone");en.setValue(anum);
                    ps.getEntry().add(en);
                    String vip="0";
                    try {
                        BankPaymentFascadeBean serp = new BankPaymentFascadeBean();



BankPaymentService serv=serp.getBankPaymentServicePort();//2017-7-24
BindingProvider bp=((BindingProvider)serv);
Map<String, Object> requestContext = bp.getRequestContext();
requestContext.put(BindingProviderProperties.REQUEST_TIMEOUT, 3000); // Timeout in millis
requestContext.put(BindingProviderProperties.CONNECT_TIMEOUT, 1000); // Timeout in millis


                        vip = serv.executeQuery(170, ps);
                    }catch(Exception e){
                        System.out.println("wsdl err"+e.toString());
                    }

                    String langeng = "0";
                    String langrus = "0";
                    String langgeo = "1";
                    //String ss="$call1\t"+callid+"\t"+anum;
                    //  System.out.println("bbb="+bnum);
                    String cc_output = cc_command + "," + callid + "," + anum + "," + vip + "," + langeng + "," + langrus + "," + langgeo;
                    System.out.println(functions.getnowdatetime()+" "+cc_output);
                    out.println(cc_output);

                    //EchoServer.sendmessage("","$command1"+"\t"+callid+"\t"+anum);
                } else if (cc_command.equals("2")) {

                    //
                    System.out.println("command=2");
                    String callid = request.getParameter("callid");
                    anum = request.getParameter("anum");
                    bnum = request.getParameter("bnum");
                    System.out.println("callid=" + callid);

                    if (anum.length()<6){
                        anum=bnum;
                        bnum=request.getParameter("anum");
                        if (anum.startsWith("0")) anum=anum.substring(1);
                    }

                    System.out.println("anum=" + anum);
                    System.out.println("bnum=" + bnum);

//                /* TODO output your page here. You may use following sample code. */
//                out.println("<!DOCTYPE html>");
//                out.println("<html>");
//                out.println("<head>");
//                out.println("<title>Servlet cc_get_number</title>");
//                out.println("</head>");
//                out.println("<body>");
//                out.println("<h1> " + anum + "---" + bnum + "</h1>");
//                out.println("</body>");
//                out.println("</html>");
                    String cc_output = cc_command + "," + callid + "," + anum + "," + bnum;
                    System.out.println(functions.getnowdatetime()+" "+cc_output);
                    out.println(cc_output);
//                    sprintf(query,"SELECT log.operat, log.provider, log.problems, log.info, log.call_start,log.callid FROM log WHERE log.anumber='%s' AND log.op_answer IS NOT NULL AND log.call_start > '%s' ORDER BY log.call_start desc LIMIT 10",Chan[op[chan].chan1].CallingNumber, ttmp);






                    mysession sess=EchoServer.getsession(bnum);
                    boolean isgobs=false;String ssgobs="";
                    if (sess!=null){
                        isgobs=functions.isgobs(sess.grp);
                        if (isgobs) ssgobs= gobs.getSDMessageAD(sess.uname).toString();
                        sess.callid=callid;sess.anumber=anum;sess.status= sets.CON;
                        String query="insert into oplog(uname,dt,action,channel) values ('"+sess.uname+
                                "',NOW(),'answer','"+bnum+"');";

                        functions.execSql(query,functions.isnewcc);
                    }
                    String ss="";
                    String sql;
                    if (!isgobs) {

                        sql = "SELECT log.operat, log.provider, log2.problems, log2.info, log.call_start,log.callid FROM log " +
                                "left join log2 on log.callid=log2.callid and (log.operat=log2.operat or log.operat is null)  " +
                                "WHERE (log.anumber='" +
                                anum + "'or log.called='0" + anum + "') AND log.op_answer IS NOT NULL AND log.call_start > '" +
                                functions.getnowzavtra(-5) + "' ORDER BY log.call_start desc LIMIT 10";
                        System.out.println("sql=" + sql);
                        ss ="\n"+ functions.getResult2(sql, "\n", "\t", functions.isnewcc).toString();
                    }
                    sql="select descrip from phonedescrip where anumber='"+anum+"'";
                    String ss2=functions.getResult2(sql,"\n","\t",functions.isnewcc).toString();
                    System.out.println(bnum+"===="+"$command2"+"\t"+callid+"\t"+anum+ss+"\nit$is$phonedescrip\n"+ss2+ssgobs);
                    EchoServer.sendmessage(bnum,"$command2"+"\t"+callid+"\t"+anum+ss+
                            "\nit$is$phonedescrip\n"+ss2+ssgobs);
                } else if (cc_command.equals("3")) {
                    System.out.println("command=3");
                    String callid = request.getParameter("callid");
                    anum = request.getParameter("anum");
                    bnum = request.getParameter("bnum");
                    String transfer = request.getParameter("transfer");
                    if (transfer!=null&&transfer.equals("1")) transfer="\ttransfer";
                    else transfer="";
                    String res=EchoServer.sendmessage_callid(callid,"$command3"+transfer);
                    System.out.println("res==="+res);
                    String cc_output = cc_command + "," + callid + "," + anum + "," + bnum + ",ok";
                    System.out.println(functions.getnowdatetime()+" "+cc_output);
                    out.println(cc_output);

                } else if (cc_command.equals("99")) {
                    System.out.println("command=99");
                    String sql="select * from secrets";
                    StringBuffer ss=functions.getResult2(sql,"\n","\t",functions.isnewcceksp);
                    System.out.println("result===="+ss);
                    out.println(ss);

                }else if (cc_command.equals("98")) {
                    System.out.println("command=98");

                    out.println("kuku");

                }
                System.out.println("/CallCentr/cc_get_number" + ssreq);

                    String warname=functions.getwarname(this);

                    if (anum.contains("599283399")||bnum.contains("599283399"))
                 if (warname.equals("CallCenter")) {
                     //functions.mysocket("/CallCentr/cc_get_number" + ssreq, "192.168.27.30", 8080);
                     functions.mysocket("/CallCentr/cc_get_number" + ssreq, "192.168.18.22", 9080);
                 }



            }

    }catch(Exception e){

        System.out.println("eeeeeeeeeerrrr================"+e.toString());
        e.printStackTrace();
    }

    finally {
        out.close();
    }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
package ge.magti;

import ge.magti.server.GreetingServiceImpl;
import ge.magti.server.functions;
import ge.magti.server.sets;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by user on 3/7/17.
 */
@WebServlet(name = "mymessage")
@WebListener
public class mymessage extends HttpServlet implements ServletContextListener {



    @Override
    public void contextInitialized(ServletContextEvent event) {
        // Do your job here during webapp startup.
        System.out.println("======================CallCenter Started==============111===========");
        sets.init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        // Do your job here during webapp shutdown.
        EchoServer.stoptimer();
        System.out.println("=====================CallCenter Destroyed=========222=====");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        try{

        out.println("doPost kukukuku   kuku");
        System.out.println("uuuuuuuuuuuuuuuuuu");
    } finally {
        out.close();
    }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        String number=request.getParameter("number");
        String mess=request.getParameter("message");
        String ss0=request.getParameter("resp");
        String type=request.getParameter("type");
        try{

            if (ss0!=null){
                    System.out.println("otvet="+ss0);
                    String[] s2=ss0.split("-");
                    if (s2[0].equals("1"))
                    {

                        String str="$sendopmessage\t"+functions.getnowdatetime("HH:mm ")+s2[4]+"\nmivida";
                        EchoServer.sendmessage(s2[1], str);
                    }else   if (s2[0].equals("2")){

                        String str="$sendopmessage\t"+functions.getnowdatetime("HH:mm ")+s2[4]+"\nshectoma";
                        EchoServer.sendmessage(s2[1], str);
                    }
            }else if (mess!=null&&number!=null){
                String ss = EchoServer.sendmessage(number, mess.replace("_tt_","\t").replace("_nn_","\n").replace("_time_",functions.getnowdatetime("HH:mm")));
                System.out.println(ss);
                out.println(ss);
            }else if (type!=null&&type.equals("sinfo")){
                out.println(EchoServer.getserverinfo());

            }else {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet cc_get_number</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<table>");
                //int i=0;
                out.println("<tr><td> </td><td>uname</td><td>mynumber"
                        +"</td><td>grp</td>"
                        +"<td>anumber</td><td>callid</td><td>status</td><td>time</td></tr>\n");
                for (Map.Entry entr:EchoServer.session2.entrySet()){
                    mysession ses=(mysession)entr.getValue();
                    //i++;
                    if (ses==null) {
                        out.println("<tr><td>mysess-null</td></tr>\n");
                    }else if (ses.session==null){
                        out.println("<tr><td>sess-null</td></tr>\n");
                    }else if (!ses.session.isOpen()) {
                        out.println("<tr><td>sess-closed</td></tr>\n");
                    }else {
                        long tt=(System.nanoTime() / 1000000 - ses.tim)/1000;
                        out.println("<tr><td>"+ses.uname+"</td><td>"+entr.getKey()
                                +"</td><td>" +ses.grp
                                +"</td><td>" +ses.anumber+"</td><td>" +ses.callid
                                +"</td><td>"+functions.getstatus(ses.status)
                                +"</td><td>" + tt
                                +"</td></tr>\n");
                    }
                }
                out.println("<table><br>");
                out.println(EchoServer.getserverinfo().replace("\n","<br>\n"));
//                out.println("<h1> " + anum + "---" + bnum + "</h1>");
                out.println("</body>");
                out.println("</html>");
            }
        }catch(Exception e){
            System.out.println("eeeeeeeeeerrrr================"+e.toString());
        }

        finally {
        out.close();
        }
    }
}

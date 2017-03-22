package ge.magti;

import ge.magti.server.GreetingServiceImpl;
import ge.magti.server.functions;

import javax.servlet.ServletException;
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
public class mymessage extends HttpServlet {
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
        try{

            if (ss0!=null){
                    System.out.println("otvet="+ss0);
                    String[] s2=ss0.split("-");
                    if (s2[0].equals("1"))
                    {

                        String str="$sendopmessage<<"+s2[4]+"\t"+functions.getnowdatetime()+"\t mivida";
                        EchoServer.sendmessage(s2[1], str);
                    }else   if (s2[0].equals("2")){

                        String str="$sendopmessage<<"+s2[4]+"\t"+functions.getnowdatetime()+"\t shectoma";
                        EchoServer.sendmessage(s2[1], str);
                    }
            }else if (mess!=null&&number!=null){
                String ss = EchoServer.sendmessage(number, mess);
                System.out.println(ss);
                out.println(ss);
            }else {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet cc_get_number</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<table>");
                int i=0;
                out.println("<tr><td> </td><td>uname</td><td>mynumber"
                               +"</td><td>grp</td><td>callid"
                                +"</td><td>anumber</td><td>status</td></tr>\n");
                for (Map.Entry entr:EchoServer.session2.entrySet()){
                    mysession ses=(mysession)entr.getValue();
                    i++;
                    if (ses==null) {
                        out.println("<tr><td> " + i + "</td><td>mysess-null</td></tr>\n");
                    }else if (ses.session==null){
                        out.println("<tr><td> " + i + "</td><td>sess-null</td></tr>\n");
                    }else if (!ses.session.isOpen()) {
                        out.println("<tr><td> " + i + "</td><td>sess-closed</td></tr>\n");
                    }else {
                        out.println("<tr><td> " + i + "</td><td>"+ses.uname+"</td><td>"+entr.getKey()
                               +"</td><td>" +ses.grp+"</td><td>" +ses.callid
                                +"</td><td>" +ses.anumber+"</td><td>" + functions.getstatus(ses.status)+"</td></tr>\n");
                    }
                }
                out.println("<table>");
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

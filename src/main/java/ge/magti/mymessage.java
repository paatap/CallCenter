package ge.magti;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
        try{

            out.println("doGet kukukuku   kuku");
        System.out.println("uuuuuuuuuuuuuuuuuu");
            EchoServer.sessiont.getBasicRemote().sendText("kutdxcvhhfdc vvfffd");
    }catch(Exception e){
            System.out.println("eeeeeeeeeerrrr================"+e.toString());
        }

    finally {
        out.close();
    }
    }
}

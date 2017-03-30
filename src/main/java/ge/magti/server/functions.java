package ge.magti.server;

        import java.io.BufferedReader;
        import java.io.DataInputStream;
        import java.io.File;
        import java.io.FileInputStream;
        import java.io.FileOutputStream;
        import java.io.InputStreamReader;
        import java.io.OutputStreamWriter;
        import java.io.PrintWriter;
        import java.net.InetAddress;
        import java.net.InetSocketAddress;
        import java.net.NetworkInterface;
        import java.net.Socket;
        import java.net.URL;
        import java.net.URLConnection;
        import java.security.MessageDigest;
        import java.security.NoSuchAlgorithmException;
        import java.sql.*;
        import java.sql.Connection;
        import java.sql.SQLException;

        import java.util.*;

        import java.text.*;

        import javax.naming.InitialContext;
        import javax.servlet.http.*;
        import javax.sql.DataSource;

public class functions {
    public static final int isnew=1;
    public static final int isbackup=2;
    public static final int isgwt=3;
    public static final int isnewcc=4;
    public static final int isnewcceksp=5;
    public static final int isaster12=12;

/*
        InitialContext cxt = new InitialContext();
        if (cxt == null) {
            throw new Exception("Uh oh -- no context!");
        }

        DataSource ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/postgres");

        if (ds == null) {
            throw new Exception("Data source not found!");
        }
        return ds.getConnection();
  */

    public static Connection GetMyConnection(int _isnew) {
        //System.out.println("isnew==========="+_isnew);
        try {
            if (_isnew==isaster12) {
                Class.forName(sets.db_drivermysql);
                //System.out.println("driver===="+sets.db_drivermysql);
            }
            else {
                Class.forName(sets.db_driver);
                 //System.out.println("driver===="+sets.db_driver);
            }
        } catch (Exception e) {
            System.out.println("111111111" + e.toString());
            return null;
        }
        //            DriverManager.registerDriver(new com.microsoft.jdbc.sqlserver.SQLServerDriver());
        try {
            //            DriverManager.registerDriver(new com.microsoft.jdbc.sqlserver.SQLServerDriver());
            if (_isnew==isnew)
            {
                //System.out.println("new");
                return DriverManager.getConnection(sets.db_stringnew, sets.db_user,sets.db_pass);}
            else if (_isnew==isbackup)
            {
                //System.out.println("backup");
                return DriverManager.getConnection(sets.db_stringbackup, sets.db_user,sets.db_pass);}
            else if (_isnew==isgwt)
            {
                //System.out.println("gwt");
                return DriverManager.getConnection(sets.db_stringgwt, sets.db_user,sets.db_pass);}
            else if (_isnew==isnewcc)
            {

                return DriverManager.getConnection(sets.db_stringnewcc, sets.db_usernewcc,sets.db_passnewcc);
            }
            else if (_isnew==isnewcceksp)
            {
                System.out.println("get connection");
                InitialContext cxt = new InitialContext();
                if (cxt == null) {
                    throw new Exception("Uh oh -- no context!");
                }

                DataSource ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/postgres");

                if (ds == null) {
                    throw new Exception("Data source not found!");
                }
                System.out.println("ura conn est");
                Connection con=ds.getConnection();
                return con;
                //    return DriverManager.getConnection(sets.db_stringnewcc, sets.db_usernewcc,sets.db_passnewcc);
            }
            else if (_isnew==isaster12)
            {
                //System.out.println("gwt");
                //System.out.println("link===="+sets.db_string12);
                return DriverManager.getConnection(sets.db_string12, sets.db_user12,sets.db_pass12);}
        } catch (Exception e) {
            System.out.println("22222222" + e.toString()+"==_isnew="+_isnew);
            System.out.println("gwt="+sets.db_stringnewcc+"="+sets.db_usernewcc);
            return null;
        }
        return null;
    }

/*
    public static Connection GetMyConnection(isnew) {
        Connection conn=null;
//        if (conn==null)
        {
            try {

                Context initContext = new InitialContext();

                Context envContext  = (Context)initContext.lookup("java:/comp/env");



                DataSource ds = (DataSource)envContext.lookup("jdbc/callsql");

                conn = ds.getConnection();
//                System.out.println("pooling");
            } catch( Exception e ) {
                e.printStackTrace();

            }

        }
        return conn;
    }
*/

    public static String execSqlProcedure(String procedureName,int _isnew) {

        Connection connection = null;
        CallableStatement pstmt=null;

        try {

            connection = GetMyConnection(_isnew);
            pstmt = connection.prepareCall("{? = call " + procedureName);
            pstmt.registerOutParameter(1, Types.INTEGER);
            pstmt.execute();

            String result = pstmt.getString(1);
            return result;

        } catch (SQLException e) {

            e.printStackTrace();
            return e.toString();

        }finally {
            try { pstmt.close();      } catch (Exception e) {}
            try { connection.close();} catch (Exception e) {}

        }

    }

    public static String execFunction(String in_function, String[] in_values,int _isnew) {
        return execFunction(in_function,in_values,true,_isnew);
    }
    public static String execFunction(String in_function, String[] in_values,boolean sysout,int _isnew) {
        Connection connection = null;
        CallableStatement pstmt=null;
        try {

            connection = GetMyConnection(_isnew);
            //String [] temp = null;
            //temp = in_values.split("\\,");
            String tmpX = new String("");
            //                            System.out.println("Lengh: " + in_values.length);
            String cs = "";
            if (in_values == null)
                tmpX = "";
            else {
                //                            System.out.println("Lengh: " + in_values.length);
                for (int i = 1; i < in_values.length; i++) {
                    tmpX += "?,";

                }
                tmpX += "?";
            }
            pstmt =
                    connection.prepareCall("{? = call " + in_function + "(" + tmpX + ")}");
            System.out.println("{? = call " + in_function + "(" + tmpX + ")}");
            pstmt.registerOutParameter(1, Types.INTEGER);
            if (in_values != null)
                for (int i = 0; i < in_values.length; i++) {
                    pstmt.setString(i + 2, in_values[i]);
//                pstmt.setInt(i + 2, str2int0(in_values[i]));
                    cs += "," + in_values[i];
                }
            if (sysout) System.out.println("procedure=" + in_function + cs);
            pstmt.execute();
            String ss=String.format("%s",pstmt.getInt(1));
            //String ss = pstmt.getString(1);

            return ss;
        } catch (SQLException e) {
            e.printStackTrace();
            return e.toString();
        }finally {
            try { pstmt.close();      } catch (Exception e) {}
            try { connection.close();} catch (Exception e) {}

        }
        //        return "true";
    }

    public static String execSql(String sql,int _isnew) {
        Connection connection = null; Statement stmt=null;
        try {
            //                table_props.gettable_names(in_statement);
            connection = GetMyConnection(_isnew);
            stmt = connection.createStatement();
            //stmt.executeQuery(sql);
            stmt.execute(sql);
            connection.close();
        } catch (SQLException e) {

            e.printStackTrace();
            return e.toString();

        }finally {
            try { stmt.close();      } catch (Exception e) {}
            try { connection.close();} catch (Exception e) {}

        }
        return "0";
    }




    public static String[][] getResult(String sql,int _isnew) {
//        System.out.println(sql);
        String[][] retVal;
        Connection connection = null;Statement stmt =null;
        try {

            connection = GetMyConnection(_isnew);
            stmt =
                    connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                            ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(sql);
            int columnCount = rs.getMetaData().getColumnCount();
            int rowCount = 0;

            while (rs.next())
                rowCount++;
            rs.beforeFirst();

            retVal = new String[rowCount][columnCount];

            int i = 0;

            while (rs.next()) {

                for (int k = 1; k <= columnCount; k++) {
                    retVal[i][k - 1] = rs.getString(k);
                    //   System.out.println("===="+k+"="+rs.getString(k));
                }
                i++;

            }



            return retVal;


        } catch (SQLException e) {
            System.out.println("***: " + e.toString());

        }finally {
            try { stmt.close();      } catch (Exception e) {}
            try { connection.close();} catch (Exception e) {}

        }
        return null;
    }
    public static StringBuffer getResult2(String sql,String nn,String tt,int _isnew) {
        System.out.println(sql);
        StringBuffer retVal=new StringBuffer("");
        Connection connection = null;Statement stmt =null;
        try {

            connection = GetMyConnection(_isnew);
            System.out.println("11111111111111111111111");
            stmt =
                    connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                            ResultSet.CONCUR_READ_ONLY);
            System.out.println("222222222222222222222");
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("333333333333333333333333333333");
            int columnCount = rs.getMetaData().getColumnCount();
            System.out.println("44444444444444444444=="+columnCount);
      /*      int rowCount = 0;

            while (rs.next())
                rowCount++;
            rs.beforeFirst();*/

//            retVal = new String[rowCount][columnCount];

//            int i = 0;

            while (rs.next()) {
                for (int k = 1; k <= columnCount; k++) {
                    retVal.append(rs.getString(k)+tt);
                }
                retVal.append(nn);
                //              i++;

            }
            System.out.println("55555555555555555555");


            return retVal;


        } catch (SQLException e) {
            System.out.println("***: " + e.toString());

        }finally {
            try { stmt.close();      } catch (Exception e) {}
            try { connection.close();} catch (Exception e) {}

        }
        return null;
    }
    public static String getTableNames(String name,int _isnew) {
        String sql = "select * from " + name+" limit 1";
        System.out.println(sql);
        Connection connection = null; Statement stmt =null;
        String retVal = "";
        try {

            connection = GetMyConnection(_isnew);
            stmt =
                    connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                            ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(sql);
            int columnCount = rs.getMetaData().getColumnCount();
            for (int k = 0; k < columnCount; k++)
                if (k == 0)
                    retVal = rs.getMetaData().getColumnName(k + 1);
                else
                    retVal += "," + rs.getMetaData().getColumnName(k + 1);




            return retVal;


        } catch (SQLException e) {
            System.out.println("***: " + e.toString());

        }finally {
            try { stmt.close();      } catch (Exception e) {}
            try { connection.close();} catch (Exception e) {}

        }
        return null;
    }

    public static String deleteFunction(String in_statement, String idName,
                                        String uid,int _isnew) {
        Connection connection = null;Statement stmt =null;
        try {
            //                table_props.gettable_names(in_statement);
            connection = GetMyConnection(_isnew);
            stmt = connection.createStatement();
            String sql =
                    "delete from " + in_statement + " where " + idName + "=" + uid;
            //stmt.executeQuery(sql);
            boolean x = stmt.execute(sql);
            if (x)
                return "error";

        } catch (SQLException e) {
            return e.toString();
        }finally {
            try { stmt.close();      } catch (Exception e) {}
            try { connection.close();} catch (Exception e) {}

        }
        return "1";
    }



    public static java.util.Date getServerTime(int _isnew) {
        Connection con = null;Statement stmt =null;
        java.util.Date ret = new java.util.Date();
        try {
            con = GetMyConnection(_isnew);
            String sql = "SELECT GETDATE() ";
            stmt =con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                java.sql.Date d = rs.getDate(1);
                ret.setTime(d.getTime());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            try { stmt.close();      } catch (Exception e) {}
            try { con.close();} catch (Exception e) {}

        }


        return ret;

    }

    public static String updateFunction(String in_statement,
                                        String[] table_names,
                                        String[] in_values,int _isnew) {
        return updateFunction(in_statement, table_names, in_values, "-1",_isnew);
    }

    public static String updateFunction(String in_statement,
                                        String[] table_names,
                                        String[] in_values, String where,int _isnew) {

        Connection connection = null;Statement stmt =null;
        String sql = "";
        try {
            //                table_props.gettable_names(in_statement);
            connection = GetMyConnection(_isnew);
            stmt = connection.createStatement();

            sql = "update " + in_statement + " set ";
            boolean da = false;

            for (int ii = 1; ii < in_values.length; ii++) {
                String nam = table_names[ii];
                String val = in_values[ii];

                boolean am=nam.equalsIgnoreCase("amount");
                if ((val != null) && (val.length() != 0)) {
                    if (am) {
                        if (da)
                            sql += String.format(",%s=%s", nam, val);
                        else
                            sql += String.format("%s=%s", nam, val);
                    }else {
                        if (da)
                            sql += String.format(",%s='%s'", nam, val);
                        else
                            sql += String.format("%s='%s'", nam, val);
                    }
                    da = true;
                } else {
                    if (da)
                        sql += String.format(",%s=null", nam);
                    else
                        sql += String.format("%s=null", nam);
                    da = true;

                }
            }
            sql += " where " + table_names[0] + "=" + in_values[0];

            if (!where.equals("-1")) {
                sql += (" and " + where);
            }

            System.out.println("update sql=" + sql);
            //                stmt.executeQuery(sql);
            boolean x = stmt.execute(sql);
            if (x)
                return "error";


        } catch (SQLException e) {
            return sql + e.toString();
        }finally {
            try { stmt.close();      } catch (Exception e) {}
            try { connection.close();} catch (Exception e) {}

        }
        return "1";
    }

    public static String insertFunction(String in_statement,
                                        String[] table_names,
                                        String[] in_values,int _isnew) {

        String sql = "";

        Connection connection = null;Statement stmt =null;
        try {
            //                table_props.gettable_names(in_statement);
            connection = GetMyConnection(_isnew);
            stmt = connection.createStatement();

            String lin1 = "";
            String lin2 = "";
            boolean da = false;
            for (int ii = 0; ii < in_values.length; ii++) {
                String nam = table_names[ii];
                String val = in_values[ii];

                if (val != null && val.length() != 0) {
                    if (da) {
                        lin1 += "," + nam;
                        lin2 += ",'" + val + "'";
                    } else {
                        lin1 += nam;
                        lin2 += "'" + val + "'";
                    }
                    da = true;
                }
            }

            sql =
                    "insert into " + in_statement + String.format(" (%s) values (%s)", lin1,
                            lin2);
            System.out.println(sql);

            //                stmt.executeQuery(sql);
            boolean x = stmt.execute(sql);
            if (x)
                return "error";


        } catch (SQLException e) {
            System.out.println(sql + e.toString());
            return sql + e.toString();
        }finally {
            try { stmt.close();      } catch (Exception e) {}
            try { connection.close();} catch (Exception e) {}

        }
        return "1";
    }




    ////////////////////////////////////////////////////////////////////////
    public static boolean isjobtime(int h1,int h2){

        int hh=(new GregorianCalendar()).get(Calendar.HOUR_OF_DAY);
        if (hh<h1) return false;
        if (hh>h2-1) return false;
        return true;
    }
    public static String getnow() {
        Calendar calendar = new GregorianCalendar();
        java.util.Date date = calendar.getTime();
        DateFormat localFormat = DateFormat.getDateInstance();
        DateFormat ff = new SimpleDateFormat("yyyy-MM-dd");
        return ff.format(date);
    }

    public static String getnowdatetime() {
        Calendar calendar = new GregorianCalendar();
        java.util.Date date = calendar.getTime();
        DateFormat localFormat = DateFormat.getDateInstance();
        DateFormat ff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return ff.format(date);
    }
    public static String getnowzavtra() {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        java.util.Date date = calendar.getTime();
        DateFormat localFormat = DateFormat.getDateInstance();
        DateFormat ff = new SimpleDateFormat("yyyy-MM-dd");
        return ff.format(date);

    }
    public static String getnowzavtra(int day) {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_MONTH, day);
        java.util.Date date = calendar.getTime();
        DateFormat localFormat = DateFormat.getDateInstance();
        DateFormat ff = new SimpleDateFormat("yyyy-MM-dd");
        return ff.format(date);

    }
    public static String getdate(String ss,int d) {
        Calendar calendar = new GregorianCalendar();
        String[] s2=ss.split("-");
        calendar.setTime(new java.util.Date(str2int0(s2[0])-1900,str2int0(s2[1])-1,str2int0(s2[2])));
        calendar.add(Calendar.DAY_OF_MONTH, d);
        java.util.Date date = calendar.getTime();
        int ww=calendar.get(Calendar.DAY_OF_WEEK);
        DateFormat localFormat = DateFormat.getDateInstance();
        DateFormat ff = new SimpleDateFormat("yyyy-MM-dd E="+ww);
        return ff.format(date);

    }
    public static java.util.Date str2datetime(String ss) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date date = format.parse(ss);
            System.out.println("Date : " + date);
            return date;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String datetime2str(java.util.Date date) {
        DateFormat ff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return ff.format(date);
    }

    public static Integer str2int(String str) {
        Integer rr = 0;
        try {
            rr = Integer.parseInt(str,10);
        } catch (Exception e) {
            // rr=-111111;
            rr = null;
        }
        return rr;
    }

    public static int str2int0(String str) {
        int rr = 0;
        try {
//            rr = Integer.decode(str);
            rr = Integer.parseInt(str,10);
        } catch (Exception e) {
            // rr=-111111;
            rr = 0;
        }
        return rr;
    }
    public static long str2long0(String str) {
        long rr = 0;
        try {
            rr = Long.parseLong(str,10);
        } catch (Exception e) {
            // rr=-111111;
            rr = 0;
        }
        return rr;
    }
    public static int str2int(String str,int def) {
        int rr = 0;
        try {
            rr = Integer.parseInt(str,10);
        } catch (Exception e) {
            // rr=-111111;
            rr = def;
        }
        return rr;
    }
    public static long str2long(String str,long def) {
        long rr = 0;
        try {
            rr = rr = Long.parseLong(str,10);
        } catch (Exception e) {
            // rr=-111111;
            rr = def;
        }
        return rr;
    }
    public static Double str2double(String str) {

        try {
            double rr = Double.valueOf(str);
            return rr;
        } catch (Exception e) {
            return null;
        }

    }

    public static double str2double0(String str) {

        try {
            double rr = Double.valueOf(str);
            return rr;
        } catch (Exception e) {
            return 0;
        }

    }
    public static String requestgetParameter(String par,HttpServletRequest request
    ) {
        String cs = request.getParameter(par);
        if (cs != null) {

            cs = cs.replace("'", "`");

            cs = cs.replace("\"", "`");
            //System.out.println("1=="+cs);

        }else return "";
//        System.out.println("2=="+cs);
        //      System.out.println(par+"="+cs);
        return cs;
    }
    public static String requestgetParameter(HttpServletRequest request,
                                             String par) {
        String cs = request.getParameter(par);
        if (cs != null) {

            cs = cs.replace("'", "`");

            cs = cs.replace("\"", "`");


        }else return "";
        //      System.out.println(par+"="+cs);
        return cs;
    }
    public static String requestgetParameter2(HttpServletRequest request,
                                              String par) {
        String cs = request.getParameter(par);
        return cs;
    }




    public static void addLog(int type,String descrip) {
//        execFunction("oraAddLog",new String[] {String.format("%s",type),descrip}, false);
//        System.out.println(String.format("log---type=%d---%s",type,descrip));
    }


    public static int getmd5(String arg) {
        // TODO code application logic here
//System.out.println("md5 off="+arg);
        int md5val = 0;
        MessageDigest algorithm = null;

        try {
            algorithm = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException nsae) {
            System.out.println("Cannot find digest algorithm");
            return 0;
//            System.exit(1);
        }


        byte[] defaultBytes = arg.getBytes();
        algorithm.reset();
        algorithm.update(defaultBytes);
        byte md[] = algorithm.digest();

        for (int i = 0; i < md.length; i++) {
            md5val+=java.lang.Math.abs(md[i]);
//System.out.println(String.format("%d==%d==%d",i,md[i],md5val));

        }
        md5val=(md5val % 900)+100;
//System.out.println(String.format("md5val==%d",md5val));
        return md5val;


    }

    public static String printvars(HttpServletRequest request)
    {
        Enumeration ee=request.getHeaderNames();
        while (ee.hasMoreElements())
        {
            String ss=(String) ee.nextElement();
            String s2=request.getHeader(ss);
            System.out.println("h---"+ss+"--"+s2);
        }
        ee=request.getParameterNames();
        String sdop="";
        while (ee.hasMoreElements())
        {
            String ss=(String) ee.nextElement();
            String s2=request.getParameter(ss);
            sdop+=ss+"="+s2+"&";//"&amp;";
            System.out.println("p---"+ss+"--"+s2);
        }
        if (!sdop.equals(""))sdop="?"+sdop;
        return sdop;
    }

    public static String str2file(String str,String filename) {
        try{
//        FileOutputStream ff=new FileOutputStream(filename);
            OutputStreamWriter ff=new OutputStreamWriter(new FileOutputStream(filename));
//            BufferedWriter ff=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename)));
            ff.write(str);
            ff.close();
            return "ok";
        }catch (Exception e) {System.out.println(e.toString());
            return e.toString();
        }
    }
    public static String file2str(String filename) {
        DataInputStream InStream;BufferedReader ff;
        String  ss,s1="";
        try {


            ff = new BufferedReader(new InputStreamReader(new FileInputStream(
                    filename)));
            while (true)
            {
                ss=ff.readLine();
                if (ss==null) break;
                s1+=ss+"\n";
            }
            ff.close();


        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return s1;
    }
    public static String[] getfiles(String dirname) {

        File dir = new File(dirname);

        return  dir.list();
    }
    public static boolean deletefile(String fname) {
        File dd = new File(fname);
        return dd.delete();
    }
    public static boolean renamefile(String fname,String fname2) {
        File dd = new File(fname);
        File dd2 = new File(fname2);
        return dd.renameTo(dd2);
    }


    public static String getmyip()
    {
        String ips="";
        try{
            Enumeration<NetworkInterface> e1 = (Enumeration<NetworkInterface>)NetworkInterface.getNetworkInterfaces();
            while(e1.hasMoreElements()) {
                NetworkInterface ni = e1.nextElement();
                ips+=ni.getName()+",";
//			System.out.print(ni.getName());
//			System.out.print(" : [");
                Enumeration<InetAddress> e2 = ni.getInetAddresses();
                while(e2.hasMoreElements()) {
                    InetAddress ia = e2.nextElement();
                    ips+=ia.toString()+",";
//				System.out.print(ia);
//				if( e2.hasMoreElements()) {
//					System.out.print(",");
//				}
                }
//			System.out.println("]");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ips;
    }

    static String getpar(String ss,String s1){
        int i1=ss.indexOf(s1+"=");
        if (i1<0) return "";
        int i2=ss.indexOf(" ", i1);
        return ss.substring(i1+s1.length()+1, i2);
    }
    public static String getparam(String str,String par,String def)   {
        int p=-1;
        while(true)
        {
            p=str.indexOf(par+"=",p+1);
            if (p==-1) return def;
            if (p==0) break;
            if (str.toCharArray()[p-1]=='\n') break;
            if (str.toCharArray()[p-1]=='\r') break;
        }
        p+=par.length()+1;
        int p1=str.indexOf('\n',p);
        if (p1==-1) p1=str.length();
        //System.out.println(String.format("%s=%s=",par,str.substring(p,p1)));
        return str.substring(p,p1);
    }
    static String insertselect(String name,String str,String s1){
        String ss="\n<select name='"+name+"' id='in_type'>";
        String[] s2=str.split(",");
        for (int i=0;i<s2.length;i++){
            System.out.println(s2[i]+"===="+s1);
            if (s2[i].equals(s1))
                ss+="<option selected='selected' value='"+s2[i]+"'>"+s2[i]+"</option>";
            else
                ss+="<option value='"+s2[i]+"'>"+s2[i]+"</option>";
        }
        ss+="</select>";
        return ss;
    }


    public static String sendrequest(String ss){

        String out="";
        BufferedReader in=null;
        try {
            URL url = new URL(ss);
            URLConnection yc = url.openConnection();
            in = new BufferedReader(
                    new InputStreamReader(
                            yc.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null)
                out+=inputLine+"\n";
            in.close();in=null;
        }catch(Exception e){}
        if (in!=null){
            try {
                in.close();
            }catch(Exception e){}

        }
        return out;
    }


    public static void send(String instr,String myserv,int myservport){
//        System.out.println("isssssssssisssssssssssisss");
        try{
//                    System.out.println("\n socket OK--1");
//         System.out.println(myserv);System.out.println(myservport);
            System.out.println(myserv);System.out.println(myservport);
            PrintWriter out = null;
            Socket clientSocketw = new Socket();

            clientSocketw.connect(new InetSocketAddress( myserv,myservport),1000);
//        clientSocketw.connect(new InetSocketAddress( "localhost",myservport),1000);

            //     System.out.println(instr);
//        clientSocketw = new Socket("localhost",8998);
            out=new PrintWriter(clientSocketw.getOutputStream(), true);



            out.println(instr);

            out.close();
            clientSocketw.close();
        }catch(Exception e){e.printStackTrace();}

    }
    public static String mysocket(String instr,String myserv,int myservport){
        PrintWriter out = null;
        BufferedReader in = null;
        Socket clientSocket = null;

        String fromServer="";
        try{
            clientSocket = new Socket(myserv,myservport);
            out=new PrintWriter(clientSocket.getOutputStream(), true);
//get the socket's input
            in=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            clientSocket.setSoTimeout(10000);

            String getstr=String.format("GET %s HTTP/1.0\r\nHost: %s:%d\r\n",instr,myserv,myservport);

            out.println(getstr);

            String str;
            int ii=0;

            while ((str = in.readLine()) != null) {
                //moshet taim out
                //Thread.sleep(10);????
                ii++;
                fromServer+=str+"\n";
                if (ii>1000) {System.out.println("error (time out)");break;}
            }
            in.close();in=null;
            out.close();out=null;
            clientSocket.close();clientSocket=null;
        }catch(Exception e){
            System.out.println(myserv);
            System.out.println(myservport);
            System.out.println(e.toString());
            fromServer+="\nerr="+e.toString();
        }
        try{
         if (in!=null)    in.close();
        }catch(Exception e){}
        try{
         if (out!=null)    out.close();
        }catch(Exception e){}
         try{
         if (clientSocket!=null)    clientSocket.close();
        }catch(Exception e){}
        return fromServer;
    }

    public static String getstatus(int status){
        if(status==sets.LOGIN) return "LOGIN";
        if(status==sets.BUSY) return "BUSY";
        if(status==sets.READY) return "READY";
        if(status==sets.REST) return "REST";
        if(status==sets.CON) return "CON";
        if(status==sets.TERMINATE) return "TERMINATE";
        return ""+status;
    }


    /*
   public static String sendrequest(String instr,String myserv,int myservport){
       URL url = new URL(myserv);
        URLConnection connection = url.openConnection();
        connection.setDoOutput(true);

        OutputStreamWriter out = new OutputStreamWriter(
                                         connection.getOutputStream());
        out.write("string=" + stringToReverse);
        out.close();

        BufferedReader in = new BufferedReader(
                                    new InputStreamReader(
                                    connection.getInputStream()));
        String decodedString;
        while ((decodedString = in.readLine()) != null) {
            System.out.println(decodedString);
        }
        in.close();
   }
    */
    private static String warname=null;
    public static String getwarname(HttpServlet ser){
        if (warname==null)
         warname = new File(ser.getServletContext().getRealPath("/")).getName();
        return warname;
    }
    public static String grp2grps(int grp){
        if (grp==sets.mobile) return "mobile";
        if (grp==sets.gov) return "gov";
        if (grp==sets.magtisat) return "magtisat";
        if (grp==sets.magtifix) return "magtifix";
        if (grp==sets.marketing) return "marketing";
        return ""+grp;
    }

}

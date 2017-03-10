package ge.magti.server;


//package com.tbilgazi.billing.db.common;

//import com.tbilgazi.billing.db.common.*;

public class sets {
    public static boolean firstinit =true;
    public static void init(){
        if (firstinit){
            String ss=functions.file2str("/opt/cc_crm.conf");

            firstinit=false;
        }
    }

    public static final String db_driver = "org.postgresql.Driver";



    public static final String db_stringnew = "jdbc:postgresql://192.168.1.63:5432/callcenter";
    public static final String db_stringbackup = "jdbc:postgresql://192.168.1.69:5432/callcenter";
    public static final String db_stringgwt = "jdbc:postgresql://192.168.18.43:5432/gwtadmin";


    public static final String db_user = "cc_crm";
    public static final String db_pass = "";


    public static final String db_stringnewcc = "jdbc:postgresql://192.168.27.30:5432/cc_crm";
    public static final String db_usernewcc = "cc_crm";
    public static final String db_passnewcc = "";

}

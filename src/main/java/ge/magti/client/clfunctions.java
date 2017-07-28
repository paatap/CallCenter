package ge.magti.client;

import com.google.gwt.i18n.client.DateTimeFormat;

import ge.magti.client.layout.MainArea;

import java.util.Date;

/**
 * Created by user on 3/29/17.
 */
public class clfunctions {

    public static final String httpaddr="http://192.168.27.30:8080/CallCenterAdd/";
    public static final String GobsUrl="http://gobs.magticom.ge";
    public static final String CUSTOMER_PRIVILEGE_TYPE_ID_CORPORATE = "3";
    public static String grp2grps(int grp) {
        if (grp == MainArea.mobile) return "mobile";
        if (grp == MainArea.gov) return "gov";
        if (grp == MainArea.magtisat) return "magtisat";
        if (grp == MainArea.magtifix) return "magtifix";
        if (grp == MainArea.marketing) return "marketing";
        if (grp == MainArea.info) return "info";
        if (grp == MainArea.nophone) return "nophone";
        return "" + grp;
    }

    public static int grps2grp(String sgrp) {
        if (sgrp.equals("mobile")) return MainArea.mobile;
        if (sgrp.equals("gov")) return MainArea.gov;
        if (sgrp.equals("magtisat")) return MainArea.magtisat;
        if (sgrp.equals("magtifix")) return MainArea.magtifix;
        if (sgrp.equals("marketing")) return MainArea.marketing;
        if (sgrp.equals("info")) return MainArea.info;
        if (sgrp.equals("nophone")) return MainArea.nophone;
        return 0;
    }

    public static int str2int(String str, int def) {
        int rr = 0;
        try {
            rr = Integer.parseInt(str, 10);
        } catch (Exception e) {
            // rr=-111111;
            rr = def;
        }
        return rr;
    }

    public static String getnow() {

        return DateTimeFormat.getFormat("yyyy-MM-dd").format(new Date());
    }
    public static String gettime() {

        return DateTimeFormat.getFormat("HH:mm:ss").format(new Date());
    }
    public static boolean isgobs(){
        return CallCenter.callCenterInstance.mygrp==MainArea.magtisat;
    }
    public static String mytrim(Object ss){
        if (ss==null) return "";
        return ss.toString().trim();
    }

}

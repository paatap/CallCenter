package ge.magti.client;

import ge.magti.client.layout.MainArea;

/**
 * Created by user on 3/29/17.
 */
public class clfunctions {
    public static String grp2grps(int grp){
        if (grp== MainArea.mobile) return "mobile";
        if (grp== MainArea.gov) return "gov";
        if (grp== MainArea.magtisat) return "magtisat";
        if (grp== MainArea.magtifix) return "magtifix";
        if (grp== MainArea.marketing) return "marketing";
        if (grp== MainArea.nophone) return "nophone";
        return ""+grp;
    }
    public static int grps2grp(String sgrp){
        if (sgrp.equals("mobile")) return MainArea.mobile;
        if (sgrp.equals("gov")) return MainArea.gov;
        if (sgrp.equals("magtisat")) return MainArea.magtisat;
        if (sgrp.equals("magtifix")) return MainArea.magtifix;
        if (sgrp.equals("marketing")) return MainArea.marketing;
        if (sgrp.equals("nophone")) return MainArea.nophone;
        return 0;
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
}

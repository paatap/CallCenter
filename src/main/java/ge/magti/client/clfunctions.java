package ge.magti.client;

import ge.magti.client.layout.MainArea;

/**
 * Created by user on 3/29/17.
 */
public class clfunctions {
    public String grp2grps(int grp){
        if (grp== MainArea.mobile) return "mobile";
        if (grp== MainArea.gov) return "gov";
        if (grp== MainArea.magtisat) return "magtisat";
        if (grp== MainArea.magtifix) return "magtifix";
        if (grp== MainArea.marketing) return "marketing";
        return ""+grp;
    }
}

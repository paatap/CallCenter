package ge.magti.client.layout;

import com.smartgwt.client.widgets.layout.Layout;
import ge.magti.client.CallCenter;

/**
 * Created by user on 3/28/17.
 */
public class MyVLayout {
    public MyVLayout(String _name){
        name=_name;
        if (name.equals("mainarea")) {destroy=false;}
    }
    public String name="";
    Boolean destroy=true;
    public Layout myvlayout=null;
    public void mydestroy(){
        if (name.equals("mainarea")) {myvlayout=CallCenter.callCenterInstance.maincc;}
        if (destroy) {
            if (myvlayout!=null) {
                if (name.equals("serverinfo")){((ReportAreaSinfo)myvlayout).tt.cancel();}
                myvlayout.destroy();
                myvlayout = null;
            }
        }else myvlayout.setVisible(false);
    }
    public void myinit(String ss){
        //SC.say("2222=="+name);
        if (name.equals("mainarea")) {myvlayout=CallCenter.callCenterInstance.maincc;}
        //SC.say("3333");
        if (myvlayout==null){
        //SC.say("44444");
            if (name.equals("mainarea")) myvlayout = new MainArea();
            else if (name.equals("findring")) {myvlayout = new ReportAreaFring();((ReportAreaFring)myvlayout).setprobleminfo();}
            else if (name.equals("serverinfo")) myvlayout = new ReportAreaSinfo();
            else if (name.equals("admin")) {myvlayout = new ReportAreaAdmin();}
            else if (name.equals("repframe")) {
                    myvlayout = new ReportAreaFrame();
                    ((ReportAreaFrame)myvlayout).init(ss);
            }
            myvlayout.setWidth("85%");

        }else if (myvlayout instanceof ReportAreaFrame&&name.equals("repframe")){
            ((ReportAreaFrame)myvlayout).init(ss);
        }

        CallCenter.callCenterInstance.setsouthLayout(myvlayout);// .addMember(myvlayout);
        myvlayout.setVisible(true);
    }
}

package ge.magti.client.layout;

import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;
import ge.magti.client.CallCenter;

/**
 * Created by user on 3/28/17.
 */
public class MyVLayout {
    public MyVLayout(String _name){
        name=_name;
        if (name.equals("mainarea")) {destroy=false;}
        else if (name.equals("findring")) {destroy=false;}
        else if (name.equals("oldrep")) {destroy=false;}
        else if (name.equals("repadd")) {destroy=false;}
        myvlayout0.setWidth("85%");
    }
    public String name="";
    Boolean destroy=true;
    public Layout myvlayout=null;
    public VLayout myvlayout0=new VLayout();
    public void mydestroy(){
        if (name.equals("mainarea")) {myvlayout=CallCenter.callCenterInstance.maincc;}
        if (destroy) {
            if (myvlayout!=null) {
               // if (CallCenter.callCenterInstance.debug) CallCenter.callCenterInstance.tolenta("destroy "+name);
                if (name.equals("serverinfo")){((ReportAreaSinfo)myvlayout).tt.cancel();}
                myvlayout0.setMembers();
                myvlayout.destroy();
                myvlayout = null;
            }
        }
           // if (myvlayout!=null)
            myvlayout0.setVisible(false);

    }
    public void myinit(String ss,String type0){
        //SC.say("2222=="+name);



      //  if (CallCenter.callCenterInstance.debug) CallCenter.callCenterInstance.tolenta("myinit "+name);



            if (name.equals("mainarea")) {
                myvlayout = CallCenter.callCenterInstance.maincc;
            }
            //SC.say("3333");
            if (myvlayout == null) {
                //SC.say("44444");
                if (name.equals("mainarea")) myvlayout = new MainArea();
                else if (name.equals("findring")) {
                    myvlayout = new ReportAreaFring();
                    ((ReportAreaFring) myvlayout).setprobleminfo();
                } else if (name.equals("serverinfo")) myvlayout = new ReportAreaSinfo();
                else if (name.equals("admin")) {
                    myvlayout = new ReportAreaAdmin();
                } else if (name.equals("repframe")) {
                   // if (CallCenter.callCenterInstance.debug) CallCenter.callCenterInstance.tolenta("new " + name);
                    myvlayout = new ReportAreaFrame();
                    ((ReportAreaFrame) myvlayout).init(ss);
                } else if (name.equals("oldrep")) {
           //         if (CallCenter.callCenterInstance.debug) CallCenter.callCenterInstance.tolenta("new " + name);
                    myvlayout = new ReportAreaFrame();
                    ((ReportAreaFrame) myvlayout).init(ss);
                } else if (name.equals("repadd")) {
                   // if (CallCenter.callCenterInstance.debug) CallCenter.callCenterInstance.tolenta("new " + name);
                    myvlayout = new ReportAreaFrame();
                    ((ReportAreaFrame) myvlayout).init(ss);
                }
                //myvlayout.setWidth("85%");
                //myvlayout.setWidth100();myvlayout.setBackgroundColor("#ff0000");
            } else if (myvlayout instanceof ReportAreaFrame && name.equals("repframe")) {
                    ((ReportAreaFrame) myvlayout).init(ss);
            } else if (myvlayout instanceof ReportAreaFrame && name.equals("repadd")) {
                    CallCenter.callCenterInstance.sendframemessage("repadd\tchangelay\t"+type0);
            }



        myvlayout0.setMembers(myvlayout);
        CallCenter.callCenterInstance.setsouthLayout(myvlayout);// .addMember(myvlayout);
        myvlayout0.setVisible(true);
    }


    public static MyVLayout[] getlayouts(){
        return new MyVLayout[]{
                new MyVLayout("mainarea"),
                new MyVLayout("findring"),
                new MyVLayout("serverinfo"),
                new MyVLayout("admin"),
                new MyVLayout("repframe"),
                new MyVLayout("oldrep"),
                new MyVLayout("repadd")
        };
    }
    public static String isframe(String type,String ss){
        if (type.startsWith("oldrep")) return type;
        if (ss.contains("CallCenterReports")) return "repadd";
        if (ss.startsWith("<iframe")) return "repframe";
        return type;
    }
}

package ge.magti.client.layout;

import com.smartgwt.client.util.SC;
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
    }
    public String name="";
    Boolean destroy=true;
    public Layout myvlayout=null;
    public void mydestroy(){
        if (name.equals("mainarea")) {myvlayout=CallCenter.callCenterInstance.maincc;}
        if (destroy) {
            if (myvlayout!=null) {
                if (name.equals("rep4")){((ReportArea4)myvlayout).tt.cancel();}
                myvlayout.destroy();
                myvlayout = null;
            }
        }else myvlayout.setVisible(false);
    }
    public void myinit(){
        if (name.equals("mainarea")) {myvlayout=CallCenter.callCenterInstance.maincc;}
        if (myvlayout==null){

            if (name.equals("mainarea")) myvlayout = new MainArea();
            else if (name.equals("rep1")) {myvlayout = new ReportArea();((ReportArea)myvlayout).setprobleminfo();}
            else if (name.equals("rep2")) myvlayout = new ReportArea2();
            else if (name.equals("rep3")) myvlayout = new ReportArea3();
            else if (name.equals("rep4")) myvlayout = new ReportArea4();
            myvlayout.setWidth("85%");

        }

        CallCenter.callCenterInstance.setsouthLayout(myvlayout);// .addMember(myvlayout);
        myvlayout.setVisible(true);
    }
}

package ge.magti.client.layout;

import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.layout.VLayout;
import ge.magti.client.CallCenter;

/**
 * Created by user on 3/28/17.
 */
public class ReportAreaFrame extends VLayout {
    HTMLPane htmlPane = new HTMLPane();
    public ReportAreaFrame() {

        super();
        //http://192.168.27.13/monitoring/index.php?qname=mobile
        this.setShowEdges(true);
     /*   HTMLPane htmlPane = new HTMLPane();
        htmlPane.setWidth100();
        htmlPane.setHeight100();
        String ss="<iframe src=\"http://192.168.27.30:8080/oprep/rep1?in_ccuser="+
                CallCenter.callCenterInstance.uname
                +"&in_ccpass="+
                CallCenter.callCenterInstance.pass
                +"\" name=\"Frame1\" width='100%' height='100%'>\n" +
                "\n" +
                "</iframe>";
        // htmlPane.
        // this.addMember(htmlPane);
        this.setContents(ss);*/
    }
    public void init(String ss0){
        String ss=ss0.replace("myuname",CallCenter.callCenterInstance.uname).replace("mypass",CallCenter.callCenterInstance.pass);
        if(CallCenter.callCenterInstance.number!=null&&CallCenter.callCenterInstance.number.length()>3){
            ss=ss.replace("&phone=5","&phone="+CallCenter.callCenterInstance.number);
        }
        //SC.say(ss.replace("<","."));
        this.setContents(ss);
        //this.redraw();
    }
}


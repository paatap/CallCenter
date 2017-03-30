package ge.magti.client.layout;

import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.layout.VLayout;
import ge.magti.client.CallCenter;

/**
 * Created by user on 3/28/17.
 */
public class ReportArea3 extends VLayout {
    public ReportArea3() {

        super();
        //http://192.168.27.13/monitoring/index.php?qname=mobile
        this.getShowEdges();
        HTMLPane htmlPane = new HTMLPane();
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
        this.setContents(ss);
    }
}

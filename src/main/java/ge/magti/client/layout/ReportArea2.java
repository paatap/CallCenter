package ge.magti.client.layout;

import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * Created by user on 3/27/17.
 */
public class ReportArea2 extends VLayout {
    public ReportArea2() {

        super();
        //http://192.168.27.13/monitoring/index.php?qname=mobile
        this.getShowEdges();
        HTMLPane htmlPane = new HTMLPane();
        htmlPane.setWidth100();
        htmlPane.setHeight100();
        String ss="<iframe src=\"http://192.168.27.13/monitoring/index.php?qname=mobile\" name=\"Frame1\" width='100%' height='100%'>\n" +
                "\n" +
                "</iframe>";
       // htmlPane.
       // this.addMember(htmlPane);
        this.setContents(ss);
    }
}

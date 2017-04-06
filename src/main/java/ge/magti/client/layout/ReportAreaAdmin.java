package ge.magti.client.layout;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import ge.magti.client.CallCenter;
import ge.magti.client.clfunctions;

/**
 * Created by user on 3/31/17.
 */
public class ReportAreaAdmin extends VLayout {
    SelectItem grp = new SelectItem("group");
    TextItem add = new TextItem("Set rest add");
    ButtonItem fbutton = new ButtonItem("Apply");
    public ReportAreaAdmin() {

        super();
        //SC.say("thtrhgghbyfg");
        //http://192.168.27.13/monitoring/index.php?qname=mobile
        this.setShowEdges(true);


        //grp.setAlign(Alignment.CENTER);

        grp.setValueMap("mobile", "gov", "magtisat", "magtifix", "marketing");

        fbutton.setColSpan(2);

        fbutton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                CallCenter.callCenterInstance.sendgreet("grpadd\t"+
                        clfunctions.grps2grp(grp.getValueAsString())+"\t"+
                        clfunctions.str2int(add.getValue().toString().trim(),0)
                );
            }
        });
        DynamicForm form=new DynamicForm();
        form.setFields(grp,add,fbutton);
        form.setShowEdges(true);
        this.setMembers(form);

    }
}
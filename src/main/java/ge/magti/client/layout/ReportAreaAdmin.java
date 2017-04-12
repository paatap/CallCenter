package ge.magti.client.layout;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import ge.magti.client.CallCenter;
import ge.magti.client.MyWidgets.MyButtonItem;
import ge.magti.client.MyWidgets.MyIButton;
import ge.magti.client.MyWidgets.MySelectItem;
import ge.magti.client.MyWidgets.MyTextItem;
import ge.magti.client.clfunctions;

import java.util.LinkedHashMap;

/**
 * Created by user on 3/31/17.
 */
public class ReportAreaAdmin extends VLayout {
    MySelectItem grp = new MySelectItem("group",CallCenter.style);
    MyTextItem add = new MyTextItem("Set rest add",CallCenter.style);
    MyButtonItem fbutton = new MyButtonItem("Apply",CallCenter.style);
    MySelectItem ops=new MySelectItem("",CallCenter.style);
    public ReportAreaAdmin() {

        super();
        //SC.say("thtrhgghbyfg");
        //http://192.168.27.13/monitoring/index.php?qname=mobile
        this.setShowEdges(true);


        //grp.setAlign(Alignment.CENTER);
//-----------------------------------------addtorest--------------------
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
        this.addMembers(form);


//-----------------------------------------kill opsession--------------------

        ops.setShowTitle(false);
        ops.setColSpan(2);
        ops.setWidth("100%");ops.setHeight("100%");
        DynamicForm opsform=new DynamicForm();
        opsform.setWidth(200);
        opsform.setHeight100();
        opsform.setTitleOrientation(TitleOrientation.TOP);


        opsform.setFields(ops);
        MyIButton gopsbutton = new MyIButton("",CallCenter.style);gopsbutton.setHeight100();
        gopsbutton.setIcon("users.png");
        gopsbutton.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
            public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
                CallCenter.callCenterInstance.sendgreet("get2ops\t"+CallCenter.callCenterInstance.mynumber+"\t");
            }
        });

        MyIButton killbutton = new MyIButton("kill session",CallCenter.style);killbutton.setHeight100();

        killbutton.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
            public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
                 CallCenter.callCenterInstance.sendgreet("killopsession\t"+ops.getValue().toString()+"\t");
            }
        });
        HLayout HLayout2=new HLayout();
        HLayout2.setMembers(opsform,gopsbutton,killbutton);
        HLayout2.setHeight(30);HLayout2.setShowEdges(true);
        this.addMembers(HLayout2);


    }
    LinkedHashMap<String,String> mp;
    public void getops(String result){
        String[] s2=result.split("\n");
        mp=new LinkedHashMap();
        for (int i=1;i<s2.length;i++){
            String[] s22=s2[i].split("\t");

            mp.put(s22[1],s22[0]);

        }
        ops.setValueMap(mp);
    }

}
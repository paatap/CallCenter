package ge.magti.client.layout;

import com.google.gwt.core.client.JavaScriptObject;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.VLayout;
import ge.magti.client.CallCenter;

import java.awt.*;

/**
 * Created by user on 3/28/17.
 */
public class ReportAreaFrame extends VLayout {

    //HTMLPane lay=new HTMLPane();
    VLayout lay=new VLayout();
    //IButton but1=new IButton("111");
    //IButton but2=new IButton("222");
    public ReportAreaFrame() {

        super();
        //http://192.168.27.13/monitoring/index.php?qname=mobile
        this.setShowEdges(true);


        this.setMembers(lay);
//lay.setID("Frame1");


        lay.setWidth100();lay.setHeight100();
/*
        TextItem it=new TextItem();
        DynamicForm form=new DynamicForm();
        form.setFields(it);


but1.addClickHandler(new ClickHandler() {
    @Override
    public void onClick(ClickEvent clickEvent) {

        //callfunc();
    }
});
but2.addClickHandler(new ClickHandler() {
    @Override
    public void onClick(ClickEvent clickEvent) {

    }
});
*/
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
        //if (CallCenter.callCenterInstance.debug) CallCenter.callCenterInstance.tolenta("init111 "+this.getVisibility().getValue());



        String ss=ss0.replace("myuname",CallCenter.callCenterInstance.uname).replace("mypass",CallCenter.callCenterInstance.pass);
        if(CallCenter.callCenterInstance.number!=null&&CallCenter.callCenterInstance.number.length()>3){
            ss=ss.replace("&phone=5","&phone="+CallCenter.callCenterInstance.number);
        }

        //if (CallCenter.callCenterInstance.debug) CallCenter.callCenterInstance.tolenta("init222 ");

        //SC.say(ss.replace("<","."));
        lay.setContents(ss);

        //this.redraw();
    }

}

//window.document.getElementById("Frame1").contentWindow.myalert();
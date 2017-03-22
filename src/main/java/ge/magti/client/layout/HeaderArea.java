package ge.magti.client.layout;

import com.google.gwt.dom.client.Style;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import ge.magti.client.CallCenter;

public class HeaderArea extends HLayout {

//    private static final int HEADER_AREA_HEIGHT = 20;

    public HeaderArea(String ss) {

        super();

//        this.setHeight(HEADER_AREA_HEIGHT);

//        Img logo = new Img("jcg_logo.png", 282, 60);

        Label name = new Label();
        //name.setOverflow(Overflow.HIDDEN);
        name.setContents(ss);
       // name.getElement().getStyle().setBackgroundColor("#FF0000");// .setFontSize(4, Style.Unit.EM);
        this.setAlign(Alignment.CENTER);
        name.setAlign(Alignment.CENTER);
        name.setWidth100();


        IButton logout = new IButton("logout");logout.setHeight100();
        logout.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
            public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
                SC.confirm("logout", new BooleanCallback(){
                    @Override
                    public void execute(Boolean aBoolean) {
                        if (aBoolean){
                            CallCenter.callCenterInstance.closeLayouts(true);
                        }
                    }
                });
            }
        });

        //name.setHeight(20);
//this.setTitle("<h1>"+ss+"</h1>");

        this.addMember(name);this.addMember(logout);

    }

}
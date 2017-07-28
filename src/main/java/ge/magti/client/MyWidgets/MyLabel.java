package ge.magti.client.MyWidgets;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.*;
import ge.magti.client.CallCenter;

/**
 * Created by user on 4/11/17.
 */
public class MyLabel extends Label {
    static final String addcss=" mymytext";
String ss="";

    public  MyLabel(String title,String style,String name){


        setTitle(title);



        ss=name;
        if (CallCenter.callCenterInstance.debug)
        this.addMouseDownHandler(new MouseDownHandler() {
            @Override
            public void onMouseDown(MouseDownEvent mouseDownEvent) {
                if (mouseDownEvent.isCtrlKeyDown())
                    CallCenter.callCenterInstance.tolenta(ss);
            }
        });


        if (style==null||style.equals("")) return;
        this.setBaseStyle(this.getBaseStyle()+addcss+style);

    }

}

package ge.magti.client.MyWidgets;

import com.google.gwt.dom.client.Style;
import com.smartgwt.client.widgets.IButton;

/**
 * Created by user on 4/10/17.
 */
public class MyIButton extends IButton {
    //static String addcss=" mymyfont";//mymybutton
    //String style;
    public  MyIButton(){
    }
    public  MyIButton(String _title,String style){
        this.setTitle(_title);
        if (style==null||style.equals("")) return;
        this.setBaseStyle(this.getBaseStyle()+" mymybutton"+style);
    }
    /*
    public void setmystyle(String _style){

        this.setBaseStyle(this.getBaseStyle()+" mymybutton"+_style);

        if (_style.equals("")) {
            this.setBaseStyle(this.getBaseStyle()+" mymybutton");
            //DOM.setStyleAttribute(butt1.getElement(),"backgroundColor", "#ff0000");
            // butt1.getElement().getStyle().setFontSize(11, Style.Unit.PT);
           // this.getElement().setAttribute("backgroundColor", "#ff0000");
            //butt1.getElement().getStyle().setBackgroundColor("#ff0000");

        }
        else if (_style.equals("1")) {
            this.setBaseStyle(this.getBaseStyle()+" mymybutton1");
            // butt1.getElement().getStyle().setFontSize(14, Style.Unit.PT);
          //  butt1.getElement().getStyle().setBackgroundColor("#00ff00");

        }
        else {
            this.setBaseStyle(this.getBaseStyle()+" mymybutton2");
            //  butt1.getElement().getStyle().setFontSize(22, Style.Unit.PT);
           // butt1.getElement().getStyle().setBackgroundColor("#0000ff");

        }


    }
*/
}

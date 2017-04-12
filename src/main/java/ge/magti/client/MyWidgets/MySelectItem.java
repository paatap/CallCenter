package ge.magti.client.MyWidgets;

import com.smartgwt.client.widgets.form.fields.SelectItem;

/**
 * Created by user on 4/10/17.
 */
public class MySelectItem extends SelectItem {
    static final String addcss=" mymyselectitemText";

    public  MySelectItem(String title,String style){
        setTitle(title);
        if (style==null||style.equals("")) return;

        this.setTextBoxStyle(this.getTextBoxStyle()+addcss+style);

        this.setControlStyle(this.getControlStyle()+addcss+style);
        this.setCellStyle(this.getCellStyle()+addcss+style);

this.setTitleStyle(this.getTitleStyle()+addcss+style);

 //       this.setHintStyle(this.getHintStyle()+addcss+style);
   //     this.setHoverStyle(this.getHoverStyle()+addcss+style);

        this.setPickListBaseStyle(this.getPickListBaseStyle()+addcss+style);
    }

}

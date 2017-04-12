package ge.magti.client.MyWidgets;

import com.smartgwt.client.widgets.form.fields.TextAreaItem;

/**
 * Created by user on 4/10/17.
 */
public class MyTextAreaItem extends TextAreaItem {
    static final String addcss=" mymytext";

    public  MyTextAreaItem(String title,String style){
        setTitle(title);
        if (style==null||style.equals("")) return;
        this.setTextBoxStyle(this.getTextBoxStyle()+addcss+style);

        this.setTitleStyle(this.getTitleStyle()+addcss+style);
        this.setCellStyle(this.getCellStyle()+addcss+style);
        this.setHoverStyle(this.getHoverStyle()+addcss+style);
        this.setControlStyle(this.getControlStyle()+addcss+style);
    }

}

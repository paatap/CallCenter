package ge.magti.client.MyWidgets;

import com.smartgwt.client.widgets.form.fields.ButtonItem;

/**
 * Created by user on 4/11/17.
 */
public class MyButtonItem extends ButtonItem {

    public MyButtonItem(String _title, String style) {
        this.setTitle(_title);
        if (style == null || style.equals("")) return;
        this.setBaseStyle(this.getBaseStyle() + " mymybutton" + style);
    }
}
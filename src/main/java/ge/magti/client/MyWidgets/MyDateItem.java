package ge.magti.client.MyWidgets;

import com.smartgwt.client.widgets.form.fields.DateItem;

/**
 * Created by user on 4/11/17.
 */

    public class MyDateItem extends DateItem {
        static final String addcss=" mymytext";


        public  MyDateItem(String title,String style){
            setTitle(title);
            if (style==null||style.equals("")) return;
            this.setTextBoxStyle(this.getTextBoxStyle()+addcss+style);

            this.setTitleStyle(this.getTitleStyle()+addcss+style);
            this.setCellStyle(this.getCellStyle()+addcss+style);
            this.setHoverStyle(this.getHoverStyle()+addcss+style);
            this.setControlStyle(this.getControlStyle()+addcss+style);
        }

    }
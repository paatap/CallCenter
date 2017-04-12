package ge.magti.client.MyWidgets;


import com.smartgwt.client.data.AdvancedCriteria;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.KeyPressEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyPressHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * Created by user on 4/5/17.
 */
public class MyListGrid extends ListGrid {
    static final String addcss0="mymylistgrid";
    String addcss="mymylistgrid";
    Boolean da=true;
    public MyListGrid(String style){
        addcss=addcss0+style;
        if (style==null||style.equals("")) da=false;
        if (da)
        this.setHeaderBaseStyle(this.getHeaderBaseStyle()+" "+addcss);
    }
        @Override
        protected String getBaseStyle(ListGridRecord record, int rowNum, int colNum) {
        if (da) return addcss;
        else return super.getBaseStyle(record, rowNum, colNum);
        }
}

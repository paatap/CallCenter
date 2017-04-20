package ge.magti.client.MyWidgets;

import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * Created by user on 4/19/17.
 */
public class MyListGrid2 extends ListGrid {
    static final String addcss0="mymylistgrid";
    String addcss="mymylistgrid";
    Boolean da=true;
    public MyListGrid2(String style){
        addcss=addcss0+style;
        if (style==null||style.equals("")) da=false;
        if (da)
            this.setHeaderBaseStyle(this.getHeaderBaseStyle()+" "+addcss);
    }
    @Override
    protected String getBaseStyle(ListGridRecord record, int rowNum, int colNum) {
        if (!record.getAttribute("group").startsWith("mobile")){
            if (colNum==0) {
                 return addcss+"ex";
            }
        }
        if (da) return addcss;
        else return super.getBaseStyle(record, rowNum, colNum);
    }
}

package ge.magti.client.MyWidgets;


import com.smartgwt.client.data.AdvancedCriteria;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.MouseDownEvent;
import com.smartgwt.client.widgets.events.MouseDownHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.KeyPressEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyPressHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellMouseDownEvent;
import com.smartgwt.client.widgets.grid.events.CellMouseDownHandler;
import com.smartgwt.client.widgets.grid.events.EditorExitEvent;
import com.smartgwt.client.widgets.grid.events.EditorExitHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import ge.magti.client.CallCenter;
import ge.magti.client.Dialogs.MyCopy;
import ge.magti.client.clfunctions;

/**
 * Created by user on 4/5/17.
 */
public class MyListGrid extends ListGrid {
    static final String addcss0="mymylistgrid";
    String addcss="mymylistgrid";
    Boolean da=true;
    ListGrid grid;

    public String toexcel;

    public MyListGrid(String style){
        this(style,false);
    }

    public MyListGrid(String style,boolean excel){
        grid=this;
        addcss=addcss0+style;
        if (style==null||style.equals("")) da=false;
        if (da)
            this.setHeaderBaseStyle(this.getHeaderBaseStyle()+" "+addcss);

        setCanDragSelect(true);
        //setCanSelectCells(true);




        this.setCanEdit(true);

        this.addEditorExitHandler(new EditorExitHandler() {
            @Override
            public void onEditorExit(EditorExitEvent editorExitEvent) {
                grid.cancelEditing();
            }
        });

        MenuItem item = new MenuItem("Copy");
        item.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent menuItemClickEvent) {
                //new MyCopy(grid,false).draw();
                CallCenter.newwin(MyCopy.gettable(grid,false));
            }
        });
        MenuItem itemall = new MenuItem("CopyAll");
        itemall.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent menuItemClickEvent) {
                //new MyCopy(grid,true).draw();
                CallCenter.newwin(MyCopy.gettable(grid,true));
            }
        });

        Menu menu = new Menu();
        menu.setWidth(150);
        menu.setItems(item,itemall);
        if (excel){
            MenuItem itemexc = new MenuItem("toExcel");

            itemexc.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                @Override
                public void onClick(MenuItemClickEvent menuItemClickEvent) {
                    //new MyCopy(grid,true).draw();
                    if (toexcel!=null) {
                        // CallCenterReports.CallCenterReportsInstance.tolenta("http://192.168.27.30:8080/CallCenterAdd/getinfo?type=" + toexcel);
                        //com.google.gwt.user.client.Window.Location.assign("http://127.0.0.1:8888/getinfo?type=" + toexcel);
                        //com.google.gwt.user.client.Window.Location.assign("http://192.168.27.30:8080/CallCenterAdd/getinfo?type=" + toexcel);
                        com.google.gwt.user.client.Window.Location.assign(clfunctions.httpaddr+"getinfo?type="+toexcel);
                    }
                }
            });
            menu.addItem(itemexc);
        }
        setContextMenu(menu);

    }
    @Override
    protected String getBaseStyle(ListGridRecord record, int rowNum, int colNum) {
        if (da) return addcss;
        else return super.getBaseStyle(record, rowNum, colNum);
    }
    public void setmydata(String ss){
        String[] ss2=ss.split("\n");
        setData(new ListGridRecord[] {});

        for(int j=1;j<ss2.length;j++){
            String s5=ss2[j];
            String[] s22=s5.split("\t");
            Record rr=new Record();
            for (int i=0;i<s22.length&&i<this.getFieldCount();i++) {
                rr.setAttribute(getFieldName(i), s22[i]);
            }
            this.addData(rr);
        }

    }
}

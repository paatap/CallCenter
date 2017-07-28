package ge.magti.client.MyWidgets;


import com.smartgwt.client.widgets.events.MouseDownEvent;
import com.smartgwt.client.widgets.events.MouseDownHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.events.KeyPressEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyPressHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;
import ge.magti.client.CallCenter;

/**
 * Created by user on 4/5/17.
 */
public class MyListGridEx extends VLayout {
    int ii=-1;
    String prop="";
    static String addcss0="mymylistgrid";
    String addcss="mymylistgrid";

    public final ListGrid grid = new ListGrid() {
        @Override
        protected String getBaseStyle(ListGridRecord record, int rowNum, int colNum) {
            if (text.getValue()==null)
            {if (da) return addcss;
                else return super.getBaseStyle(record, rowNum, colNum);
            }
            if (text.getValue().toString().equals(""))
            {if (da) return addcss;
                else return super.getBaseStyle(record, rowNum, colNum);
            }
            if (record.getAttribute(prop) .contains(text.getValue().toString())) {

                if (record.getAttributeAsBoolean("isSelected"))
                {if (da) return addcss;
                    else return super.getBaseStyle(record, rowNum, colNum);
                }
                if (rowNum==ii) return addcss+"excurr";
                return addcss+"ex";//"myHighGridCell";
            }  else {
                if (da) return addcss;
                    else return super.getBaseStyle(record, rowNum, colNum);
            }

        }
    };

    MyTextItem text;
    Boolean da=true;String ss="";
    public MyListGridEx(String _prop,String style,String name) {
        super();


        ss=name;
        if (CallCenter.callCenterInstance.debug)
            this.addMouseDownHandler(new MouseDownHandler() {
                @Override
                public void onMouseDown(MouseDownEvent mouseDownEvent) {
                    if (mouseDownEvent.isCtrlKeyDown())
                        CallCenter.callCenterInstance.tolenta(ss);
                }
            });


        if (style==null||style.equals("")) da=false;

        addcss=addcss0+style;
        text = new MyTextItem("",style);
        if (da)
            grid.setHeaderBaseStyle(grid.getHeaderBaseStyle()+" "+addcss);

        prop=_prop;grid.setSelectionProperty("isSelected");
        DynamicForm form=new DynamicForm();
        text.setColSpan(2);text.setShowTitle(false);form.setWidth100();text.setWidth("100%");
        form.setFields(text);

        this.setMembers(form,grid);
        text.addKeyPressHandler(new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent keyPressEvent) {


                if(keyPressEvent.getKeyName().equals("Enter")) {

                    if (text.getValue() == null || text.getValue().toString().equals("")) {ii=-1;grid.redraw();}
                    else {
                        String ss = text.getValue().toString();
                        for (int i = 0; i < grid.getRecords().length; i++) {
                            int i1 = (ii + i + 1) % grid.getRecords().length;
                            if (grid.getRecord(i1).getAttribute(prop).contains(ss)) {
                                grid.scrollToRow(i1);
                                ii = i1;
                                grid.redraw();
                                break;
                            }
                        }

                    }
                    /*AdvancedCriteria cr=new AdvancedCriteria();
                    cr.setAttribute("problem",ss);
                    int k=grid.findNextIndex(0,cr);
                    SC.say("==="+k);*/
                }else ii = -1;


            /*    Integer i=keyPressEvent.getCharacterValue();
                if (i==null){
                    SC.say(text.getValue().toString()+"="+null+"="+keyPressEvent.getKeyName());
                }else {
                    int k = i;
                    char c = (char) k;
                    SC.say(text.getValue().toString() + "=" + c + "=" + k+"="+keyPressEvent.getKeyName());
                }*/
            }
        });
    }

}

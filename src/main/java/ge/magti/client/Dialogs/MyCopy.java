package ge.magti.client.Dialogs;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.TextExportSettings;
import com.smartgwt.client.types.EscapingMode;

import com.smartgwt.client.widgets.Dialog;
import com.smartgwt.client.widgets.HTMLPane;

import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;


import java.util.ArrayList;

/**
 * Created by user on 5/5/17.
 */
public class MyCopy extends Dialog implements

        com.smartgwt.client.widgets.form.fields.events.ClickHandler {



    public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {

        this.markForDestroy();
        this.hide();
    };
    HTMLPane pan=new HTMLPane();
    public MyCopy(ListGrid grid,boolean getall) {



        //this.setAutoSize(true);
        this.setWidth("50%");
        this.setHeight("50%");
        this.setShowToolbar(false);
        this.setCanDragReposition(true);
        this.setCanDragResize(true);
        this.setTitle("Copy Cells");
        this.setShowModalMask(true);
        this.setIsModal(true);

        this.addItem(pan);

/*        MyIButton but=new MyIButton();
        but.setTitle("sel");
        but.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
           //pan.setSelectContentOnSelectAll(true);
                selectme();
            }
        });
        this.addItem(but);
*/





        pan.setContents(gettable( grid, getall));
    }

    public static String gettable(ListGrid grid,boolean getall){
        String ss="<title>Copy</title>";

        if (grid.getDataSource()!=null) {


            ArrayList<String> names=new ArrayList<String>();
            ArrayList<String> titles=new ArrayList<String>();
            for (int i=0;i<grid.getFields().length;i++){



                names.add(grid.getField(i).getName());

                titles.add(grid.getField(i).getTitle());

            }
            ss+="<table border='1'>";
            String s1="<tr>";
            for (int j=0;j<names.size();j++) s1+="<th>"+titles.get(j)+"</th>";
            ss+=s1+"</tr><tr><td>";





            Record[] records;
            if (getall) records= grid.getRecords();
            else records= grid.getSelectedRecords();
            for (int i = 0; i < records.length; i++) {
                int index = grid.getRecordIndex((ListGridRecord)records[i]);
                if (index >= 0) records[i] = grid.getEditedRecord(index);
            }
            TextExportSettings settings = new TextExportSettings();
            settings.setFieldList(names.toArray(new String[0]));
            settings.setFieldSeparator("</td><td>");
            settings.setLineSeparator("</td></tr>\n<tr><td>");
            settings.setEscapingMode(EscapingMode.DOUBLE);

            DataSource dataSource = grid.getDataSource();

            ss += dataSource.recordsAsText(records, settings).replace("\"","");
            ss+="</td></tr>";
            s1="<tr>";
            for (int j=0;j<names.size();j++) s1+="<td></td>";
            ss+=s1+"</tr>";
            ss+="</table>";
            // pan.setContents(ss);
            return ss;
        }

        ss+="<table border='1'>";
        ArrayList<String> names=new ArrayList<String>();
        ArrayList<String> titles=new ArrayList<String>();

        for (int i=0;i<grid.getFields().length;i++){
            ListGridField ff=grid.getField(i);

            if (ff!=null)
            //if (!ff.getHidden())
            {

                names.add(ff.getName());

                titles.add(ff.getTitle());

            }
        }

        ListGridRecord[] recs;
        if (getall) recs= grid.getRecords();
        else recs= grid.getSelectedRecords();

        String s1="<tr>";
        for (int j=0;j<names.size();j++) s1+="<th>"+titles.get(j)+"</th>";
        ss+=s1+"</tr>";

        for (int i=0;i<recs.length;i++){
            ListGridRecord rr=recs[i];

            s1="<tr>";
            for (int j=0;j<names.size();j++) {
                String s11=rr.getAttribute(names.get(j));
                if (s11==null) s11="";
                s1+="<td>"+s11+"</td>";
            }
            ss+=s1+"</tr>";
        }

        s1="<tr>";
        for (int j=0;j<names.size();j++) s1+="<td></td>";
        ss+=s1+"</tr>";
        ss+="</table>";
        return ss;
    }

    //private native void selectme()
        /*-{
        var el= document.getElementById('tableId');
        var body = document.body, range, sel;
        if (document.createRange && window.getSelection) {
            range = document.createRange();
            sel = window.getSelection();
            sel.removeAllRanges();
            try {
                range.selectNodeContents(el);
                sel.addRange(range);
            } catch (e) {
                range.selectNode(el);
                sel.addRange(range);
            }
        } else if (body.createTextRange) {
            range = body.createTextRange();
            range.moveToElementText(el);
            range.select();
        }

    }-*/
    //;
};

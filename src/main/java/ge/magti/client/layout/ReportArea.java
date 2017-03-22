package ge.magti.client.layout;


import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.widgets.IButton;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * Created by user on 3/21/17.
 */
public class ReportArea extends VLayout {

    public ReportArea() {

        super();
        final VLayout VLayout1001 = new VLayout();VLayout1001.setShowEdges(true);
        this.addMember(VLayout1001);




        final ListGrid listGrid = new ListGrid();
        listGrid.setWidth(900);
        listGrid.setHeight(224);
        listGrid.setAlternateRecordStyles(true);
        listGrid.setDataSource(DataSource.get("worldDS"));
        listGrid.setAutoFetchData(true);
        listGrid.setShowFilterEditor(true);
        listGrid.setCanEdit(true);
        listGrid.setEditEvent(ListGridEditEvent.CLICK);
        listGrid.setCanRemoveRecords(true);

        IButton newButton = new IButton("Add New");
        newButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                listGrid.startEditingNew();
            }
        });


        VLayout1001.addMember(listGrid);
        VLayout1001.addMember(newButton);

    }




    //final VLayout mainLayout = new VLayout();
}

package ge.magti.client.layout;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.*;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

import java.awt.*;

public class MainArea extends VLayout {

    //private Label label;


    final int y100=100;//buttons height
    final int y300=150;//buttons+ring+save/height
    final ListGrid problems = new ListGrid();
    final ListGrid infos = new ListGrid();
    final ListGrid requests = new ListGrid();
    final ListGrid problems2 = new ListGrid();
    final ListGrid infos2 = new ListGrid();
    SelectItem chaninfo = new SelectItem("lastrings");
    SelectItem shablon = new SelectItem("shablon");
    final DynamicForm shablonform = new DynamicForm();
    final DynamicForm chaninfoform = new DynamicForm();
    public MainArea() {

        super();

        final VLayout mainLayout = new VLayout();


        final VLayout VLayout10 = new VLayout(); VLayout10.setShowEdges(true);
        final VLayout VLayout20 = new VLayout(); VLayout10.setShowEdges(true);
        //mainLayout.setEdgeImage("edges/custom/sharpframe_10.png");
        //mainLayout.setDragAppearance(DragAppearance.TARGET);
        //mainLayout.setOverflow(Overflow.HIDDEN);
        //mainLayout.setCanDragResize(true);
        //mainLayout.setResizeFrom(EdgeName.L, EdgeName.R);
        //mainLayout.setLayoutMargin(10);
        //mainLayout.setMembersMargin(10);
        //mainLayout.setMinWidth(100);
        //mainLayout.setMinHeight(50);


        final HLayout HLayout1 = new HLayout();HLayout1.setShowEdges(true);

        final HLayout HLayout2 = new HLayout();HLayout2.setShowEdges(true);







        IButton readybutton = new IButton("READY");readybutton.setHeight100();
        final Label readyint = new Label("0");readyint.setHeight100();readyint.setAlign(Alignment.CENTER);
        IButton restbutton= new IButton("REST");restbutton.setHeight100();


        IButton busybutton = new IButton("BUSY");busybutton.setHeight100();
        IButton endbutton = new IButton("END");endbutton.setHeight100();
        IButton restendbutton = new IButton("RESTEND");restendbutton.setHeight100();



        HLayout1.addMember(readybutton);HLayout1.addMember(readyint);HLayout1.addMember(restbutton);
        HLayout2.addMember(busybutton);HLayout2.addMember(endbutton);HLayout2.addMember(restendbutton);

        VLayout10.addMember(HLayout1);
        VLayout10.addMember(HLayout2);
        //HLayout3.addMember(kukubutton);



        //int y=readybutton.getHeight();
        HLayout1.setHeight100();HLayout2.setHeight100();
        VLayout10.setHeight(y100);

        int x=readybutton.getWidth();
        HLayout1.setWidth(x*3);HLayout2.setWidth(x*3);
        VLayout10.setWidth(x*3);



        final HLayout HLayout100 = new HLayout();HLayout100.setShowEdges(true);
        HLayout100.setHeight(y300);
        HLayout100.setWidth100();

        //VLayout20.setWidth(x*2);
        VLayout20.setWidth100();
        VLayout20.setBackgroundColor("#FF0000");

        HLayout100.addMember(VLayout10);
        HLayout100.addMember(VLayout20);


        final VLayout VLayout1000 = new VLayout(); VLayout1000.setShowEdges(true);
        VLayout1000.setHeight100();
        VLayout1000.setWidth(5*x);
        VLayout1000.setBackgroundColor("#FF8800");
        VLayout1000.addMember(HLayout100);

        final DynamicForm textform = new DynamicForm();textform.setBackgroundColor("#00FF00");

        textform.setWidth100();
        textform.setHeight100();
        //textform.setWidth(5*x);






        TextAreaItem text = new TextAreaItem();//text.setTitle("");
        text.setShowTitle(false);
        //text.setWidth("100%");
        text.setWidth(5*x-6);
        text.setHeight("100%");

        textform.setFields(text);
//        VLayout1000.setLayoutLeftMargin(5);
//        VLayout1000.setLayoutRightMargin(5);
        VLayout1000.addMember(textform);


        final HLayout HLayout10000 = new HLayout();HLayout10000.setShowEdges(true);


        HLayout10000.addMember(VLayout1000);




        final VLayout VLayout1001 = new VLayout();VLayout1001.setShowEdges(true);

        HLayout10000.addMember(VLayout1001);


        final HLayout HLayout101 = new HLayout();HLayout101.setShowEdges(true);
        HLayout101.setHeight(30);


        //chaninfo.setWidth(HLayout101.getWidth());
        textform.setBackgroundColor("#00FF00");
        chaninfoform.setFields(chaninfo);
        chaninfoform.setWidth("100%");chaninfoform.setAlign(Alignment.CENTER);
        HLayout101.addMember(chaninfoform);
HLayout101.setBackgroundColor("#008888");


        final HLayout HLayout102 = new HLayout();HLayout102.setShowEdges(true);
        HLayout102.setHeight(30);
        final Label member1 = new Label("problem");member1.setWidth("35%");member1.setAlign(Alignment.CENTER);
        HLayout102.addMember(member1);



        textform.setBackgroundColor("#00FF00");
        shablonform.setFields(shablon);
        shablonform.setWidth("30%");shablonform.setAlign(Alignment.CENTER);
        HLayout102.addMember(shablonform);
        final Label member3 = new Label("info");member3.setWidth("35%");member3.setAlign(Alignment.CENTER);
        HLayout102.addMember(member3);
        final HLayout HLayout103 = new HLayout();HLayout103.setShowEdges(true);
        HLayout103.setHeight(100);

        HLayout103.addMember(problems2);HLayout103.addMember(infos2);


        final HLayout HLayout104 = new HLayout();HLayout104.setShowEdges(true);
        HLayout104.setHeight100();
        final VLayout VLayout11 = new VLayout();VLayout11.setShowEdges(true);
        final VLayout VLayout12 = new VLayout();VLayout12.setShowEdges(true);

        VLayout11.addMember(problems);VLayout12.addMember(infos);
        final Label requestsl = new Label("request");
        requestsl.setHeight(30);requestsl.setAlign(Alignment.CENTER);
        VLayout11.addMember(requestsl);
        VLayout11.addMember(requests);
HLayout104.addMember(VLayout11);HLayout104.addMember(VLayout12);






        VLayout1001.addMember(HLayout101);
        VLayout1001.addMember(HLayout102);
        VLayout1001.addMember(HLayout103);
        VLayout1001.addMember(HLayout104);
        //HLayout10000.addMember(problems);HLayout10000.addMember(infos);


        mainLayout.addMember(HLayout10000);

        this.addMember(mainLayout);


        setprobleminfo();

/*
        final Label member1 = new Label();
        member1.setContents("Member 1");
        member1.setOverflow(Overflow.HIDDEN);
        member1.setShowEdges(true);
        member1.setCanDragResize(true);
        member1.setResizeFrom(EdgeName.L, EdgeName.R);
        member1.setAlign(Alignment.CENTER);

        Label member2 = new Label();
        member2.setContents("Member 2");
        member2.setOverflow(Overflow.HIDDEN);
        member2.setShowEdges(true);
        member2.setCanDragResize(true);
        member2.setResizeFrom(EdgeName.L, EdgeName.R);
        member2.setAlign(Alignment.CENTER);

        mainLayout.addMember(member1);
        mainLayout.addMember(member2);
        mainLayout.setHeight(100);
        this.addMember(mainLayout);
*/

/*


        label = new Label();
        label.setContents("Main Area");
        label.setAlign(Alignment.CENTER);
        label.setOverflow(Overflow.HIDDEN);

        this.addMember(label);

        IButton swapButton;
        swapButton = new IButton("Swap titles");
        swapButton.setLeft(300);
        this.addMember(swapButton);
        */
       /* swapButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                System.out.println("qqqqq");
            }
        });
*/


    }

    void setprobleminfo(){
        problems.setShowAllRecords(true);problems.setShowHeader(false);
        //countryGrid.setSelectionType(SelectionStyle.SIMPLE);
        problems.setSelectionAppearance(SelectionAppearance.CHECKBOX);
        problems.setCanSelectAll(false);
        ListGridField problemField = new ListGridField("problem", "problem");



        problems.setFields( problemField);
        for (int i=0;i<10;i++) {
            Record rr = new Record();
            rr.setAttribute("problem", "problem"+i);
            problems.addData(rr);
        }



        infos.setShowAllRecords(true);infos.setShowHeader(false);
        //countryGrid.setSelectionType(SelectionStyle.SIMPLE);
        infos.setSelectionAppearance(SelectionAppearance.CHECKBOX);
        infos.setCanSelectAll(false);
        ListGridField infoField = new ListGridField("info", "info");



        infos.setFields( infoField);
        for (int i=0;i<10;i++) {
            Record rr = new Record();
            rr.setAttribute("info", "info"+i);
            infos.addData(rr);
        }


        requests.setShowAllRecords(true);requests.setShowHeader(false);
        //countryGrid.setSelectionType(SelectionStyle.SIMPLE);
        requests.setSelectionAppearance(SelectionAppearance.CHECKBOX);
        requests.setCanSelectAll(false);
        ListGridField requestsField = new ListGridField("request", "request");



        requests.setFields( requestsField);
        for (int i=0;i<10;i++) {
            Record rr = new Record();
            rr.setAttribute("request", "request"+i);
            requests.addData(rr);
        }


        //chaninfo
        //chaninfo.setShowTitle(false);
        chaninfo.setTitle("lastRings");
        //chaninfo.setAlign(Alignment.CENTER);
        chaninfo.setWidth("100%");

        chaninfo.setValueMap("ring1", "ring2");
        //shablon
        shablon.setShowTitle(false);
        shablon.setAlign(Alignment.CENTER);
        shablon.setValueMap("Magti", "Bani");
        shablonform.setValue("shablon", "Magti");
    }


}
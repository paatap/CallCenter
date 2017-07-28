package ge.magti.client.layout;


import com.google.gwt.i18n.client.DateTimeFormat;
import com.smartgwt.client.data.*;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.*;
import com.smartgwt.client.util.DateDisplayFormatter;
import com.smartgwt.client.util.DateUtil;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.*;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.*;
import com.smartgwt.client.widgets.form.fields.events.KeyDownEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyDownHandler;
import com.smartgwt.client.widgets.form.fields.events.KeyPressEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyPressHandler;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import ge.magti.client.CallCenter;
import ge.magti.client.MyWidgets.*;
import ge.magti.server.functions;


import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by user on 3/21/17.
 */
public class ReportAreaFring extends VLayout {
    final Window audiowindow;
    HLayout soundlayout;
     final MySelectItem soperat ;

     final   MySelectItem infos ;

    final    MySelectItem problems ;

    final MyTextItem sanumber;
    final MyTextItem finfo ;

    final MyTextItem fproblem ;
    final MyTextItem foperat ;
    final MyDateItem date1;
    final MyDateItem date2;
    final    MySelectItem hour1 ;
    final    MySelectItem hour2 ;
    final MyListGrid problems2;
    final MyListGrid infos2;
    public ReportAreaFring() {

        super();
        final VLayout VLayout1001 = new VLayout();VLayout1001.setShowEdges(true);
        this.addMember(VLayout1001);

        audiowindow = new Window();
        //audiowindow.setAutoSize(true);
        audiowindow.setTitle("audiowindow");
        audiowindow.setWidth(350);
        audiowindow.setHeight(60);
        audiowindow.setMinHeight(40);

        audiowindow.setCanDragReposition(true);
        audiowindow.setCanDragResize(true);







        soundlayout = new HLayout();
        soundlayout.setWidth100();
        soundlayout.setHeight100();//"60%");
        soundlayout.setShowEdges(true);


        audiowindow.addCloseClickHandler(new CloseClickHandler() {
            @Override
            public void onCloseClick(CloseClickEvent closeClickEvent) {
                soundlayout.setContents("");
            }
        });
        audiowindow.setVisible(false);

        HLayout filtrlayout = new HLayout();


        filtrlayout.setWidth100();
        filtrlayout.setHeight("120");


        DynamicForm form1 = new DynamicForm();
        DynamicForm form2 = new DynamicForm();
        //DynamicForm form3 = new DynamicForm();

//filtrlayout.setShowEdges(true);



        form1.setWidth("40%");form1.setHeight100();//form1.setShowEdges(true);
        form1.setTitleOrientation(TitleOrientation.LEFT);



         sanumber = new MyTextItem("",CallCenter.style);

        sanumber.setTitle("number");



        date1 = new MyDateItem("date1",CallCenter.style);
        date2 = new MyDateItem("date2",CallCenter.style);


        hour1=new MySelectItem("",CallCenter.style);hour1.setTitle("");hour1.setWidth(50);hour1.setAlign(Alignment.LEFT);hour1.setShowTitle(false);hour1.setColSpan(2);
        hour1.setValueMap("0","1","2","3","4","5","6","7","8","9","10",
                "11","12","13","14","15","16","17","18","19","20","21","22","23","24");
        hour1.setValue("0");
        hour2=new MySelectItem("",CallCenter.style);hour2.setTitle("");hour2.setWidth(50);hour2.setAlign(Alignment.LEFT);hour2.setShowTitle(false);hour2.setColSpan(2);
        hour2.setValueMap("0","1","2","3","4","5","6","7","8","9","10",
                "11","12","13","14","15","16","17","18","19","20","21","22","23","24");
        hour2.setValue("24");
        form1.setNumCols(4);

        form1.setItems(date1,hour1,date2,hour2,sanumber);

         soperat = new MySelectItem("operator",CallCenter.style);

         /////////////////////////////////////////////



        //fillCombo(null, "CorporateUsersDS", "getRoles", "role_id", "role");


        fillCombo(soperat,  "operatDS",  "getoperats",
        "number", "uname", null);

        /*
MyListGrid oMyListGrid=new MyListGrid();

        oMyListGrid.setShowRecordComponents(true);
        oMyListGrid.setShowRecordComponentsByCell(true);



        //MyListGrid.setCriteria(getcriteria());
        //MyListGrid.setWidth100();
        //MyListGrid.setHeight100();
        oMyListGrid.setAlternateRecordStyles(true);
        oMyListGrid.setDataSource(DataSource.get("operatDS"));
        oMyListGrid.setFetchOperation("getoperats");
        oMyListGrid.setAutoFetchData(true);
        oMyListGrid.setSelectionType(SelectionStyle.SINGLE);
        //MyListGrid.setShowFilterEditor(true);
        //MyListGrid.setCanEdit(true);
        //MyListGrid.setEditEvent(MyListGridEditEvent.CLICK);
        //MyListGrid.setCanRemoveRecords(true);
        oMyListGrid.setAutoFetchData(true);


//        MyListGridField countryName = new MyListGridField("oid","OID", 200);
        //      MyListGridField capital = new MyListGridField("call_start","sdfsfdsdfsdf", 200);

        //    MyListGrid.setFields(countryName, capital);



        //oid,abonent,call_start,call_end-call_start,problems,info,operat,anumber,called,callid,op_answer




        MyListGridField ooid = new MyListGridField("oid","oid");
        MyListGridField uname = new MyListGridField("uname","uname");
     //   MyListGridField onumber = new MyListGridField("number","number");

        oMyListGrid.setCellFormatter(new CellFormatter() {
            @Override
            public String format(Object value, MyListGridRecord record, int rowNum, int colNum) {
                String uname = record.getAttribute("uname");
                String oid = record.getAttribute("oid");
                String number = record.getAttribute("number");
                if (uname == null) uname = "[no description]";
if (oid == null) oid = "[no description2]";
if (number == null) number = "[no description3]";

                String retStr = oid+uname+number;
                return retStr;

            }
        });

    //    oMyListGrid.setFields(ooid,ouname);

oMyListGrid.setHeight(200);
DataSource ds=DataSource.get("operatDS1");
   //     DataSourceTextField oidField = new DataSourceTextField("oid", "oid");
     //   DataSourceTextField unameField = new DataSourceTextField("uname", "uname");
      //  ds.setFields(oidField,unameField);
soperat.setOptionDataSource(ds);
//soperat.setDisplayField("uname");soperat.setValueField("oid");

        MyListGrid oMyListGrid2=new MyListGrid();
soperat.setPickListFields(ooid,uname);
        //soperat.setDisplayField("uname");
        soperat.setPickListProperties(oMyListGrid2);

soperat.setValue("fhgtrhrth");
*/


        /////////////////////////////////////////////////////////

         infos = new MySelectItem("info",CallCenter.style);

         problems = new MySelectItem("problem",CallCenter.style);

         form2.setNumCols(4);
        form2.setWidth("60%");form2.setHeight100();//form2.setShowEdges(true);
        form2.setTitleOrientation(TitleOrientation.LEFT);

      //  form2.setItems(infos,finfo,problems,fproblem,soperat,fbutton);

         finfo = new MyTextItem("",CallCenter.style);finfo.setTitle("info pattern");

         fproblem = new MyTextItem("",CallCenter.style);fproblem.setTitle("problem pattern");
        foperat = new MyTextItem("",CallCenter.style);foperat.setTitle("operat pattern");

        MyIButton fbutton = new MyIButton("Filter",CallCenter.style,"fbutton");//fbutton.setStartRow(false);
fbutton.addClickHandler(new ClickHandler() {
    @Override
    public void onClick(ClickEvent clickEvent) {
        setprobleminfo() ;
    }
});



       // fbutton.setColSpan(4);
        fbutton.setWidth("200");
        //fbutton.setLeft(20);
        //fbutton.setAlign(Alignment.RIGHT);
        /*
fbutton.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
    @Override
    public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent clickEvent) {
        setprobleminfo();
    }
});*/

        form2.setItems(infos,finfo,problems,fproblem,soperat,foperat);

        //form3.setWidth("33%");form3.setHeight100();//form3.setShowEdges(true);
        //form3.setTitleOrientation(TitleOrientation.LEFT);

        //form3.setItems(finfo,fproblem,fbutton);

        filtrlayout.addMember(form1);
        filtrlayout.addMember(form2);
//        filtrlayout.addMember(form3);

        VLayout1001.addMember(filtrlayout);//VLayout1001.addMember(oMyListGrid);
        HLayout lay100=new HLayout();
        lay100.setAlign(Alignment.RIGHT);
        lay100.setMembers(fbutton);
VLayout1001.addMember(lay100);

        MyIButton refreshbutton = new MyIButton("Find ring",CallCenter.style,"refreshbutton");
        refreshbutton.setHeight(30);
        refreshbutton.setWidth(200);
        MyIButton clearbutton = new MyIButton("Clear fields",CallCenter.style,"clearbutton");
        clearbutton.setHeight(30);
        clearbutton.setWidth(200);

        clearbutton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                myclear();
            }
        });
        MyLabel cnt=new MyLabel("count",CallCenter.style,"cnt");
        cnt.setWidth(200);cnt.setHeight(30);cnt.setAlign(Alignment.CENTER);

        HLayout buttlayout=new HLayout();//buttlayout.setLayoutLeftMargin(10);buttlayout.setLayoutRightMargin(10);
        buttlayout.addMember(refreshbutton);buttlayout.addMember(clearbutton);buttlayout.addMember(cnt);
        VLayout1001.addMember(buttlayout);


        final MyListGrid grid = new MyListGrid(CallCenter.style) {
            @Override
            protected Canvas createRecordComponent(final ListGridRecord record, Integer colNum) {

                String fieldName = this.getFieldName(colNum);

                 if (fieldName.equals("audio")) {
                     IButton button = new IButton();
                    button.setHeight100();
                    button.setWidth100();
                    //button.setSrc("star.png");
                    button.setIcon("play.png");
                    button.setTitle("");
                    button.addClickHandler(new ClickHandler() {
                        public void onClick(ClickEvent event) {
                            String callid=record.getAttribute("callid");
                            Date dat=record.getAttributeAsDate("call_start");
                            String operat=record.getAttribute("operat");
                            String anumber=record.getAttribute("anumber");

                            String[] ss= DateTimeFormat.getFormat( "yyyy-MM-dd" ).format( dat ).split("-");
                            String su="http://192.168.27.14/storage/"+ss[0]+"/"+ss[0]+ss[1]+"/"+ss[0]+ss[1]+ss[2]+"/"+callid+".mp3";
//width="100%" height="100%"
                            String audioTag = "<audio style=\"width: 100%;\" src=\""+su+"\" autobuffer preload controls autoplay width=\"100%\">" +
                                    " Your browser does not support the <audio> element. </audio>";
                            soundlayout.setContents(audioTag);
                           //  audioTag = "<a href=\""+su+"\" download>download</a>";
                           // soundlayout2.setContents(audioTag);
                            audiowindow.setTitle(callid+" "+DateTimeFormat.getFormat( "yyyy-MM-dd HH:mm:ss" ).format( dat )+" "+operat+" "+anumber);
                            audiowindow.setVisible(true);
                        }
                    });
                    return button;
                } else {
                    return null;
                }

            }
        };
        grid.addSelectionChangedHandler(new SelectionChangedHandler() {
    @Override
    public void onSelectionChanged(SelectionEvent selectionEvent) {
        String pr=selectionEvent.getSelectedRecord().getAttribute("problems");
        String inf=selectionEvent.getSelectedRecord().getAttribute("info");
        problems2.setData(new ListGridRecord[] {});
        if (pr!=null){
            Map<String, String> problems0= ((MainArea)CallCenter.callCenterInstance.maincc).problems0;
            String[] pr2=pr.split(",");
            for (int i=0;i<pr2.length;i++) {
                String val=problems0.get(pr2[i]);
                Record rr = new Record();
                if (val==null) rr.setAttribute("problem",pr2[i]);
                else  {String[] v2=val.split("\t");
                    if (v2[1].equals("100")) rr.setAttribute("problem","(req)"+v2[0]);
                    else rr.setAttribute("problem",v2[0]);
                }
                problems2.addData(rr);
            }
        }
        infos2.setData(new ListGridRecord[] {});
        if (inf!=null){
            Map<String, String> infos0= ((MainArea)CallCenter.callCenterInstance.maincc).infos0;
            String[] inf2=inf.split(",");
            for (int i=0;i<inf2.length;i++) {
                String val=infos0.get(inf2[i]);
                Record rr = new Record();
                if (val==null) rr.setAttribute("info",inf2[i]);
                else {
                    rr.setAttribute("info",val.split("\t")[0]);
                }
                infos2.addData(rr);
            }
        }
    }
});
        myclear();
        grid.setShowRecordComponents(true);
        grid.setShowRecordComponentsByCell(true);


//grid.setMinFieldWidth(100);


        grid.setCriteria(getcriteria());
        grid.setWidth100();
        grid.setHeight100();
        grid.setAlternateRecordStyles(true);
        grid.setDataSource(DataSource.get("LogDS"));
        grid.setFetchOperation("getLogs");
        grid.setAutoFetchData(true);
        grid.setSelectionType(SelectionStyle.SINGLE);
        //grid.setShowFilterEditor(true);
        //grid.setCanEdit(true);
        //grid.setEditEvent(MyListGridEditEvent.CLICK);
        //grid.setCanRemoveRecords(true);
        grid.setAutoFetchData(true);


//        MyListGridField countryName = new MyListGridField("oid","OID", 200);
  //      MyListGridField capital = new MyListGridField("call_start","sdfsfdsdfsdf", 200);

    //    MyListGrid.setFields(countryName, capital);



        //oid,abonent,call_start,call_end-call_start,problems,info,operat,anumber,called,callid,op_answer




        ListGridField oid = new ListGridField("oid","oid");



        ListGridField call_start = new ListGridField("call_start","call_start");
        ListGridField op_answer = new ListGridField("op_answer","op_answer");
        ListGridField duration = new ListGridField("duration","duration");
        ListGridField problem = new ListGridField("problems","problems");
        ListGridField info = new ListGridField("info","info");
        ListGridField operat = new ListGridField("operat","operat");
        ListGridField uname = new ListGridField("uname","name");
        ListGridField anumber = new ListGridField("anumber","anumber");
        ListGridField called = new ListGridField("called","called");
        ListGridField callid = new ListGridField("callid","callid");



        ListGridField audioField = new ListGridField("audio", "audio");
        audioField.setWidth(65);

        call_start.setAttribute("displayFormat", "yyyy-MM-dd HH:mm:ss");
        op_answer.setAttribute("displayFormat", "yyyy-MM-dd HH:mm:ss");
        grid.setFields(oid,call_start,op_answer,duration,problem,info,operat,uname,anumber,called,callid,audioField);


/*
        IButton newButton = new IButton("Add New");
        newButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                MyListGrid.startEditingNew();
            }
        });

*/
        DSRequest dsRequest = new DSRequest();
        dsRequest.setOperationId("getLogs");
refreshbutton.addClickHandler(new ClickHandler() {
    @Override
    public void onClick(ClickEvent clickEvent) {


     //   MyListGrid.fetchData(new Criteria("anumber", sanumber.getValue().toString()));



      //  DSRequest dsRequest = new DSRequest();
      //  dsRequest.setOperationId("getLogs");
//dsRequest.setStartRow(0);
/*
        AdvancedCriteria criteria22 = new AdvancedCriteria(OperatorId.AND, new Criterion[]{
                new Criterion("call_start", OperatorId.LESS_THAN, rangeItem.getToDate()),
                new Criterion("call_start", OperatorId.GREATER_OR_EQUAL,rangeItem.getToDate()),
                new Criterion("anumber", OperatorId.CONTAINS, sanumber.getValue().toString())
//                new AdvancedCriteria(OperatorId.OR, new Criterion[]{
  //                      new Criterion("title", OperatorId.ICONTAINS, "Manager"),
    //                    new Criterion("reports", OperatorId.NOT_NULL)
      //          })
        });
*/
//SC.say("=="+date1.getValue().toString()+"=="+date2.getValue().toString()+"=="+sanumber.getValue().toString()+"==");
//SC.say(date1.getValue().toString());
//SC.say(date2.getValue().toString());




   //     Criteria criteria2 =new  Criteria();criteria2.setAttribute("anumber","12");
  //      Criteria criteria3 =new  Criteria();criteria3.setAttribute("anumber","123");
//and anumber like '%'||$criteria.anumber||'%'

 //       dsRequest.setCriteria(criteria);

//        grid.getAutoFetchData();

        grid.invalidateCache();//???????????????????????????????????
dsRequest.setStartRow(0);
      //  grid.setCriteria(criteria2);


        grid.fetchData(getcriteria(), new DSCallback() {
            @Override
            public void execute(DSResponse dsResponse, Object o, DSRequest dsRequest) {
                if(dsResponse.getStatus() == DSResponse.STATUS_VALIDATION_ERROR)
                {SC.say("err");
                    if(dsResponse.getErrors().size()!=0)
                    {
                        SC.say(dsResponse.getErrors().toString());
                    }
                }
                else
                {//cnt.setContents("count=kuku="+dsResponse.getStatus()+""+dsResponse.getTotalRows());

                 //   SC.say(""+dsResponse.getTotalRows());
                   // ResultSet resultSetProperties = new ResultSet();
                    //resultSetProperties.setFetchMode(FetchMode.PAGED);
                    //resultSetProperties.setInitialLength(dsResponse.getTotalRows());
                   Integer nn=dsResponse.getTotalRows();

                   // Integer nn=dsResponse.getData().length;
                    //resultSetProperties.setLength(nn);
                    //grid.getResultSet().setLength(nn);
                    cnt.setContents("count="+nn);
                    //grid.setData(dsResponse.getDataAsRecordList());
                    //grid.setDataProperties(resultSetProperties);
                 //   grid.setData(dsResponse.getDataAsRecordList());
                }
            }
        },dsRequest);



    }
});


        VLayout1001.addMember(grid);



        HLayout prinfolayout=new HLayout();prinfolayout.setWidth100();prinfolayout.setHeight(200);
        problems2= new MyListGrid(CallCenter.style);
        infos2= new MyListGrid(CallCenter.style);
        ListGridField problem2Field = new ListGridField("problem", "problem");
        ListGridField info2Field = new ListGridField("info", "info");
        problems2.setFields(problem2Field);
        infos2.setFields(info2Field);
        problems2.setEmptyMessage("");
        infos2.setEmptyMessage("");
        prinfolayout.setMembers(problems2,infos2);
        VLayout1001.addMember(prinfolayout);

//        VLayout1001.addMember(newButton);









        audiowindow.addMember(soundlayout);//audiowindow.addMember(soundlayout2);


        this.addChild(audiowindow);

//setprobleminfo() ;

  }


    public void setprobleminfo() {

        Map<String, String> problems0= ((MainArea)CallCenter.callCenterInstance.maincc).problems0;
        Map<String, String> infos0= ((MainArea)CallCenter.callCenterInstance.maincc).infos0;

   //     String vv="";
        //logika pokaza???????????????????????????????

//        vv="0";
        //end logika pokaza
      //  String[] vv2=vv.split(",");
       // problems.setData(new MyListGridRecord[] {});
     //   infos.setData(new MyListGridRecord[] {});


        LinkedHashMap<String,String> mpp=new LinkedHashMap();

        boolean da=fproblem.getValue()==null||fproblem.getValue().toString().equals("");
String sh="";if (!da) sh=fproblem.getValue().toString().toLowerCase();
        for(Map.Entry<String, String> entry : problems0.entrySet()){

            String key=entry.getKey();
            String[] val2=entry.getValue().split("\t");
            String val=val2[0];

                if (da)
                    mpp.put(key,val);
                else if(val.toLowerCase().contains(sh))
                    mpp.put(key,val);
            }

        problems.setValueMap(mpp);problems.setValue("");


        LinkedHashMap<String,String> mpi=new LinkedHashMap();
        da=finfo.getValue()==null||finfo.getValue().toString().equals("");
        sh="";if (!da) sh=finfo.getValue().toString().toLowerCase();
        for(Map.Entry<String, String> entry : infos0.entrySet()){
            String key=entry.getKey();
            String[] val2=entry.getValue().split("\t");
            String val=val2[0];

            if (da)
                mpi.put(key,val);
             else if(val.toLowerCase().contains(sh))
                mpi.put(key,val);
        }
        infos.setValueMap(mpi);infos.setValue("");

        HashMap<String,String> hm=null;
        soperat.setValue("");
        if(foperat.getValue()!=null&&!foperat.getValue().toString().equals(""))
        {
            hm=new HashMap<String,String>();
            hm.put("uname","%"+foperat.getValue().toString()+"%");
            hm.put("number",foperat.getValue().toString());
        }
            fillCombo(soperat,  "operatDS",  "getoperats",
                    "number", "uname", hm);
    }

    void myclear(){Date dt=new Date();
        String sdt=DateTimeFormat.getFormat( "yyyy-MM-dd" ).format( dt );
        date1.setValue(sdt);
        date2.setValue(sdt);
        soperat.setValue("");

        infos.setValue("");

        problems.setValue("");

        sanumber.setValue("");
        finfo.setValue("");

        fproblem.setValue("");
        foperat.setValue("");

    }
    Criteria getcriteria(){
        Criteria criteria =new  Criteria();
        if (sanumber.getValue()!=null&&!sanumber.getValue().toString().equals("")){
            criteria.setAttribute("anumber",sanumber.getValue().toString());
            criteria.setAttribute("called","0"+sanumber.getValue().toString());
        }

        if(date1.getValue()!=null&&!date1.getValue().toString().equals(""))
            criteria.setAttribute("date1", date1.getValue().toString()+" "+hour1.getValue().toString()+":0:0");

        if(date2.getValue()!=null&&!date2.getValue().toString().equals(""))
            criteria.setAttribute("date2", date2.getValue().toString()+" "+hour2.getValue().toString()+":0:0");

        if(problems.getValue()!=null&&!problems.getValue().toString().equals(""))
            criteria.setAttribute("problems", problems.getValue().toString());

        if(infos.getValue()!=null&&!infos.getValue().toString().equals(""))
            criteria.setAttribute("infos", infos.getValue().toString());

        if(soperat.getValue()!=null&&!soperat.getValue().toString().equals(""))
            criteria.setAttribute("operat", "SIP/"+soperat.getValue().toString());

        return criteria;
    }



    public static void fillCombo(final FormItem formItem, String sDataSource, String sFetchOperation,
                                 final String valueField, String displayField, Map<String, String> aditionalCriteria) {

        try {
            if (!(formItem instanceof ComboBoxItem) && !(formItem instanceof MySelectItem)) {
                return;
            }

            formItem.setFetchMissingValues(true);
            formItem.setFilterLocally(false);
            if (formItem instanceof ComboBoxItem) {
                ComboBoxItem cItem = (ComboBoxItem) formItem;
                cItem.setAddUnknownValues(false);
                cItem.setAutoFetchData(false);
            } else {
                MySelectItem sItem = (MySelectItem) formItem;
                sItem.setAddUnknownValues(false);
                sItem.setAutoFetchData(false);
            }

            formItem.setFilterLocally(false);
            formItem.setFetchMissingValues(true);

            DataSource comboDS = DataSource.get(sDataSource);
            formItem.setOptionOperationId(sFetchOperation);
            formItem.setOptionDataSource(comboDS);
            formItem.setValueField(valueField);
            formItem.setDisplayField(displayField);
            Criteria criteria = new Criteria();
            //addEditionalCriteria(aditionalCriteria, criteria);???????????????????
if (aditionalCriteria!=null)
            for(Map.Entry<String, String> entry : aditionalCriteria.entrySet()){
                    criteria.setAttribute(entry.getKey(), entry.getValue());
            }else{
    criteria.setAttribute("aditionalCriteria", "aditionalCriteria");
            }


            formItem.setOptionCriteria(criteria);
            if (formItem instanceof MySelectItem)
                formItem.addKeyDownHandler(new KeyDownHandler() {

                    @Override
                    public void onKeyDown(KeyDownEvent event) {
                        String key = event.getKeyName();
                        if ((key.equals("Escape") || key.equals("Delete"))) {
                            formItem.clearValue();
                        }

                    }
                });
            formItem.addKeyPressHandler(new KeyPressHandler() {
                @Override
                public void onKeyPress(KeyPressEvent event) {

                    Criteria criteria = formItem.getOptionCriteria();
                    if (criteria != null) {
                        Object oldAttr = criteria.getAttribute(valueField);
                        if (oldAttr != null) {
                            criteria.setAttribute(valueField, (String) null);
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            SC.say(e.toString());
        }
    }

}

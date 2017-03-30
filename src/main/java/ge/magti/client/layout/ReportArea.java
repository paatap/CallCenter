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
import ge.magti.server.functions;


import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by user on 3/21/17.
 */
public class ReportArea extends VLayout {
    final Window audiowindow;
     final       SelectItem soperat ;

     final   SelectItem infos ;

    final    SelectItem problems ;

    final TextItem sanumber;
    final TextItem finfo ;

    final TextItem fproblem ;
    final TextItem foperat ;
    final DateItem date1;
    final DateItem date2;
    final    SelectItem hour1 ;
    final    SelectItem hour2 ;
    final ListGrid problems2;
    final ListGrid infos2;
    public ReportArea() {

        super();
        final VLayout VLayout1001 = new VLayout();VLayout1001.setShowEdges(true);
        this.addMember(VLayout1001);

        audiowindow = new Window();
        //audiowindow.setAutoSize(true);
        audiowindow.setTitle("audiowindow");
        audiowindow.setWidth(350);
        audiowindow.setHeight(100);
        audiowindow.setMinHeight(40);

        audiowindow.setCanDragReposition(true);
        audiowindow.setCanDragResize(true);

        audiowindow.setVisible(false);





        HLayout soundlayout = new HLayout();
        soundlayout.setWidth100();
        soundlayout.setHeight("60%");
        soundlayout.setShowEdges(true);
        HLayout soundlayout2 = new HLayout();
        soundlayout2.setWidth100();
        soundlayout2.setHeight("40%");
        soundlayout2.setShowEdges(true);




        HLayout filtrlayout = new HLayout();


        filtrlayout.setWidth100();
        filtrlayout.setHeight("120");


        DynamicForm form1 = new DynamicForm();
        DynamicForm form2 = new DynamicForm();
        //DynamicForm form3 = new DynamicForm();

//filtrlayout.setShowEdges(true);



        form1.setWidth("40%");form1.setHeight100();//form1.setShowEdges(true);
        form1.setTitleOrientation(TitleOrientation.LEFT);



         sanumber = new TextItem();

        sanumber.setTitle("number");



        date1 = new DateItem("date1");
        date2 = new DateItem("date2");


        hour1=new SelectItem();hour1.setTitle("");hour1.setWidth(40);hour1.setAlign(Alignment.LEFT);hour1.setShowTitle(false);hour1.setColSpan(2);
        hour1.setValueMap("0","1","2","3","4","5","6","7","8","9","10",
                "11","12","13","14","15","16","17","18","19","20","21","22","23","24");
        hour1.setValue("0");
        hour2=new SelectItem();hour2.setTitle("");hour2.setWidth(40);hour2.setAlign(Alignment.LEFT);hour2.setShowTitle(false);hour2.setColSpan(2);
        hour2.setValueMap("0","1","2","3","4","5","6","7","8","9","10",
                "11","12","13","14","15","16","17","18","19","20","21","22","23","24");
        hour2.setValue("24");
        form1.setNumCols(4);

        form1.setItems(date1,hour1,date2,hour2,sanumber);

         soperat = new SelectItem("operator");

         /////////////////////////////////////////////



        //fillCombo(null, "CorporateUsersDS", "getRoles", "role_id", "role");


        fillCombo(soperat,  "operatDS",  "getoperats",
        "number", "uname", null);

        /*
ListGrid olistGrid=new ListGrid();

        olistGrid.setShowRecordComponents(true);
        olistGrid.setShowRecordComponentsByCell(true);



        //listGrid.setCriteria(getcriteria());
        //listGrid.setWidth100();
        //listGrid.setHeight100();
        olistGrid.setAlternateRecordStyles(true);
        olistGrid.setDataSource(DataSource.get("operatDS"));
        olistGrid.setFetchOperation("getoperats");
        olistGrid.setAutoFetchData(true);
        olistGrid.setSelectionType(SelectionStyle.SINGLE);
        //listGrid.setShowFilterEditor(true);
        //listGrid.setCanEdit(true);
        //listGrid.setEditEvent(ListGridEditEvent.CLICK);
        //listGrid.setCanRemoveRecords(true);
        olistGrid.setAutoFetchData(true);


//        ListGridField countryName = new ListGridField("oid","OID", 200);
        //      ListGridField capital = new ListGridField("call_start","sdfsfdsdfsdf", 200);

        //    listGrid.setFields(countryName, capital);



        //oid,abonent,call_start,call_end-call_start,problems,info,operat,anumber,called,callid,op_answer




        ListGridField ooid = new ListGridField("oid","oid");
        ListGridField uname = new ListGridField("uname","uname");
     //   ListGridField onumber = new ListGridField("number","number");

        olistGrid.setCellFormatter(new CellFormatter() {
            @Override
            public String format(Object value, ListGridRecord record, int rowNum, int colNum) {
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

    //    olistGrid.setFields(ooid,ouname);

olistGrid.setHeight(200);
DataSource ds=DataSource.get("operatDS1");
   //     DataSourceTextField oidField = new DataSourceTextField("oid", "oid");
     //   DataSourceTextField unameField = new DataSourceTextField("uname", "uname");
      //  ds.setFields(oidField,unameField);
soperat.setOptionDataSource(ds);
//soperat.setDisplayField("uname");soperat.setValueField("oid");

        ListGrid olistGrid2=new ListGrid();
soperat.setPickListFields(ooid,uname);
        //soperat.setDisplayField("uname");
        soperat.setPickListProperties(olistGrid2);

soperat.setValue("fhgtrhrth");
*/


        /////////////////////////////////////////////////////////

         infos = new SelectItem("info");

         problems = new SelectItem("problem");

         form2.setNumCols(4);
        form2.setWidth("60%");form2.setHeight100();//form2.setShowEdges(true);
        form2.setTitleOrientation(TitleOrientation.LEFT);

      //  form2.setItems(infos,finfo,problems,fproblem,soperat,fbutton);

         finfo = new TextItem();finfo.setTitle("info pattern");

         fproblem = new TextItem();fproblem.setTitle("problem pattern");
        foperat = new TextItem();foperat.setTitle("operat pattern");

        ButtonItem fbutton = new ButtonItem("Filter");fbutton.setStartRow(false);

        fbutton.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
            @Override
            public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent clickEvent) {
                setprobleminfo() ;
            }
        });

        fbutton.setColSpan(4);
        fbutton.setWidth("200");
        //fbutton.setLeft(20);
        fbutton.setAlign(Alignment.RIGHT);
fbutton.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
    @Override
    public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent clickEvent) {
        setprobleminfo();
    }
});

        form2.setItems(infos,finfo,problems,fproblem,soperat,foperat,fbutton);

        //form3.setWidth("33%");form3.setHeight100();//form3.setShowEdges(true);
        //form3.setTitleOrientation(TitleOrientation.LEFT);

        //form3.setItems(finfo,fproblem,fbutton);

        filtrlayout.addMember(form1);
        filtrlayout.addMember(form2);
//        filtrlayout.addMember(form3);

        VLayout1001.addMember(filtrlayout);//VLayout1001.addMember(olistGrid);


        IButton refreshbutton = new IButton("Find ring");
        refreshbutton.setHeight(30);
        refreshbutton.setWidth(200);
        IButton clearbutton = new IButton("Clear fields");
        clearbutton.setHeight(30);
        clearbutton.setWidth(200);

        clearbutton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                myclear();
            }
        });

        HLayout buttlayout=new HLayout();//buttlayout.setLayoutLeftMargin(10);buttlayout.setLayoutRightMargin(10);
        buttlayout.addMember(refreshbutton);buttlayout.addMember(clearbutton);
        VLayout1001.addMember(buttlayout);


        final ListGrid listGrid = new ListGrid() {
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
                            String audioTag = "<audio style=\"width: 100%;\" src=\""+su+"\" autobuffer preload controls autoplay width=\"100%\"> Your browser does not support the <audio> element. </audio>";
                            soundlayout.setContents(audioTag);
                             audioTag = "<a href=\""+su+"\" download>download</a>";
                            soundlayout2.setContents(audioTag);
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
listGrid.addSelectionChangedHandler(new SelectionChangedHandler() {
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
        listGrid.setShowRecordComponents(true);
        listGrid.setShowRecordComponentsByCell(true);



        listGrid.setCriteria(getcriteria());
        listGrid.setWidth100();
        listGrid.setHeight100();
        listGrid.setAlternateRecordStyles(true);
        listGrid.setDataSource(DataSource.get("LogDS"));
        listGrid.setFetchOperation("getLogs");
        listGrid.setAutoFetchData(true);
        listGrid.setSelectionType(SelectionStyle.SINGLE);
        //listGrid.setShowFilterEditor(true);
        //listGrid.setCanEdit(true);
        //listGrid.setEditEvent(ListGridEditEvent.CLICK);
        //listGrid.setCanRemoveRecords(true);
        listGrid.setAutoFetchData(true);


//        ListGridField countryName = new ListGridField("oid","OID", 200);
  //      ListGridField capital = new ListGridField("call_start","sdfsfdsdfsdf", 200);

    //    listGrid.setFields(countryName, capital);



        //oid,abonent,call_start,call_end-call_start,problems,info,operat,anumber,called,callid,op_answer




        ListGridField oid = new ListGridField("oid","oid");



        ListGridField call_start = new ListGridField("call_start","call_start");
        ListGridField op_answer = new ListGridField("op_answer","op_answer");
        ListGridField duration = new ListGridField("duration","duration");
        ListGridField problem = new ListGridField("problems","problems");
        ListGridField info = new ListGridField("info","info");
        ListGridField operat = new ListGridField("operat","operat");
        ListGridField anumber = new ListGridField("anumber","anumber");
        ListGridField called = new ListGridField("called","called");
        ListGridField callid = new ListGridField("callid","callid");



        ListGridField audioField = new ListGridField("audio", "audio");
        audioField.setWidth(65);

        call_start.setAttribute("displayFormat", "yyyy-MM-dd HH:mm:ss");
        op_answer.setAttribute("displayFormat", "yyyy-MM-dd HH:mm:ss");
           listGrid.setFields(oid,call_start,op_answer,duration,problem,info,operat,anumber,called,callid,audioField);


/*
        IButton newButton = new IButton("Add New");
        newButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                listGrid.startEditingNew();
            }
        });

*/

refreshbutton.addClickHandler(new ClickHandler() {
    @Override
    public void onClick(ClickEvent clickEvent) {


     //   listGrid.fetchData(new Criteria("anumber", sanumber.getValue().toString()));



        DSRequest dsRequest = new DSRequest();
        dsRequest.setOperationId("getLogs");

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
        listGrid.invalidateCache();//???????????????????????????????????

      //  listGrid.setCriteria(criteria2);


        listGrid.fetchData(getcriteria(), new DSCallback() {
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
                {
                 //   SC.say(""+dsResponse.getTotalRows());
                 //   ResultSet resultSetProperties = new ResultSet();
                 //   resultSetProperties.setLength(dsResponse.getTotalRows());

//                    listGrid.setDataProperties(resultSetProperties);
                    listGrid.setData(dsResponse.getDataAsRecordList());
                }
            }
        },dsRequest);



    }
});


        VLayout1001.addMember(listGrid);



        HLayout prinfolayout=new HLayout();prinfolayout.setWidth100();prinfolayout.setHeight(200);
        problems2= new ListGrid();
        infos2= new ListGrid();
        ListGridField problem2Field = new ListGridField("problem", "problem");
        ListGridField info2Field = new ListGridField("info", "info");
        problems2.setFields(problem2Field);
        infos2.setFields(info2Field);
        problems2.setEmptyMessage("");
        infos2.setEmptyMessage("");
        prinfolayout.setMembers(problems2,infos2);
        VLayout1001.addMember(prinfolayout);

//        VLayout1001.addMember(newButton);









        audiowindow.addMember(soundlayout);audiowindow.addMember(soundlayout2);


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
       // problems.setData(new ListGridRecord[] {});
     //   infos.setData(new ListGridRecord[] {});


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

        problems.setValueMap(mpp);


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
        infos.setValueMap(mpi);

        HashMap<String,String> hm=null;

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
            if (!(formItem instanceof ComboBoxItem) && !(formItem instanceof SelectItem)) {
                return;
            }

            formItem.setFetchMissingValues(true);
            formItem.setFilterLocally(false);
            if (formItem instanceof ComboBoxItem) {
                ComboBoxItem cItem = (ComboBoxItem) formItem;
                cItem.setAddUnknownValues(false);
                cItem.setAutoFetchData(false);
            } else {
                SelectItem sItem = (SelectItem) formItem;
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
            if (formItem instanceof SelectItem)
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

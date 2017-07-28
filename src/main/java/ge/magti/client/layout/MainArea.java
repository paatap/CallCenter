package ge.magti.client.layout;




import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.*;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.*;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.ResizedEvent;
import com.smartgwt.client.widgets.events.ResizedHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import ge.magti.client.CallCenter;
import ge.magti.client.MyWidgets.*;
import ge.magti.client.clfunctions;


import java.util.*;

public class MainArea extends VLayout {

    //private Label label;
//Operator States
    public static final int   SendRingFile  =200;
    public static final int   LOGIN 		=201;
    public static final int   BUSY		=202;
    public static final int   READY		=203;
    public static final int   RINGING	=204;
    public static final int   REST		=205;
    public static final int   ANSWER	=206;
    public static final int   CON		=207;
    public static final int   TERMINATE	=208;
    public static final int   END		=209;
    public static final int   PlayWtOpFile  =210;
    public static final int   PlayMenu      =211;


    public static final int   mobile        =0;
    public static final int   gov           =2;
    public static final int   magtisat      =13;
    public static final int   magtifix      =3;
    public static final int   marketing     =5;
    public static final int   info          =7;
    public static final int   nophone     =-1;
    int state=0;



    final int y100=50;//buttons height
    final int y300=150;//buttons+ring+save/height
    final MyListGridEx problems = new MyListGridEx("problem",CallCenter.style,"problems");
    final MyListGridEx infos = new MyListGridEx("info",CallCenter.style,"infos");
    final MyListGridEx requests = new MyListGridEx("request",CallCenter.style,"requests");
    final MyListGrid problems2 = new MyListGrid(CallCenter.style);
    final MyListGrid infos2 = new MyListGrid(CallCenter.style);
    MySelectItem chaninfo = new MySelectItem("lastrings",CallCenter.style);
    MySelectItem shablon = new MySelectItem("shablon",CallCenter.style);
    final DynamicForm shablonform = new DynamicForm();
    final DynamicForm chaninfoform = new DynamicForm();
    public RichTextEditor txt = new RichTextEditor();
    MyTextItem number = new MyTextItem("",CallCenter.style);
    MyTextAreaItem ninfo = new MyTextAreaItem("",CallCenter.style);
    String callid="";

    //String problemsinfo="";
    HashMap<String,String> problems0;
    HashMap<String,String> infos0;
    HashMap<String,String> gobstemplates0;
    HashMap<String,String> gobscorptemplates0;
    LinkedHashMap<String,String> mp=new LinkedHashMap();

    String numberdescrip="";

    MyIButton readybutton;
    MyIButton restbutton;
    MyIButton busybutton;
    MyIButton endbutton;
    MyIButton restendbutton;
    MyIButton infobutton;
    MyIButton savebutton;
    MyIButton clirbutton;
    MyIButton garbagebutton;
    Window callwindow = null;
    MyListGrid calls;
    VLayout callpanel;HLayout phonepanel;
    final MyLabel readyint;
    MyListGridEx cbTemplate=new MyListGridEx("template",CallCenter.style,"cbTemplate");
    MyTextAreaItem txtComment=new MyTextAreaItem("",CallCenter.style);
    final MyListGrid lvServices=new MyListGrid(CallCenter.style);
    Record lvServicesrec;
    MyIButton btnSvcSelect=new MyIButton("set service",CallCenter.style,"btnSvcSelect");
    MyLabel lblSelectedSvc=new MyLabel("",CallCenter.style,"lblSelectedSvc");
    CheckboxItem chbTransfer=new CheckboxItem();
    CheckboxItem chbPotentialChurn=new CheckboxItem();
    CheckboxItem chbDT_Is220=new CheckboxItem();
    CheckboxItem chbDT_IsKey=new CheckboxItem();
    MyTextItem txtCallerName = new MyTextItem("name",CallCenter.style);
    MyTextAreaItem txtCallerComment = new MyTextAreaItem("comment",CallCenter.style);
    MyLabel labhist=new MyLabel("",CallCenter.style,"labhist");
    MyLabel labservices=new MyLabel("",CallCenter.style,"labservices");
String DeviceTroubleId;
    CheckboxItem chbAllTemplates=new CheckboxItem();
    MyTextItem txtFltPhone = new MyTextItem("phone",CallCenter.style);
    MyTextItem txtFltrPersonalNumber = new MyTextItem("personal N",CallCenter.style);
    MyTextItem txtFltrFirstName = new MyTextItem("first name",CallCenter.style);
    MyTextItem txtFltrLastName = new MyTextItem("last name",CallCenter.style);
    final MyListGrid lvHistory=new MyListGrid(CallCenter.style);

    String gcallANumber,gcallBNumber,gcallCallerComment,gcallCallerName,gcallCallId,gcallCallStatus,gcallCreationDate,gcallId;
    boolean gcallisCorporateCustomer;
    final VLayout VLayout103 = new VLayout();
    final VLayout VLayout104 = new VLayout();
    VLayout VLayout103104=new VLayout();
    public MainArea() {

        super();


        //final VLayout mainLayout = new VLayout();


        final VLayout VLayout10 = new VLayout();
        VLayout10.setShowEdges(true);
        callpanel = new VLayout();
        VLayout10.setShowEdges(true);
        //mainLayout.setEdgeImage("edges/custom/sharpframe_10.png");
        //mainLayout.setDragAppearance(DragAppearance.TARGET);
        //mainLayout.setOverflow(Overflow.HIDDEN);
        //mainLayout.setCanDragResize(true);
        //mainLayout.setResizeFrom(EdgeName.L, EdgeName.R);
        //mainLayout.setLayoutMargin(10);
        //mainLayout.setMembersMargin(10);
        //mainLayout.setMinWidth(100);
        //mainLayout.setMinHeight(50);


        final HLayout HLayout1 = new HLayout();
        HLayout1.setShowEdges(true);

        final HLayout HLayout2 = new HLayout();
        HLayout2.setShowEdges(true);
        final HLayout HLayout3 = new HLayout();
        HLayout3.setShowEdges(true);


        //com.smartgwt.client.widgets.events.
        //com.smartgwt.client.widgets.events.

        readybutton = new MyIButton("READY",CallCenter.style,"readybutton");
        readybutton.setHeight100();
        readybutton.setWidth("33%");
        readybutton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                myonclick("ready", readybutton);
            }
        });
        readyint = new MyLabel("0",CallCenter.style,"readyint");
        readyint.setHeight100();
        readyint.setAlign(Alignment.CENTER);
        readyint.setWidth("33%");
        restbutton = new MyIButton("REST",CallCenter.style,"restbutton");
        restbutton.setHeight100();
        restbutton.setWidth("33%");
        restbutton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                myonclick("rest", restbutton);
            }
        });

        busybutton = new MyIButton("BUSY",CallCenter.style,"busybutton");
        busybutton.setHeight100();
        busybutton.setWidth("33%");
        busybutton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                myonclick("busy", busybutton);
            }
        });
        endbutton = new MyIButton("END",CallCenter.style,"endbutton");
        endbutton.setHeight100();
        endbutton.setWidth("33%");
        endbutton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                myonclick("end", endbutton);
            }
        });
        restendbutton = new MyIButton("REST END",CallCenter.style,"restendbutton");
        restendbutton.setHeight100();
        restendbutton.setWidth("33%");
        restendbutton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                myonclick("restend", restendbutton);
            }
        });
        savebutton = new MyIButton("SAVE",CallCenter.style,"savebutton");
        savebutton.setHeight(y100 / 2);
        savebutton.setWidth("33%");
        savebutton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                String ss = txt.getValue().toString();
                if (ss.contains("it is my test")) CallCenter.callCenterInstance.debug = true;
                else if (ss.contains("it is not my test")) CallCenter.callCenterInstance.debug = false;

                CallCenter.callCenterInstance.sendgreet("savetxt\t" + CallCenter.callCenterInstance.uname
                        + "\n" + ss);


            }
        });
        clirbutton = new MyIButton("CLIR",CallCenter.style,"clirbutton");
        clirbutton.setHeight(y100 / 2);
        clirbutton.setWidth("33%");
        clirbutton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                com.google.gwt.user.client.Window.open(
                        "http://cc2.magti.ge:8080/oprep/rep1?job=clir",
                        "_blank",
                        ""
                );
            }
        });

        final Label tmp1 = new Label("");
        tmp1.setWidth("33%");

        HLayout1.addMember(readybutton);
        HLayout1.addMember(readyint);
        HLayout1.addMember(restbutton);
        HLayout2.addMember(busybutton);
        HLayout2.addMember(endbutton);
        HLayout2.addMember(restendbutton);

        //HLayout3.setAlign(VerticalAlignment.BOTTOM);
        HLayout3.setDefaultLayoutAlign(VerticalAlignment.CENTER);
        HLayout3.setAlign(Alignment.LEFT);

        HLayout3.addMember(savebutton);
        HLayout3.addMember(clirbutton);
        HLayout3.addMember(tmp1);
        HLayout1.setHeight(y100);
        HLayout2.setHeight(y100);
        //HLayout3.setHeight(y100);
        HLayout3.setHeight100();



        if (CallCenter.callCenterInstance.grps.contains("p")) {

            phonepanel = new HLayout();phonepanel.setShowEdges(true);phonepanel.setHeight(y100);
            MyIButton pbutt = new MyIButton("CALL", CallCenter.style,"pbutt");
            DynamicForm pform = new DynamicForm();
            MyTextAreaItem ptxt = new MyTextAreaItem("", CallCenter.style);ptxt.setShowTitle(false);
            pbutt.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent clickEvent) {
                    if (ptxt.getValue()!=null) {
                        String ss = ptxt.getValue().toString().trim();
                        String[] s2=ss.split("\n");

                        if (s2.length==1&&ss.length()>2)
                                CallCenter.callCenterInstance.sendgreet("myphone\ndial\n" + ss);
                        else{
if (callwindow==null){
    callwindow = new Window();
    callwindow.setTitle("Batch Call");
    callwindow.setWidth(200);
    callwindow.setHeight(200);
    callwindow.setCanDragResize(true);
    calls = new MyListGrid(CallCenter.style);calls.setWidth100();calls.setHeight100();
    MyIButton bcall=new MyIButton();bcall.setWidth100();bcall.setTitle("Call");
    callwindow.setMembers(calls,bcall);
    callwindow.centerInPage();
    //addChild(callwindow);
    bcall.addClickHandler(new ClickHandler() {
        @Override
        public void onClick(ClickEvent clickEvent) {
            Record rr=calls.getSelectedRecord();
            if (rr!=null){
                String bss=rr.getAttribute("number");
                if (bss!=null){
                    CallCenter.callCenterInstance.sendgreet("myphone\ndial\n" + bss.trim());
                }
            }
        }
    });
}
                            calls.setCanSelectAll(false);
                            ListGridField problem2Field = new ListGridField("number", "number");
                            calls.setFields( problem2Field);calls.setShowHeader(false);

                            calls.setData(new ListGridRecord[] {});

                            for (int i=0;i<s2.length;i++){
                                Record rr=new Record();
                                rr.setAttribute("number",s2[i]);
                                calls.addData(rr);
                            }
callwindow.show();
                        }
                    }
                }
            });
            ptxt.setColSpan(2);ptxt.setWidth("100%");ptxt.setHeight("100%");
            pform.setFields(ptxt);
            pbutt.setValign(VerticalAlignment.CENTER);
            pbutt.setWidth("33%");pbutt.setHeight100();
            pform.setWidth("66%");
            phonepanel.setMembers(pbutt, pform);


            VLayout10.addMember(phonepanel);
        }




        VLayout10.addMember(HLayout1);
        VLayout10.addMember(HLayout2);
        VLayout10.addMember(HLayout3);
        //HLayout3.addMember(kukubutton);


        //int y=readybutton.getHeight();
        HLayout1.setHeight100();
        HLayout2.setHeight100();
        ;
        HLayout3.setHeight100();
        VLayout10.setHeight100();

        //int x = 100;
        //HLayout1.setWidth(x*3);HLayout2.setWidth(x*3);HLayout3.setWidth(x*3);
        HLayout1.setWidth100();
        HLayout2.setWidth100();
        HLayout3.setWidth100();
        VLayout10.setWidth100();
        //VLayout10.setEdgeBackgroundColor("#ff0000");


        final VLayout HLayout100 = new VLayout();
        HLayout100.setShowEdges(true);

        HLayout100.setWidth100();
        if (CallCenter.callCenterInstance.grps.contains("p"))  HLayout100.setHeight(y300*2+y100);
        else  HLayout100.setHeight(y300*2);
        //VLayout20.setWidth(x*2);

        callpanel.setBackgroundColor("#FF0000");

        final DynamicForm call = new DynamicForm();
        //call.setWidth(400);
        //call.setColWidths(120, "*");
        call.setWidth("100%");
        call.setAlign(Alignment.CENTER);
        number.setColSpan(2);
        number.setTitle("Number");
        number.setWidth("100%");
        number.setAlign(Alignment.CENTER);
        infobutton = new MyIButton("CCARE",CallCenter.style,"infobutton");
        infobutton.setWidth100();
        infobutton.setAlign(Alignment.CENTER);
        infobutton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                //com.google.gwt.user.client.Window.Location.assign("http://www.google.com");
                com.google.gwt.user.client.Window.open(
                        "http://ccare.magti.ge/CCareFE/ccarefe/initAppServlet?userName=" +
                                CallCenter.callCenterInstance.uname
                                + "&phone=" + number.getValue()
                                + "&password=" +
                                CallCenter.callCenterInstance.pass, "_blank",
                        ""
                );

            }
        });
        garbagebutton = new MyIButton("GARBAGE",CallCenter.style,"garbagebutton");
        garbagebutton.setWidth100();
        garbagebutton.setAlign(Alignment.CENTER);
        garbagebutton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {

                //com.google.gwt.user.client.Window.Location.assign("http://www.google.com");
                com.google.gwt.user.client.Window.open(
                        "http://sdesk.magticom.ge:8080/sm/index.do"
                        , "_blank", ""
                );
            }
        });

        ninfo.setColSpan(2);
        ninfo.setShowTitle(false);
        ninfo.setWidth("100%");
        ninfo.setAlign(Alignment.CENTER);
        ninfo.setHeight("100%");


        //        prgarb=Runtime.getRuntime().exec(brouser+" http://sdesk.magticom.ge:8080/sm/index.do");
        //callpanel.setWidth100();
        VLayout callpanel2 = new VLayout();
        callpanel2.setWidth100();//x * 2);
        callpanel2.setHeight100();
        callpanel.setWidth100();
        callpanel.setHeight100();
        callpanel.addMember(call);
        HLayout hbutts=new HLayout();
        hbutts.setMembers(infobutton,garbagebutton);
        callpanel.addMember(hbutts);


        call.setFields(number, ninfo);
        call.setHeight(80);//call.setBackgroundColor("#00FF00");
        call.setTitleOrientation(TitleOrientation.TOP);


        callpanel.setLayoutMargin(10);

        final VLayout VLayout1000 = new VLayout();
        VLayout1000.setShowEdges(true);
        VLayout1000.setHeight100();
        VLayout1000.setWidth(300);//5 * x);//VLayout1000.setEdgeBackgroundColor("#ff0088");
        //VLayout1000.setBackgroundColor("#FF8800");
        //VLayout1000.addMember(HLayout100);

        //  final DynamicForm textform = new DynamicForm();//textform.setBackgroundColor("#00FF00");

        //      textform.setWidth100();
        //      textform.setHeight100();
        //textform.setWidth(5*x);


        //text.setTitle("");
        //     txt.setShowTitle(false);
        //text.setWidth("100%");
        //txt.setWidth(5*x-6);

        txt.setOverflow(Overflow.HIDDEN);
        // txt.setCanDragResize(true);

        txt.setWidth100();
        txt.setHeight100();
        txt.setControlGroups(new String[]{"fontControls", "styleControls", "colorControls"});
        txt.setFontControls("fontSizeSelector");
//"fontControls", "formatControls", "styleControls", "colorControls"
        //    textform.setFields(txt);
//        VLayout1000.setLayoutLeftMargin(5);
//        VLayout1000.setLayoutRightMargin(5);
        VLayout1000.setCanDragResize(true);
        VLayout1000.addResizedHandler(new ResizedHandler() {
            @Override
            public void onResized(ResizedEvent resizedEvent) {
                txt.setWidth100();
            }
        });

        VLayout1000.setMembers(HLayout100, txt);
        callpanel2.setMembers(callpanel);




        HLayout100.setMembers(callpanel2,  VLayout10);
        final HLayout HLayout10000 = new HLayout();//HLayout10000.setBackgroundColor("#ff0000");
        HLayout10000.setShowEdges(true);//setBackgroundColor("#00ff00");


        HLayout10000.addMember(VLayout1000);


        final VLayout VLayout1001 = new VLayout();
        VLayout1001.setShowEdges(true);

        HLayout10000.addMember(VLayout1001);

        if (CallCenter.callCenterInstance.mygrps.equals("nophone")) {
            VLayout1000.setVisible(false);
            VLayout1001.setVisible(false);
        }
//-------------------------------------------------------------------------
        if (clfunctions.isgobs()){
            final VLayout VLayout101 = new VLayout();
            VLayout101.setShowEdges(true);
            VLayout101.setHeight("25%");

            HLayout HLayout1011=new HLayout();HLayout1011.setHeight(25);
            HLayout HLayout1012=new HLayout();HLayout1012.setHeight(25);

            final DynamicForm find = new DynamicForm();find.setTitleOrientation(TitleOrientation.TOP);
//            find.setMinWidth(1);find.setDefaultWidth(1);find.setColWidths("50%","10%","25%","25%");
//find.setMinColWidth(1);find.setWidth100();//find.setAutoWidth();
            find.setNumCols(4);find.setWidth100();






            txtFltPhone.setWidth("100%");txtFltrPersonalNumber.setWidth("100%");txtFltrFirstName.setWidth("100%");txtFltrLastName.setWidth("100%");

            find.setFields(txtFltPhone,txtFltrPersonalNumber,txtFltrFirstName,txtFltrLastName);

            MyIButton findbutt=new MyIButton("find",CallCenter.style,"findbutt");findbutt.setLayoutAlign(VerticalAlignment.BOTTOM);

findbutt.addClickHandler(new ClickHandler() {
    @Override
    public void onClick(ClickEvent clickEvent) {
        if (txtFltPhone.getValue()!=null || txtFltrFirstName.getValue()!=null ||
                txtFltrLastName.getValue()!=null || txtFltrPersonalNumber.getValue()!=null)
        {
            try
            {
                //FillListView(
                        findServices(txtFltPhone.getValueAsString(), txtFltrFirstName.getValueAsString(),
                        txtFltrLastName.getValueAsString(), txtFltrPersonalNumber.getValueAsString());
            }
            catch (Exception ex)
            {
                SC.say(ex.toString());
            }
        }
    }
});

            btnSvcSelect.setHeight100();
            lblSelectedSvc.setContents("service not selected");
            lblSelectedSvc.setBackgroundColor("#FF0000");lblSelectedSvc.setWidth100();lblSelectedSvc.setHeight100();
            HLayout1012.setMembers(btnSvcSelect,lblSelectedSvc);


            HLayout1011.setMembers(find,findbutt);



            ListGridField field1 = new ListGridField("service","service");
            ListGridField field2 = new ListGridField("status","status");
            ListGridField field3 = new ListGridField("uid","UID");
            ListGridField field4 = new ListGridField("subscriber","subscriber");
            ListGridField field5 = new ListGridField("address","address");
            ListGridField field6 = new ListGridField("csid","csid");

            ListGridField field7 = new ListGridField("getUID","getUID");
            ListGridField field8 = new ListGridField("getServiceId","getServiceId");
            ListGridField field9 = new ListGridField("getCustomerPrivilegeType","getCustomerPrivilegeType");
            ListGridField field10 = new ListGridField("getDeviceTroubleId","getDeviceTroubleId");
            ListGridField field11 = new ListGridField("getLinkURL","getLinkURL");

            lvServices.setFields(field1,field2,field3,field4,field5,field6,field7,field8,field9,field10,field11);lvServices.setSelectionType(SelectionStyle.SINGLE);lvServices.setCanEdit(false);

            labservices.setContents("<font color=\"#0000ff\">services</font>");
            labservices.setHeight(10);
            VLayout101.setMembers(labservices,HLayout1011,lvServices,HLayout1012);

            lvServices.addSelectionChangedHandler(new SelectionChangedHandler() {
                @Override
                public void onSelectionChanged(SelectionEvent selectionEvent) {
                    if (lvServices.getSelectedRecord()==null) btnSvcSelect.disable();
                    else btnSvcSelect.enable();
                    //btnSvcSelect.setEnabled(lvServices.getSelectedRowCount() == 1);
                }
            });
            btnSvcSelect.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent clickEvent) {


                    String csid=lvServices.getSelectedRecord().getAttribute("csid");
                    lvServicesrec=lvServices.getSelectedRecord();
                    CallCenter.callCenterInstance.sendgreet("mainarea.sdCallProcess\n"+gcallCallId+"\n"+csid);

                }
            });



            final VLayout VLayout102 = new VLayout();
            VLayout102.setShowEdges(true);
            VLayout102.setHeight("25%");




            ListGridField hfield1 = new ListGridField("date","date");
            ListGridField hfield2 = new ListGridField("tr","tr.");
            ListGridField hfield3 = new ListGridField("name","name");
            ListGridField hfield4 = new ListGridField("comment","comment");
            ListGridField hfield5 = new ListGridField("user","user");
            ListGridField hfield6 = new ListGridField("template","template");
            lvHistory.setFields(hfield1,hfield2,hfield3,hfield4,hfield5,hfield6);lvHistory.setCanEdit(false);
            //lvHistory.setSelectionType(SelectionStyle.SINGLE);

            labhist.setContents("<font color=\"#0000ff\">history</font>");
            labhist.setHeight(10);

            VLayout102.setMembers(labhist,lvHistory);







            VLayout103.setShowEdges(true);
            VLayout103.setHeight("20%");


            final DynamicForm info = new DynamicForm();info.setTitleOrientation(TitleOrientation.TOP);
//            find.setMinWidth(1);find.setDefaultWidth(1);find.setColWidths("50%","10%","25%","25%");
//find.setMinColWidth(1);find.setWidth100();//find.setAutoWidth();
            info.setNumCols(2);info.setWidth100();

            //phone.setTitleOrientation(TitleOrientation.TOP);
            //persn.setTitleOrientation(TitleOrientation.TOP);




            txtCallerName.setWidth("100%");txtCallerName.setVAlign(VerticalAlignment.TOP);
            txtCallerComment.setWidth("100%");txtCallerComment.setHeight("100%");

            info.setFields(txtCallerName,txtCallerComment);info.setHeight100();
            MyLabel lab3=new MyLabel("",CallCenter.style,"lab3");
            lab3.setContents("<font color=\"#0000ff\">customer info</font>");
            lab3.setHeight(10);
            VLayout103.setMembers(lab3,info);



            VLayout104.setShowEdges(true);
            VLayout104.setHeight("80%");
            HLayout HLayout1041=new HLayout();
            HLayout HLayout1042=new HLayout();
            HLayout HLayout1043=new HLayout();
            HLayout1041.setHeight100();
            MyLabel slabtemp=new MyLabel("",CallCenter.style,"slabtemp");slabtemp.setContents("template");

            cbTemplate.setWidth100();cbTemplate.grid.setSelectionType(SelectionStyle.SINGLE);cbTemplate.grid.setCanEdit(false);
            chbAllTemplates.setTitle("");
            chbAllTemplates.setShowTitle(false);chbAllTemplates.setWidth(5);
            DynamicForm sform=new DynamicForm();sform.setWidth(5);
            sform.setNumCols(1);
            sform.setFields(chbAllTemplates);
            HLayout1041.setMembers(slabtemp,cbTemplate,sform);

            HLayout1042.setHeight(50);HLayout1042.setWidth100();
            MyLabel slabcomm=new MyLabel("",CallCenter.style,"slabcomm");slabcomm.setContents("comment");

            DynamicForm form1042=new DynamicForm();form1042.setWidth100();form1042.setNumCols(1);

            txtComment.setShowTitle(false);txtComment.setWidth("100%");
            form1042.setFields(txtComment);

HLayout1042.setMembers(slabcomm,form1042);
            HLayout1043.setHeight(25);
            DynamicForm form1043=new DynamicForm();
            chbTransfer.setTitle("transfer");
            chbPotentialChurn.setTitle("deactivation request");
chbDT_Is220.setTitle("220");chbDT_Is220.setVisible(false);
chbDT_IsKey.setTitle("key");chbDT_IsKey.setVisible(false);

            form1043.setFields(chbTransfer,chbPotentialChurn,chbDT_Is220,chbDT_IsKey);form1043.setNumCols(4);form1043.setWidth100();



            MyIButton savebutt=new MyIButton("save",CallCenter.style,"savebutt");
            savebutt.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent clickEvent) {
                    //SDTemplate SelectedTemplate = cbTemplate.getSelectedItem()!= null ? ((titem)cbTemplate.getSelectedItem()).sdtemplate : null;
                    ListGridRecord rr=cbTemplate.grid.getSelectedRecord();
                    if (rr == null || rr.getAttribute("myid")==null)
                    {
                        SC.warn("გთხოვთ აირჩიოთ ტემპლეითი!");
                        return;
                    }

                    String TemplateComment =clfunctions.mytrim(txtComment.getValue());
                    int MinCommentLength = clfunctions.str2int(rr.getAttribute("minlength"),0);
                    if (MinCommentLength>0)
                    {
                        if (TemplateComment==null || TemplateComment.length() < MinCommentLength)
                        {
                            SC.warn("ტემპლეითისთვის განკუთვნილი კომენტარის ველი არის ცარიელი!");
                            return;
                        }
                    }



                     String ss="mainarea.gobssave\n"+
                             gcallId+"\n"+
                                chbTransfer.getValue().toString()+"\n"+
                                rr.getAttribute("myid")+"\n"+
                                TemplateComment.replace("\n","\t")+"\n"+
                                clfunctions.mytrim(txtCallerName.getValue())+"\n"+
                                clfunctions.mytrim(txtCallerComment.getValue())+"\n"+
                             (chbPotentialChurn.getValueAsBoolean()?"true":"null")+"\n"+
                                clfunctions.mytrim(DeviceTroubleId)+"\n"+
                             ((!clfunctions.mytrim(DeviceTroubleId).equals("")) ? chbDT_Is220.getValue().toString() : "null")+"\n"+
                             ((!clfunctions.mytrim(DeviceTroubleId).equals("")) ? chbDT_IsKey.getValue().toString() : "null");

                    CallCenter.callCenterInstance.sendgreet(ss);

                        //Close();

                }
            });
            //savebutt.setLayoutAlign(Alignment.RIGHT);
HLayout1043.setMembers(form1043,savebutt);

VLayout104.setMembers(HLayout1041,HLayout1042,HLayout1043);//<p style="color:red font-size:30px">"<font color=\"#00ff00\" size=\"8\">call wait</font>"

            VLayout103104.setHeight("50%");VLayout103104.setWidth100();
            VLayout103104.setMembers(VLayout103,VLayout104);
VLayout1001.setMembers(VLayout101,VLayout102,VLayout103104);
            this.ShowNoCalls("");
        }else {
            final HLayout HLayout101 = new HLayout();
            HLayout101.setShowEdges(true);
            HLayout101.setHeight(30);


            //chaninfo.setWidth(HLayout101.getWidth());
            //textform.setBackgroundColor("#00FF00");
            chaninfoform.setFields(chaninfo);
            chaninfoform.setWidth("100%");
            chaninfoform.setAlign(Alignment.CENTER);
            HLayout101.addMember(chaninfoform);
//HLayout101.setBackgroundColor("#008888");


            final HLayout HLayout102 = new HLayout();
            HLayout102.setShowEdges(true);
            HLayout102.setHeight(30);
            final MyLabel member1 = new MyLabel("", CallCenter.style,"member1");
            member1.setWidth("35%");
            member1.setAlign(Alignment.CENTER);
            member1.setContents("problem");
            HLayout102.addMember(member1);


            //textform.setBackgroundColor("#00FF00");
            shablonform.setFields(shablon);
            shablonform.setWidth("30%");
            shablonform.setAlign(Alignment.CENTER);
            HLayout102.addMember(shablonform);
            final MyLabel member3 = new MyLabel("", CallCenter.style,"member3");
            member3.setWidth("35%");
            member3.setAlign(Alignment.CENTER);
            member3.setContents("info");
            HLayout102.addMember(member3);
            final HLayout HLayout103 = new HLayout();
            HLayout103.setShowEdges(true);
            HLayout103.setHeight(100);

            HLayout103.addMember(problems2);
            HLayout103.addMember(infos2);


            final HLayout HLayout104 = new HLayout();
            HLayout104.setShowEdges(true);
            HLayout104.setHeight100();
            final VLayout VLayout11 = new VLayout();
            VLayout11.setShowEdges(true);
            final VLayout VLayout12 = new VLayout();
            VLayout12.setShowEdges(true);

            VLayout11.addMember(problems);
            VLayout12.addMember(infos);
            final MyLabel requestsl = new MyLabel("", CallCenter.style,"requestsl");
            requestsl.setHeight(30);
            requestsl.setAlign(Alignment.CENTER);
            requestsl.setContents("request");
            VLayout11.addMember(requestsl);
            VLayout11.addMember(requests);
            HLayout104.addMember(VLayout11);
            HLayout104.addMember(VLayout12);


            VLayout1001.addMember(HLayout101);
            VLayout1001.addMember(HLayout102);
            VLayout1001.addMember(HLayout103);
            VLayout1001.addMember(HLayout104);
        }

//---------------------------------------------------------------------------------------------------------
        //HLayout10000.addMember(problems);HLayout10000.addMember(infos);


        //  mainLayout.addMember(HLayout10000);

        this.addMember(HLayout10000);

//this.setBackgroundColor("#00ffff");
        initprobleminfo();
    }
    public void init2() {
        String vv="";
        int mygrp=CallCenter.callCenterInstance.mygrp;
        if (mygrp==0||mygrp==1) vv="0,2";
        else if (mygrp==3) vv="0";
        else if (mygrp==6) vv="6";
        else vv=""+mygrp+",0";

        CallCenter.callCenterInstance.sendgreet("getprobleminfo\t"+vv+"\t"+mygrp+"\t"+
                CallCenter.callCenterInstance.uname+"\t"+CallCenter.callCenterInstance.mynumber+"\t"+
                CallCenter.callCenterInstance.optype+"\t"+CallCenter.callCenterInstance.sippass+"\t"+CallCenter.callCenterInstance.pass);

    }
    public void init() {
        if (!CallCenter.callCenterInstance.messagestring.equals("no"))
        registerCallBack(this, CallCenter.callCenterInstance.messagestring + "/" +
                CallCenter.callCenterInstance.mynumber + "," + CallCenter.callCenterInstance.mygrp);
    }
    private String getWebSocketURL()
    {
        final String moduleBaseURL = com.google.gwt.core.client.GWT.getHostPageBaseURL();
        return moduleBaseURL.replaceFirst( "^http\\:", "ws:" ) + "chat";
    }

    void initprobleminfo(){
        problems.grid.setShowAllRecords(true);problems.grid.setShowHeader(false);
        //problems.setSelectionType(SelectionStyle.MULTIPLE);
       // problems.setSelectionAppearance(SelectionAppearance.CHECKBOX);
        problems.grid.setCanSelectAll(false);
        ListGridField problemField = new ListGridField("problem", "problem");
        ListGridField problemField1 = new ListGridField("myid", "myid");
        problemField1.setHidden(true);

        problems.grid.setFields( problemField,problemField1);
/*        for (int i=0;i<10;i++) {
            Record rr = new Record();
            rr.setAttribute("problem", "problem"+i);
            problems.addData(rr);
        }*/



        infos.grid.setShowAllRecords(true);infos.grid.setShowHeader(false);
        //countryGrid.setSelectionType(SelectionStyle.SIMPLE);
        //infos.setSelectionAppearance(SelectionAppearance.CHECKBOX);
        infos.grid.setCanSelectAll(false);
        ListGridField infoField = new ListGridField("info", "info");
        ListGridField infoField1 = new ListGridField("myid", "myid");
        infoField1.setHidden(true);


        infos.grid.setFields( infoField,infoField1);
/*        for (int i=0;i<10;i++) {
            Record rr = new Record();
            rr.setAttribute("info", "info"+i);
            infos.addData(rr);
        }*/

        cbTemplate.grid.setShowAllRecords(true);cbTemplate.grid.setShowHeader(false);
        //countryGrid.setSelectionType(SelectionStyle.SIMPLE);
        //infos.setSelectionAppearance(SelectionAppearance.CHECKBOX);
        cbTemplate.grid.setCanSelectAll(false);
        ListGridField tempField = new ListGridField("template", "template");
        ListGridField tempField1 = new ListGridField("myid", "myid");
        ListGridField tempField2 = new ListGridField("minlength", "minlength");
        tempField1.setHidden(true);tempField2.setHidden(true);


        cbTemplate.grid.setFields( tempField,tempField1,tempField2);


        requests.grid.setShowAllRecords(true);requests.grid.setShowHeader(false);
        //countryGrid.setSelectionType(SelectionStyle.SIMPLE);
        //requests.grid.setSelectionAppearance(SelectionAppearance.CHECKBOX);
        requests.grid.setCanSelectAll(false);
        ListGridField requestsField = new ListGridField("request", "request");
        ListGridField requestsField1 = new ListGridField("myid", "myid");
        requestsField1.setHidden(true);

        requests.grid.setFields( requestsField,requestsField1);
/*        for (int i=0;i<10;i++) {
            Record rr = new Record();
            rr.setAttribute("request", "request"+i);
            requests.addData(rr);
        }*/









        //chaninfo
        //chaninfo.setShowTitle(false);
        chaninfo.setTitle("lastRings");
        //chaninfo.setAlign(Alignment.CENTER);
        chaninfo.setWidth("100%");

        chaninfo.addChangeHandler(new ChangeHandler() {
            public void onChange(ChangeEvent event) {
            //    txt.setValue(shablon.getValueAsString()+"="+shablon.getValue()+"="+shablon.getDisplayField()
              //  +"="+event.getValue());
                setprobleminfo2(event.getValue().toString());
            }
        });

        //chaninfo.setValueMap("ring1", "ring2");
        //shablon
        shablon.setShowTitle(false);
        shablon.setAlign(Alignment.CENTER);
        shablon.setValueMap("Magti", "Bali", "Bani", "Fix", "Evdo", "Satellite", "Telemarket", "Samt", "DTH");
        //shablonform.setValue("shablon", "Magti");
        shablon.setValue("Magti");
        shablon.addChangeHandler(new ChangeHandler() {
            public void onChange(ChangeEvent event) {
            //    txt.setValue(shablon.getValueAsString()+"="+shablon.getValue()+"="+shablon.getDisplayField()
              //  +"="+event.getValue());
                setprobleminfo(event.getValue().toString());
            }
        });


        problems2.setCanSelectAll(false);
        ListGridField problem2Field = new ListGridField("problem", "problem");
        problems2.setFields( problem2Field);problems2.setShowHeader(false);
        infos2.setCanSelectAll(false);
        ListGridField info2Field = new ListGridField("info", "info");
        infos2.setFields( info2Field);infos2.setShowHeader(false);

     /*   Messaging.subscribe("stockQuotes" , new MessagingCallback() {
            @Override
            public void execute(Object data) {
                //updateStockQuotes(data);
                txt.setValue("Messaging"+data.toString());
            }
        });*/

    }
    void showgcall(String shistory,String sservices){

            // StopGetSDMessageProcess();

      /*      CallId = call.getId();
            CallAsterixId = call.getCallId();
            ANumber = call.getANumber();
            CallProcessed = false;

            pError.setVisible(false);
            pNoCalls.setVisible(false);
            pSvc.setVisible(false);
            gbHistory.setVisible(false);
            gbCallerInfo.setVisible(false);
            gbTemplate.setVisible(false);*/

        VLayout103104.setContents("");

if (gcallisCorporateCustomer)
    labservices.setContents("<font color=\"#0000ff\">history</font>&nbsp;&nbsp;"+gcallANumber+"&nbsp;&nbsp;"+gcallCreationDate+"&nbsp;&nbsp;corporate");
            else
    labservices.setContents("<font color=\"#0000ff\">history</font>&nbsp;&nbsp;"+gcallANumber+"&nbsp;&nbsp;"+gcallCreationDate);
            txtCallerName.setValue(gcallCallerName);
            txtCallerComment.setValue(gcallCallerComment);
            //pCall.setVisible(true);

            //lbtnShowDetails.setVisible(false);
            //   Show();

            //   timer1.Enabled = true;


        lvHistory.setmydata(shistory);

        FillListView(sservices,false);
        txtFltPhone.setValue("");
        txtFltrFirstName.setValue("");
        txtFltrLastName.setValue("");
        txtFltrPersonalNumber.setValue("");

        VLayout103.setVisible(true);VLayout104.setVisible(true);

    }
    void setprobleminfo2(String ss11) {
        String ss="";
        if (chanmp)        ss=mp.get(ss11);//txt.setValue(ss);
        else ss=chaninfomp.get(ss11);
        problems2.setData(new ListGridRecord[] {});
        infos2.setData(new ListGridRecord[] {});

        String[] s2=ss.split("\t");
        //log.operat, log.provider, log2.problems, log2.info, log.call_start,log.callid


        if (!s2[2].equals(" ")) {
            String[] sp2=s2[2].replace("{","").replace("}","").split(",");
            for (int i = 0; i < sp2.length; i++) {
                Record rr = new Record();
                String[] sp = problems0.get(sp2[i]).split("\t");
                if (sp[1].equals("100")) rr.setAttribute("problem", "(reg)" + sp[0]);
                else rr.setAttribute("problem", sp[0]);
                problems2.addData(rr);
            }
            //if (sp2.length>0) problems2.setVisible(true);
        }

        if (!s2[3].equals(" ")) {
            String[] si2=s2[3].replace("{","").replace("}","").split(",");
            for (int i = 0; i < si2.length; i++) {
                Record rr = new Record();
                rr.setAttribute("info", infos0.get(si2[i]).split("\t")[0]);
                infos2.addData(rr);
            }
            //if (si2.length>0) infos2.setVisible(true);
        }

    }
    public void fromgreet(String result) {
        if (result.startsWith("$mainarea.findServices")){
            FillListView(result,true);
        }else if (result.startsWith("$mainarea.sdCallProcess")){
            sdCallProcess(result);
        }else if (result.startsWith("$mainarea.gobssave")) {
            ShowNoCalls(result);
        }else {
            //problemsinfo = result;
            initprobleminfo0(result);
            setprobleminfo(shablon.getValueAsString());
        }
    }
    void ShowNoCalls(String result){
        if (result.contains("error")) {SC.say("error");return;}
        //aaaaaaaaaaaaa
        VLayout103104.setContents("<p style=\"color:green;font-size:60px;text-align:center;\">call wait</p>");
        labservices.setContents("<font color=\"#0000ff\">services</font>");
        VLayout103.setVisible(false);VLayout104.setVisible(false);
        btnSvcSelect.disable();

    }
    void sdCallProcess(String result){

        if (result.contains("error")) {SC.say("error");return;}



        lblSelectedSvc.setContents("არჩეული სერვისი: "+lvServicesrec.getAttribute("service")+" ("+lvServicesrec.getAttribute("getUID")+")");
        lblSelectedSvc.setBackgroundColor("#00ff00");

        FillTemplatesCombo(lvServicesrec.getAttribute("getServiceId"), lvServicesrec.getAttribute("getCustomerPrivilegeType"));

        chbDT_Is220.setValue(true);
        chbDT_IsKey.setValue(false);



        if (DeviceTroubleId!=null&&!DeviceTroubleId.equals("")){
            chbDT_Is220.setVisible(true);chbDT_IsKey.setVisible(true);
        }else{
           chbDT_Is220.setVisible(false);chbDT_IsKey.setVisible(false);
        }

     //   lbtnHideDetailsMouseClicked(null);

        String url = lvServicesrec.getAttribute("getLinkURL");
        if (url!=null&&!url.equals(""))
        {

                com.google.gwt.user.client.Window.open(
                        clfunctions.GobsUrl + url.replace("~", ""),
                        "_blank",
                        ""
                );


        }

        lvServices.deselectAllRecords();
        btnSvcSelect.disable();
    }
    void FillTemplatesComboall(){
         cbTemplate.grid.setData(new ListGridRecord[] {});

        for(Map.Entry<String, String> entry : gobstemplates0.entrySet()){
            String key=entry.getKey();

String[] s2=entry.getValue().split("\t");
            String val=s2[0];
            String minl=s2[2];
            Record rr = new Record();
            rr.setAttribute("myid", key);
            rr.setAttribute("template", val);
            rr.setAttribute("minlength", minl);
            cbTemplate.grid.addData(rr);

        }
    }
    private void FillTemplatesCombo(String ServiceId, String CustomerPrivilegeType)
    {
        if (ServiceId==null) {FillTemplatesComboall();return;}

        cbTemplate.grid.setData(new ListGridRecord[] {});

        for(Map.Entry<String, String> entry : gobstemplates0.entrySet()){
            String tid=entry.getKey();


            String[] s2=entry.getValue().split("\t");
            String templ=s2[0];String sid=s2[1];String minl=s2[2];
            Record rr = new Record();
            rr.setAttribute("myid", tid);
            rr.setAttribute("template", templ);
            rr.setAttribute("minlength", minl);
            //cbTemplate.grid.addData(rr);
            if (//OperatorType == sdOperatorType.Desk &&
                    CustomerPrivilegeType!=null){
                if (CustomerPrivilegeType.equals(clfunctions.CUSTOMER_PRIVILEGE_TYPE_ID_CORPORATE)){
                    if (gobscorptemplates0.containsKey(tid)) cbTemplate.grid.addData(rr);
                }else{
                    if (!gobscorptemplates0.containsKey(tid)) cbTemplate.grid.addData(rr);
                }
            }else{//getServiceId
                     if (sid.equals(ServiceId)) cbTemplate.grid.addData(rr);
            }




        }


        if (cbTemplate.grid.getRecords().length == 0)
            FillTemplatesComboall();

        //IEnumerable<SDService.SDTemplate> data = ServiceId.HasValue ? lstTemplates.Where(w => w.ServiceId == ServiceId.Value) : lstTemplates;
        //if (data.Count() == 0)
        //    data = lstTemplates;
boolean da=cbTemplate.grid.getRecords().length == gobscorptemplates0.size();
        chbAllTemplates.setValue(da);
        if (da )chbAllTemplates.disable();
        else chbAllTemplates.enable();
        //chbAllTemplates.setEnabled(!da);

        //       cbTemplate.DisplayMember = "TemplateTitle";
        //       cbTemplate.ValueMember = "TemplateId";

cbTemplate.grid.deselectAllRecords();


    }
    void initprobleminfo0(String result) {
        problems0=new HashMap();
        infos0=new HashMap();
        gobstemplates0=new HashMap();
        gobscorptemplates0=new HashMap();
        String[] s2=result.split("\n");
        int type=0;
        StringBuffer txt0=new StringBuffer("");StringBuffer sh0=new StringBuffer("");
        boolean btxt=true;boolean bsh=true;String reps="";
        for (int i=1;i<s2.length;i++) {
            if (s2[i].equals("$problem")) type=0;
            else if (s2[i].equals("$info")) type=1;
            else if (s2[i].equals("$savetxt")) type=11;
            else if (s2[i].equals("$shablon")) type=22;
            else if (s2[i].equals("$reports")) type=33;
            else if (s2[i].equals("$gobstemplates")) type=44;
            else if (s2[i].equals("$gobscorptemplates")) type=55;
            else {
                if (type==11){
                    if (btxt) {txt0.append(s2[i]);btxt=false;}
                    else txt0.append("\n"+s2[i]);
                }else if (type==22){
                    if (bsh) {sh0.append(s2[i]);bsh=false;}
                    else sh0.append("\n"+s2[i]);
                }else if (type==33){
                    reps+=s2[i]+"\n";
                }else if (type==44){
                    String[] s3 = s2[i].split("\t");
                        gobstemplates0.put(s3[0], s3[1]+"\t"+s3[2]+"\t"+s3[3]);
                }else if (type==55){
                    String[] s3 = s2[i].split("\t");
                        gobscorptemplates0.put(s3[0], s3[1]+"\t"+s3[2]+"\t"+s3[3]);
                }else {
                    String[] s3 = s2[i].split("\t");
                    if (type == 0) {
                        problems0.put(s3[0], s3[1] + "\t" + s3[2]);
                    } else if (type == 1) {
                        infos0.put(s3[0], s3[1] + "\t" + s3[2]);
                    }
                }
            }
        }
        txt.setValue(txt0.toString());
        //txt.setContents(txt0.toString());
        CallCenter.callCenterInstance.setchatsh(sh0.toString());
        CallCenter.callCenterInstance.settree(reps);
    }

    void setprobleminfo(String shabl) {

        String vv="";
        //logika pokaza???????????????????????????????
        int mygrp=CallCenter.callCenterInstance.mygrp;
        if (mygrp==0||mygrp==1) vv="0,2";
        else if (mygrp==3) vv="0";
        else if (mygrp==6) vv="6";
        else vv=""+mygrp+",0";
//        vv="0";
        //end logika pokaza
        String[] vv2=vv.split(",");
problems.grid.setData(new ListGridRecord[] {});
infos.grid.setData(new ListGridRecord[] {});
requests.grid.setData(new ListGridRecord[] {});


        problems2.setData(new ListGridRecord[] {});
        infos2.setData(new ListGridRecord[] {});
problems2.setEmptyMessage("");
infos2.setEmptyMessage("");

        for(Map.Entry<String, String> entry : problems0.entrySet()){

            String key=entry.getKey();
            String[] val2=entry.getValue().split("\t");
            String val=val2[0];
            String grp=val2[1];
            if (grp.equals("100")){
                Record rr = new Record();
                rr.setAttribute("myid", key);
                rr.setAttribute("request", val);
                requests.grid.addData(rr);
            }else if (val.indexOf(shabl)>0){
                if (vv.contains(grp)) {
                    Record rr = new Record();
                    rr.setAttribute("myid", key);
                    rr.setAttribute("problem", val);
                    problems.grid.addData(rr);
                }
            }
        }

        for(Map.Entry<String, String> entry : infos0.entrySet()){
            String key=entry.getKey();
            String[] val2=entry.getValue().split("\t");
            String val=val2[0];
            String grp=val2[1];
            if (val.indexOf(shabl)>0){
                if (vv.contains(grp)) {
                    Record rr = new Record();
                    rr.setAttribute("myid", key);
                    rr.setAttribute("info", val);
                    infos.grid.addData(rr);
                }
            }
        }

        FillTemplatesComboall();
//txt.setValue(mygrp+"=="+vv);

    }
    public void callMeBackclose(String msg1,String msg2){
        CallCenter.callCenterInstance.closeLayouts(false);//onclose???????????????????
    }
    public void callMeBackopen(String msg1,String msg2){
        //SC.say("kuku");
        init2();
        //CallCenter.callCenterInstance.closeLayouts(false);//onclose???????????????????
    }
    public void callMeBack(String msg1,String msg2){
        //SC.say(msg1,msg2);
      //  if (CallCenter.callCenterInstance.debug) CallCenter.callCenterInstance.tolenta("message===="+msg1+msg2);
        //$command2	1489392937.94	599283399mess
        if (msg1.startsWith("$command2")){
            if (state==REST) return;
            String[] s22=msg1.split("\n");
            String[] s2=s22[0].split("\t");
            number.setValue(s2[2]);
            CallCenter.callCenterInstance.setchatsmsnumber(s2[2]);
            CallCenter.callCenterInstance.number=s2[2];
            state=CON;
            callid=s2[1];

            mp=new LinkedHashMap();
            numberdescrip="";int type=0;String sg3="hist\n",sg4="serv\n";
            for (int i=1;i<s22.length;i++){
                //String[] s222=s22[i].replace("null","").split("\t");
                //log.operat, log.provider, log2.problems, log2.info, log.call_start,log.callid
                if (s22[i].equals("it$is$phonedescrip")) {type=1;continue;}
                else if (s22[i].equals("it$is$getSDMessageAD")) {type=2;continue;}
                else if (s22[i].equals("it$is$getCallHistory")) {type=3;continue;}
                else if (s22[i].equals("it$is$findServices")) {type=4;continue;}
                if (type==0) mp.put(""+i,s22[i].replace("null"," "));
                else if (type==2){
                    String s1a=s22[i].substring(0,4);
                    String s1b=s22[i].substring(4);

                    if (s1a.equals("anum")) gcallANumber=s1b;
                    else if (s1a.equals("bnum")) gcallBNumber=s1b;
                    else if (s1a.equals("comm")) gcallCallerComment=s1b.replace("\t","\n");
                    else if (s1a.equals("name")) gcallCallerName=s1b;
                    else if (s1a.equals("cidd")) gcallCallId=s1b;
                    else if (s1a.equals("stat")) gcallCallStatus=s1b;
                    else if (s1a.equals("date")) gcallCreationDate=s1b;
                    else if (s1a.equals("iddd")) gcallId=s1b;
                    else if (s1a.equals("corp")) gcallisCorporateCustomer=s1b.toLowerCase().equals("true");
                }
                else if (type==3){sg3+=s22[i]+"\n";

                }
                else if (type==4){sg4+=s22[i]+"\n";

                }
                else {
                    String s1=s22[i].trim();
                    if (!s1.equals("")) {
                        if (numberdescrip.equals("")) numberdescrip = s1;
                         else numberdescrip +="\n"+ s1;
                    }
                }
            }
            ninfo.setValue(numberdescrip);
            chaninfo.setValueMap(mp);chanmp=true;
            chaninfo.setValue(mp.get("1"));

            this.setbuttons();
            if (clfunctions.isgobs()) showgcall(sg3,sg4);
            else setprobleminfo2("1");
        }else if (msg1.startsWith("$command3")) {
            //txt.setValue("command3");
            if (state==REST) return;
            state=TERMINATE;
            setbuttons();
            if (msg1.contains("\ttransfer")) {
                t30=10;
                CallCenter.callCenterInstance.sendgreet("button\t"+CallCenter.callCenterInstance.uname+"\t"+
                        "ringreturn"+"\t"+CallCenter.callCenterInstance.mynumber+"\t"+
                        " "+"\t"+CallCenter.callCenterInstance.mygrp+"\t"+CallCenter.callCenterInstance.mygrps);
            }
            else {
                t30=30;
                CallCenter.callCenterInstance.sendgreet("button\t"+CallCenter.callCenterInstance.uname+"\t"+
                        "terminate"+"\t"+CallCenter.callCenterInstance.mynumber+"\t"+
                        " "+"\t"+CallCenter.callCenterInstance.mygrp+"\t"+CallCenter.callCenterInstance.mygrps);
            }


        }else if (msg1.startsWith("$check\t")) {
            CallCenter.callCenterInstance.sendgreet(msg1);
        }
        else CallCenter.callCenterInstance.message(msg1);
    }
    LinkedHashMap<String,String> chaninfomp=new LinkedHashMap();
    boolean chanmp=true;
    int t30=30;
    public native void closesocket() /*-{
        //$wnd.closeSocket();
$wndaa.close();
        }-*/;

    public native void registerCallBack(MainArea callBackObject,String messagestring) /*-{




    var webSocket;
    var messages = document.getElementById("messages");


    function openSocket(){
        // Ensures only one connection is open at a time
        if(webSocket !== undefined && webSocket.readyState !== WebSocket.CLOSED){
            writeResponse("WebSocket is already opened.");
            return;
        }
        // Create a new instance of the websocket
        //webSocket = new WebSocket("ws://127.0.0.1:8888/acc1111");
        //webSocket = new WebSocket("ws://127.0.0.1:9080/CallCenter_war/message");
        webSocket = new WebSocket(messagestring);

    webSocket.onopen = function(event){
        // For reasons I can't determine, onopen gets called twice
        // and the first time event.data is undefined.
        // Leave a comment if you know the answer.

        callBackObject.@ge.magti.client.layout.MainArea::callMeBackopen(Ljava/lang/String;Ljava/lang/String;)(event.data,"mess");
        if(event.data === undefined)
            return;
        writeResponse(event.data);
    };

    webSocket.onmessage = function(event){
        callBackObject.@ge.magti.client.layout.MainArea::callMeBack(Ljava/lang/String;Ljava/lang/String;)(event.data,"mess");

//        $wnd.callBackTo = function (msg1,msg2) {
//            callBackObject.@ge.magti.client.layout.MainArea::callMeBack(Ljava/lang/String;Ljava/lang/String;)(event.data,"mess");
 //           this.@ge.magti.client.layout.MainArea::callMeBack(Ljava/lang/String;Ljava/lang/String;)("event.data","mess");
   //     };


        //this.@com.proprintsgear.design_lab.client.ValueBox::fireChange()();

        //writeResponse(event.data);
    };

    webSocket.onclose = function(event){
        callBackObject.@ge.magti.client.layout.MainArea::callMeBackclose(Ljava/lang/String;Ljava/lang/String;)(event.data,"mess");

        writeResponse("Connection closed");
    };

}


    function send(){
        var text = document.getElementById("messageinput").value;
        webSocket.send(text);
    }

    function closeSocket(){
        webSocket.close();
    }

    function writeResponse(text){
        messages.innerHTML += "<br/>" + text;
    }


        openSocket();
$wndaa =webSocket;

    //    $wnd.callBackTo = function (msg1,msg2) {
    //        callBackObject.@ge.magti.client.layout.MainArea::callMeBack(Ljava/lang/String;Ljava/lang/String;)(msg1,msg2);
    //    };
    }-*/;

    /*
    private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
    AsyncCallback<String> cb=new AsyncCallback<String>() {
        public void onFailure(Throwable caught) {
            // Show the RPC error message to the user

            txt.setValue("onFailure");
        }

        public void onSuccess(String result) {

             if(result.startsWith("$getprobleminfo"))
             {
                 problemsinfo=result;
                 setprobleminfo(shablon.getValueAsString());
             }
            txt.setValue("onSuccess"+result);
        }
    };
    */
    void endclick(){
        String ss="\n\tproblems";String spr="{";
        for (ListGridRecord rec:problems.grid.getSelectedRecords()){
            ss+="\n"+rec.getAttribute("myid");
            if (spr.equals("{")) spr+=rec.getAttribute("myid");
            else spr+=","+rec.getAttribute("myid");
        }
        spr+="}";
        ss+="\n\tinfos";String sinfo="{";
        for (ListGridRecord rec:infos.grid.getSelectedRecords()){
            ss+="\n"+rec.getAttribute("myid");
            if (sinfo.equals("{")) sinfo+=rec.getAttribute("myid");
            else sinfo+=","+rec.getAttribute("myid");
        }
        sinfo+="}";
        ss+="\n\trequests";
        for (ListGridRecord rec:requests.grid.getSelectedRecords()){
            ss+="\n"+rec.getAttribute("myid");
        }
//txt.setValue(numberdescrip+"=="+ninfo.getValue().toString());
        if (!numberdescrip.equals(ninfo.getValue().toString())){
            ss+="\n\tphonedescrip\n";
            ss+=ninfo.getValueAsString();
        }
//txt.setValue(numberdescrip+"=="+ninfo.getValue().toString()+"\n"+ss);
        CallCenter.callCenterInstance.sendgreet("button\t"+CallCenter.callCenterInstance.uname+"\t"+
                "end"+"\t"+CallCenter.callCenterInstance.mynumber+"\t"+
                callid+"\t"+CallCenter.callCenterInstance.mygrp+"\t"+CallCenter.callCenterInstance.mygrps+"\t"+number.getValue().toString()+"\t"+ss);
        problems.grid.deselectAllRecords();
        infos.grid.deselectAllRecords();
        requests.grid.deselectAllRecords();

        problems2.setData(new ListGridRecord[] {});//problems2.setVisible(false);
        infos2.setData(new ListGridRecord[] {});//infos2.setVisible(false);
        //mp=new LinkedHashMap();
        String s1=number.getValue().toString()+"\t\t"+spr+"\t"+sinfo+"\t"+clfunctions.gettime()+"\t"+callid;
        number.setValue("");
        chaninfomp.put(""+chaninfomp.size(),s1);
        chaninfo.setValueMap(chaninfomp);chanmp=false;
      //  chaninfo.setValue(chaninfomp.get(""+(chaninfomp.size()-1)));
      //  setprobleminfo2(s1);
        numberdescrip="";
    }
    void myonclick(String click,MyIButton butt){
        ////            uname            dt    action            channel

        if (click.equals("end")){

            endclick();
        }else

        CallCenter.callCenterInstance.sendgreet("button\t"+CallCenter.callCenterInstance.uname+"\t"+
            click+"\t"+CallCenter.callCenterInstance.mynumber+"\t"+
                " "+"\t"+CallCenter.callCenterInstance.mygrp+"\t"+CallCenter.callCenterInstance.mygrps+"\t"+CallCenter.callCenterInstance.myoid);
        butt.disable();
    }
    public void fromgreetbutton(String result) {
        String[] s2=result.split("\t");
        if (s2.length>2&&!s2[2].equals("?")&&!s2[1].equals("restno"))
            CallCenter.callCenterInstance.resttime= clfunctions.str2int(s2[2],0);
        if (s2[1].equals("ready")){
            state=READY;
        }else if (s2[1].equals("end")){
            state=READY;
        }else if (s2[1].equals("busy")){
            state=BUSY;
        }else if (s2[1].equals("rest")){
            state=REST;
        }else if (s2[1].equals("restend")){
            state=READY;
        }else if (s2[1].equals("terminate")){
            state=TERMINATE;
        }else if (s2[1].equals("restno")){
            SC.say("rest is unavailable");
        }
        setbuttons();
    }
    public void login(String reststr){
        String[] s2=reststr.split("\t");
        if (s2.length>1){
            state=REST;
            CallCenter.callCenterInstance.sendgreet("button\t"+CallCenter.callCenterInstance.uname+"\t"+
                        "rest"+"\t"+CallCenter.callCenterInstance.mynumber+"\t"+
                        " "+"\t"+CallCenter.callCenterInstance.mygrp+"\t"+CallCenter.callCenterInstance.mygrps);
        }else {
            state = READY;
            CallCenter.callCenterInstance.sendgreet("button\t" + CallCenter.callCenterInstance.uname + "\t" +
                    "ready" + "\t" + CallCenter.callCenterInstance.mynumber + "\t" +
                    " " + "\t" + CallCenter.callCenterInstance.mygrp + "\t" + CallCenter.callCenterInstance.mygrps);
        }
        //init2();
        setbuttons();
    }
    int tt0=0;
    com.google.gwt.user.client.Timer mytimer=new com.google.gwt.user.client.Timer() {


    public void run() {


        tt0--;


        if (state==TERMINATE) {
            if (tt0<=0) {
                mytimer.cancel();
                readyint.setContents("0");
                state=READY;
                endclick();//tut greet
            }else{
                readyint.setContents(""+tt0);
            }
        }else
/*        if (state==BUSY) {
            if (tt0<=0) {
                mytimer.cancel();
                readyint.setContents("0");
                state=READY;
                CallCenter.callCenterInstance.sendgreet("button\t"+CallCenter.callCenterInstance.uname+"\t"+
                        "ready"+"\t"+CallCenter.callCenterInstance.mynumber+"\t"+
                        " "+"\t"+mygrp+"\t"+mygrps);
            }else{
                readyint.setContents(""+tt0);
            }
        }else*/
        if(state==REST) {
            readyint.setContents(""+tt0);
        }



    }
};


    void setbuttons(){
/*        if (state==BUSY){
            tt0=15;
            mytimer.scheduleRepeating(1000);
            readyint.setContents(""+tt0);
            //tt.run();
        }else {mytimer.cancel();readyint.setContents("0");}
*/

mytimer.cancel();readyint.setContents("0");

        if (state==READY){callpanel.setVisible(false);//????????????
            readybutton.disable();CallCenter.callCenterInstance.setleft("READY rest="+CallCenter.callCenterInstance.resttime);
            restbutton.enable();
            busybutton.enable();
            endbutton.disable();
            restendbutton.disable();
        }else if (state==END){callpanel.setVisible(false);CallCenter.callCenterInstance.setleft("END rest="+CallCenter.callCenterInstance.resttime);
            readybutton.disable();
            restbutton.disable();
            busybutton.enable();
            endbutton.disable();
            restendbutton.disable();
        }else if (state==BUSY){callpanel.setVisible(false);CallCenter.callCenterInstance.setleft("BUSY rest="+CallCenter.callCenterInstance.resttime);
            readybutton.enable();
            restbutton.enable();
            busybutton.disable();
            endbutton.disable();
            restendbutton.disable();
        }else if (state==REST){callpanel.setVisible(false);CallCenter.callCenterInstance.setleft("REST rest="+CallCenter.callCenterInstance.resttime);
            readybutton.disable();
            restbutton.disable();
            busybutton.disable();
            endbutton.disable();
            restendbutton.enable();
            tt0=CallCenter.callCenterInstance.resttime;
            mytimer.scheduleRepeating(1000);
        }else if (state==CON){callpanel.setVisible(true);CallCenter.callCenterInstance.setleft("CON rest="+CallCenter.callCenterInstance.resttime);
            readybutton.disable();
            restbutton.disable();
            busybutton.disable();
            endbutton.disable();
            restendbutton.disable();
        }else if (state==TERMINATE){callpanel.setVisible(true);CallCenter.callCenterInstance.setleft("TERMINATE rest="+CallCenter.callCenterInstance.resttime);
            readybutton.disable();
            restbutton.disable();
            busybutton.disable();
            endbutton.enable();
            restendbutton.disable();
            tt0=t30;
            mytimer.scheduleRepeating(1000);
            readyint.setContents(""+tt0);
        }
    }
    private void FillListView(String lstSvc,boolean fromgreet)//List<SDService.SDSvc>
    {
        if (fromgreet) {
            String[] s2 = lstSvc.split("\n");
            String sser = "ss\n", shis = "ss\n";
            int type = 0;
            for (int i = 0; i < s2.length; i++) {
                if (s2[i].equals("$Services")) {
                    type = 1;
                    continue;
                } else if (s2[i].equals("$CallHistory")) {
                    type = 2;
                    continue;
                } else if (type == 1) sser += s2[i] + "\n";
                else if (type == 2) shis += s2[i] + "\n";
            }
            lvServices.setmydata(sser);
            lvHistory.setmydata(shis);
        }else{
            lvServices.setmydata(lstSvc);
        }

        btnSvcSelect.disable();
        lblSelectedSvc.setBackgroundColor("#ff0000");
        lblSelectedSvc.setContents("სერვისი არ არის არჩეული !");

     //   pSvc.setVisible(true);
     //   gbHistory.setVisible(true);
     //   gbCallerInfo.setVisible(true);
     //   gbTemplate.setVisible(true);

        //         Width = MaxWidth;
        //       Height = MaxHeight;



//            this.BackColor = WndDefBgColor;
        //          timer1.Enabled = false;

        chbTransfer.setValue(false);
        chbPotentialChurn.setValue(false);

        chbDT_Is220.setValue(true);chbDT_Is220.setVisible(false);
        chbDT_IsKey.setValue(false);chbDT_IsKey.setVisible(false);
//        pDeviceTroubleData.setVisible(false);
        //DeviceTroubleId = null;

        cbTemplate.grid.deselectAllRecords();

        //cbTemplate.setSelectedIndex(-1);// .SelectedItem = null;
        txtComment.setValue("");

        if (lvServices.getRecords().length == 0) {
            lblSelectedSvc.setContents("არცერთი სერვისი არ მოიძებნა!");
        }

    }
    private static String findServices(String phone, String firstName, String lastName, String personalNumber) {
        CallCenter.callCenterInstance.sendgreet("mainarea.findServices\n"+
            CallCenter.callCenterInstance.uname+"\n"+CallCenter.callCenterInstance.pass+"\n"+
        phone+"\n"+firstName+"\n"+lastName+"\n"+personalNumber);
        return "";
    }
}
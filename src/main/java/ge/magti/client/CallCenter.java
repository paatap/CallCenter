package ge.magti.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootLayoutPanel;

import com.smartgwt.client.util.DateDisplayFormatter;
import com.smartgwt.client.util.DateUtil;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;
import ge.magti.client.Dialogs.DlgLogin;
import ge.magti.client.layout.*;

import java.util.Date;


/**  --select * from pg_stat_activity;
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CallCenter implements EntryPoint{
  /**
   * The message displayed to the user when the server cannot be reached or
   * returns an error.
   */
  private static final String SERVER_ERROR = "An error occurred while "
      + "attempting to contact the server. Please check your network "
      + "connection and try again.";

  /**
   * Create a remote service proxy to talk to the server-side Greeting service.
   */
  public static String ver="Call Center 2.020";
  public static CallCenter callCenterInstance;
  public static String style="";
  public  boolean debug=false;
  public  String messagestring;
  public  String mynumber;
  public String number="";
  public String myoid;
  public  String uname;
  public  String pass;
    public int mygrp=0;
    public String mygrps="";public String grps="";public String sippass="";
    public String mygrplink;
  public String reststr;
  public int resttime=0;
  public  int optype=-1;
  private static final int HEADER_HEIGHT = 20;

  private VLayout mainLayout;
  private HLayout northLayout;
  public HLayout southLayout;
  public VLayout maincc;
  HeaderArea hh;
  //public VLayout reportcc=null;
  //public VLayout reportcc2=null;
  //public VLayout reportcc3=null;
  private NavigationArea westLayout;


MyVLayout[] reps=MyVLayout.getlayouts();

public Layout getlayout(String name){
    for (int i=0;i<reps.length;i++)
        if (name.equals(reps[i].name)) return reps[i].myvlayout;
    return null;
}
  /**
   * This is the entry point method.
   */
  public void closeLayouts(boolean closesock){

      if (closesock) ((MainArea)maincc).closesocket();

      for(int i=0;i<reps.length;i++)
       if (reps[i].myvlayout!=null)
      {
          reps[i].myvlayout.destroy();
          reps[i].myvlayout=null;
      }
      mainLayout.destroy();

       onModuleLoad1();
  }
//http://exposure101-development.blogspot.com/2012/08/registering-close-handler-for-newly.html
public native void registeronclose() /*-{

    window.onbeforeunload = function(event) {
        event.returnValue = "Write something ..";
    };

  }-*/;


  public void onModuleLoad() {
      DateUtil.setShortDateDisplayFormatter(new DateDisplayFormatter() {
          @Override
          public String format(Date date) {
              if(date == null) return null;
              //you'll probably want to create the DateTimeFormat outside this method.
              //here for illustration purposes
              DateTimeFormat dateFormatter = DateTimeFormat.getFormat("yyyy-MM-dd");
              String format = dateFormatter.format(date);
              return format;
          }
      });


    String myeksp = com.google.gwt.user.client.Window.Location.getParameter("myeksp");

    if (myeksp!=null&&myeksp.equals("true")) {
            Window.enableScrolling(false);
    Window.setMargin("0px");
    Window.setTitle("eksp");
        myeksp meksp=new myeksp();
        meksp.setWidth100();
        meksp.setHeight100();
        RootLayoutPanel.get().add(meksp);
        meksp.show();
     return;
    }
       mygrplink = com.google.gwt.user.client.Window.Location.getParameter("grp");

      //registerHandlers(Jsni.this, window);
        registeronclose();
        callCenterInstance = this;
        onModuleLoad1();
  }
    public void onModuleLoad1() {
   // callCenterInstance = this;

    Window.enableScrolling(false);
    Window.setMargin("0px");

    // main layout occupies the whole area
    mainLayout = new VLayout();
    mainLayout.setWidth100();
    mainLayout.setHeight100();


    RootLayoutPanel.get().add(mainLayout);

    Window.setTitle(ver);
     if (dlgLogin==null) {
         dlgLogin = new DlgLogin();
         dlgLogin.show();
     }
  }
DlgLogin dlgLogin=null;



    public void loginSuccess2(String ss0){
       String ss=ss0.replace("\t","").replace("\n","").replace(" ","");
        int grp=0;
      if (ss.startsWith("mobile")) grp=MainArea.mobile;
      else if (ss.startsWith("gov")) grp=MainArea.gov;
      else if (ss.startsWith("magtisat")) grp=MainArea.magtisat;
      else if (ss.startsWith("magtifix")) grp=MainArea.magtifix;
      else if (ss.startsWith("marketing")) grp=MainArea.marketing;
      else if (ss.startsWith("nophone")) grp=MainArea.nophone;
      else if (ss.startsWith("info")) grp=MainArea.info;
      else {
          SC.warn("Group not found !"+ss+"!");

          dlgLogin.buttonItem.setDisabled(false);
          return;
      }
//        dlgLogin.close();
    dlgLogin.destroy();
      dlgLogin=null;

    Window.enableScrolling(false);
    Window.setMargin("0px");

    // main layout occupies the whole area
    mainLayout = new VLayout();
    mainLayout.setWidth100();
    mainLayout.setHeight100();

    northLayout = new HLayout();
    northLayout.setHeight(HEADER_HEIGHT);

    VLayout vLayout = new VLayout();
    hh=new HeaderArea(ver + " " + uname + " " + mynumber+" "+ss);
    vLayout.addMember(hh);
    //vLayout.addMember(new ApplicationMenu());
    northLayout.addMember(vLayout);

    westLayout = new NavigationArea();
    westLayout.setWidth("25%");
        mygrp=grp;
        mygrps=ss;
      maincc = new MainArea();

      ((MainArea)maincc).init();
      //maincc.setWidth("85%");

    southLayout = new HLayout();
    //southLayout.setMembers(westLayout, maincc);
        setsouthLayoutall();
    mainLayout.addMember(northLayout);
    mainLayout.addMember(southLayout);

    // add the main layout container to GWT's root panel
    RootLayoutPanel.get().add(mainLayout);
        Window.setTitle(ver + " " + uname + " " + mynumber+" "+ mygrps);
        ((MainArea) maincc).login(reststr);
        //if (uname.equals("Alexander.Sarchimelia")) debug = true;
  }
  public void setleft(String ss,boolean add,boolean color){
        String s1;
        if (add)  s1=hh.leftl.getContents()+ss;
        else s1=ss;
        if (color) s1="<span style='color:#ff0000'>"+s1+"</span>";

        hh.leftl.setContents(s1);
  }
  public void setleft(String ss){

         hh.leftl.setContents(ss);

         hh.leftl.setBackgroundColor("#ffffff");
  }
public void setsouthLayout(Layout lay){

  /*    southLayout.setMembers(westLayout);
      for (int i=0;i<reps.length;i++){
          if (reps[i].myvlayout!=null) {

                  southLayout.addMember(reps[i].myvlayout);


          }
      }*/

   // southLayout.setMembers(westLayout, lay);
}

    public void setsouthLayoutall(){

    southLayout.setMembers(westLayout);
      for (int i=0;i<reps.length;i++){

          if (reps[i].name.equals("mainarea")) {
              reps[i].myvlayout=maincc;
              reps[i].myvlayout0.setMembers(maincc);
              reps[i].myvlayout0.setVisible(true);
          }
           else  reps[i].myvlayout0.setVisible(false);

          southLayout.addMember(reps[i].myvlayout0);

      }
    }
  public void setvisiblearea(String type0,String ss){

        String type=MyVLayout.isframe(type0,ss);// type="repframe";
        MyVLayout myv=null;
        for (int i=0;i<reps.length;i++){

          String key=reps[i].name;

          if (key.equals(type)) myv=reps[i];
          else reps[i].mydestroy();

      }
      //if (!da)
      if (myv!=null)
        myv.myinit(ss,type0);


  }


  public void sendgreet(String mess){
    greetingService.greetServer(mess, cb);
  }

  private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
  AsyncCallback<String> cb=new AsyncCallback<String>() {
    public void onFailure(Throwable caught) {
      // Show the RPC error message to the user
         /*       dialogBox.setText("Remote Procedure Call - Failure");
                serverResponseLabel.addStyleName("serverResponseLabelError");
                serverResponseLabel.setHTML(SERVER_ERROR);
                dialogBox.center();
                closeButton.setFocus(true);*/
      if (debug) tolenta("onFailure");
    }

    public void onSuccess(String result) {
             /*   dialogBox.setText("Remote Procedure Call");
                serverResponseLabel.removeStyleName("serverResponseLabelError");
                serverResponseLabel.setHTML(result);
                dialogBox.center();
                closeButton.setFocus(true);*/

        if(result.startsWith("$savetxtok"))
        {
            SC.say("save ok");
        }
      else if(result.startsWith("$getprobleminfo")||result.startsWith("$mainarea"))
      {

        ((MainArea)maincc).fromgreet(result);

      }else if (result.startsWith("$login")){

        if (result.startsWith("$login\tok")){
            String[] s22=result.split("\n");
            String[] s2=s22[0].split("\t");
            debug=s2[3].equals("true");
            messagestring=s2[2];
            mynumber=s2[4];
            uname=s2[5];
            optype= clfunctions.str2int(s2[6],-1);
            myoid=s2[7];
            if (s22.length<3){
                SC.warn("Group not found !");
                dlgLogin.buttonItem.setDisabled(false);
            }else {
                if (!s2[8].equals(ver))
                    SC.say("version is old!"
                    +"<br>"+s2[8]+"<br>"+ver+"<br><br><br>please click 'ctrl+F5'");
                style=s2[9];
                grps=s2[10];
                sippass=s2[11];
                if (style.equalsIgnoreCase("null")) style="";
                reststr=s22[1];
                dlgLogin.loginSuccess(s22);

            }
        }else {
            String[] s2=result.split("\t");
            SC.warn(s2[2]);
          //SC.warn("Error user password !");

          dlgLogin.buttonItem.setDisabled(false);
        }

      }else if (result.startsWith("$button")){
          ((MainArea)maincc).fromgreetbutton(result);
      }else if (result.startsWith("$getops")){
          westLayout.chat.getops(result);

      }else if (result.startsWith("$getserverinfo")){
          ReportAreaSinfo r4=(ReportAreaSinfo) getlayout("serverinfo");
          if (r4!=null){
              r4.fromgreet(result);
          }
      }else if (result.startsWith("$say")){
          String[] s2=result.split("\t");
          SC.say(s2[1]);
      }else if (result.startsWith("$get2ops")){
            ReportAreaAdmin r4=(ReportAreaAdmin) getlayout("admin");

            if (r4!=null) r4.getops(result);
      }else if (result.startsWith("$connectionestablished")){
            //((MainArea)maincc).init2();
            SC.say("$connectionestablished");
      }




      //if (debug) tolenta("onSuccess"+result);
    }
  };

  public void message(String result){
      if (result.startsWith("$sendopmessage")) {
          westLayout.chat.getmessage(result);
      }else if (result.startsWith("$restwarning")){
          setleft(" rest warnig",true,true);
      }
  }
  public void setchatsh(String sh0){
       westLayout.chat.setchatsh(sh0);
  }
  public void setchatsmsnumber(String smsnumber){
        westLayout.chat.setsmsnumber(smsnumber);
  }
  public void settree(String ss){
      westLayout.settree(ss);
  }
    public void tolenta(String ss){
      if (ss.length()>100) westLayout.chat.lentasetValue(ss.substring(0,100).replace("<",".").replace(">","."));
      else  westLayout.chat.lentasetValue(ss.replace("<",".").replace(">","."));
    }

    public native void sendframemessage(String mess) /*-{


        //var domain = $wnd.location.protocol + "//" + $wnd.location.hostname;
        var iframe0 = $wnd.document.getElementById('Frame1');
        if(typeof iframe0 !== 'undefined' && iframe0 !== null) {
            var iframe = iframe0.contentWindow;

            iframe.postMessage(mess, "*");
        }


    }-*/;
    public static native void newwin(String ss) /*-{

        var w=300;
        var h=200;


        var dualScreenLeft = window.screenLeft != undefined ? window.screenLeft : screen.left;
        var dualScreenTop = window.screenTop != undefined ? window.screenTop : screen.top;

        var width = window.innerWidth ? window.innerWidth : document.documentElement.clientWidth ? document.documentElement.clientWidth : screen.width;
        var height = window.innerHeight ? window.innerHeight : document.documentElement.clientHeight ? document.documentElement.clientHeight : screen.height;

        var left = ((width / 2) - (w / 2)) + dualScreenLeft;
        var top = ((height / 2) - (h / 2)) + dualScreenTop;



        var myWin = window.open('about:blank','Copy','scrollbars=yes, width=' + w + ', height=' + h+ ', top=' + top + ', left=' + left);
        myWin.document.write(ss);

        //      var div = myWin.document.createElement('div'),
        //          body = myWin.document.body;

//        div.innerHTML = ss
        //      div.style.fontSize = '30px'

        // вставить первым элементом в body нового окна
        //    body.insertBefore(div, body.firstChild);

        myWin.focus();
        myWin.onblur = function() { this.close(); };
        setTimeout(function() {
            myWin.close();
        }, 50000);
    }-*/;
}

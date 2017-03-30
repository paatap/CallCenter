package ge.magti.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootLayoutPanel;

import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.DateDisplayFormatter;
import com.smartgwt.client.util.DateUtil;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;
import ge.magti.client.Dialogs.DlgLogin;
import ge.magti.client.layout.*;
import ge.magti.server.functions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


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
  public static String ver="Call Center 2.0";
  public static CallCenter callCenterInstance;
  public  boolean debug=false;
  public  String messagestring;
  public  String mynumber;
  public String myoid;
  public  String uname;
  public  String pass;
  public String reststr;
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


MyVLayout[] reps=new MyVLayout[]{
        new MyVLayout("mainarea"),
        new MyVLayout("rep1"),
        new MyVLayout("rep2"),
        new MyVLayout("rep3"),
        new MyVLayout("rep4")
};
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
      else {
          SC.warn("Group not found !"+ss+"!");

          dlgLogin.buttonItem.setDisabled(false);
          return;
      }

    dlgLogin.destroy();dlgLogin=null;

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
    westLayout.setWidth("15%");

      maincc = new MainArea();

      ((MainArea)maincc).mygrp=grp;
      ((MainArea)maincc).mygrps=ss;
      ((MainArea)maincc).init();
      maincc.setWidth("85%");

    southLayout = new HLayout();
    southLayout.setMembers(westLayout, maincc);

    mainLayout.addMember(northLayout);
    mainLayout.addMember(southLayout);

    // add the main layout container to GWT's root panel
    RootLayoutPanel.get().add(mainLayout);
        Window.setTitle(ver + " " + uname + " " + mynumber+" "+((MainArea)maincc).mygrps);
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
    southLayout.setMembers(westLayout, lay);
}
  public void setvisiblearea(String type){

        for (int i=0;i<reps.length;i++){

          String key=reps[i].name;

          if (key.equals(type)) reps[i].myinit();
          else reps[i].mydestroy();

      }


        /*
      if (type.equals("mainarea")) {
          maincc.setVisible(true);

          if (reportcc!=null) reportcc.destroy();reportcc=null;
          if (reportcc2!=null) reportcc2.destroy();reportcc2=null;
          if (reportcc3!=null) reportcc3.destroy();reportcc3=null;
      }else if (type.equals("rep1")){
          maincc.setVisible(false);
          if (reportcc2!=null) reportcc2.destroy();reportcc2=null;
          if (reportcc==null){
              reportcc = new ReportArea();reportcc.setWidth("85%");
              southLayout.addMember(reportcc);
              ((ReportArea)reportcc).setprobleminfo();
          }
            reportcc.setVisible(true);
      }else if (type.equals("rep2")){
          maincc.setVisible(false);
          if (reportcc!=null) reportcc.destroy();reportcc=null;
          if (reportcc2==null){
              reportcc2 = new ReportArea2();reportcc2.setWidth("85%");
              southLayout.addMember(reportcc2);
              //((ReportArea)reportcc).setprobleminfo();
          }
            reportcc2.setVisible(true);
      }else if (type.equals("rep3")){
          maincc.setVisible(false);
          if (reportcc!=null) reportcc.destroy();reportcc=null;
          if (reportcc2==null){
              reportcc2 = new ReportArea2();reportcc2.setWidth("85%");
              southLayout.addMember(reportcc2);
              //((ReportArea)reportcc).setprobleminfo();
          }
            reportcc2.setVisible(true);
      }
      */
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
      if (debug) ((MainArea)maincc).txt.setValue("onFailure");
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
      else if(result.startsWith("$getprobleminfo"))
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
            optype= str2int(s2[6],-1);
            myoid=s2[7];
            if (s22.length<3){
                SC.warn("Group not found !");
                dlgLogin.buttonItem.setDisabled(false);
            }else {

                dlgLogin.loginSuccess(s22);
                reststr=s22[1];
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
          ReportArea4 r4=(ReportArea4) getlayout("rep4");
          if (r4!=null){
              r4.fromgreet(result);
          }
      }




      if (debug) ((MainArea)maincc).txt.setValue("onSuccess"+result);
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
    public static int str2int(String str,int def) {
        int rr = 0;
        try {
            rr = Integer.parseInt(str,10);
        } catch (Exception e) {
            // rr=-111111;
            rr = def;
        }
        return rr;
    }

}

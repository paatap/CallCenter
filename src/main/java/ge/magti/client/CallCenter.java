package ge.magti.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dev.shell.Jsni;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootLayoutPanel;

import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.events.TabCloseClickEvent;
import ge.magti.client.Dialogs.DlgLogin;
import ge.magti.client.layout.*;


/**
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
  public  String uname;
  public  String pass;
  private static final int HEADER_HEIGHT = 20;

  private VLayout mainLayout;
  private HLayout northLayout;
  private HLayout southLayout;
  public VLayout maincc;
  public VLayout reportcc;
  private NavigationArea westLayout;

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
    vLayout.addMember(new HeaderArea(ver + " " + uname + " " + mynumber+" "+ss));
    //vLayout.addMember(new ApplicationMenu());
    northLayout.addMember(vLayout);

    westLayout = new NavigationArea();
    westLayout.setWidth("15%");

      maincc = new MainArea();
      reportcc = new ReportArea();reportcc.setVisible(false);reportcc.setWidth("85%");
      ((MainArea)maincc).mygrp=grp;
      ((MainArea)maincc).mygrps=ss;
      ((MainArea)maincc).init();
      maincc.setWidth("85%");

    southLayout = new HLayout();
    southLayout.setMembers(westLayout, maincc,reportcc);

    mainLayout.addMember(northLayout);
    mainLayout.addMember(southLayout);

    // add the main layout container to GWT's root panel
    RootLayoutPanel.get().add(mainLayout);
        Window.setTitle(ver + " " + uname + " " + mynumber+" "+((MainArea)maincc).mygrps);
        ((MainArea) maincc).login();
        //if (uname.equals("Alexander.Sarchimelia")) debug = true;
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

            if (s22.length<2){
                SC.warn("Group not found !");
                dlgLogin.buttonItem.setDisabled(false);
            }else {

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

      }




      if (debug) ((MainArea)maincc).txt.setValue("onSuccess"+result);
    }
  };

  public void message(String result){
      if (result.startsWith("$sendopmessage")) {
          westLayout.chat.getmessage(result);
      }
  }
  public void setchatsh(String sh0){
       westLayout.chat.setchatsh(sh0);
  }

}

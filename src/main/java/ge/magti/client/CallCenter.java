package ge.magti.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootLayoutPanel;

import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import ge.magti.client.Dialogs.DlgLogin;
import ge.magti.client.layout.ApplicationMenu;
import ge.magti.client.layout.HeaderArea;
import ge.magti.client.layout.MainArea;
import ge.magti.client.layout.NavigationArea;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CallCenter implements EntryPoint {
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
  public boolean debug=true;
  public static CallCenter callCenterInstance;
  private static final int HEADER_HEIGHT = 85;

  private VLayout mainLayout;
  private HLayout northLayout;
  private HLayout southLayout;
  private VLayout maincc;
  private HLayout westLayout;

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {


    callCenterInstance = this;

    Window.enableScrolling(false);
    Window.setMargin("0px");

    // main layout occupies the whole area
    mainLayout = new VLayout();
    mainLayout.setWidth100();
    mainLayout.setHeight100();


    RootLayoutPanel.get().add(mainLayout);


     dlgLogin = new DlgLogin();
    dlgLogin.show();
  }
DlgLogin dlgLogin;
    public void loginSuccess(){
    dlgLogin.destroy();
    Window.enableScrolling(false);
    Window.setMargin("0px");

    // main layout occupies the whole area
    mainLayout = new VLayout();
    mainLayout.setWidth100();
    mainLayout.setHeight100();

    northLayout = new HLayout();
    northLayout.setHeight(HEADER_HEIGHT);

    VLayout vLayout = new VLayout();
    vLayout.addMember(new HeaderArea());
    vLayout.addMember(new ApplicationMenu());
    northLayout.addMember(vLayout);

    westLayout = new NavigationArea();
    westLayout.setWidth("15%");

      maincc = new MainArea();
      maincc.setWidth("85%");

    southLayout = new HLayout();
    southLayout.setMembers(westLayout, maincc);

    mainLayout.addMember(northLayout);
    mainLayout.addMember(southLayout);

    // add the main layout container to GWT's root panel
    RootLayoutPanel.get().add(mainLayout);
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
      if(result.startsWith("$getprobleminfo"))
      {

        ((MainArea)maincc).fromgreet(result);

      }else if (result.startsWith("$login")){

        if (result.equals("$login\tok")){
          loginSuccess();
        }else {
          SC.warn("Error user password !");

          dlgLogin.buttonItem.setDisabled(false);
        }

      }
      if (debug) ((MainArea)maincc).txt.setValue("onSuccess"+result);
    }
  };
}

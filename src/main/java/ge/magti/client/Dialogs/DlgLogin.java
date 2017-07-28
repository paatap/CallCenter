package ge.magti.client.Dialogs;


        import com.google.gwt.user.client.Cookies;
        import com.smartgwt.client.types.Alignment;
        import com.smartgwt.client.util.SC;
        import com.smartgwt.client.widgets.Canvas;
        import com.smartgwt.client.widgets.Window;
        import com.smartgwt.client.widgets.form.DynamicForm;
        import com.smartgwt.client.widgets.form.fields.ButtonItem;
        import com.smartgwt.client.widgets.form.fields.PasswordItem;
        import com.smartgwt.client.widgets.form.fields.SelectItem;
        import com.smartgwt.client.widgets.form.fields.TextItem;
        import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
        import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
        import com.smartgwt.client.widgets.form.fields.events.KeyPressEvent;
        import com.smartgwt.client.widgets.form.fields.events.KeyPressHandler;
        import com.smartgwt.client.widgets.layout.HLayout;
        import ge.magti.EchoServer;
        import ge.magti.client.CallCenter;
        import ge.magti.client.clfunctions;


        import java.util.Date;
        import java.util.LinkedHashMap;

public class DlgLogin extends Window {

    private HLayout hLayout;

    private DynamicForm form;
    private TextItem userNameItem;
    private PasswordItem passwordItem;
    public ButtonItem buttonItem;
    public ButtonItem buttonItem2;
    SelectItem grp;
    private Canvas mainWindow;

    public DlgLogin() {
        setWidth(280);
        setHeight(130);
        setTitle("Login "+ CallCenter.ver);
        setShowMinimizeButton(false);
        setIsModal(true);
        setShowModalMask(true);
        setShowCloseButton(false);
        setCanDrag(false);
        setCanDragReposition(false);
        setCanDragResize(false);
        setCanDragScroll(false);
        centerInPage();

        hLayout = new HLayout();
        hLayout.setWidth100();
        hLayout.setHeight100();



        form = new DynamicForm();
        form.setHeight100();
        form.setWidth100();
        form.setPadding(10);
        form.setAutoFocus(true);


        userNameItem = new TextItem();
        userNameItem.setTitle("User Name");
        userNameItem.setWrapTitle(false);
        userNameItem.setWidth("*");

        passwordItem = new PasswordItem();
        passwordItem.setTitle("Password");
        passwordItem.setWidth("*");
        // TODO - Remove.
        passwordItem.addKeyPressHandler(new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
                if (event.getKeyName().equals("Enter")) {
                    login();
                }
            }
        });


        buttonItem = new ButtonItem();
        buttonItem.setTitle("Login");
        buttonItem.setColSpan(2);
        buttonItem.setAlign(Alignment.RIGHT);
        buttonItem.setWidth(80);
//        userNameItem.setHint("თქვენი email-ი, @magticom.ge-ს გარეშე");
        buttonItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                login();
            /*    String skinCookieName="Graphite";
                String skinCookieValue="Graphite";
                Cookies.setCookie(skinCookieName, skinCookieValue);
                com.google.gwt.user.client.Window.Location.reload();
                SC.say("1111111111111111");*/
            }
        });


        grp = new SelectItem("Group");
        buttonItem2 = new ButtonItem();
        buttonItem2.setTitle("Apply");
        buttonItem2.setColSpan(2);
        buttonItem2.setAlign(Alignment.RIGHT);
        buttonItem2.setWidth(80);
//        userNameItem.setHint("თქვენი email-ი, @magticom.ge-ს გარეშე");
        buttonItem2.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                //SC.say(grp.getValueAsString());
                //SC.say(mp.get(grp.getValueAsString()).toString());
                CallCenter.callCenterInstance.loginSuccess2(grp.getValueAsString());
            }
        });
buttonItem2.setVisible(false);grp.setVisible(false);


        form.setFields(userNameItem, passwordItem,
                buttonItem,grp,buttonItem2);
        hLayout.setMembers(form);
        addItem(hLayout);
        setCookieValues();
        setcss("",false);
    }

    public void setMainWindow(Canvas mainWindow) {
        this.mainWindow = mainWindow;
    }

    private void login() {
        // SC.showConsole();
        try {

            String userName = userNameItem.getValueAsString();
            if (userName == null || userName.trim().equals("")) {
                SC.warn("Enter User Name !");
                return;
            }
            String password = passwordItem.getValueAsString();
            if (password == null || password.trim().equals("")) {
                SC.warn("Enter Password !");
                return;
            }
            buttonItem.setDisabled(true);
            CallCenter.callCenterInstance.pass=password;
            CallCenter.callCenterInstance.sendgreet("login\t"+userName+"\t"+password);

            //CallCenter.callCenterInstance.loginSuccess(this);

        } catch (Exception e) {
            e.printStackTrace();
            SC.say(e.toString());
            buttonItem.setDisabled(false);
        }
    }



    private void setCookieValues() {
        String objUserName = Cookies.getCookie("MagticomUserName");
        String objLanguage = Cookies.getCookie("MagticomLanguage");
        String objActiveDir = Cookies.getCookie("MagticomActiveDir");
        if (objUserName != null) {
            userNameItem.setValue(objUserName);
            form.focusInItem(passwordItem);
        }

    }

    private void saveCookies() {
        String objUserName = userNameItem.getValueAsString();
        if (objUserName == null || objUserName.trim().equals("")) {
            return;
        }
        Date now = new Date();
        long nowLong = now.getTime();
        nowLong = nowLong + (1000 * 60 * 60 * 24 * 7);// seven days
        now.setTime(nowLong);
        Cookies.setCookie("MagticomUserName", userNameItem.getValueAsString(),
                now);

    }

    @Override
    public void destroy() {
        super.destroy();
        if (mainWindow != null)
            mainWindow.destroy();
    }
    LinkedHashMap mp;
    public void loginSuccess(String[] s22){
        CallCenter.callCenterInstance.resttime= clfunctions.str2int(s22[1].split("\t")[0],0);
        int kk=-1;
        //String ss="";
        if (CallCenter.callCenterInstance.mygrplink!=null){
          //  ss+="--"+CallCenter.callCenterInstance.mygrplink+"--\n";
            for (int i = 2; i < s22.length; i++) {
             //   ss+="=="+s22[i]+"==\n";
                if (s22[i].replace("\t","").equals(CallCenter.callCenterInstance.mygrplink)){
                    kk=i;break;
                }
            }
        }
        int kinfo=-1;
        for (int i = 2; i < s22.length; i++) {
            if (s22[i].replace("\t","").equals("info")){
                kinfo=i;break;
            }
        }

        if (kk>=0) { CallCenter.callCenterInstance.loginSuccess2(s22[kk]);}
        else if (s22.length==3) {
            CallCenter.callCenterInstance.loginSuccess2(s22[2]);
        }else
        if (s22.length==4&&kinfo>=0) {
            if (kinfo==2) CallCenter.callCenterInstance.loginSuccess2(s22[3]);
            else CallCenter.callCenterInstance.loginSuccess2(s22[2]);
        }
        else {
            setHeight(200);
            buttonItem2.setVisible(true);
            grp.setVisible(true);
            mp = new LinkedHashMap();
            int k1=-1;
            for (int i = 2; i < s22.length; i++) {
                //String[] s222=s22[i].replace("null","").split("\t");
                //log.operat, log.provider, log2.problems, log2.info, log.call_start,log.callid
                if(i!=kinfo){
                    mp.put(s22[i], s22[i]);
                    if (k1<0) k1=i;
                }

            }
            grp.setValueMap(mp);
            grp.setValue(mp.get(s22[k1]));
            form.redraw();
        }
    }
    public void setcss(String css,boolean refr){
        //private DynamicForm form;
       // userNameItem.setTextBoxStyle(userNameItem.getTextBoxStyle()+" myfont1");
       // userNameItem.setTitleStyle(userNameItem.getTitleStyle()+" myfont1");

       // passwordItem.setTextBoxStyle(passwordItem.getTextBoxStyle()+" myfont1");
       // buttonItem.setBaseStyle("button myfont1");
       // buttonItem2.setBaseStyle("button myfont1");
       // grp.setTextBoxStyle(grp.getTextBoxStyle()+" myfont1");
       // grp.setPickListBaseStyle(grp.getPickListBaseStyle()+" myfont1");

        form.setStyleName(form.getStyleName()+" myfont1");
    }
}
package ge.magti.client.layout;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.PopupPanel;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.RichTextEditor;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import ge.magti.client.CallCenter;
import ge.magti.client.MyWidgets.MyIButton;
import ge.magti.client.MyWidgets.MySelectItem;
import ge.magti.client.MyWidgets.MyTextAreaItem;
import ge.magti.client.MyWidgets.MyTextItem;


import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by user on 3/17/17.
 */
public class Chat extends SectionStackSection {
    final MyTextAreaItem txt = new MyTextAreaItem("",CallCenter.style);
    final DynamicForm txtform = new DynamicForm();
    MyIButton sendbutton = new MyIButton();
    MyIButton okbutton = new MyIButton();
    MyIButton smsbutton = new MyIButton();

    MySelectItem ops = new MySelectItem("ops",CallCenter.style);
    final DynamicForm opsform = new DynamicForm();

    MyIButton gopsbutton = new MyIButton();

    //final RichTextEditor lenta = new RichTextEditor();

    final HTMLPane lenta = new HTMLPane();

    MyTextItem number = new MyTextItem("",CallCenter.style);
    final DynamicForm numberform = new DynamicForm();

    MyIButton shablonbutton = new MyIButton();

    MySelectItem shablon1 = new MySelectItem("shablon1",CallCenter.style);
    final DynamicForm shablon1form = new DynamicForm();

    MySelectItem shablon2 = new MySelectItem("shablon2",CallCenter.style);
    final DynamicForm shablon2form = new DynamicForm();

    Chat(){
/*
        IButton but1 = new IButton("111");IButton but2 = new IButton("222");
        but1.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                Layout lay=CallCenter.callCenterInstance.getlayout("oldrep");
                CallCenter.callCenterInstance.setsouthLayout(lay);
                //lay.setVisible(true);
            }
        });
        but2.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                Layout lay=CallCenter.callCenterInstance.getlayout("mainarea");
                lay.setVisible(true);
                CallCenter.callCenterInstance.setsouthLayout(lay);
            }
        });
        addItem(but1);addItem(but2);
*/
        setTitle("Chat");

        txt.setShowTitle(false);
        txt.setColSpan(2);
        txt.setWidth("100%");txt.setHeight("100%");
        txtform.setWidth100();
        txtform.setHeight(100);
        txtform.setTitleOrientation(TitleOrientation.TOP);
        txtform.setWidth100();

        txtform.setFields(txt);
        addItem(txtform);


        final HLayout HLayout1 = new HLayout();HLayout1.setWidth100();HLayout1.setHeight(25);
        final HLayout HLayout2 = new HLayout();HLayout2.setWidth100();HLayout2.setHeight(25);
        final HLayout HLayout3 = new HLayout();HLayout3.setWidth100();HLayout3.setHeight(25);
        final HLayout HLayout4 = new HLayout();HLayout4.setWidth100();HLayout4.setHeight(25);
        final HLayout HLayout5 = new HLayout();HLayout5.setWidth100();HLayout5.setHeight(25);

        sendbutton = new MyIButton("SEND",CallCenter.style,"sendbutton");sendbutton.setHeight100();sendbutton.setWidth("70%");
        sendbutton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                if (ops.getValue()!=null&&!ops.getValue().toString().equals("")) {
                    String ss="<span style='color:red'>"+mp.get(ops.getValue())+"<<< </span>"+txt.getValue().toString();
                    lentasetValue(ss);
                  //  String s1=ss+"<br>"+lenta.getValue();
                  //  lenta.setValue(s1.substring(0,5000));
                    CallCenter.callCenterInstance.sendgreet("sendmessage\t" +
                            CallCenter.callCenterInstance.mynumber + "\t" + CallCenter.callCenterInstance.uname +
                            "\t" + ops.getValue().toString() + "\n" + txt.getValue().toString());
                    txt.setValue("");
                }
            }
        });
        okbutton = new MyIButton("OK",CallCenter.style,"okbutton");okbutton.setHeight100();okbutton.setWidth("30%");
        okbutton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                if (ops.getValue()!=null&&!ops.getValue().toString().equals("")) {
                    String ss="<span style='color:red'>"+mp.get(ops.getValue())+"<<< </span>ok";
                    lentasetValue(ss);
                    //String s1=ss+"<br>"+lenta.getValue();
                    //lenta.setValue(s1.substring(0,5000));
                    CallCenter.callCenterInstance.sendgreet("sendmessage\t" +
                            CallCenter.callCenterInstance.mynumber + "\t" + CallCenter.callCenterInstance.uname +
                            "\t" + ops.getValue().toString() + "\nok");
                }
            }
        });
        ops.setShowTitle(false);
        ops.setColSpan(2);
        ops.setWidth("100%");ops.setHeight("100%");
        opsform.setWidth("80%");
        opsform.setHeight100();
        opsform.setTitleOrientation(TitleOrientation.TOP);
        opsform.setWidth100();

        opsform.setFields(ops);

        gopsbutton = new MyIButton("",CallCenter.style,"gopsbutton");gopsbutton.setHeight100();gopsbutton.setWidth("20%");
        gopsbutton.setIcon("users.png");
        gopsbutton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                if (CallCenter.callCenterInstance.mynumber==null) SC.say("nulllll");

                CallCenter.callCenterInstance.sendgreet("getops\t"+CallCenter.callCenterInstance.mynumber+"\t");
            }
        });
        HLayout1.addMember(sendbutton);HLayout1.addMember(okbutton);
        this.addItem(HLayout1);

        HLayout2.addMember(opsform);HLayout2.addMember(gopsbutton);
        this.addItem(HLayout2);





        smsbutton = new MyIButton("sms",CallCenter.style,"smsbutton");smsbutton.setHeight100();smsbutton.setWidth("30%");
        smsbutton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                if (number.getValue()!=null&&number.getValue().toString().length()>2) {
                    String ss="<span style='color:red'>"+number.getValue().toString()+"<<< </span>"+txt.getValue().toString();
                    lentasetValue(ss);
                    //String s1=ss+"<br>"+lenta.getValue();
                    //lenta.setValue(s1.substring(0,5000));
                    CallCenter.callCenterInstance.sendgreet("sendsms\t" +
                            CallCenter.callCenterInstance.mynumber + "\t" + CallCenter.callCenterInstance.uname +
                            "\t" + number.getValue().toString() + "\n"+txt.getValue().toString());
                    txt.setValue("");
                }


            }
        });

        number.setShowTitle(false);
        number.setColSpan(2);
        number.setWidth("100%");number.setHeight("100%");
        numberform.setWidth100();
        numberform.setHeight100();
        numberform.setTitleOrientation(TitleOrientation.TOP);
        numberform.setWidth("80%");
        numberform.setFields(number);


        HLayout3.addMember(smsbutton);HLayout3.addMember(numberform);
        this.addItem(HLayout3);



        shablonbutton = new MyIButton("pattern",CallCenter.style,"shablonbutton");shablonbutton.setHeight100();shablonbutton.setWidth("35%");
        shablonbutton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                txt.setValue(shablon2.getValue());
            }
        });

        shablon1.setShowTitle(false);
        shablon1.setColSpan(2);
        shablon1.setWidth("100%");shablon1.setHeight("100%");
        shablon1form.setWidth100();
        shablon1form.setHeight100();
        shablon1form.setTitleOrientation(TitleOrientation.TOP);
        shablon1form.setWidth("80%");
        shablon1form.setFields(shablon1);
        shablon1.addChangeHandler(new ChangeHandler() {
            public void onChange(ChangeEvent event) {
                //    txt.setValue(shablon.getValueAsString()+"="+shablon.getValue()+"="+shablon.getDisplayField()
                //  +"="+event.getValue());
                setsmssh(event.getValue().toString());
            }
        });

        HLayout4.addMember(shablonbutton);HLayout4.addMember(shablon1form);
        this.addItem(HLayout4);

        shablon2.setShowTitle(false);
        shablon2.setColSpan(2);
        shablon2.setWidth("100%");shablon2.setHeight("100%");
        shablon2form.setWidth100();
        shablon2form.setHeight100();
        shablon2form.setTitleOrientation(TitleOrientation.TOP);
        shablon2form.setWidth("80%");
        shablon2form.setFields(shablon2);


        HLayout5.addMember(shablon2form);
        this.addItem(HLayout5);


        lenta.setWidth("100%");lenta.setHeight("100%");
        //lenta.setControlGroups(new String[]{});

        addItem(lenta);

lenta.setContents("");
        //lenta.setValue("");

    }
    LinkedHashMap<String,String> mp;
    public void getops(String result){
        String[] s2=result.split("\n");
        mp=new LinkedHashMap();
        for (int i=1;i<s2.length;i++){
            String[] s22=s2[i].split("\t");

            mp.put(s22[1],s22[0]);

        }
        ops.setValueMap(mp);
    }
    public class DelayedPopup extends PopupPanel {

        public DelayedPopup(String text, boolean autoHide, boolean modal) {
            super(autoHide, modal);
            setWidget(new Label(text));
        }

        void show(int delayMilliseconds) {
            show();
            DOM.setIntStyleAttribute(getElement(), "zIndex", 1000000);
        }
    }
    public void getmessage(String result){
            String[] s2= result.split("\n");
            String[] s22=s2[0].split("\t");

            String ss = "<span style='color:red'>"+s22[1]+">>> </span>";
            for (int i=1;i<s2.length;i++) {
                if (i==1) ss+=s2[i];
                else ss+="<br>"+s2[i];
            }
            lentasetValue(ss);
            //String s1 = ss  + lenta.getValue(); //SC.say(s1.replace("<",".").replace(">",","));
            //lenta.setValue(s1.substring(0, 5000));
            this.setExpanded(true);
        //SC.say(ss);
       /* final com.smartgwt.client.widgets.Window window = new com.smartgwt.client.widgets.Window();
        com.smartgwt.client.widgets.Label label
    		= new com.smartgwt.client.widgets.Label(
    			"<b>Window test</b><br>This window is visible since we have an IFrame (shim) between the Window and the Google Earth Plugin.");
	     label.setHeight100();
	     label.setPadding(5);
	     label.setValign(com.smartgwt.client.types.VerticalAlignment.TOP);
		 window.setTitle("Shim test");
         window.setWidth(300);
         window.setHeight(100);
         window.setCanDragReposition(false);
         window.setCanDragResize(false);
         window.setShowMinimizeButton(false);
         window.setShowMaximizeButton(false);
         window.addItem(label);
         window.show();
         window.setZIndex(1000000);
         window.centerInPage();


        window.bringToFront();
        window.focus();*/
//       if (!CallCenter.callCenterInstance.isvisible())
            newwin(ss);

    }//window.open('','winName','location=0,width=300,height=214'); myWin.focus()

    public native void newwincenter(String ss) /*-{

    var w=300;
    var h=200;

        var dualScreenLeft = window.screenLeft != undefined ? window.screenLeft : screen.left;
        var dualScreenTop = window.screenTop != undefined ? window.screenTop : screen.top;

        var width = window.innerWidth ? window.innerWidth : document.documentElement.clientWidth ? document.documentElement.clientWidth : screen.width;
        var height = window.innerHeight ? window.innerHeight : document.documentElement.clientHeight ? document.documentElement.clientHeight : screen.height;

        var left = ((width / 2) - (w / 2)) + dualScreenLeft;
        var top = ((height / 2) - (h / 2)) + dualScreenTop;




        var myWin = window.open('about:blank','Message','scrollbars=yes, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left);
             myWin.document.write(ss);

   //     var div = myWin.document.createElement('div'),
    //        body = myWin.document.body;

      //  div.innerHTML = ss
      //  div.style.fontSize = '30px'

        // вставить первым элементом в body нового окна
     //   body.insertBefore(div, body.firstChild);

        myWin.focus();

    }-*/;

    public native void newwin(String ss) /*-{

        var w=300;
        var h=200;




        var myWin = window.open('about:blank','Message','scrollbars=yes, width=' + w + ', height=' + h);
             myWin.document.write(ss);

  //      var div = myWin.document.createElement('div'),
  //          body = myWin.document.body;

//        div.innerHTML = ss
  //      div.style.fontSize = '30px'

        // вставить первым элементом в body нового окна
    //    body.insertBefore(div, body.firstChild);

        myWin.focus();
        setTimeout(function() {
            myWin.close();
        }, 5000);

    }-*/;

    String[] ssh=null;
    public void setchatsh(String sh0){
        ssh=sh0.split("\n");
        LinkedHashMap<String,String> mp1=new LinkedHashMap();
        String ss="";
        for (int i=0;i<ssh.length;i++)
               if (!ssh[i].startsWith("\t")&&!ssh[i].equals("")){
                  mp1.put(ssh[i],ssh[i]);
                  if (ss.equals("")) ss=ssh[i];
               }
         shablon1.setValueMap(mp1);
        shablon1.setValue(mp1.get(ss));
        setsmssh(ss);
    }
    void setsmssh(String st){
        LinkedHashMap<String,String> mp2=new LinkedHashMap();
        boolean da=false;String ss="";
//           System.out.println("----------------------------------------------------"+st+"========================"+ssh.length);
        for (int i=0;i<ssh.length;i++){
            //System.out.println(""+i+"=="+ssh[i]);
            if (da&&!ssh[i].startsWith("\t")&&!ssh[i].equals("")) break;
            if (ssh[i].equals(st)) da=true;
            else if (da){
                String s1=ssh[i].replace("\t", "");
                mp2.put(s1,s1); if (ss.equals("")) ss=s1;
            }
        }
        shablon2.setValueMap(mp2);
        shablon2.setValue(mp2.get(ss));
    }
    public void setsmsnumber(String smsnumber){
        number.setValue(smsnumber);
    }

    ArrayList<String> lenta2=new ArrayList<String>();
    public void lentasetValue(String ss){
        //<font size="4"><span style="background-color: rgb(255, 255, 255);">
        if (lenta2.size()>100) lenta2.remove(lenta2.size()-1);
        lenta2.add(0,ss);
        boolean lentada=true;
        StringBuffer sb=new StringBuffer("");
        sb.append("<table>");
        for (int i=0;i<lenta2.size();i++){
            String s1;
            if (i==0) {
                if (lentada) s1="<tr><td><font size=\"5\">"+lenta2.get(i)+"</font></td></tr>";
                else s1="<tr style=\"background-color: rgb(245, 245, 245);\"><td><font size=\"5\">"+lenta2.get(i)+"</font></td></tr>";
            }
            else{
                if (lentada) s1="<tr><td><font size=\"4\">"+lenta2.get(i)+"</font></td></tr>";
                else s1="<tr style=\"background-color: rgb(245, 245, 245);\"><td><font size=\"4\">"+lenta2.get(i)+"</font></td></tr>";

            }
            sb.append(s1+"\n");
            lentada=!lentada;
        }

    //    String s1;
    //    if (lentada) s1="<span style=\"background-color: rgb(204, 255, 255);\"><font size=\"5\">"+ss+"</font></span><br>"+lenta.getContents().replace("<font size=\"5\">","<font size=\"4\">");
    //    else s1="<font size=\"5\">"+ss+"</font><br>"+lenta.getContents().replace("<font size=\"5\">","<font size=\"4\">");


      //  lenta.setContents(s1.substring(0,5000));
        lenta.setContents(sb.toString());
    }

}

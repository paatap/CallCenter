package ge.magti.client.layout;

import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import ge.magti.client.CallCenter;
import ge.magti.client.MyWidgets.MyListGrid;
import ge.magti.client.MyWidgets.MyListGrid2;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by user on 3/28/17.
 */
public class ReportAreaSinfo extends HLayout {
    MyListGrid2 ops1;
    MyListGrid2 ops2;
    MyListGrid info;
    int infom=-1;
    public ReportAreaSinfo() {

        super();

        this.setShowEdges(true);

         ops1=new MyListGrid2(CallCenter.style);





//        ss.append(ses.grp+"\t" + functions.getstatus(ses.status)+"\t"+"time"+"\t"+
  //              ses.uname+"\t"+entr.getKey()+"\t" +ses.anumber+"\t" +ses.callid+"\n");
        ListGridField grpField = new ListGridField("group", "group");
        ListGridField statusField = new ListGridField("status", "status");
        ListGridField timeField = new ListGridField("time", "time");
        ListGridField unameField = new ListGridField("uname", "uname");
        ListGridField numberField = new ListGridField("number", "number");
        ListGridField anumberField = new ListGridField("anumber", "anumber");
        ListGridField callidField = new ListGridField("callid", "callid");
        ops1.setFields( grpField,statusField,timeField,unameField,numberField,anumberField,callidField);


         ops2=new MyListGrid2(CallCenter.style);ops2.setHeight("60%");

        ops2.setFields( grpField,statusField,timeField,unameField,numberField,anumberField,callidField);

         info=new MyListGrid(CallCenter.style);info.setHeight("40%");
        ListGridField info0 = new ListGridField("info0", "");
        ListGridField info1 = new ListGridField("info1", "");
        ListGridField info2 = new ListGridField("info2", "");
        ListGridField info3 = new ListGridField("info3", "");
        ListGridField info4 = new ListGridField("info4", "");
        ListGridField info5 = new ListGridField("info5", "");
        ListGridField info6 = new ListGridField("info6", "");
        ListGridField info7 = new ListGridField("info7", "");
        ListGridField info8 = new ListGridField("info8", "");
        ListGridField info9 = new ListGridField("info9", "");

        info.setFields(info0,info1,info2,info3,info4,info5,info6,info7,info8,info9);



        VLayout layout=new VLayout();
        layout.setMembers(info,ops2);
        this.setMembers(ops1,layout);
        getinfo();
        tt.scheduleRepeating(3000);
    }
    public com.google.gwt.user.client.Timer tt=new com.google.gwt.user.client.Timer() {


        public void run() {
        getinfo();

        }
    };
    void getinfo(){
        CallCenter.callCenterInstance.sendgreet("getserverinfo");
    }
    public void fromgreet(String ss){
        String[] s2=ss.split("\n");
        int typ=0;
        ops1.setData(new ListGridRecord[] {});
        ops2.setData(new ListGridRecord[] {});
        info.setData(new ListGridRecord[] {});
        boolean binf=true;

        String s2a="";

        for (int i=1;i<s2.length;i++){
            if (s2[i].equals("$stat")) {typ=1;continue;}
            String[] s22=s2[i].split("\t");
            if (typ==0){
                s2a+=s22[3]+"\t"+s2[i]+"\n";
            }else{
                if (binf){
                    int jm=s22.length;if (jm>10) jm=10;
                    if (infom!=jm){
                        for (int j=0;j<10;j++) {
                            if (j<jm)info.showField("info"+j);
                            else info.hideField("info"+j);
                        }
                        infom=jm;
                    }
                    for (int j=0;j<jm;j++) info.getField(j).setTitle(s22[j]);
                    binf=false;
                }else{
                    Record rr=new Record();
                    for (int j=0;j<infom;j++) rr.setAttribute("info"+j,s22[j]);
                    info.addData(rr);
                }
            }
        }

        String[] s2a2=s2a.split("\n");

        Arrays.sort(s2a2);

        for (int i=0;i<s2a2.length;i++){

            String[] s22=s2a2[i].split("\t");

                Record rr=new Record();
                rr.setAttribute("group",s22[1]);
                rr.setAttribute("status",s22[2]);
                rr.setAttribute("time",s22[3]);
                rr.setAttribute("uname",s22[4]);
                rr.setAttribute("number",s22[5]);
                rr.setAttribute("anumber",s22[6]);
                rr.setAttribute("callid",s22[7]);

                if (1000*i/s2a2.length>600) ops2.addData(rr);
                else ops1.addData(rr);
//        ss.append(ses.grp+"\t" + functions.getstatus(ses.status)+"\t"+"time"+"\t"+
  //              ses.uname+"\t"+entr.getKey()+"\t" +ses.anumber+"\t" +ses.callid+"\n");

        }
        info.refreshFields();
    }
}
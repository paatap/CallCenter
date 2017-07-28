package ge.magti.server;

import ge.magti.EchoServer;
import ge.magti.mysession;
import mypack.*;

import javax.xml.ws.Holder;
import java.util.List;

/**
 * Created by user on 7/19/17.
 */
public class gobs {
    static String myuser="Alexander.Sarchimelia@magticom.ge";
    static String mypass="Qwerty1";
    public static StringBuilder gettemplates(String user,String pass){
        StringBuilder sb=new StringBuilder("");
        try {
            Holder<Boolean> authenticateUserResult=
                    new Holder<Boolean>();
            Holder<String> errorMessage=
                    new Holder<String>();
            SDService service = new SDService();
            SDServiceSoap st = service.getSDServiceSoap();

            //        SDServiceSoap_BindingStub st = new SDServiceSoap_BindingStub();
            java.net.Authenticator myAuth = new java.net.Authenticator() {
                @Override
                protected java.net.PasswordAuthentication getPasswordAuthentication() {
                    return new java.net.PasswordAuthentication(myuser, mypass.toCharArray());
                }
            };
            java.net.Authenticator.setDefault(myAuth);



            st.authenticateUser(authenticateUserResult,errorMessage);

            List<SDTemplate> tt=st.getTemplates().getSDTemplate();
            sb.append("$gobstemplates\n");

            for (int i=0;i<tt.size();i++) sb.append(tt.get(i).getTemplateId()+"\t"+tt.get(i).getTemplateTitle()+"\t"+tt.get(i).getServiceId()+"\t"+tt.get(i).getMinCommentLength()+"\n");

            tt=st.getTemplatesCORPORATE().getSDTemplate();
            sb.append("$gobscorptemplates\n");
             for (int i=0;i<tt.size();i++) sb.append(tt.get(i).getTemplateId()+"\t"+tt.get(i).getTemplateTitle()+"\t"+tt.get(i).getServiceId()+"\t"+tt.get(i).getMinCommentLength()+"\n");

            //            System.out.println("temlates==============================!!!===="+tt.size());
            return sb;
        }catch(Exception e){
            e.printStackTrace();
            return sb.append("error");
        }
    }
    public static StringBuilder findServices(String input){
        StringBuilder sb=new StringBuilder("$mainarea.findServices\n");
        try {
            String[] s2=input.split("\n");
            SDService service = new SDService();
            SDServiceSoap st = service.getSDServiceSoap();

/*
            java.net.Authenticator myAuth = new java.net.Authenticator() {
                @Override
                protected java.net.PasswordAuthentication getPasswordAuthentication() {
                    return new java.net.PasswordAuthentication(s2[1], s2[2].toCharArray());
                }
            };
            java.net.Authenticator.setDefault(myAuth);
*/






 List<SDSvc> tt=st.findServices(s2[3],s2[4],s2[5],s2[6]).getSDSvc();//s.getUID()+" "+(s.getOrderFlag()==1?"(Order)":"")
sb.append("$Services\n");
            for (int i=0;i<tt.size();i++) sb.append(tt.get(i).getServiceName()+"\t"+tt.get(i).getCustomerStatus()+"\t"+tt.get(i).getUID()+" "+(tt.get(i).getOrderFlag()==1?"(Order)":"")+
                    "\t"+tt.get(i).getCustomerName()+"\t"+tt.get(i).getCsAddress()+"\t"+tt.get(i).getCSID()
                    +"\t"+tt.get(i).getUID()
                    +"\t"+tt.get(i).getServiceId()
                    +"\t"+tt.get(i).getCustomerPrivilegeType()
                    +"\t"+tt.get(i).getDeviceTroubleId()
                    +"\t"+tt.get(i).getLinkURL()
                    +"\n");
            if (!s2[3].equals("")) {
                sb.append("$CallHistory\n");
                List<SDCallHistory> tt2 = st.getCallHistory(s2[3], 10).getSDCallHistory();
                for (int i = 0; i < tt2.size(); i++)
                    sb.append(tt2.get(i).getCreationDate().toString() + "\t"
                            + tt2.get(i).isTransfer() + "\t"
                            + tt2.get(i).getCallerName() + "\t"
                            + tt2.get(i).getCallerComment() + "\t"
                            + tt2.get(i).getGobsUserName() + "\t"
                            + tt2.get(i).getTemplate() + "\t"
                            + "\n");
            }

//            System.out.println("temlates==============================!!!===="+tt.size());
            return sb;
        }catch(Exception e){
            e.printStackTrace();
            return sb.append("error");
        }
    }

    public static StringBuilder getSDMessageAD(String user0) {
          StringBuilder sb=new StringBuilder("it$is$getSDMessageAD\n");
        try {

            SDService service = new SDService();
            SDServiceSoap st = service.getSDServiceSoap();
            String user="MAGTICOM\\"+user0;
            user=user.substring(0,29);
            SDCall call=st.getSDMessageAD(user).getSdCall();
            if (call==null) return sb;

            sb.append("anum"+call.getANumber()+"\n");
            sb.append("bnum"+call.getBNumber()+"\n");
            sb.append("comm"+call.getCallerComment().replace("\n","\t")+"\n");
            sb.append("name"+call.getCallerName()+"\n");
            sb.append("cidd"+call.getCallId()+"\n");
            sb.append("stat"+call.getCallStatus()+"\n");
            sb.append("date"+call.getCreationDate()+"\n");
            sb.append("iddd"+call.getId()+"\n");
            sb.append("corp"+call.isCorporateCustomer()+"\n");
 sb.append("it$is$getCallHistory\n");
  List<SDCallHistory> tt2=st.getCallHistory(call.getANumber(),10).getSDCallHistory();
            for (int i=0;i<tt2.size();i++) sb.append(tt2.get(i).getCreationDate().toString()+"\t"
                    +tt2.get(i).isTransfer()+"\t"
                    +tt2.get(i).getCallerName()+"\t"
                    +tt2.get(i).getCallerComment()+"\t"
                    +tt2.get(i).getGobsUserName()+"\t"
                    +tt2.get(i).getTemplate()+"\t"
                    +"\n");
 sb.append("it$is$findServices\n");
List<SDSvc> tt=st.findServices(call.getANumber(),null,null,null).getSDSvc();
            for (int i=0;i<tt.size();i++) sb.append(tt.get(i).getServiceName()+"\t"+tt.get(i).getCustomerStatus()+"\t"+tt.get(i).getUID()+" "+(tt.get(i).getOrderFlag()==1?"(Order)":"")+
                    "\t"+tt.get(i).getCustomerName()+"\t"+tt.get(i).getCsAddress()+"\t"+tt.get(i).getCSID()
                    +"\t"+tt.get(i).getUID()
                    +"\t"+tt.get(i).getServiceId()
                    +"\t"+tt.get(i).getCustomerPrivilegeType()
                    +"\t"+tt.get(i).getDeviceTroubleId()
                    +"\t"+tt.get(i).getLinkURL()
                    +"\n");
          //  System.out.println(call.getANumber()+"*********"+sb.toString());
            return sb;
        }catch(Exception e){
            e.printStackTrace();
            return sb.append("error");
        }
    }
    public static StringBuilder sdCallProcess(String input) {
        StringBuilder sb=new StringBuilder("$mainarea.sdCallProcess\n");
        try {
            String[] s2=input.split("\n");
            SDService service = new SDService();
            SDServiceSoap st = service.getSDServiceSoap();
            int id=functions.str2int(s2[1],-1);
            int csid=functions.str2int(s2[2],-1);
            st.sdCallProcess(id, csid);

            return sb;
        }catch(Exception e){
            e.printStackTrace();
            return sb.append("error");
        }
    }
    public static StringBuilder gobssave(String input) {
        StringBuilder sb=new StringBuilder("$mainarea.gobssave\n");
        try {
            String[] s2=input.split("\n");
            SDService service = new SDService();
            SDServiceSoap st = service.getSDServiceSoap();

            int id=functions.str2int(s2[1],-1);
            boolean tr=functions.str2bool(s2[2]);
            Integer tid=functions.str2int(s2[3]);
            String comm=nullorspace2null(s2[4].replace("\t","\n"));
            String callername=nullorspace2null(s2[5]);
            String callercomm=nullorspace2null(s2[6]);
            Boolean potential=functions.str2Bool(s2[7]);
            String troubleid=nullorspace2null(s2[8]);
            Boolean is220=functions.str2Bool(s2[9]);
            Boolean iskey=functions.str2Bool(s2[10]);
            System.out.println("-------------"+input);
            System.out.println("==="+id+","+tr+","+tid+","+comm+","+callername+","+callercomm+","+potential+","+troubleid+","+is220+","+iskey+"===");
            st.sdCallSave(id,tr,tid,comm,callername,callercomm,potential,troubleid,is220,iskey);

            return sb;
        }catch(Exception e){
            e.printStackTrace();
            return sb.append("error");
        }
    }
    static String nullorspace2null(String ss){
        if (ss==null) return null;
        if (ss.equals("null")) return null;
        if (ss.equals("")) return null;
        return ss;
    }
}

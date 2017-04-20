package ge.magti.client.layout;

import com.google.gwt.dom.client.Style;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.widgets.RichTextEditor;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeNode;
import ge.magti.client.CallCenter;
import ge.magti.client.MyWidgets.*;

import java.util.ArrayList;

/**
 * Created by user on 4/6/17.
 */
public class myeksp extends VLayout {
    RichTextEditor rich = null;
    MyTextItem txt = null;
    MyTextAreaItem txt2 = null;
    MySelectItem shablon= null;
    MyIButton butt1= null;MyIButton butt11= null;
    MyIButton butt2= null;
    MyListGrid grid= null;
    MyListGridEx gridex= null;
    HLayout h1=null;
    HLayout h2=null;
    MyTreeGrid reportTreeGrid;
    MyLabel lab;
    String style="2";
    public void myredraw(){
        redraw();
    }
    public myeksp() {
        super();
        int kk=com.google.gwt.user.client.Random.nextInt();
        if (kk<0) kk=-kk;
        int i=kk % 3;
        if (i==0) style="";
        else style=""+i;

        init();
    }


    void init(){
if (h1!=null) {
   // butt1.destroy();
   // butt2.destroy();
   // butt11.destroy();
    h1.destroy();
    h2.destroy();

}
         h1=new HLayout();h1.setShowEdges(true);
         h2=new HLayout();h2.setShowEdges(true);
        rich = null;
     txt = new MyTextItem("kuku",style);
     txt2 = new MyTextAreaItem("kuku2",style);
     shablon=new MySelectItem("kuku333",style);
     butt1=new MyIButton("but1",style);//butt1.setmystyle(style);
butt1.setWidth(200);
/*
       if (style.equals("")) {
            butt1.setBaseStyle(butt1.getBaseStyle()+" mymybutton");
            //DOM.setStyleAttribute(butt1.getElement(),"backgroundColor", "#ff0000");
           // butt1.getElement().getStyle().setFontSize(11, Style.Unit.PT);
            butt1.getElement().setAttribute("backgroundColor", "#ff0000");
            //butt1.getElement().getStyle().setBackgroundColor("#ff0000");

        }
        else if (style.equals("1")) {
            butt1.setBaseStyle(butt1.getBaseStyle()+" mymybutton1");
           // butt1.getElement().getStyle().setFontSize(14, Style.Unit.PT);
            butt1.getElement().getStyle().setBackgroundColor("#00ff00");

        }
        else {
            butt1.setBaseStyle(butt1.getBaseStyle()+" mymybutton2");
          //  butt1.getElement().getStyle().setFontSize(22, Style.Unit.PT);
            butt1.getElement().getStyle().setBackgroundColor("#0000ff");

        }*/

butt1.setTitle("==="+butt1.getBaseStyle()+"="+style);

        butt11=new MyIButton("but1",style);//butt11.setWidth(200);
        butt11.setTitle(butt11.getBaseStyle()+" mymybutton"+style);
        //butt11.setBaseStyle(butt11.getBaseStyle()+" mymyfont"+style);


      //  butt11.setBaseStyle("mymyfont"+style);

     butt2=new MyIButton("but2",style);//butt2.setmystyle(style);
     grid=new MyListGrid(style);
     gridex=new MyListGridEx("problem",style);
        //this.setShowEdges(true);



       // shablon.setShowTitle(false);
        shablon.setAlign(Alignment.CENTER);
        shablon.setValueMap("Magti", "Bali", "Bani", "Fix", "Evdo", "Satellite", "Telemarket", "Samt", "DTH");
        DynamicForm form=new DynamicForm();

       // init2();
 lab= new MyLabel("",style);
 lab.setContents("Contents=="+style);
        form.setFields(shablon,txt,txt2);









        String ajaxDefinition ="rgvgrsg=="+style;


        rich = new RichTextEditor();
        rich.setValue(ajaxDefinition);
        //richTextEditor.setShowEdges(true);
        rich.setWidth(222);rich.setHeight(222);
        rich.setOverflow(Overflow.HIDDEN);
        rich.setCanDragResize(true);
        rich.setShowEdges(true);




        butt1.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                style="1";
                init();
            }
        });
        butt2.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                style="2";
                init();
            }
        });
        butt11.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                style="";
                init();
            }
        });
lab.setWidth(100);
lab.setHeight(100);

VLayout v1=new VLayout();
MyIButton rbut1=new MyIButton();
rbut1.addClickHandler(new ClickHandler() {
    @Override
    public void onClick(ClickEvent clickEvent) {
        txt2.setValue(rich.getValue().toString());
    }
});
MyIButton rbut2=new MyIButton();
rbut2.addClickHandler(new ClickHandler() {
    @Override
    public void onClick(ClickEvent clickEvent) {
        String ss=txt2.getValue().toString();
        String[] s2=ss.split("\n");
        StringBuffer sb=new StringBuffer("");
        for (int i=0;i<s2.length;i++){
            sb.append("\n"+s2[i]);
        }
        rich.setValue(sb.toString());
    }
});

v1.setMembers(rich,rbut1,rbut2);

        h1.setMembers(lab,butt1,butt11,form,v1);
        h2.setMembers(butt2);


        ListGridField problemField = new ListGridField("aaa", "aaa");
        ListGridField problemField1 = new ListGridField("myid", "myid");
        problemField1.setHidden(true);

        grid.setFields( problemField,problemField1);

        for (int i=0;i<10;i++){
            Record rr=new Record();
            rr.setAttribute("aaa","kuku="+grid.getBaseStyle()+i);
            rr.setAttribute("myid","myid"+i);
            grid.addData(rr);
        }


        ListGridField problemFieldex = new ListGridField("problem", "problem");
        ListGridField problemFieldex1 = new ListGridField("myid", "myid");
        problemFieldex1.setHidden(true);

        gridex.grid.setFields( problemFieldex,problemFieldex1);

        for (int i=0;i<10;i++){
            Record rr=new Record();
            rr.setAttribute("problem","kuku"+i);
            rr.setAttribute("myid","myid"+i);
            gridex.grid.addData(rr);
        }


         reportTreeGrid = new MyTreeGrid("",style);

        reportTreeGrid.setWidth(200);
        reportTreeGrid.setHeight(200);
        reportTreeGrid.setShowHeader(false);
//        employeeTreeGrid.setNodeIcon("icons/16/person.png");
//        employeeTreeGrid.setFolderIcon("icons/16/person.png");
        //reportTreeGrid.setShowOpenIcons(false);
        //reportTreeGrid.setShowDropIcons(false);
        reportTreeGrid.setClosedIconSuffix("");
        //reportTreeGrid.setData(reportTree);
        reportTreeGrid.setShowRoot(false);
//        employeeTreeGrid.setSelectionAppearance(SelectionAppearance.CHECKBOX);
        reportTreeGrid.setShowSelectedStyle(false);
//        employeeTreeGrid.setShowPartialSelection(true);
//        employeeTreeGrid.setCascadeSelection(true);

  /*      reportTreeGrid.addDrawHandler(new DrawHandler() {
            public void onDraw(DrawEvent event) {
                reportTreeGrid.getTree().openAll();
            }
        });*/

/*
section1.getSectionHeader().addClickHandler(new ClickHandler() {
    @Override
    public void onClick(ClickEvent clickEvent) {

            CallCenter.callCenterInstance.maincc.setVisible(true);
            CallCenter.callCenterInstance.reportcc.setVisible(false);
    }
});
*/
        settree("");
        h2.addMember(reportTreeGrid);
        h2.addMember(grid);h2.addMember(gridex);
        this.setMembers(h1,h2);

    }



    public static class reportTreeNode extends TreeNode {
        public reportTreeNode(String myid, String idto, String name,String title, boolean isOpen,String dat) {
            setAttribute("myid", myid);
            setAttribute("idto", idto);
            setAttribute("name", name);
            setAttribute("title", title);
            setAttribute("isOpen", isOpen);
            setAttribute("dat", dat);
        }
    }
    public void settree(String ss) {
        Tree reportTree = new Tree();
        reportTree.setModelType(TreeModelType.PARENT);
        reportTree.setRootValue(1);
        reportTree.setNameProperty("name");
        reportTree.setIdField("myid");
        reportTree.setParentIdField("idto");
        reportTree.setOpenProperty("isOpen");
        reportTree.setTitleProperty("title");
        reportTree.setData(getreportData(ss));
        reportTreeGrid.setData(reportTree);
    }

    public static TreeNode[] getreportData(String ss) {

        ArrayList<TreeNode> rd=new ArrayList<TreeNode>();

        for (int i=0;i<10;i++){

            rd.add(new NavigationArea.reportTreeNode(""+(i+2), "1", "name"+i, "title"+i,
                    true,"dat"+i));
        }


        //SC.say(ss22);
        Object[] ob2=rd.toArray();

        TreeNode[] reportData=new TreeNode[ob2.length];
        for (int i=0;i<ob2.length;i++) reportData[i]=(TreeNode)ob2[i];

        return reportData;
    }
















    void init2(){

      //  butt1.setmystyle(style);butt2.setmystyle(style);butt11.setmystyle(style);

        if (style.equals("")) {
            butt1.setBaseStyle(butt1.getBaseStyle()+" mymybutton");
            //DOM.setStyleAttribute(butt1.getElement(),"backgroundColor", "#ff0000");
            // butt1.getElement().getStyle().setFontSize(11, Style.Unit.PT);
            butt1.getElement().setAttribute("backgroundColor", "#ff0000");
            //butt1.getElement().getStyle().setBackgroundColor("#ff0000");

        }
        else if (style.equals("1")) {
            butt1.setBaseStyle(butt1.getBaseStyle()+" mymybutton1");
            // butt1.getElement().getStyle().setFontSize(14, Style.Unit.PT);
            butt1.getElement().getStyle().setBackgroundColor("#00ff00");

        }
        else {
            butt1.setBaseStyle(butt1.getBaseStyle()+" mymybutton2");
            //  butt1.getElement().getStyle().setFontSize(22, Style.Unit.PT);
            butt1.getElement().getStyle().setBackgroundColor("#0000ff");

        }



        if (style.equals("")) {

         //   shablon.getContainerWidget().getElement().getStyle().setFontSize(11, Style.Unit.PT);
         //   shablon.getPickListPlacementAsCanvas().getElement().getStyle().setFontSize(11, Style.Unit.PT);
            butt1.getElement().getStyle().setFontSize(11, Style.Unit.PT);
            butt2.getElement().getStyle().setFontSize(11, Style.Unit.PT);
            butt11.getElement().getStyle().setFontSize(11, Style.Unit.PT);
        }
        else if (style.equals("1")) {
        //    shablon.getContainerWidget().getElement().getStyle().setFontSize(14, Style.Unit.PT);
         //   shablon.getPickListPlacementAsCanvas().getElement().getStyle().setFontSize(14, Style.Unit.PT);
            butt1.getElement().getStyle().setFontSize(14, Style.Unit.PT);
            butt2.getElement().getStyle().setFontSize(14, Style.Unit.PT);
            butt11.getElement().getStyle().setFontSize(14, Style.Unit.PT);
        }else{
         //   shablon.getContainerWidget().getElement().getStyle().setFontSize(22, Style.Unit.PT);
         //   shablon.getPickListPlacementAsCanvas().getElement().getStyle().setFontSize(22, Style.Unit.PT);
            butt1.getElement().getStyle().setFontSize(22, Style.Unit.PT);
            butt2.getElement().getStyle().setFontSize(22, Style.Unit.PT);
            butt11.getElement().getStyle().setFontSize(22, Style.Unit.PT);
        }
        txt.setValue("=style="+style);
    }
}

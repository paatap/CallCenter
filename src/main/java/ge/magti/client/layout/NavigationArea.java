package ge.magti.client.layout;

import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.DrawEvent;
import com.smartgwt.client.widgets.events.DrawHandler;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.events.SectionHeaderClickEvent;
import com.smartgwt.client.widgets.layout.events.SectionHeaderClickHandler;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeGridField;
import com.smartgwt.client.widgets.tree.TreeNode;
import com.smartgwt.client.widgets.tree.events.NodeClickEvent;
import com.smartgwt.client.widgets.tree.events.NodeClickHandler;
import ge.magti.client.CallCenter;


import java.util.ArrayList;
import java.util.List;


public class NavigationArea extends HLayout {
    public Chat chat;
 //   Tree reportTree = new Tree();
    TreeGrid reportTreeGrid = new TreeGrid();
    public NavigationArea() {

        super();

        this.setMembersMargin(20);
        this.setOverflow(Overflow.HIDDEN);
        this.setShowResizeBar(true);

        final SectionStack sectionStack = new SectionStack();
        sectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);
        sectionStack.setShowExpandControls(true);
        sectionStack.setAnimateSections(true);
        sectionStack.setVisibilityMode(VisibilityMode.MUTEX);
        sectionStack.setOverflow(Overflow.HIDDEN);

        SectionStackSection section1 = new SectionStackSection("Call Center");//Registration
        section1.setExpanded(true);

        //section1.setCanCollapse(false);
        /*Label label1 = new Label();
        label1.setContents("In/Out Call");label1.setHeight(0);
        section1.addItem(label1);*/

        SectionStackSection section2 = new SectionStackSection("Reporting");
        section2.setExpanded(true);
      /*  Label label2 = new Label();
        label2.setContents("Restricted");
        label2.setOverflow(Overflow.AUTO);
        label2.setPadding(10);
        section2.addItem(label2);*/




  /*      reportTree.setModelType(TreeModelType.PARENT);
        reportTree.setRootValue(1);
        reportTree.setNameProperty("name");
        reportTree.setIdField("myid");
        reportTree.setParentIdField("idto");
        reportTree.setOpenProperty("isOpen");
        reportTree.setTitleProperty("title");*/
        //reportTree.setData(getreportData(CallCenter.callCenterInstance.optype));



        reportTreeGrid.setWidth100();
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
reportTreeGrid.addNodeClickHandler(new NodeClickHandler() {
    @Override
    public void onNodeClick(NodeClickEvent nodeClickEvent) {
      //  SC.say(nodeClickEvent.getNode().getAttribute("dat").toString());

        CallCenter.callCenterInstance.setvisiblearea(nodeClickEvent.getNode().getName(),
                nodeClickEvent.getNode().getAttribute("dat").toString());

    }
});
/*
section1.getSectionHeader().addClickHandler(new ClickHandler() {
    @Override
    public void onClick(ClickEvent clickEvent) {

            CallCenter.callCenterInstance.maincc.setVisible(true);
            CallCenter.callCenterInstance.reportcc.setVisible(false);
    }
});
*/
section2.addItem(reportTreeGrid);



        chat=new Chat();
        chat.setExpanded(true);
    /*    SectionStackSection section3 = new SectionStackSection("Chat");
        section3.setExpanded(false);
        Label label3 = new Label();
        label3.setContents("Restricted");
        label3.setOverflow(Overflow.AUTO);
        label3.setPadding(10);
        section3.addItem(label3);*/

        sectionStack.addSection(section1);
        sectionStack.addSection(section2);
        sectionStack.addSection(chat);
        sectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);

        sectionStack.addSectionHeaderClickHandler(new SectionHeaderClickHandler() {
            @Override
            public void onSectionHeaderClick(SectionHeaderClickEvent sectionHeaderClickEvent) {
 //               ((MainArea)CallCenter.callCenterInstance.maincc).txt.setValue("aa"+sectionHeaderClickEvent.getSection().getTitle());
               if (sectionHeaderClickEvent.getSection().getTitle().equals("Call Center")){
                   CallCenter.callCenterInstance.setvisiblearea("mainarea","mainarea");
                     //CallCenter.callCenterInstance.maincc.setVisible(true);
                     //CallCenter.callCenterInstance.reportcc.setVisible(false);
               }
            }
        });

        this.addMember(sectionStack);

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

        String[] s2=ss.split("\n");
        ArrayList<TreeNode> rd=new ArrayList<TreeNode>();
        String ss22="";
        for (int i=0;i<s2.length;i++){
            String[] s22=s2[i].split("\t");

            ss22+=s22[0]+s22[1]+"\n";
            rd.add(new reportTreeNode(""+(i+2), "1", s22[0], s22[1], true,s22[2]));
        }


        //SC.say(ss22);
        Object[] ob2=rd.toArray();

        TreeNode[] reportData=new TreeNode[ob2.length];
        for (int i=0;i<ob2.length;i++) reportData[i]=(TreeNode)ob2[i];

        return reportData;
    }
    /*
    public static TreeNode[] getreportData2(int optype){
        ArrayList<TreeNode> rd=new ArrayList<TreeNode>();
        if (optype>0) rd.add(new reportTreeNode("2", "1", "rep1", "find ring", true));
        if (optype>0) rd.add(new reportTreeNode("5", "1", "rep4", "server info", true));
        if (optype>-2) rd.add(new reportTreeNode("3", "1", "rep2", "server info2", true));
        if (optype>0) rd.add(new reportTreeNode("4", "1", "rep3", "old rep", true));
        if (optype>0) rd.add(new reportTreeNode("6", "1", "rep5", "admin", true));


        Object[] ob2=rd.toArray();

        TreeNode[] reportData=new TreeNode[ob2.length];
        for (int i=0;i<ob2.length;i++) reportData[i]=(TreeNode)ob2[i];

        return reportData;
    }*/
    /*
    public static final TreeNode[] reportData11 = new TreeNode[] {
            new reportTreeNode("4", "1", "rep1", "find ring", true),
            new reportTreeNode("189", "1", "rep2", "server info", true),
            new reportTreeNode("265", "1", "rep3", "old rep", true)
     //       ,new reportTreeNode("264", "1", "rep4", "rep 4", true)
     //       ,new reportTreeNode("188", "1", "rep5", "rep 5", true)
    };
    */
}
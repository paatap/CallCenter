package ge.magti.client.layout;

import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.types.VisibilityMode;
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
import jdk.nashorn.internal.codegen.CompilerConstants;


public class NavigationArea extends HLayout {
    public Chat chat;
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

        SectionStackSection section1 = new SectionStackSection("Call Registration");
        section1.setExpanded(true);

        //section1.setCanCollapse(false);
        /*Label label1 = new Label();
        label1.setContents("In/Out Call");label1.setHeight(0);
        section1.addItem(label1);*/

        SectionStackSection section2 = new SectionStackSection("Reporting");
        section2.setExpanded(false);
      /*  Label label2 = new Label();
        label2.setContents("Restricted");
        label2.setOverflow(Overflow.AUTO);
        label2.setPadding(10);
        section2.addItem(label2);*/



        Tree reportTree = new Tree();
        reportTree.setModelType(TreeModelType.PARENT);
        reportTree.setRootValue(1);
        reportTree.setNameProperty("name");
        reportTree.setIdField("myid");
        reportTree.setParentIdField("idto");
        reportTree.setOpenProperty("isOpen");
        reportTree.setTitleProperty("title");
        reportTree.setData(reportData);


        final TreeGrid reportTreeGrid = new TreeGrid();
        reportTreeGrid.setWidth100();
        reportTreeGrid.setHeight(200);
        reportTreeGrid.setShowHeader(false);
//        employeeTreeGrid.setNodeIcon("icons/16/person.png");
//        employeeTreeGrid.setFolderIcon("icons/16/person.png");
        //reportTreeGrid.setShowOpenIcons(false);
        //reportTreeGrid.setShowDropIcons(false);
        reportTreeGrid.setClosedIconSuffix("");
        reportTreeGrid.setData(reportTree);
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
        if (nodeClickEvent.getNode().getName().equals("rep1")){
            CallCenter.callCenterInstance.setvisiblearea(CallCenter.reportarea);
            //CallCenter.callCenterInstance.maincc.setVisible(false);
            //CallCenter.callCenterInstance.reportcc.setVisible(true);
        }

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


        sectionStack.addSectionHeaderClickHandler(new SectionHeaderClickHandler() {
            @Override
            public void onSectionHeaderClick(SectionHeaderClickEvent sectionHeaderClickEvent) {
 //               ((MainArea)CallCenter.callCenterInstance.maincc).txt.setValue("aa"+sectionHeaderClickEvent.getSection().getTitle());
               if (sectionHeaderClickEvent.getSection().getTitle().equals("Call Registration")){
                   CallCenter.callCenterInstance.setvisiblearea(CallCenter.mainarea);
                     //CallCenter.callCenterInstance.maincc.setVisible(true);
                     //CallCenter.callCenterInstance.reportcc.setVisible(false);
               }

            }
        });

        this.addMember(sectionStack);

    }
    public static class reportTreeNode extends TreeNode {
        public reportTreeNode(String myid, String idto, String name,String title, boolean isOpen) {
            setAttribute("myid", myid);
            setAttribute("idto", idto);
            setAttribute("name", name);
            setAttribute("title", title);
            setAttribute("isOpen", isOpen);
        }
    }
    public static final TreeNode[] reportData = new TreeNode[] {
            new reportTreeNode("4", "1", "rep1", "rep 1", true),
            new reportTreeNode("189", "1", "rep2", "rep 2", true),
            new reportTreeNode("265", "1", "rep3", "rep 3", true),
            new reportTreeNode("264", "1", "rep4", "rep 4", true),
            new reportTreeNode("188", "1", "rep5", "rep 5", true)
    };
}
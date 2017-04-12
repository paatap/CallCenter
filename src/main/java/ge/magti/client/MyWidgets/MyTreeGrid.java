package ge.magti.client.MyWidgets;

import com.smartgwt.client.widgets.tree.TreeGrid;

/**
 * Created by user on 4/11/17.
 */
public class MyTreeGrid extends TreeGrid {
    static final String addcss=" mymylistgrid";


    public  MyTreeGrid(String title,String style){
        setTitle(title);
        if (style==null||style.equals("")) return;

        this.setBaseStyle(this.getBaseStyle()+addcss+style);

    }
}

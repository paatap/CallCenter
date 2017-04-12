package ge.magti.client.MyWidgets;
import com.smartgwt.client.widgets.Label;
/**
 * Created by user on 4/11/17.
 */
public class MyLabel extends Label {
    static final String addcss=" mymytext";


    public  MyLabel(String title,String style){
        setTitle(title);
        if (style==null||style.equals("")) return;
;
        this.setBaseStyle(this.getBaseStyle()+addcss+style);

    }

}

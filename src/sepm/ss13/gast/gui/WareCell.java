package sepm.ss13.gast.gui;

import javafx.scene.control.ListCell;
import sepm.ss13.gast.domain.Ware;

public class WareCell extends ListCell<Ware> {
	
	@Override protected void updateItem(Ware item, boolean empty) {
        // calling super here is very important - don't skip this!
        super.updateItem(item, empty);
        
        if (item != null) {
        	setText(item.getBezeichnung());
        //	Tooltip tooltip = new Tooltip();
        //	tooltip.setText(item.);
           // setTooltip(tooltip);
        }
	}

}

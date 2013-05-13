package sepm.ss13.gast.gui;

import javafx.scene.control.ListCell;
import javafx.scene.control.Tooltip;
import sepm.ss13.gast.domain.Produkt;

public class ProduktCell extends ListCell<Produkt> {
	
	@Override protected void updateItem(Produkt item, boolean empty) {
        // calling super here is very important - don't skip this!
        super.updateItem(item, empty);
        
        if (item != null) {
        	setText(item.getName());
        //	Tooltip tooltip = new Tooltip();
        //	tooltip.setText(item.);
           // setTooltip(tooltip);
        }
        
        
	}

}

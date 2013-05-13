package sepm.ss13.gast.gui;

import sepm.ss13.gast.domain.ProduktKategorie;
import javafx.scene.control.ListCell;
import javafx.scene.control.Tooltip;

public class ProduktKategorieCell extends ListCell<ProduktKategorie> {
	
	@Override protected void updateItem(ProduktKategorie item, boolean empty) {
        // calling super here is very important - don't skip this!
        super.updateItem(item, empty);
        
        if (item != null) {
        	setText(item.getBezeichnung());
        	Tooltip tooltip = new Tooltip();
        	tooltip.setText(item.getKurzbezeichnung());
            setTooltip(tooltip);
        }
        
        
	}

}

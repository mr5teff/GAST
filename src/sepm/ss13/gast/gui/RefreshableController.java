package sepm.ss13.gast.gui;

import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.control.Dialogs;
import sepm.ss13.gast.dao.DAOException;
import sepm.ss13.gast.service.Service;

public abstract class RefreshableController extends Controller {
	private Timer t;
	
	protected void startRefresh() {
		Service s = (Service) this.getApplicationContext().getBean("GASTService");
		
		t = new Timer(true);
		try 
		{
			t.schedule(new TimerTask() 
			{
				public void run() 
				{
					refresh();
				}
			}, 0, s.loadKonfiguration().getTimerIntervall()*1000);
		} 
		catch (DAOException e) 
		{
			Dialogs.showErrorDialog(this.getStage(), "Konfiguration konnte nicht geladen werden.", "Ladefehler", "Konfiguration laden", e);
		}
	}
	
	protected void stopRefresh() {
		t.cancel();
	}
	
	protected abstract void refresh();
}
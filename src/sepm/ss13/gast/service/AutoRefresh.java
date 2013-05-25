package sepm.ss13.gast.service;

import sepm.ss13.gast.gui.KuecheController;

public class AutoRefresh implements Runnable{

	protected boolean isStopped = false;
	private KuecheController kuecheController;
	
	public AutoRefresh(KuecheController kuecheController)
	{
		this.kuecheController = kuecheController;
	}
	
	public void run() {

		while(!isStopped())
        {	
			try
		      {
		       Thread.sleep( 10000 );
		      }
		      catch (InterruptedException e)
		      {
		    	  return;
		      }
			kuecheController.clickOnBearbeitungszeitAktualisieren();
			
        }
	}
	
	public synchronized void stop(){
        this.isStopped = true;
    }
	
	private synchronized boolean isStopped() {
        return this.isStopped;
    }
		

}

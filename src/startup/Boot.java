package startup;

import methods.MethodRun;
import userinterface.MethodRunTextBox;

public class Boot {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MethodRunTextBox mrtb = new MethodRunTextBox();

		MethodRun dgs= new MethodRun();
		Process dgs_proc=dgs.scan("NPHGS",4);
		mrtb.setProcInputStream(dgs_proc);
		new Thread(mrtb).start();
		
		
		try {
			dgs_proc.waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Thread.sleep(100000);
	}

}

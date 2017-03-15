package startup;

import dgraphscan.DGraphScan;
import userinterface.MethodRunTextBox;

public class Boot {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MethodRunTextBox mrtb = new MethodRunTextBox();

		DGraphScan dgs= new DGraphScan();
		Process dgs_proc=dgs.scan();
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

package dgraphscan;

import java.io.IOException;

public class DGraphScan {
	public Process scan(){
		System.out.println("go this way");
		try {
			//Process proc = Runtime.getRuntime().exec("./dGraphScan/dist/dp/dp data");
			//Process proc = Runtime.getRuntime().exec("python ./NPHGS/NPHGS_trafficNetwork.py data");
			Process proc = Runtime.getRuntime().exec("python ./test.py");
			/*		
			 * BufferedInputStream bis = new BufferedInputStream (proc.getInputStream());
			StringBuffer sb = new StringBuffer("output:\n");
			int ch;
			try {
				while ((ch=bis.read())!=-1){
					sb.append((char)ch);
					if (ch=='\n'){
						System.out.println(sb);
						sb=new StringBuffer();
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			
			return proc;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

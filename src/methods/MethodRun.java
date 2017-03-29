package methods;

import java.io.IOException;

import streamcontrol.StreamControl;

public class MethodRun {
	public Process scan(String inputpath, int method){
		System.out.println("launching one program");
		try {
			//Process proc = Runtime.getRuntime().exec("./dGraphScan/dist/dp/dp data");
			//Process proc = Runtime.getRuntime().exec("python ./NPHGS/NPHGS_trafficNetwork.py data");
			String command ="python /home/hongyi/Desktop/SubgraphDetection/SubgraphDetection.py "
					+inputpath+" "+Integer.toString(method);
			System.out.println(command);
			Process proc = Runtime.getRuntime().exec
					(command);
			StreamControl.proc=proc;
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

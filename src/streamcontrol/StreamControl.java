package streamcontrol;

import java.io.BufferedInputStream;
import java.io.IOException;

public class StreamControl extends Thread{
	public static Process proc=null;
	public void run(){
		System.out.println("StreamControl Runs");
		while (true){
			if (this.proc==null){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				continue;
			}
			BufferedInputStream bis = new BufferedInputStream (proc.getInputStream());
			StringBuffer sb = new StringBuffer("\nNew output:\n");
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
		}
		
	}
}

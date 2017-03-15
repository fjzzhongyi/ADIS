package userinterface;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import javax.swing.JFrame;

public class MethodRunTextBox extends JFrame implements Runnable{
	TextArea ta;
	BufferedReader bufferedReader;
	public MethodRunTextBox(){
		
		Container container = this.getContentPane();
		int framewidth=500;
		int frameheight=400;
		this.setBounds(200,200,framewidth,frameheight);
		container.setLayout(new FlowLayout());
		
		ta = new TextArea();
		container.add(ta);
		
		this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2-framewidth/2, 
				Toolkit.getDefaultToolkit().getScreenSize().height/2-framewidth/2);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
	}
	public void setProcInputStream(Process proc){
		InputStreamReader bis = new InputStreamReader (proc.getInputStream());
		this.bufferedReader = new BufferedReader(bis);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub    
		ta.append("Message\n");
        String line;
        try {
            
            //System.out.println(bufferedReader);
            while(true) {
            	line = bufferedReader.readLine();
            	if (line==null){
            			continue;
            	}
                //System.out.println(line);
                ta.append(line+'\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }    
	}
}

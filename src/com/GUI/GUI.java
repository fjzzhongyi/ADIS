package com.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.*;

import curve.anomaly.AnomalyCurve;
import curve.event.EventCurve;
import imageDis.RuntimeImage;
import methods.ProgressBar;
import streamcontrol.StreamControl;

public class GUI extends JFrame implements ActionListener{
	
	JPanel jp0,jp1,jp2,jp3,jp4,jp5,jp6;
	JButton jbSubDet,jbEveDetRes,jbAnoDetRes,jbSubDis,jbEveDis,jbchoose;
	JLabel jlchoose;
	JRadioButton jrbS,jrbD,jrbSM1,jrbSM2,jrbSM3,jrbSM4,jrbDM1,jrbDM2,jrbDM3;
	ButtonGroup bg1,bg2,bg3;
	JTextField input;
	JFileChooser jfc = new JFileChooser();
	JTextArea ta,inter;
	JScrollPane jspDis,jspInter;
	String DisplayString,testDisString, InterString, testInterString;
	String inputfile_path;

	public static void main(String[] args) {
		try {
			String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		GUI main_window = new GUI();
		StreamControl sc =new StreamControl(main_window.ta);
		sc.start();
		RuntimeImage ri = new RuntimeImage();
		
		
	}
	
	public GUI(){
		
		jp0 = new JPanel();
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jp4 = new JPanel(new GridLayout(2,2));
		jp5 = new JPanel(null);
		jp6 = new JPanel(new BorderLayout());
		testDisString = new String("Display Area");
//test
//		testDisString = "Display Area\nDisplay Area\n"
//				+ "Display Area\nDisplay Area\nDisplay Area\n"
//				+ "Display Area\nDisplay Area\nDisplay Area\n"
//				+ "Display Area\nDisplay Area\nDisplay Area\n"
//				+ "Display Area\nDisplay Area\nDisplay Area\n"
//				+ "Display Area\nDisplay Area\nDisplay Area\n"
//				+ "Display Area\nDisplay Area\nDisplay Area\n"
//				+ "Display Area\nDisplay Area\nDisplay Area\n"
//				+ "Display Area\nDisplay Area\nDisplay Area\n"
//				+ "Display Area\nDisplay Area\nDisplay Area\n";
		DisplayString = new String();
		DisplayString = testDisString;
		ta = new JTextArea(DisplayString,15,15);					//initialize the Size
		jspDis = new JScrollPane(ta);
		testInterString = new String("your further needs:");
		InterString = new String();
		InterString = testInterString;
		inter = new JTextArea(InterString,12,15);					//initialize the Size
		jspInter = new JScrollPane(inter);
		jbSubDet = new JButton("Run Subgraph Detection");
		jbEveDetRes = new JButton("Event Detection Result");
		jbAnoDetRes = new JButton("Anomaly Detection Result");
		jbSubDis = new JButton("Subgraph Display");
		jbEveDis = new JButton("Event Display");
		jbchoose = new JButton("...");
		input = new JTextField("Your Input File Path");
		jlchoose = new JLabel("Please Choose File Path");
		jrbS = new JRadioButton("Nodewise");
		jrbD = new JRadioButton("Edgewise");
		jrbSM1 = new JRadioButton("dGraphScan");
		jrbSM2 = new JRadioButton("DepthFirstScan");
		jrbSM3 = new JRadioButton("AdditiveScan");
		jrbSM4 = new JRadioButton("NPHGS");
		jrbDM1 = new JRadioButton("Meden");
		jrbDM2 = new JRadioButton("NetSpot");
		jrbDM3 = new JRadioButton("DM3");
		jrbS.setSelected(true);
		bg1 = new ButtonGroup();
		bg1.add(jrbS);
		bg1.add(jrbD);
		bg2 = new ButtonGroup();
		bg2.add(jrbSM1);
		bg2.add(jrbSM2);
		bg2.add(jrbSM3);
		bg2.add(jrbSM4);
		bg3 = new ButtonGroup();
		bg3.add(jrbDM1);
		bg3.add(jrbDM2);
		bg3.add(jrbDM3);
		
		jp1.add(jrbS);
		jp1.add(jrbD);
		jp2.add(jrbSM1);
		jp2.add(jrbSM2);
		jp2.add(jrbSM3);
		jp2.add(jrbSM4);
		jp3.add(jbSubDet);
		jp4.add(jbEveDetRes);
		jp4.add(jbAnoDetRes);
		jp4.add(jbSubDis);
		jp4.add(jbEveDis);
		jp5.add(jlchoose);
		jp5.add(input);
		jp5.add(jbchoose);
		jp6.add(jspDis,BorderLayout.NORTH);
		jp6.add(jspInter,BorderLayout.SOUTH);
		ta.setLineWrap(true);
		ta.setWrapStyleWord(true);
		ta.setEnabled(false);
		//ta.setFont(new Font("华文行楷",  1,  20));
		ta.setFont(new Font("Courier",  1,  20));
		//ta.setBackground(Color.green); 
		inter.setFont(new Font("Courier",  1,  15));
		jspDis.setVerticalScrollBarPolicy(                
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jspInter.setVerticalScrollBarPolicy(                
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		input.setEnabled(false);
		input.setHorizontalAlignment(JTextField.CENTER);
		jlchoose.setBounds(50, 45, 150, 30);
		input.setBounds(200, 45, 300, 30);
		jbchoose.setBounds(510,45,50,30);
		jbchoose.addActionListener(this);										//add actions
		jrbS.addActionListener(this);
		jrbD.addActionListener(this);
		jbSubDet.addActionListener(this);
//		ImageIcon bj = new ImageIcon(getClass().getResource("/1.png")); 
//		jbSubDet.setIcon(bj);
		jbSubDet.setMargin(new Insets(10,10,10,10)); 
		jbEveDetRes.addActionListener(this);
		jbAnoDetRes.addActionListener(this);
		jbSubDis.addActionListener(this);
		jbEveDis.addActionListener(this);
		
		double lx = Toolkit.getDefaultToolkit().getScreenSize().getWidth();  
        double ly = Toolkit.getDefaultToolkit().getScreenSize().getHeight();  
        this.setLocation(new Point((int) ((lx-800) / 2), (int) ((ly-600) / 2)));
        this.add(jp0,BorderLayout.CENTER);
        this.add(jp6,BorderLayout.EAST);
		jp0.setLayout(new GridLayout(5,1,0,0));
		jp0.add(jp5);
		jp0.add(jp1);
		jp0.add(jp2);
		jp0.add(jp3);
		jp0.add(jp4);
		this.setTitle("Anomaly Detection");
		this.setSize(800, 600);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(jbchoose)) {						  
            jfc.setFileSelectionMode(1);								// only folder is allowed
            int state = jfc.showOpenDialog(null);						// open the folder  
            if (state == 1) {  
                return;  
            } else {  
                File f = jfc.getSelectedFile();							// get the path  
                input.setText(f.getAbsolutePath()); 
                inputfile_path=f.getAbsolutePath();
            }  
        }
		
		if(jrbS.isSelected()){
			jp2.removeAll();
			jp2.updateUI();
			jp2.add(jrbSM1);
			jp2.add(jrbSM2);
			jp2.add(jrbSM3);
			jp2.add(jrbSM4);
		}
		else{
			jp2.removeAll();
			jp2.updateUI();
			jp2.add(jrbDM1);
			jp2.add(jrbDM2);
			jp2.add(jrbDM3);
		}
		
		if (e.getSource().equals(jbSubDet)){
			//Subgraph Detection algorithm
			if (this.inputfile_path==null){
				JOptionPane.showMessageDialog(null, "Choose your input path", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (jrbSM1.isSelected()){
				new ProgressBar(this.inputfile_path,1);
			}else if (jrbSM2.isSelected()){
				new ProgressBar(this.inputfile_path,2);
			}else if (jrbSM3.isSelected()){
				new ProgressBar(this.inputfile_path,3);
			}else if (jrbSM4.isSelected()){
				new ProgressBar(this.inputfile_path,4);
			}
			
			
		}
		
		if (e.getSource().equals(jbEveDetRes)){
			//Event Detection Result Part
			EventCurve ec = new EventCurve();
			JOptionPane.showMessageDialog(null,"Result","Event Detection Result",JOptionPane.INFORMATION_MESSAGE);
		}
		
		if (e.getSource().equals(jbAnoDetRes)){
			//Anomaly Detection Result Part
			AnomalyCurve ac = new AnomalyCurve("true.dat","NPHGS_result__20170207_003349.txt");
			JOptionPane.showMessageDialog(null,"Result","Anomaly Detection Result",JOptionPane.INFORMATION_MESSAGE);
		}
		
		if (e.getSource().equals(jbSubDis)){
			//Subgraph Display Part
			java.net.URI uri;
			try {
				uri = new java.net.URI("http://127.0.0.1:8000/GIS/");
				java.awt.Desktop.getDesktop().browse(uri);
			} catch (URISyntaxException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null,"Result","Subgraph Display Part",JOptionPane.INFORMATION_MESSAGE);
		}
		
		if (e.getSource().equals(jbEveDis)){
			//Subgraph Display Part
			JOptionPane.showMessageDialog(null,"Result","Event Display Part",JOptionPane.INFORMATION_MESSAGE);
		}
	}

}

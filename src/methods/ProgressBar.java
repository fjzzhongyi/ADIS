package methods;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
public class ProgressBar implements ActionListener, ChangeListener {
	JFrame frame = null;
	JProgressBar progressbar;
	JLabel label;
	Timer timer;
	JButton b;
	String inputpath;
	int method;
	public ProgressBar(String ip,int m) {
		this.inputpath=ip;
		this.method=m;
		
		frame = new JFrame(method+" is ready to run");
		frame.setBounds(100, 100, 400, 150);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		Container contentPanel = frame.getContentPane();
		label = new JLabel("Press button above to begin", JLabel.CENTER);
		progressbar = new JProgressBar();
		progressbar.setOrientation(JProgressBar.CENTER);
		progressbar.setMinimum(0);
		progressbar.setMaximum(100);
		progressbar.setStringPainted(true);
		//progressbar.addChangeListener(this);
		 
		progressbar.setPreferredSize(new Dimension(300, 50));
		progressbar.setBorderPainted(true);
		progressbar.setBackground(Color.pink);
		
		JPanel panel = new JPanel(new BorderLayout());
		b = new JButton("Sure to Run");
		b.setForeground(Color.blue);
		b.addActionListener(this);
		panel.add(b,BorderLayout.CENTER);
		//timer = new Timer(2000, this);
		
		/*contentPanel.add(panel, BorderLayout.NORTH);
		contentPanel.add(label, BorderLayout.CENTER);
		contentPanel.add(progressbar, BorderLayout.SOUTH);
		*/
		contentPanel.setLayout(new GridLayout(3,1));
		contentPanel.add(panel);
		contentPanel.add(label);
		contentPanel.add(progressbar);
		// frame.pack();
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b) {
			progressbar.setIndeterminate(true);
			label.setText("The Program is Running：" +  "Unknown %");
			label.setForeground(Color.blue);
			
			MethodRun mr = new MethodRun();
			Process proc =mr.scan(this.inputpath,this.method);
			try {
				proc.waitFor();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			progressbar.setValue(100);
			progressbar.setIndeterminate(false);
			
			
		}
		/*if (e.getSource() == timer) {
				progressbar.setValue(100);
				progressbar.setIndeterminate(false);
				//frame.dispose();
			
		}*/
	}
	public void stateChanged(ChangeEvent e1) {
		int value = progressbar.getValue();
		if (e1.getSource() == progressbar) {
			//label.setText("The Program is Running：" + Integer.toString(value) + "%");
			//label.setForeground(Color.blue);
		}
	}
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			Logger.getLogger(ProgressBar.class.getName()).log(Level.FINE,
					e.getMessage());
			e.printStackTrace();
		}
		new ProgressBar("/home/hongyi/Desktop/SubgraphDetection/data/StandardDataset/com_traffic",4);
	}
}
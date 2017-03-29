package imageDis; 
import java.awt.Frame;  
import java.awt.event.WindowAdapter;  
import java.awt.event.WindowEvent;

import javax.swing.JFrame;  
  
public class RuntimeImage extends JFrame{  
      
    public RuntimeImage() {  
  
        // 默认的窗体名称  
        this.setTitle("Reference Runtime");  
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        // 获得面板的实例  
        MyPanel panel = new MyPanel();  
        this.add(panel);  
        this.addWindowListener(new WindowAdapter() {  
            //设置关闭  
            public void windowClosing(WindowEvent e) {  
                System.exit(0);  
            }  
        });  
        // 执行并构建窗体设定  
        
        pack();  
        setVisible(true);  
    }  

  
}  
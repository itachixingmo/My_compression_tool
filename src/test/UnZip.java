package test;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UnZip {
	public UnZip(){
		JFrame jf = new JFrame("itachi解压");
		jf.setBounds(300,200,400,300);
		
		JPanel jp=new JPanel();		
		JLabel label1=new JLabel("源文件地址");
		JLabel label2=new JLabel("解压文件名");		
		JLabel label3=new JLabel("解压文件地址");		
						
		JTextField jtf1=new JTextField(30);
		JTextField jtf2=new JTextField(30);
		JTextField jtf3=new JTextField(30);

	    JButton jb1 = new JButton("检测");
	    JButton jb2 = new JButton("解压");
	    
	    jp.add(label1);
	    jp.add(jtf1);
	    jp.add(label2);
	    jp.add(jtf2);
	    jp.add(label3);
	    jp.add(jtf3);

        jp.add(jb1);
        jp.add(jb2);       
        jf.add(jp); 
        
        jb1.addActionListener(new MyActionListener(jtf1,jtf2,jtf3));
        jb2.addActionListener(new MyActionListener(jtf1,jtf2,jtf3)); 

        jf.setVisible(true); 
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}

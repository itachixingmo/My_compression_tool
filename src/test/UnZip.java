package test;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UnZip {
	public UnZip(){
		JFrame jf = new JFrame("itachi��ѹ");
		jf.setBounds(300,200,400,300);
		
		JPanel jp=new JPanel();		
		JLabel label1=new JLabel("Դ�ļ���ַ");
		JLabel label2=new JLabel("��ѹ�ļ���");		
		JLabel label3=new JLabel("��ѹ�ļ���ַ");		
						
		JTextField jtf1=new JTextField(30);
		JTextField jtf2=new JTextField(30);
		JTextField jtf3=new JTextField(30);

	    JButton jb1 = new JButton("���");
	    JButton jb2 = new JButton("��ѹ");
	    
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

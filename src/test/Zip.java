package test;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Zip{
	public Zip(){
		JFrame jf = new JFrame("itachi压缩");
		jf.setBounds(300,200,400,300);
		
		JPanel jp=new JPanel();		
		JLabel label1=new JLabel("源文件地址");
		JLabel label2=new JLabel("压缩文件名");		
		JLabel label3=new JLabel("压缩文件地址");		
						
		JTextField jtf1=new JTextField(30);
		JTextField jtf2=new JTextField(30);
		JTextField jtf3=new JTextField(30);

	    JButton jb1 = new JButton("预览");
	    JButton jb2 = new JButton("Huffm压缩");
	    JButton jb3 = new JButton("LZW压缩");
	    
	    jp.add(label1);
	    jp.add(jtf1);
	    jp.add(label2);
	    jp.add(jtf2);
	    jp.add(label3);
	    jp.add(jtf3);

        jp.add(jb1);
        jp.add(jb2);
        jp.add(jb3);
        jf.add(jp); 
        
//        System.out.println(jtf1.toString());
        
        jb1.addActionListener(new MyActionListener(jtf1,jtf2,jtf3));
        jb2.addActionListener(new MyActionListener(jtf1,jtf2,jtf3));
        jb3.addActionListener(new MyActionListener(jtf1,jtf2,jtf3));

        jf.setVisible(true); 
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        System.out.println(jtf1.getText().toString());
	}
}
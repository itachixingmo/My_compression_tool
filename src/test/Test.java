package test;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Test{
	public static void test(String text) {
		String[] last = text.split("\\.");
		String out = "";
		if(last[1].equals("Huffm")) {
			out = text +  " 是itachi使用Huffm压缩";
		}
		else if(last[1].equals("Lzw")) {
			out = text +  " 是itachi使用LZW压缩";
		}
		else {
			out = text +  " 不是itachi压缩的";
		}
		JFrame jf = new JFrame("itachi压缩");
		jf.setBounds(300,200,400,300);
		
		JPanel jp=new JPanel();		
		JLabel label1=new JLabel(out);

	    jp.add(label1);
        jf.add(jp); 

        jf.setVisible(true); 
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
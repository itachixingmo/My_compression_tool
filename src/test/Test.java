package test;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Test{
	public static void test(String text) {
		String[] last = text.split("\\.");
		String out = "";
		if(last[1].equals("Huffm")) {
			out = text +  " ��itachiʹ��Huffmѹ��";
		}
		else if(last[1].equals("Lzw")) {
			out = text +  " ��itachiʹ��LZWѹ��";
		}
		else {
			out = text +  " ����itachiѹ����";
		}
		JFrame jf = new JFrame("itachiѹ��");
		jf.setBounds(300,200,400,300);
		
		JPanel jp=new JPanel();		
		JLabel label1=new JLabel(out);

	    jp.add(label1);
        jf.add(jp); 

        jf.setVisible(true); 
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.NumberFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Compare {
	public static void compare(String text) throws Exception{
		float a = source(text);
		Huffm huffm = new Huffm();
		huffm.countTimes(text);
		HuffmNode root = huffm.createTree();
		huffm.getHuffmCode(root, "");
		float b = Huffm.huffm(text);

		float c = LZW.lzw(text);
		double ratio1 = b / a;
		double ratio2 = c / a;
//		System.out.println(a+" "+ b+ " "+ c);
        NumberFormat format = NumberFormat.getPercentInstance();
        format.setMaximumFractionDigits(2);//���ñ�����λС��
//        System.out.println("�ٷֱ�Ϊ��"+format.format(ratio));        
        String out = "Huffumѹ����Ϊ" + format.format(ratio1) + ",SLZWѹ����Ϊ" + format.format(ratio2);
        if(b < c) {
        	out += ",����ʹ��Huffumѹ��";
        }
        else {
        	out += ",����ʹ��LZWѹ��";
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
	
	public static float source(String text) throws IOException {
		String[] last = text.split("/");
		float a = 0;
		FileInputStream fis = new FileInputStream(text);
		
//		System.out.println(last[last.length-1]);
		File f = new File(text);
		a = f.length();
		return a;
		
	}

}

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
        format.setMaximumFractionDigits(2);//设置保留几位小数
//        System.out.println("百分比为："+format.format(ratio));        
        String out = "Huffum压缩率为" + format.format(ratio1) + ",SLZW压缩率为" + format.format(ratio2);
        if(b < c) {
        	out += ",建议使用Huffum压缩";
        }
        else {
        	out += ",建议使用LZW压缩";
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

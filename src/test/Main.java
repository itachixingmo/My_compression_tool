package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main {
	public static void main(String[] args) {
		JFrame jf = new JFrame("itachiѹ��"); 
		jf.setBounds(300,200,400,300);
		JLabel jl=new JLabel();	
		JPanel jp=new JPanel();
		JButton jb1 = new JButton("ѹ��"); 	    
	    JButton jb2 = new JButton("��ѹ");	    
	    jb1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		Zip x = new Zip();
	    	}
	    });
	    jb2.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent e) {
	      		UnZip x = new UnZip();
	       	}
	    });
	    
	    jp.add(jl);
	    jp.add(jb1);
	    jp.add(jb2);
	    jf.add(jp);      
	    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    jf.setVisible(true); 
	    
    }
}

class MyActionListener implements ActionListener{
	JTextField jtf1;
	JTextField jtf2;
	JTextField jtf3;

	public MyActionListener(JTextField jtf1, JTextField jtf2, JTextField jtf3) {
		this.jtf1 = jtf1;
		this.jtf2 = jtf2;
		this.jtf3 = jtf3;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if(str.equals("Ԥ��")) {
			try {
//				System.out.println(jtf1.getText()+ " " + jtf2.getText()+ " " +jtf3.getText()+ " " +jtf1.getText());
				Compare.compare(jtf1.getText() + jtf2.getText());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(str.equals("Huffmѹ��")) {
			try {
				Huffm huffm = new Huffm();
				huffm.countTimes(jtf1.getText() + jtf2.getText());
				HuffmNode root = huffm.createTree();
				huffm.getHuffmCode(root, "");
				Huffm.compress(jtf1.getText() + jtf2.getText(),jtf3.getText());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(str.equals("LZWѹ��")) {
			LZW tool = new LZW(jtf1.getText() , jtf2.getText(),jtf3.getText());
			tool.compress();
		}
		else if(str.equals("���")) {
			Test.test(jtf2.getText());
		}
		else if(str.equals("��ѹ")) {
			String last = jtf2.getText().split("\\.")[1];
			String out = "";
			if(last.equals("Huffm")) {
				Huffm.uncompress(jtf1.getText() + jtf2.getText(),jtf3.getText());
			}
			else if(last.equals("Lzw")) {
				LZW.uncompress(jtf1.getText() + jtf2.getText(),jtf3.getText());
			}
		}
		
	}
}
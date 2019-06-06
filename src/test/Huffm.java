package test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;

public class Huffm {

	public int [] times = new int[256];
	public static String [] HuffmCodes=new String[256];
	public LinkedList<HuffmNode> list = new LinkedList<HuffmNode>();

	
	//ÿ������ĳ���
	public static int [] codelengths = new int [256];
	//��Ӧ�Ĺ���������ֵ
	public static String [] codeMap=new String[256];

	//ͳ�ƴ���
	//��ʼ��

	public Huffm(){
		for (int i = 0; i < HuffmCodes.length; i++) {
			HuffmCodes[i]="";
		}
	}

	public void countTimes(String path) throws Exception{
		//�����ļ�������
		FileInputStream fis = new FileInputStream(path);
		//��ȡ�ļ�
		int value=fis.read();
		while(value!=-1){
			times[value]++;
			value=fis.read();
		}
		//�ر���
		fis.close();
	}

	//�����������
	public HuffmNode createTree(){
		//��������ΪȨֵ����ɭ��
		for (int i = 0; i < times.length; i++) {
			if(times[i]!=0){
				HuffmNode node = new HuffmNode(times[i],i);
				//������õĽڵ���뵽�����е���ȷλ��
				list.add(getIndex(node), node);
			}
		}

		//��ɭ�֣������еĸ����ڵ㣩����ɹ�������
		while(list.size()>1) {
			//��ȡ�����е�һ��Ԫ�أ�Ȩֵ��С�Ľڵ㣩
			HuffmNode firstNode =list.removeFirst();
			//��ȡ���µĵ�һ��Ԫ�أ�ԭ���ĵ�һ��Ԫ���Ѿ����Ƴ��ˣ�Ȩֵ��С�Ľڵ㣩
			HuffmNode secondNode =list.removeFirst();
			//��Ȩֵ��С�������ڵ㹹��ɸ��ڵ�
			HuffmNode fatherNode =
					new HuffmNode(firstNode.getData()+secondNode.getData(),-1);
			fatherNode.setLeft(firstNode);
			fatherNode.setRight(secondNode);
			//���ڵ���뵽�����е���ȷλ��
			list.add(getIndex(fatherNode),fatherNode);
		}
		//�����������ĸ��ڵ�
		return list.getFirst();
	}
	//����ǰ�������ȡ�����
	
	public void getHuffmCode(HuffmNode root,String code){
		//�����ߣ������������0
		if(root.getLeft()!=null){
			getHuffmCode(root.getLeft(),code+"0");
		}
		//�����ߣ������������1
		if(root.getRight()!=null){
			getHuffmCode(root.getRight(),code+"1");
		}
		//�����Ҷ�ӽڵ㣬���ظ�Ҷ�ӽڵ�Ĺ���������
		if(root.getLeft()==null && root.getRight()==null){
//			System.out.println(root.getIndex()+"�ı���Ϊ��"+code);
			HuffmCodes[root.getIndex()]=code;
		}
	}

	//ѹ���ļ�
	public static void compress(String path,String destpath) throws Exception{

		//�����ļ������
		FileOutputStream fos = new FileOutputStream(destpath);
		FileInputStream fis = new FileInputStream(path);
		/**===============�����д���ļ�================*/
		//�����������������Լ�ÿ������ĳ���д���ļ�
		String code ="";
		for (int i = 0; i < 256; i++) {
			fos.write(HuffmCodes[i].length());
			code+=HuffmCodes[i];
			fos.flush();
		}
		//�ѹ���������д���ļ�

//		System.out.println("code="+code);
		String str1="";
		while(code.length()>=8){
			str1=code.substring(0, 8);
			int c=changeStringToInt(str1);
//			System.out.println(c);
			fos.write(c);
			fos.flush();
			code=code.substring(8);
		}

		//�������һ����Ϊ8����

		int last=8-code.length();
		for (int i = 0; i <last; i++) {
			code+="0";
		}

		str1=code.substring(0, 8);
		int c=changeStringToInt(str1);
		fos.write(c);
		fos.flush();

		/**===============������д�뵽�ļ���================*/

		//���ļ���������Ӧ�Ĺ��������봮�ӳ��ַ���

		int value=fis.read();
		String str = "";
		while(value!=-1){
			str+=HuffmCodes[value];
//			System.out.println((char)value+":"+str);
			value=fis.read();

		}
		System.out.println(str);

		fis.close();
		String s="";
			//���ַ�8λ�ָ����һ���ֽ�
		while(str.length()>=8){
				s=str.substring(0, 8);
				int b=changeStringToInt(s);
//				System.out.println(c);
				fos.write(b);
				fos.flush();
				str=str.substring(8);
			}		

			//��󲻹�8λ��0
			int last1=8-str.length();
			for (int i = 0; i <last1; i++) {
				str+="0";
			}
			s=str.substring(0, 8);
//			System.out.println(s);
			int d=changeStringToInt(s);
			fos.write(d);
			//ѹ���󲻹���0�ĸ�����
			fos.write(last1);
			fos.flush();			
			fos.close();
	}

	//����Ԫ��λ�õ�����
	public int getIndex(HuffmNode node) {
		for (int i = 0; i < list.size(); i++) {
			if(node.getData()<=list.get(i).getData()){
				return i;
			}
		}
        return list.size();
	}

	//���ַ���ת��������
	public static int changeStringToInt(String s){
		int v1=(s.charAt(0)-48)*128;
		int v2=(s.charAt(1)-48)*64;
		int v3=(s.charAt(2)-48)*32;
		int v4=(s.charAt(3)-48)*16;
		int v5=(s.charAt(4)-48)*8;
		int v6=(s.charAt(5)-48)*4;
		int v7=(s.charAt(6)-48)*2;
		int v8=(s.charAt(7)-48)*1;
		return v1+v2+v3+v4+v5+v6+v7+v8;
	}

	public static void uncompress(String srcpath,String destpath) {
		try {
			FileInputStream fis = new FileInputStream(srcpath);
			FileOutputStream fos = new FileOutputStream(destpath);
			int value;
			int codeLength=0;
			String code="";
			//��ԭ���
			for (int i = 0; i < codelengths.length; i++) {
				value=fis.read();
				codelengths[i]=value;
//				System.out.println(times[i]);
				codeLength+=codelengths[i];
			}

			//�õ��ܳ���
			//���ܳ��ȳ���8�ĵ��ֽڸ���
			int len=codeLength/8;
			//�������8�ı��������ֽڸ�����1����Ӧѹ����0�������
			if((codeLength)%8!=0){
				len++;
			}
			//��ȡ����������
//			System.out.println("codeLength:"+len);
			for (int i = 0; i < len; i++) {
				//�Ѷ���������ת���ɶ�����
				code+=changeIntToString(fis.read());
			}
//			System.out.println("���������룺"+code);

			for (int i = 0; i < codeMap.length; i++) {
				//�����i��λ�ò�Ϊ0 ����˵����i��λ�ô洢�й���������
				if(codelengths[i]!=0){
					//���õ���һ�����������밴�ճ��ȷָ�ָ�
					String ss=code.substring(0, codelengths[i]);
					codeMap[i]=ss;
					code=code.substring(codelengths[i]);
				}else{
					//Ϊ0��û�ж�Ӧ�Ĺ���������
					codeMap[i]="";
				}
			}

			//��ȡѹ�����ļ�����
			String codeContent="";
			while(fis.available()>1){
				codeContent+=changeIntToString(fis.read());
			}
			//��ȡ���һ��
			value=fis.read();
			//����󲹵�0��ȥ��
			codeContent=codeContent.substring(0, codeContent.length()-value);
			for (int i = 0; i < codeContent.length(); i++) {
				String codecontent=codeContent.substring(0, i+1);
				for (int j = 0; j < codeMap.length; j++) {
					if(codeMap[j].equals(codecontent)){
//						System.out.println("��ȡ���ַ�����"+codecontent);
						fos.write(j);
						fos.flush(); 
						codeContent=codeContent.substring(i+1);
//						System.out.println("��ȡ��ʣ����볤�ȣ�"+codeContent.length());
//						count=1;
						i=-1;
						break;
					}
			}
			}
			fos.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	//ʮ����ת�������ַ���
	public static String changeIntToString(int value) {
		String s="";
		for (int i = 0; i < 8; i++) {
			s=value%2+s;
			value=value/2;
		}
		return s;
	}
	
	
	public static float huffm(String text) throws IOException {
		FileInputStream fis = new FileInputStream(text);
		/**===============�����д���ļ�================*/
		//�����������������Լ�ÿ������ĳ���д���ļ�
		String length = "";
		String code ="";
		for (int i = 0; i < 256; i++) {
//			fos.write(HuffmCodes[i].length());
			code+=HuffmCodes[i];
			length+=HuffmCodes[i].length();
		}
			//�ѹ���������д���ļ�

//		System.out.println("code="+code);
		String str1="";
		while(code.length()>=8){
			str1=code.substring(0, 8);
			int c=changeStringToInt(str1);
			length+=c;
//		    System.out.println(c);
//		    fos.write(c);
//		    fos.flush();
		    code=code.substring(8);
		}
				
		//�������һ����Ϊ8����
		int last=8-code.length();
		for (int i = 0; i <last; i++) {
		code+="0";
		}

		str1=code.substring(0, 8);
		int c=changeStringToInt(str1);
		length+=c;
//		fos.write(c);
//		fos.flush();

		/**===============������д�뵽�ļ���================*/

		//���ļ���������Ӧ�Ĺ��������봮�ӳ��ַ���

		int value=fis.read();
		String str = "";
		while(value!=-1){
			str+=HuffmCodes[value];
//			System.out.println((char)value+":"+str);
			value=fis.read();
		}
//		System.out.println(str);

		fis.close();
		String s="";
		//���ַ�8λ�ָ����һ���ֽ�
		while(str.length()>=8){
				s=str.substring(0, 8);
				int b=changeStringToInt(s);
//				System.out.println(c);
				length+=b;
//				fos.write(b);
//				fos.flush();
				str=str.substring(8);
		}		

		//��󲻹�8λ��0
		int last1=8-str.length();
		for (int i = 0; i <last1; i++) {
			str+="0";
		}
		s=str.substring(0, 8);
//		System.out.println(s);
		int d=changeStringToInt(s);
		length+=d;
		return length.length();
		
	}
}

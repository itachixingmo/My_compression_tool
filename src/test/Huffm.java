package test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;

public class Huffm {

	public int [] times = new int[256];
	public static String [] HuffmCodes=new String[256];
	public LinkedList<HuffmNode> list = new LinkedList<HuffmNode>();

	
	//每个编码的长度
	public static int [] codelengths = new int [256];
	//对应的哈夫曼编码值
	public static String [] codeMap=new String[256];

	//统计次数
	//初始化

	public Huffm(){
		for (int i = 0; i < HuffmCodes.length; i++) {
			HuffmCodes[i]="";
		}
	}

	public void countTimes(String path) throws Exception{
		//构造文件输入流
		FileInputStream fis = new FileInputStream(path);
		//读取文件
		int value=fis.read();
		while(value!=-1){
			times[value]++;
			value=fis.read();
		}
		//关闭流
		fis.close();
	}

	//构造哈夫曼树
	public HuffmNode createTree(){
		//将次数作为权值构造森林
		for (int i = 0; i < times.length; i++) {
			if(times[i]!=0){
				HuffmNode node = new HuffmNode(times[i],i);
				//将构造好的节点加入到容器中的正确位置
				list.add(getIndex(node), node);
			}
		}

		//将森林（容器中的各个节点）构造成哈夫曼树
		while(list.size()>1) {
			//获取容器中第一个元素（权值最小的节点）
			HuffmNode firstNode =list.removeFirst();
			//获取中新的第一个元素，原来的第一个元素已经被移除了（权值次小的节点）
			HuffmNode secondNode =list.removeFirst();
			//将权值最小的两个节点构造成父节点
			HuffmNode fatherNode =
					new HuffmNode(firstNode.getData()+secondNode.getData(),-1);
			fatherNode.setLeft(firstNode);
			fatherNode.setRight(secondNode);
			//父节点加入到容器中的正确位置
			list.add(getIndex(fatherNode),fatherNode);
		}
		//返回整颗树的根节点
		return list.getFirst();
	}
	//利用前序遍历获取编码表
	
	public void getHuffmCode(HuffmNode root,String code){
		//往左走，哈夫曼编码加0
		if(root.getLeft()!=null){
			getHuffmCode(root.getLeft(),code+"0");
		}
		//往右走，哈夫曼编码加1
		if(root.getRight()!=null){
			getHuffmCode(root.getRight(),code+"1");
		}
		//如果是叶子节点，返回该叶子节点的哈夫曼编码
		if(root.getLeft()==null && root.getRight()==null){
//			System.out.println(root.getIndex()+"的编码为："+code);
			HuffmCodes[root.getIndex()]=code;
		}
	}

	//压缩文件
	public static void compress(String path,String destpath) throws Exception{

		//构建文件输出流
		FileOutputStream fos = new FileOutputStream(destpath);
		FileInputStream fis = new FileInputStream(path);
		/**===============把码表写入文件================*/
		//将整个哈夫曼编码以及每个编码的长度写入文件
		String code ="";
		for (int i = 0; i < 256; i++) {
			fos.write(HuffmCodes[i].length());
			code+=HuffmCodes[i];
			fos.flush();
		}
		//把哈夫曼编码写入文件

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

		//处理最后一个不为8的数

		int last=8-code.length();
		for (int i = 0; i <last; i++) {
			code+="0";
		}

		str1=code.substring(0, 8);
		int c=changeStringToInt(str1);
		fos.write(c);
		fos.flush();

		/**===============将数据写入到文件中================*/

		//读文件，并将对应的哈夫曼编码串接成字符串

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
			//将字符8位分割，对弈一个字节
		while(str.length()>=8){
				s=str.substring(0, 8);
				int b=changeStringToInt(s);
//				System.out.println(c);
				fos.write(b);
				fos.flush();
				str=str.substring(8);
			}		

			//最后不够8位添0
			int last1=8-str.length();
			for (int i = 0; i <last1; i++) {
				str+="0";
			}
			s=str.substring(0, 8);
//			System.out.println(s);
			int d=changeStringToInt(s);
			fos.write(d);
			//压缩后不够补0的个数暂
			fos.write(last1);
			fos.flush();			
			fos.close();
	}

	//插入元素位置的索引
	public int getIndex(HuffmNode node) {
		for (int i = 0; i < list.size(); i++) {
			if(node.getData()<=list.get(i).getData()){
				return i;
			}
		}
        return list.size();
	}

	//将字符串转换成整数
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
			//还原码表
			for (int i = 0; i < codelengths.length; i++) {
				value=fis.read();
				codelengths[i]=value;
//				System.out.println(times[i]);
				codeLength+=codelengths[i];
			}

			//得到总长度
			//将总长度除以8的到字节个数
			int len=codeLength/8;
			//如果不是8的倍数，则字节个数加1（对应压缩补0的情况）
			if((codeLength)%8!=0){
				len++;
			}
			//读取哈夫曼编码
//			System.out.println("codeLength:"+len);
			for (int i = 0; i < len; i++) {
				//把读到的整数转换成二进制
				code+=changeIntToString(fis.read());
			}
//			System.out.println("哈夫曼编码："+code);

			for (int i = 0; i < codeMap.length; i++) {
				//如果第i个位置不为0 ，则说明第i个位置存储有哈夫曼编码
				if(codelengths[i]!=0){
					//将得到的一串哈夫曼编码按照长度分割分割
					String ss=code.substring(0, codelengths[i]);
					codeMap[i]=ss;
					code=code.substring(codelengths[i]);
				}else{
					//为0则没有对应的哈夫曼编码
					codeMap[i]="";
				}
			}

			//读取压缩的文件内容
			String codeContent="";
			while(fis.available()>1){
				codeContent+=changeIntToString(fis.read());
			}
			//读取最后一个
			value=fis.read();
			//把最后补的0给去掉
			codeContent=codeContent.substring(0, codeContent.length()-value);
			for (int i = 0; i < codeContent.length(); i++) {
				String codecontent=codeContent.substring(0, i+1);
				for (int j = 0; j < codeMap.length; j++) {
					if(codeMap[j].equals(codecontent)){
//						System.out.println("截取的字符串："+codecontent);
						fos.write(j);
						fos.flush(); 
						codeContent=codeContent.substring(i+1);
//						System.out.println("截取后剩余编码长度："+codeContent.length());
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
	//十进制转二进制字符串
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
		/**===============把码表写入文件================*/
		//将整个哈夫曼编码以及每个编码的长度写入文件
		String length = "";
		String code ="";
		for (int i = 0; i < 256; i++) {
//			fos.write(HuffmCodes[i].length());
			code+=HuffmCodes[i];
			length+=HuffmCodes[i].length();
		}
			//把哈夫曼编码写入文件

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
				
		//处理最后一个不为8的数
		int last=8-code.length();
		for (int i = 0; i <last; i++) {
		code+="0";
		}

		str1=code.substring(0, 8);
		int c=changeStringToInt(str1);
		length+=c;
//		fos.write(c);
//		fos.flush();

		/**===============将数据写入到文件中================*/

		//读文件，并将对应的哈夫曼编码串接成字符串

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
		//将字符8位分割，对弈一个字节
		while(str.length()>=8){
				s=str.substring(0, 8);
				int b=changeStringToInt(s);
//				System.out.println(c);
				length+=b;
//				fos.write(b);
//				fos.flush();
				str=str.substring(8);
		}		

		//最后不够8位添0
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

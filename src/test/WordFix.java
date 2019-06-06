package test;


 

import java.util.HashMap;
import java.util.Map;

/**
 * ���飬����ǰ׺�ͺ�׺
 * 
 * @author lyq
 * 
 */
public class WordFix {
	// ����ǰ׺
	String prefix;
	// �����׺
	String suffix;
	// �������ӳ���
	HashMap<WordFix, Integer> word2Code;

	public WordFix(String prefix, String suffix,HashMap<WordFix, Integer> word2Code) {
		this.prefix = prefix;
		this.suffix = suffix;
		this.word2Code = word2Code;
	}

	/**
	 * ����ǰ׺
	 * 
	 * @param str
	 */
	public void setPrefix(String str) {
		this.prefix = str;
	}

	/**
	 * ���ú�׺
	 * 
	 * @param str
	 */
	public void setSuffix(String str) {
		this.suffix = str;
	}

	/**
	 * ��ȡǰ׺�ַ�
	 * 
	 * @return
	 */
	public String getPrefix() {
		return this.prefix;
	}

	/**
	 * �ж�2�������Ƿ���ȣ��Ƚ�ǰ���ַ��Ƿ����
	 * 
	 * @param wf
	 * @return
	 */
	public boolean isSame(WordFix wf) {
		boolean isSamed = true;
		if (!this.prefix.equals(wf.prefix)) {
			isSamed = false;
		}
		if (!this.suffix.equals(wf.suffix)) {
			isSamed = false;
		}
		return isSamed;
	}

	/**
	 * �жϴ˴����Ƿ��Ѿ�������
	 * 
	 * @return
	 */
	public boolean hasWordCode() {
		boolean isContained = false;
		WordFix wf = null;
		for (Map.Entry entry : word2Code.entrySet()) {
			wf = (WordFix) entry.getKey();
			if (this.isSame(wf)) {
				isContained = true;
				break;
			}
		}
		return isContained;
	}

	/**
	 * ������б���
	 *
	 * @param wordCode
	 * �˴��齫Ҫ�������ֵ
	 */
	public void wordFixCoded(int wordCode) {
		word2Code.put(this, wordCode);
	}

	/**
	 * �����׺�ַ�
	 *
	 * @param str
	 */
	public void readSuffix(String str) {
		int code = 0;
		boolean isCoded = false;
		WordFix wf = null;

		for (Map.Entry entry : word2Code.entrySet()) {
			code = (int) entry.getValue();
			wf = (WordFix) entry.getKey();
			if (this.isSame(wf)) {
				isCoded = true;
				// �����Ϊǰ׺
				this.prefix = code + "";
				break;
			}
		}
		if (!isCoded) {
			return;
		}
		this.suffix = str;
	}

	/**
	 * ������תΪ�������ַ���ʽ
	 * 
	 * @return
	 */
	public String transToStr() {
		int code = 0;
		String currentPrefix = this.prefix;
		for(Map.Entry entry: word2Code.entrySet()){
			code = (int) entry.getValue();
			//���ǰ׺�ַ����Ǳ��룬��������
			if(currentPrefix.equals(code + "")){
				currentPrefix =((WordFix) entry.getKey()).transToStr();
				break;
			}
		}
		return currentPrefix + this.suffix;
	}
}

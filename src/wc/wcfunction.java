package wc;

import java.io.*;
import java.util.regex.*;

public class wcfunction {
	private BufferedReader br;
	
	//�ļ�����ͳ�ƺ���
	void getwordnumber(String filename) throws IOException {
		String ret = null;
		int num=0;
		String[] strword = null;
		File file = new File(filename);
		if(file.exists()) {
			//��ȡ�ļ�
			FileReader fr = new FileReader(filename);
			br = new BufferedReader(fr);
			String line = null;
			StringBuffer sbf = new StringBuffer();
			while((line=br.readLine())!= null) {
				sbf.append(line);
				String str = sbf.toString();
				//������ʽ�滻����
				str = str.replaceAll("[\\p{Nd}\\u9fa5-\\uffe5\\p{Punct}\\s&&[^-]]", " ");
				//���ո����ݷָ�
				strword = str.split("\\s+");
				num=strword.length;
			}
			ret= "�ó����ļ��е�����Ϊ��"+num;
			br.close();
			fr.close();
		}else {
			System.out.println("�ļ�������,�����������ļ�!");
		}
		System.out.print(ret);
	}
	
	//�ļ��ַ�ͳ�ƺ���
	void getCharacternumber(String filename) throws IOException {
		String ret = null;
		int number = 0;
		String[] strword = null;
		File file = new File(filename);
		if(file.exists()) {
			//��ȡ�ļ�
			FileReader fr = new FileReader(filename);
			br = new BufferedReader(fr);
			String line = null;
			String str=null;
			StringBuffer sbf = new StringBuffer();
			while((line=br.readLine())!= null) {
				sbf.append(line);
				str = sbf.toString();
				strword = str.split("\\s+");
			}
			for(int i=0;i<strword.length;i++) {
				Pattern pattern = Pattern.compile("[0-9a-zA-Z]*");
				Matcher matcher = pattern.matcher(strword[i]);
				if(matcher.find()) {
					number+=matcher.regionEnd();
				}
			}
			ret = "�ó����ļ��е��ַ���Ϊ��"+number;
			br.close();
			fr.close();
		}else {
			System.out.println("�ļ�������,�����������ļ�!");
		}
		System.out.print(ret);
	}
	
	//�ļ�����ͳ�ƺ���
	void getlinenumber(String filename) throws IOException {
		String ret = null;
		int linenum = 0;
		File file = new File(filename);
		if(file.exists()) {
			//��ȡ�ļ�
			FileReader fr = new FileReader(filename);
			//��ȡ�ļ�����
			LineNumberReader lnr = new LineNumberReader(fr);
			while(lnr.readLine()!= null) {
				linenum=lnr.getLineNumber();
			}
			ret = "�ó����ļ��е�����Ϊ��"+linenum;
			lnr.close();
			fr.close();
		}else {
			System.out.println("�ļ�������,�����������ļ�!");
		}
		System.out.print(ret);
	}
	
	 	//ͳ���ļ������ļ����г����ļ��Ŀ��С������С�ע���У��еݹ��ļ�Ŀ¼����
		void diffline(File file) throws FileNotFoundException {
			int spaceline = 0;
			int nodeline = 0;
			int codeline = 0;
			if (file == null || !file.exists()) 
				throw new FileNotFoundException(file + "���ļ������ڣ�");
	 
			if (file.isDirectory()) {
				File[] files = file.listFiles(new FileFilter() {
					public boolean accept(File pathname) {
						return pathname.getName().endsWith(".java")|| pathname.isDirectory()||pathname.getName().endsWith(".c")||pathname.getName().endsWith(".cpp")  ;
					}
				});
				//�ݹ��ļ�Ŀ¼
				for (File target : files) {
					diffline(target);
				}
			} else {
				System.out.println("�ļ���:"+file.getAbsolutePath());
				BufferedReader bufr = null;
				try {
					// ��ָ��·�����ļ����ַ�����
					bufr = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				} catch (FileNotFoundException e) {
					throw new FileNotFoundException(file + "���ļ������ڣ�" + e);
				}
	 
				// ����ƥ��ÿһ�е�����ƥ����
				Pattern nodeLinePattern = Pattern.compile("((//)|(/\\*+)|((^\\s)*\\*)|((^\\s)*\\*+/))+", 
						Pattern.MULTILINE + Pattern.DOTALL);	// ע��ƥ����(ƥ�䵥�С����С��ĵ�ע��)
	 
				Pattern spaceLinePattern = Pattern.compile("^\\s*$");	// �հ���ƥ������ƥ��س���tab�����ո�
				
				// �����ļ��е�ÿһ�У�����������ƥ��Ľ����¼ÿһ��ƥ��Ľ��
				String line = null;
				try {
					while((line = bufr.readLine()) != null) {
						if (nodeLinePattern.matcher(line).find()) {
							nodeline ++;
						}else if (spaceLinePattern.matcher(line).find()) {
							spaceline ++;
						}else{
							codeline ++;
						} 
					}
					System.out.println("����:"+spaceline);
					System.out.println("������:"+codeline);
					System.out.println("ע����:"+nodeline);
				} catch (IOException e) {
					throw new RuntimeException("��ȡ�ļ�ʧ�ܣ�" + e);
				} finally {
					try {
						bufr.close();	// �ر��ļ����������ͷ�ϵͳ��Դ
					} catch (IOException e) {
						throw new RuntimeException("�ر��ļ�������ʧ�ܣ�");
					}
				}
			}
		}
		
		//��������
		void help(){
			System.out.println("-c �ļ�(������ļ�·��)   ͳ�Ƴ����ļ��е��ַ���");
			System.out.println("-w �ļ�(������ļ�·��)   ͳ�Ƴ����ļ��еĵ�����");
			System.out.println("-l �ļ�(������ļ�·��)   ͳ�Ƴ����ļ��е�����");
			System.out.println("-a �ļ�(������ļ�·��)   ͳ�Ƴ����ļ��еĿ�����������������ע������");
			System.out.println("-s-a �ļ�·�������ļ���·��		�ݹ�ͳ�Ƴ����ļ��еĿ�����������������ע������");
		}
}

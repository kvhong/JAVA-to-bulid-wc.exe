package wc;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class wctest{
	
	private static Scanner scanner;

	public static void main(String[] args) throws IOException{
		String str = null;
		wcfunction wcf = new wcfunction();
		//ѭ��ѯ����������
		while(true) {
			System.out.print("����������:");
			//��������
			scanner = new Scanner(System.in);
			if(scanner.hasNext()) {
			str=scanner.nextLine();
			}
			//�ָ������һ����Ϊ�жϵڶ���Ϊ�ļ�·��
			String[] strword = str.split(" ");
			if(strword.length==2) {
			if(strword[0].equals("-c")) {
				int chara=wcf.getCharacternumber(strword[1]);
				System.out.println("���ļ����ַ���:"+chara);
			}else if(strword[0].equals("-w")) {
				int word=wcf.getwordnumber(strword[1]);
				System.out.println("���ļ��Ĵ���:"+word);
			}else if(strword[0].equals("-l")) {
				int line=wcf.getlinenumber(strword[1]);
				System.out.println("���ļ�������:"+line);
			}else if(strword[0].equals("-a")) {
				File file = new File(strword[1]);
				wcf.diffline(file);
			}else if(strword[0].equals("-s-a")) {
				File file = new File(strword[1]);
				wcf.diffline(file);
			}
			}else {
				if(strword[0].equals("?")) {
					wcf.help();
				}else if(strword[0].equals("-x")) {
					wcGUI.main(null);
				}else if(strword[0].equals("end")) {
					break;
				}else {
					System.out.println("��������������������룡");
				}
			}
				
		}
	}
}

package wc;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class wctest{
	
	private static Scanner scanner;

	public static void main(String[] args) throws IOException{
		String str = null;
		wcfunction wcf = new wcfunction();
		//循环询问命令输入
		while(true) {
			System.out.print("请输入命令:");
			//命令输入
			scanner = new Scanner(System.in);
			if(scanner.hasNext()) {
			str=scanner.nextLine();
			}
			//分割命令，第一个作为判断第二个为文件路径
			String[] strword = str.split(" ");
			if(strword[0].equals("-c")) {
				wcf.getCharacternumber(strword[1]);
			}else if(strword[0].equals("-w")) {
				wcf.getwordnumber(strword[1]);
			}else if(strword[0].equals("-l")) {
				wcf.getlinenumber(strword[1]);
			}else if(strword[0].equals("-a")) {
				File file = new File(strword[1]);
				wcf.diffline(file);
			}else if(strword[0].equals("-s-a")) {
				File file = new File(strword[1]);
				wcf.diffline(file);
			}else if(strword[0].equals("?")) {
				wcf.help();
			}else if(strword[0].equals("end")) {
				break;
			}
		}
	}
}

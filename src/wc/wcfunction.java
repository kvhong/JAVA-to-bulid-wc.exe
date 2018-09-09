package wc;

import java.io.*;
import java.util.regex.*;

public class wcfunction {
	private BufferedReader br;
	
	//文件单词统计函数
	void getwordnumber(String filename) throws IOException {
		String ret = null;
		int num=0;
		String[] strword = null;
		File file = new File(filename);
		if(file.exists()) {
			//读取文件
			FileReader fr = new FileReader(filename);
			br = new BufferedReader(fr);
			String line = null;
			StringBuffer sbf = new StringBuffer();
			while((line=br.readLine())!= null) {
				sbf.append(line);
				String str = sbf.toString();
				//正则表达式替换符号
				str = str.replaceAll("[\\p{Nd}\\u9fa5-\\uffe5\\p{Punct}\\s&&[^-]]", " ");
				//按空格将内容分割
				strword = str.split("\\s+");
				num=strword.length;
			}
			ret= "该程序文件中单词数为："+num;
			br.close();
			fr.close();
		}else {
			System.out.println("文件不存在,请重新输入文件!");
		}
		System.out.print(ret);
	}
	
	//文件字符统计函数
	void getCharacternumber(String filename) throws IOException {
		String ret = null;
		int number = 0;
		String[] strword = null;
		File file = new File(filename);
		if(file.exists()) {
			//读取文件
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
			ret = "该程序文件中的字符数为："+number;
			br.close();
			fr.close();
		}else {
			System.out.println("文件不存在,请重新输入文件!");
		}
		System.out.print(ret);
	}
	
	//文件行数统计函数
	void getlinenumber(String filename) throws IOException {
		String ret = null;
		int linenum = 0;
		File file = new File(filename);
		if(file.exists()) {
			//读取文件
			FileReader fr = new FileReader(filename);
			//读取文件行数
			LineNumberReader lnr = new LineNumberReader(fr);
			while(lnr.readLine()!= null) {
				linenum=lnr.getLineNumber();
			}
			ret = "该程序文件中的行数为："+linenum;
			lnr.close();
			fr.close();
		}else {
			System.out.println("文件不存在,请重新输入文件!");
		}
		System.out.print(ret);
	}
	
	 	//统计文件或者文件夹中程序文件的空行、代码行、注释行，有递归文件目录功能
		void diffline(File file) throws FileNotFoundException {
			int spaceline = 0;
			int nodeline = 0;
			int codeline = 0;
			if (file == null || !file.exists()) 
				throw new FileNotFoundException(file + "，文件不存在！");
	 
			if (file.isDirectory()) {
				File[] files = file.listFiles(new FileFilter() {
					public boolean accept(File pathname) {
						return pathname.getName().endsWith(".java")|| pathname.isDirectory()||pathname.getName().endsWith(".c")||pathname.getName().endsWith(".cpp")  ;
					}
				});
				//递归文件目录
				for (File target : files) {
					diffline(target);
				}
			} else {
				System.out.println("文件名:"+file.getAbsolutePath());
				BufferedReader bufr = null;
				try {
					// 将指定路径的文件与字符流绑定
					bufr = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				} catch (FileNotFoundException e) {
					throw new FileNotFoundException(file + "，文件不存在！" + e);
				}
	 
				// 定义匹配每一行的正则匹配器
				Pattern nodeLinePattern = Pattern.compile("((//)|(/\\*+)|((^\\s)*\\*)|((^\\s)*\\*+/))+", 
						Pattern.MULTILINE + Pattern.DOTALL);	// 注释匹配器(匹配单行、多行、文档注释)
	 
				Pattern spaceLinePattern = Pattern.compile("^\\s*$");	// 空白行匹配器（匹配回车、tab键、空格）
				
				// 遍历文件中的每一行，并根据正则匹配的结果记录每一行匹配的结果
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
					System.out.println("空行:"+spaceline);
					System.out.println("代码行:"+codeline);
					System.out.println("注释行:"+nodeline);
				} catch (IOException e) {
					throw new RuntimeException("读取文件失败！" + e);
				} finally {
					try {
						bufr.close();	// 关闭文件输入流并释放系统资源
					} catch (IOException e) {
						throw new RuntimeException("关闭文件输入流失败！");
					}
				}
			}
		}
		
		//帮助函数
		void help(){
			System.out.println("-c 文件(须包含文件路径)   统计程序文件中的字符数");
			System.out.println("-w 文件(须包含文件路径)   统计程序文件中的单词数");
			System.out.println("-l 文件(须包含文件路径)   统计程序文件中的行数");
			System.out.println("-a 文件(须包含文件路径)   统计程序文件中的空行数、代码行数、注释行数");
			System.out.println("-s-a 文件路径或者文件夹路径		递归统计程序文件中的空行数、代码行数、注释行数");
		}
}

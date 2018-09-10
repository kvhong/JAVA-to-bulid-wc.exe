package wc;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class wcGUI {

	private JFrame frmWc;
	private JTextField codenum;
	private JTextField wordnum;
	private JTextField linenum;
	private JTextField spaceline;
	private JTextField nodeline;
	private JTextField codeline;
	File file;
	wcfunction wcf=new wcfunction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					wcGUI window = new wcGUI();
					window.frmWc.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public wcGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWc = new JFrame();
		frmWc.setTitle("wc");
		frmWc.setResizable(false);
		frmWc.setBounds(280, 50, 800, 600);
		frmWc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmWc.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(291, 0, 493, 562);
		panel.add(scrollPane);
		
		JPanel panel_1 = new JPanel();
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		panel_1.add(textArea, BorderLayout.CENTER);
		
		JButton choosefile = new JButton("选择文件");
		choosefile.setBounds(10, 32, 122, 23);
		panel.add(choosefile);
		
		JLabel filename = new JLabel("文件名:");
		filename.setBounds(10,70,80,23);
		panel.add(filename);
		
		JTextArea filepath = new JTextArea();
		filepath.setBounds(10, 95, 260, 40);
		filepath.setLineWrap(true);
		filepath.setWrapStyleWord(true); 
		filepath.setEditable(false);
		panel.add(filepath);
		
		JButton code = new JButton("统计字符数");
		code.setBounds(10, 277, 100, 30);
		panel.add(code);
		
		JButton word = new JButton("统计词数");
		word.setBounds(10, 317, 93, 30);
		panel.add(word);
		
		JButton line = new JButton("统计行数");
		line.setBounds(10, 357, 93, 30);
		panel.add(line);
		
		JButton diffline = new JButton("统计空行、注释行、代码行");
		diffline.setBounds(10, 397, 224, 30);
		panel.add(diffline);
		
		codenum = new JTextField();
		codenum.setBounds(120, 278, 93, 29);
		codenum.setEditable(false);
		panel.add(codenum);
		codenum.setColumns(10);
		
		wordnum = new JTextField();
		wordnum.setBounds(113, 318, 93, 30);
		wordnum.setEditable(false);
		panel.add(wordnum);
		wordnum.setColumns(10);
		
		linenum = new JTextField();
		linenum.setColumns(10);
		linenum.setBounds(113, 358, 93, 30);
		linenum.setEditable(false);
		panel.add(linenum);
		
		spaceline = new JTextField();
		spaceline.setColumns(10);
		spaceline.setBounds(70, 437, 93, 29);
		spaceline.setEditable(false);
		panel.add(spaceline);
		
		nodeline = new JTextField();
		nodeline.setColumns(10);
		nodeline.setBounds(70, 476, 93, 29);
		nodeline.setEditable(false);
		panel.add(nodeline);
		
		codeline = new JTextField();
		codeline.setColumns(10);
		codeline.setBounds(70, 515, 93, 30);
		codeline.setEditable(false);
		panel.add(codeline);
		
		JLabel label = new JLabel("空行");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(10, 437, 54, 29);
		panel.add(label);
		
		JLabel label_1 = new JLabel("注释行");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(10, 476, 54, 29);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("代码行");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(10, 515, 54, 30);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("个");
		label_3.setBounds(216, 277, 54, 30);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("个");
		label_4.setBounds(216, 317, 54, 30);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("行");
		label_5.setBounds(216, 357, 54, 30);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("行");
		label_6.setBounds(173, 437, 54, 30);
		panel.add(label_6);
		
		JLabel label_7 = new JLabel("行");
		label_7.setBounds(173, 476, 54, 30);
		panel.add(label_7);
		
		JLabel label_8 = new JLabel("行");
		label_8.setBounds(173, 515, 54, 30);
		panel.add(label_8);
		
		choosefile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser filechooser = new JFileChooser(".");
				int result=filechooser.showOpenDialog(null);
				if(result == JFileChooser.APPROVE_OPTION){
				file=filechooser.getSelectedFile();
				if(file!=null) {
					if(textArea.getText()!=null) {
						textArea.setText("");
					}
					if(filepath.getText()!=null) {
						filepath.setText("");
						filepath.setText(file.getPath());
					}
					try {
						InputStreamReader read = new InputStreamReader(new FileInputStream(file), "GB2312");
						BufferedReader br = new BufferedReader(read);
						String line=null;
						while((line=br.readLine())!=null) {
							textArea.append(line+"\r\n");
						}
						br.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
			}
		});
		
		code.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(file.exists()) {
					String filename = file.getAbsolutePath();
					try {
						int chara=wcf.getCharacternumber(filename);
						codenum.setText(chara+"");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
			}
		});
		
		word.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(file.exists()) {
							String filename = file.getAbsolutePath();
							try {
								int word=wcf.getwordnumber(filename);
								wordnum.setText(word+"");
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						}
						
					}
				});

		line.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(file.exists()) {
					String filename = file.getAbsolutePath();
					try {
						int line=wcf.getlinenumber(filename);
						linenum.setText(line+"");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
			}
		});

		diffline.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(file.exists()) {
					try {
						int[] line=wcf.difflineGUI(file);
						spaceline.setText(line[0]+"");
						nodeline.setText(line[1]+"");
						codeline.setText(line[2]+"");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
			}
		});
	}
}

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.im.InputContext;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class Eunju extends JFrame{
	
	public static final Font font0 = new Font(null , Font.BOLD, 17);
	public static final Font font1 = new Font(null , Font.TRUETYPE_FONT, 15);
	public static int type = 1;
	public static int was_success = 1;
	public static int grade;
	public static int class_;
	public static String name;
	public static String[] users = new String[2];
	public static CSVWriter writer;
	public static CSVReader reader;
	public static JRadioButton ss;
    public static JRadioButton st;
    public static JRadioButton tt;
    public static JButton btn;
    public static JPanel p1;
    public static JPanel p2;
    public static JPanel p3;
    
    public Eunju(){
        jenna();
    }
	
	private void jenna(){
		setTitle("이심전심 Quiz v1.2 _ by Dr. Shin");
		setSize(500, 500);
        setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setResizable(false);
        setVisible(true);
        
		Container c = getContentPane();
		c.setLayout(new GridLayout(5, 1));
		c.addKeyListener(new ListenKey());
		
		ButtonGroup g = new ButtonGroup();
		ss = new JRadioButton("학생-학생", true);
		st = new JRadioButton("교사-학생");
		tt = new JRadioButton("교사-교사");
		
		ss.addActionListener(new Listen(1));
		st.addActionListener(new Listen(2));
		tt.addActionListener(new Listen(3));
		
		ss.setFocusable(false);
		st.setFocusable(false);
		tt.setFocusable(false);
		
		ss.setOpaque(false);
		st.setOpaque(false);
		tt.setOpaque(false);
		
		ss.setFont(font0);
		st.setFont(font0);
		tt.setFont(font0);
		
		g.add(ss);
		g.add(st);
		g.add(tt);
		
		p1 = new JPanel();
		p1.setLayout(new FlowLayout(FlowLayout.CENTER,20,30));
		p1.setBackground(Color.ORANGE);
		p1.add(ss);
		c.add(p1);
		
		p2 = new JPanel();
		p2.setLayout(new FlowLayout(FlowLayout.CENTER,20,30));
		p2.setBackground(Color.WHITE);
		p2.add(st);
		c.add(p2);
		
		p3 = new JPanel();
		p3.setLayout(new FlowLayout(FlowLayout.CENTER,20,30));
		p3.setBackground(Color.WHITE);
		p3.add(tt);
		c.add(p3);
		
		btn = new JButton("시작!");
		btn.setFocusable(false);
		btn.setFont(font0);
		btn.setBorder(null);
		btn.addActionListener((ActionEvent e) -> {
			coleman();
        });
		c.add(btn);
		
		JPanel help = new JPanel();
		help.setLayout(new FlowLayout(FlowLayout.CENTER,20,15));
		JLabel label1 = new JLabel("시작 버튼 대신에 ENTER를 눌러도 됩니다.");
		label1.setFont(font1);
		JLabel label2 = new JLabel("방향키(위,아래)로 설정(학생-학생, ...)을 변경 할 수 있습니다.");
		label2.setFont(font1);
		help.add(label1);
		help.add(label2);
		c.add(help);
		
		c.requestFocus();
	}
	
	private void coleman(){
		switch(type){
		case 1: getStudent(0);
				if(was_success==1){
					JOptionPane.showMessageDialog(null, "두번째 입력을 시작합니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
					getStudent(1); break;
				}
				else
					was_success=1; break;
		case 2: getTeacher(0);
				if(was_success==1){
					JOptionPane.showMessageDialog(null, "두번째 입력을 시작합니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
					getStudent(1); break;
				}
				else
					was_success=1; break;
		case 3: getTeacher(0);
				if(was_success==1){
					JOptionPane.showMessageDialog(null, "두번째 입력을 시작합니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
					getTeacher(1); break;
				}
				else
					was_success=1; break;
				}
		if(users[0]!=null && users[1]!=null){
			if(check()==true){
				try {
					writer = new CSVWriter(new FileWriter("참가자.csv", true), ','); //make append attribute to True!!!
				    writer.writeNext(users);
					writer.close();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null,"IOException occured!", "등록 실패", JOptionPane.ERROR_MESSAGE);
				}
				JOptionPane.showMessageDialog(null, users[0]+"와(과) "+users[1]+"가  등록되었습니다!", "등록 성공", JOptionPane.INFORMATION_MESSAGE);
			}
			else{
				JOptionPane.showMessageDialog(null,users[0]+"와(과) "+users[1]+"는 이미 한번 등록하였습니다!", "등록 거부", JOptionPane.ERROR_MESSAGE);
			}
		}
		users[0]=null;
		users[1]=null;
	}
	
	public void getStudent(int order){
		grade = Integer.parseInt(JOptionPane.showInputDialog(null, "학년을 숫자로 입력해 주세요!\nEx) 3", "학생 학년 입력", JOptionPane.QUESTION_MESSAGE));
		class_ = Integer.parseInt(JOptionPane.showInputDialog(null, "반을 숫자로 입력해 주세요!\nEx) 5", "학생 반 입력", JOptionPane.QUESTION_MESSAGE));
		name = JOptionPane.showInputDialog(null, "이름을 입력해 주세요!\nEx) 신현호\n주의: 한영키를 눌러주세요!!!", "학생 이름 입력", JOptionPane.QUESTION_MESSAGE);
		if(name!=null&&name.length()>0){
			try{
			users[order] = name.concat("("+Integer.toString(grade)+"-"+Integer.toString(class_)+")");
			} catch(StringIndexOutOfBoundsException e){
				JOptionPane.showMessageDialog(null,"Invalid Input!", "등록 실패", JOptionPane.ERROR_MESSAGE);
				users[order]=null;
			}
		}
		else{
			JOptionPane.showMessageDialog(null,"등록이 취소되었습니다!", "등록 실패", JOptionPane.ERROR_MESSAGE);
			was_success=0;
		}
	}
	
	public void getTeacher(int order){
		name = JOptionPane.showInputDialog(null, "이름을 입력해 주세요!\nEx) 이은주♡\n주의: 한영키를 눌러주세요!!!", "교사 이름 입력", JOptionPane.QUESTION_MESSAGE);
		if(name!=null&&name.length()>0){
			try{
				users[order] = name.concat("(교사)");
				} catch(StringIndexOutOfBoundsException e){
					JOptionPane.showMessageDialog(null,"Invalid Input!", "등록 실패", JOptionPane.ERROR_MESSAGE);
					users[order]=null;
			}
		}
		else{
			JOptionPane.showMessageDialog(null,"등록이 취소되었습니다!", "등록 실패", JOptionPane.ERROR_MESSAGE);
			was_success=0;
		}
	}
	
	public boolean check(){
		try {
			reader = new CSVReader(new FileReader("참가자.csv"));
			String [] nextLine;
			while ((nextLine = reader.readNext()) != null){
				if(users[0].equals(nextLine[0])){
					if(users[1].equals(nextLine[1]))
						return false;
				}
				else if(users[0].equals(nextLine[1])){
					if(users[1].equals(nextLine[0]))
						return false;
				}
			}
		} catch (FileNotFoundException e) {
			return true;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"IOException occured!", "등록 실패", JOptionPane.ERROR_MESSAGE);
			users[1]=null;//의미없음
		}
		return true;
	}
	
	private void toKoreanIME(Component comp) {  
        try {  
            InputContext inCtx = comp.getInputContext();  
            Character.Subset[] subset = { Character.UnicodeBlock.HANGUL_SYLLABLES };  
            inCtx.setCharacterSubsets( subset );  
        }catch (Exception x) {  
        }  
    } 
	
	class Listen implements ActionListener{
		int t;
		Listen(int t){
			this.t = t;
		}
		public void actionPerformed(ActionEvent e) {
			type=t;
			switch(type){
			case 1: p1.setBackground(Color.ORANGE);
					p2.setBackground(Color.WHITE);
					p3.setBackground(Color.WHITE); break;
			case 2: p1.setBackground(Color.WHITE);
					p2.setBackground(Color.ORANGE);
					p3.setBackground(Color.WHITE); break;
			case 3: p1.setBackground(Color.WHITE); 
					p2.setBackground(Color.WHITE);
					p3.setBackground(Color.ORANGE);
			}
		}
	}
	
	class ListenKey extends KeyAdapter{
        @Override
    	public void keyPressed (KeyEvent e) {
        	if(e.getKeyCode()==KeyEvent.VK_ENTER){
        		btn.doClick();
        	}
        	else if(e.getKeyCode()==KeyEvent.VK_UP){
            	switch(type){
    			case 1: break;
    			case 2: ss.doClick(); break;
    			case 3: st.doClick();
    			}
            }
            else if(e.getKeyCode()==KeyEvent.VK_DOWN){
            	switch(type){
    			case 1: st.doClick(); break;
    			case 2: tt.doClick(); break;
            	}
            }
        }
	}
	
	public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new Eunju();
        });
    }
}
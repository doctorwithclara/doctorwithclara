import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class Hellbent extends JFrame {
    
    public static int i, n=1, cs=0, count=0, line=0;
    public static String[] student1 = {"강찬규", "국시현", "김재윤", "김현준", "김효건", "나유성", "박종은", "방원준", "서동우", "신현호", "안신웅", "윤중우", "임유진", "정덕인", "정명석", "한재민"};
    public static String[] student2 = {"강민수", "김민승", "김재동", "김재준", "남상현", "남성현", "문준서", "박정현", "배준열", "신정용", "오세훈", "이성훈", "이유빈", "이혁재"};
    public static String[] student3 = {"강채정", "박범렬", "박지원", "백철우", "서지원", "손민서", "심용섭", "유환석", "윤종욱", "이동현", "정예찬", "조준상", "조형빈", "최재민", "최재훈"};
    public static int[] sleep1 = new int[16];
    public static int[] sleep2 = new int[14];
    public static int[] sleep3 = new int[15];
    public static String[] student = student1;
    public static int[] sleep = sleep1;
    
    public static String[] combo = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16"};
    public static String[] classN = {"1","2","3"};
    
    public static int[] check;
    
    JTextArea area = new JTextArea(5,17);

    public Hellbent() {
        Jenna();
    }

    private void Jenna() {
        
    	JFrame f = new JFrame("Hellbent 5.0");
        f.setSize(440, 480);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setVisible(true);       
        f.setLayout(new BorderLayout());
        
        JTabbedPane tab = new JTabbedPane();
        tab.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        
        JPanel ran = new JPanel();
        ran.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 24));
        tab.addTab("Random", ran);
        
        Font font1 = new Font(null , Font.BOLD, 20);
        Font font2 = new Font(null , Font.BOLD, 25);
        
        ImageIcon Jenna1 = new ImageIcon(getClass().getResource("Jenna1.png"));
        ImageIcon Jenna2 = new ImageIcon(getClass().getResource("Jenna2.png"));
        ImageIcon Jenna3 = new ImageIcon(getClass().getResource("Jenna3.png"));
        
        JLabel oswin = new JLabel("Class :");
        ran.add(oswin);
        JComboBox classbox = new JComboBox(classN);
        ran.add(classbox);
        JLabel oswald = new JLabel("  Number :");
        ran.add(oswald);
        JComboBox box = new JComboBox(combo);
        ran.add(box);
        area.setBackground(Color.LIGHT_GRAY);
        ran.add(new JScrollPane(area));
        
        JButton claraL = new JButton("Sleep");
        ran.add(claraL);
        JButton clara = new JButton(Jenna1);
        clara.setRolloverIcon(Jenna2);
        clara.setPressedIcon(Jenna3);
        ran.add(clara);
        JButton claraR = new JButton("Reset");
        ran.add(claraR);
        classbox.setFont(font1);
        oswald.setFont(font1);
        claraL.setFont(font1);
        claraR.setFont(font1);
        oswin.setFont(font1);
        clara.setFont(font1);
        area.setFont(font2);
        box.setFont(font1);
        
        for(i=0;i<16;i++){
            if(i==0||i==9)
                sleep1[i]=7;
            else
                sleep1[i]=4;
        }
        
        for(i=0;i<14;i++)
            sleep2[i]=4;
        
        for(i=0;i<15;i++)
            sleep3[i]=4;
        
        classbox.addActionListener((ActionEvent e) -> {
            
            JComboBox classB = (JComboBox)e.getSource();
            cs=classB.getSelectedIndex();
            switch(cs){
                case 0: student = student1; sleep = sleep1; break;
                case 1: student = student2; sleep = sleep2; break;
                case 2: student = student3; sleep = sleep3; break;
            }
        });
        
        box.addActionListener((ActionEvent e) -> {
            
            JComboBox b = (JComboBox)e.getSource();
            n=b.getSelectedIndex()+1;   
        });
        
        clara.addActionListener((ActionEvent e) -> {
            
            if((cs==1&&n>14)||(cs==2&&n>15)){
                JFrame frameCB= new JFrame("Error");
                JOptionPane.showMessageDialog(frameCB,
                "The number you choose is larger \n than the number of students. \n Please Choose Another Number!",
                "ERROR!",
                JOptionPane.ERROR_MESSAGE);
            }
            else{
            check= new int[student.length];
            area.append("\n\n");
            
            Random rand = new Random();
        
            for(i=0;i<n;){
                int randomNum = rand.nextInt(student.length);
                int chance = rand.nextInt(100);
                    if((chance%sleep[randomNum])!=0)
                        continue;
            
                if(check[randomNum]==0){
                    area.append(" "+student[randomNum]);
                    check[randomNum]=1;
                    count++;
                    i++;
                    if((count%4==0)&&(i!=n)){
                        area.append("\n");
                        line++;
                    }
                }        
            }
            for(i=1;i<(4-line);i++)
                area.append("\n");                    
            count=0;
            line=0;
            }
        });
        
        claraL.addActionListener((ActionEvent e) -> {
            JFrame frameL = new JFrame("Check");
            String sleeper = (String) JOptionPane.showInputDialog(frameL, 
            "Who sleeps?",
            "Hellbent",
            JOptionPane.QUESTION_MESSAGE, 
            null, 
            student, 
            student[0]);
            if(sleeper!=null){
                for(i=0;i<student.length;i++){
                    if(sleeper.equals(student[i]))
                        sleep[i]=1;
                }
            }
        });
        
        claraR.addActionListener((ActionEvent e) -> {
            
            for(i=0;i<16;i++){
                if(i==0||i==9)
                    sleep1[i]=7;
                else
                    sleep1[i]=4;
            }
            
            for(i=0;i<14;i++)
                sleep2[i]=4;
            
            for(i=0;i<15;i++)
                sleep3[i]=4;
        
            JFrame frameR= new JFrame("Action");
            JOptionPane.showMessageDialog(frameR,
            "The Sleepers successfully cleaned!",
            "Hellbent",
            JOptionPane.INFORMATION_MESSAGE);
        });
        
        JPanel dic = new JPanel();
        dic.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 24));
        tab.addTab("Dictionary", dic);
        
        
        f.add(tab, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new Hellbent();
        });
    }    
}
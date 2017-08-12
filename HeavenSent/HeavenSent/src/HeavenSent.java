import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class HeavenSent extends JFrame {
    
    public static int i, n, count=0;
    public static String[] student = {"강찬규", "국시현", "김재윤", "김현준", "김효건", "나유성", "박종은", "방원준", "서동우", "신현호", "안신웅", "윤중우", "임유진", "정덕인", "정명석", "한재민"};
    public static int[] check;
    
    JTextField field = new JTextField(6);
    JTextArea area = new JTextArea(11,20);

    public HeavenSent() {
        Jenna();
    }

    private void Jenna() {
        
        setTitle("HeavenSent");
        setSize(310, 280);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        c.add(new JLabel("Required Number(1~16) :"));
        c.add(field);
        c.add(new JScrollPane(area));
        
        field.addActionListener((ActionEvent e) -> {
            JTextField t = (JTextField)e.getSource();
            
            check= new int[16];
               
            if((t.getText()==null) || (t.getText().length()==0)) //If user typed nothing or pressed the cancel button
                n=0;
                
            n=Integer.parseInt(t.getText());
        
            if((n>16)||(n<1)) //If user type a number more thsn 16
                n=0;
            
            Random rand = new Random();
        
            for(i=0;i<n;){
                int randomNum = rand.nextInt(16);
            
                if(check[randomNum]==0){
                    area.append(" "+student[randomNum]);
                    check[randomNum]=1;
                    count++;
                    i++;
                    if((count%5==0)&&(i!=n))
                        area.append("\n");
                }        
            }
            area.append("\n-------------------------------------------------\n");                    
            t.setText("");
            count=0;
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new HeavenSent().setVisible(true);
        });
    }    
}
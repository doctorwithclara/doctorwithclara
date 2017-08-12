import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MouseEventFrame48 extends JFrame {
 
	Font font = new Font(null, Font.BOLD, 30);
    public MouseEventFrame48() {
        Jenna();
    }

    private void Jenna() {
        setSize(400, 400);
        setTitle("MouseEventFrame");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        Container c = getContentPane();
        c.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 150));
        
        JLabel text = new JLabel("I love you");
        text.setFont(font);
        
        JennaListener jen = new JennaListener();
        text.addMouseListener(jen);
        
        c.add(text);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new MouseEventFrame48().setVisible(true);
        });
    }    
}
class JennaListener implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JLabel label = (JLabel)e.getSource();
		label.setText("Sweetie");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JLabel label = (JLabel)e.getSource();
		label.setText("I love you");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		JLabel label = (JLabel)e.getSource();
		label.setText("I love Jenna");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		JLabel label = (JLabel)e.getSource();
		label.setText("Sweetie");
	}
	
}
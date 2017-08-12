import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class KeyboardEventFrame extends JFrame {
    static JFrame c =null;
	public KeyboardEventFrame() {
        Jenna();
    }

    private void Jenna() {
    	c = new JFrame("Jenna");
        c.setSize(400, 400);
        c.setTitle("KeyboardEventFrame");
        c.setLocationRelativeTo(null);
        c.setDefaultCloseOperation(EXIT_ON_CLOSE);
        c.setResizable(false);
        c.addKeyListener(new MyJennaListener());
        c.requestFocus();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new KeyboardEventFrame().setVisible(true);
        });
    }    
}
class MyJennaListener extends KeyAdapter{
        @Override
    	public void keyPressed (KeyEvent e) {
            if(e.getKeyChar()=='R');
            KeyboardEventFrame.c.setBackground(Color.RED);
        }
}
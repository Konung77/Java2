package lesson4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyWindow extends JFrame {
    private JPanel panel;
    private JTextArea bigField;
    private JTextField smallField;
    private JButton sendButton;

    public MyWindow() throws HeadlessException {
        super("Простой чат");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(200,100);
        setSize(300,500);
        panel = new JPanel(new FlowLayout());
        JTextArea big, small;
        bigField = new JTextArea(20,20);
        smallField = new JTextField(20);
        smallField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    bigField.append(smallField.getText() + "\n");
                    smallField.setText("");
                }
            }
        });
        sendButton = new JButton("Отправить");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bigField.append(smallField.getText()+"\n");
                smallField.setText("");
            }
        });
        panel.add(bigField);
        panel.add(smallField);
        panel.add(sendButton);
        setContentPane(panel);
        setVisible(true);
        smallField.requestFocus();
    }

    public static void main(String[] args) {
        MyWindow window = new MyWindow();
    }
}

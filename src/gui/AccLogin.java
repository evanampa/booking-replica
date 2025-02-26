package gui;
import api.Login;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccLogin extends JFrame {

    JTextField usertext;
    JPasswordField  passtext;
    JButton button1,button2;
    JLabel label1,label2;
    JPanel mainpanel;

    ActionListener buttonListener1,buttonListener2;

    public void buildFrame(){
        setContentPane(mainpanel);

        setTitle("Login Page");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(300, 250);
        this.setLocationRelativeTo(null);
        setResizable(false);
        getRootPane().setDefaultButton(button1);

         buttonListener1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String txt1 = usertext.getText();
                String txt2 = passtext.getText();
                if (Login.login(txt1, txt2))
                {
                    if (Login.chechforrole(txt1)) {
                        dispose();
                        new ProviderProfile().buildFrame(txt1);
                    }
                    else
                    {
                        dispose();
                        new userMain().buildFrame(txt1);
                    }

                } else {
                    JFrame jFrame = new JFrame();
                    JOptionPane.showMessageDialog(jFrame, "Wrong Username or Password! Try Again");
                }
            }

        };
        button1.addActionListener(buttonListener1);

        buttonListener2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Sign().buildFrame();
            }

        };
        button2.addActionListener(buttonListener2);
        setVisible(true);
    }
}

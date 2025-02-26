package gui;

import api.Singup;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sign extends JFrame{
    JPanel mainpanel;
    JLabel label1,label2,label3,label4,label5;
    JTextField lnametext,nametext,usertext,passtext;
    JRadioButton radio1,radio2;
    JButton button1;

    ActionListener buttonListener;

    public void buildFrame() {
        setContentPane(mainpanel);
        setTitle("Sign Up Page");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(300, 250);
        this.setLocationRelativeTo(null);
        setResizable(false);

        buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = 0;

                String txt1 = nametext.getText();
                String txt2 = lnametext.getText();
                String txt3 = usertext.getText();
                String txt4 = passtext.getText();

                if (radio1.isSelected() && radio2.isSelected())
                {
                    JFrame jFrame = new JFrame();
                    JOptionPane.showMessageDialog(jFrame, "Please select only one role");
                }

                if (radio1.isSelected()) {
                    n = 1;
                } else if (radio2.isSelected()) {
                    n = 2;
                }


                if (Singup.checkusername(txt3)) {
                    if (txt1.equals("") || txt2.equals("") || txt3.equals("") || txt4.equals("")) {
                        JFrame jFrame = new JFrame();
                        JOptionPane.showMessageDialog(jFrame, "Please complete all the fields");
                    } else if (!radio1.isSelected() && !radio2.isSelected()) {
                        JFrame jFrame = new JFrame();
                        JOptionPane.showMessageDialog(jFrame, "Please choose your role");
                    } else {
                        Singup.signup(txt1, txt2, txt3, txt4, n);
                        JFrame jFrame = new JFrame();
                        JOptionPane.showMessageDialog(jFrame, "New account has been approved. Try to login");
                        dispose();
                        new AccLogin().buildFrame();
                    }
                } else {
                    JFrame jFrame = new JFrame();
                    JOptionPane.showMessageDialog(jFrame, "This Username already exist! Try Again");
                }

            }
        };
        button1.addActionListener(buttonListener);

        setVisible(true);
    }
}


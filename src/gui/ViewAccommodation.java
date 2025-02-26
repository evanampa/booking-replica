package gui;

import api.Delete;
import api.ViewAcc;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ViewAccommodation extends JFrame{
    private JPanel viewacc;
    JLabel label1, label2,label3,label4;
    private JLabel labelto1,labelto2,labelto3;
    private JButton button1,button2;
    private JTextPane text1,text2,text3;

    ActionListener buttonListener1,buttonListener2;

    public void buildFrame(String r){
        setContentPane(viewacc);
        String[] row=r.split("/");
        setTitle(row[0]);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(900, 800);
        this.setLocationRelativeTo(null);
        setResizable(true);

        String[] info=new ViewAcc().findFull(row[0]);
        String id=info[0],user=info[1],name=info[2],type=info[3],location=info[4],benefits=info[5];

        labelto1.setText(name);
        labelto2.setText(type);
        labelto3.setText(location);

        String[] paragraph=new ViewAcc().showParagraph(id);
        text1.setText(paragraph[1]);
        text2.setText(benefits);

        //review box
        String[] reviews=new ViewAcc().findreview(id);

        String fulltext = " ";
        int j=1;
        String[] rp=new ViewAcc().findrp(id);//reviewparagraph

        if(reviews != null) {
            for (int i = 1; i < reviews.length; i = i + 2) {
                String firstname = new ViewAcc().findname(reviews[i]);
                if(rp==null){
                    fulltext = fulltext + "\n" + firstname + " rated this accommodation with " + reviews[i + 1] + " stars:\n" + "\t" + " " + "\n";
                } else {
                    fulltext = fulltext + "\n" + firstname + " rated this accommodation with " + reviews[i + 1] + " stars:\n" + "\t" + rp[j] + "\n";
                }
                j++;
            }
        }
        text3.setText(fulltext);
        //edit button
        buttonListener1 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditAcc().buildFrame(info);
            }
        };
        button1.addActionListener(buttonListener1);
        //delete button
        buttonListener2 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this accommodation?", "Are you sure", JOptionPane.YES_NO_OPTION, 1);
                    if (confirm == 0) {

                        new Delete().deleteall(id, new File("src\\files\\accommondations"));
                        new Delete().deleteall(id, new File("src\\files\\paragraphsAcc"));
                        new Delete().deleteall(id, new File("src\\files\\reviews"));
                        new Delete().deleteall(id, new File("src\\files\\reviewparagraph"));

                        JFrame jFrame = new JFrame();
                        JOptionPane.showMessageDialog(jFrame, "Accommodation was deleted");
                        dispose();
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        };
        button2.addActionListener(buttonListener2);

        setVisible(true);
    }
}

package gui;
import api.Editacc;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class EditAcc extends JFrame{
    private JPanel add;
    private JLabel edittitle,l1,l2,l3,exp3,exp1,exp2,l4,exp4,l5,exp5;
    private JTextField text1,text2;
    private JComboBox box1;
    private JCheckBox view,hairdryer,washingmachine,dryer,tv,fireplace,ac,ethernet,oven,yard,centralHeating,wifi,fridge,kitchen,kitchenware,parkout,balcony,parkprive;
    private JButton button;
    private JTextPane text3;
    private JPanel editacc;

    ActionListener buttonListener;
    ArrayList<String> bens = new ArrayList<>();

    public void checkboxes(){

        if (view.isSelected()) { bens.add("View");}
        if (hairdryer.isSelected()) {bens.add("Hair Dryer");}
        if (washingmachine.isSelected()) {bens.add("Washing Machine");}
        if (dryer.isSelected()) {bens.add("Dryer");}
        if (tv.isSelected()) {bens.add("TV");}
        if (fireplace.isSelected()) {bens.add("Fireplace");}
        if (ac.isSelected()) {bens.add("AC");}
        if (centralHeating.isSelected()) {bens.add("Central Heating");}
        if (wifi.isSelected()) {bens.add("WiFi");}
        if (ethernet.isSelected()) {bens.add("Ethernet");}
        if (kitchen.isSelected()) {bens.add("Kitchen");}
        if (fridge.isSelected()) {bens.add("Fridge");}
        if (oven.isSelected()) {bens.add("Oven");}
        if (kitchenware.isSelected()) {bens.add("Kitchenware");}
        if (balcony.isSelected()) {bens.add("Balcony");}
        if (yard.isSelected()) {bens.add("Yard");}
        if (parkprive.isSelected()) {bens.add("Free Private Parking");}
        if (parkout.isSelected()) {bens.add("Parking Outside");}

    }

    public void buildFrame(String[] info) {
        setContentPane(editacc);
        setTitle("Edit Accommodation");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(600, 800);
        this.setLocationRelativeTo(null);
        setResizable(true);

        text1.setText(info[2]);
        text2.setText(info[4]);
        box1.setSelectedItem(info[3]);
        String p=new Editacc().getparagraph(info[0]);
        text3.setText(p);

        String[] benefits=info[5].split(",");

        for(int i=0;i<benefits.length;i++){
            if(benefits[i].equals("View")){view.setSelected(true);}
            else if(benefits[i].equals("Hair Dryer")){hairdryer.setSelected(true);}
            else if(benefits[i].equals("Washing Machine")){washingmachine.setSelected(true);}
            else if(benefits[i].equals("Dryer")){dryer.setSelected(true);}
            else if(benefits[i].equals("TV")){tv.setSelected(true);}
            else if(benefits[i].equals("Fireplace")){fireplace.setSelected(true);}
            else if(benefits[i].equals("AC")){ac.setSelected(true);}
            else if(benefits[i].equals("Central Heating")){centralHeating.setSelected(true);}
            else if(benefits[i].equals("WiFi")){wifi.setSelected(true);}
            else if(benefits[i].equals("Ethernet")){ethernet.setSelected(true);}
            else if(benefits[i].equals("Kitchen")){kitchen.setSelected(true);}
            else if(benefits[i].equals("Fridge")){fridge.setSelected(true);}
            else if(benefits[i].equals("Oven")){oven.setSelected(true);}
            else if(benefits[i].equals("Kitchenware")){kitchenware.setSelected(true);}
            else if(benefits[i].equals("Balcony")){balcony.setSelected(true);}
            else if(benefits[i].equals("Yard")){yard.setSelected(true);}
            else if(benefits[i].equals("Free Private Parking")){parkprive.setSelected(true);}
            else if(benefits[i].equals("Parking Outside")){parkout.setSelected(true);}

        }

        buttonListener = l -> {

            checkboxes();
            try {
                new Editacc().changebenefits(info[0],bens);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(!text1.getText().equals(info[2])){
                try {
                    new Editacc().changename(info[0],text1.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(!text2.getText().equals(info[4])){
                try {
                    new Editacc().changelocation(info[0],text2.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(!Objects.equals(box1.getSelectedItem(), info[3])){
                try {
                    new Editacc().changetype(info[0],(String) box1.getSelectedItem());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(!text3.getText().equals(p)){
                try {
                    new Editacc().replaceLines(info[0], text3.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            JFrame jFrame = new JFrame();
            JOptionPane.showMessageDialog(jFrame, "Your accommodation changed successfully");
            dispose();
        };

        button.addActionListener(buttonListener);
        setVisible(true);
    }
}

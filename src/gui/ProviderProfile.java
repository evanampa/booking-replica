package gui;
import api.Provider;

import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;


public class ProviderProfile extends JFrame {

    JPanel providerpanel;
    JTabbedPane tabbedPane1;
    JPanel profile, add, panel1;
    JLabel label1, label2, labelA, label4, labelB,label3,edittitle, l1, l2, l3, l4, l5;
    JTextField text1, text2;
    JTextPane text3;
    JComboBox box1;
    JLabel exp1, exp2, exp3, exp4, exp5;
    JCheckBox view, hairdryer, washingmachine, dryer, tv, fireplace, ac, ethernet, oven, yard, centralHeating, wifi, fridge, kitchen, kitchenware, balcony, parkout, parkprive;
    JButton button;
    JList list1;
    private JButton logOutButton;

    ActionListener buttonListener;
    ArrayList<String> benefits = new ArrayList<>();
    String[] listelement=new String[100];
    int n=0,index=0;
    double avg=0.0;

    String row;

    public ProviderProfile() {
        list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                list1.setSelectedIndex(list1.locationToIndex(e.getPoint()));
                row=list1.getSelectedValue().toString();
                index=list1.getSelectedIndex();
                if(SwingUtilities.isLeftMouseButton(e) && list1.locationToIndex(e.getPoint())==index){
                    if(!list1.isSelectionEmpty()){
                        new ViewAccommodation().buildFrame(row);
                    }
                }
                super.mouseClicked(e);
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame jFrame = new JFrame();
                JOptionPane.showMessageDialog(jFrame, "You have been log out successfully!");
                System.exit(0);
            }
        });
    }

    public void addcheckboxes(){

        view.addActionListener(e -> { if (view.isSelected()) {benefits.add("View"); }});
        hairdryer.addActionListener(e -> {if (hairdryer.isSelected()) {benefits.add("Hair Dryer");}});
        washingmachine.addActionListener(e -> {if (washingmachine.isSelected()) {benefits.add("Washing Machine");}});
        dryer.addActionListener(e -> {if (dryer.isSelected()) {benefits.add("Dryer");}});
        tv.addActionListener(e -> {if (tv.isSelected()) {benefits.add("TV");}});
        fireplace.addActionListener(e -> {if (fireplace.isSelected()) {benefits.add("Fireplace");}});
        ac.addActionListener(e -> {if (ac.isSelected()) {benefits.add("AC");}});
        centralHeating.addActionListener(e -> {if (centralHeating.isSelected()) {benefits.add("Central Heating");}});
        wifi.addActionListener(e -> {if (wifi.isSelected()) {benefits.add("WiFi");}});
        ethernet.addActionListener(e -> {if (ethernet.isSelected()) {benefits.add("Ethernet");}});
        kitchen.addActionListener(e -> {if (kitchen.isSelected()) {benefits.add("Kitchen");}});
        fridge.addActionListener(e -> {if (fridge.isSelected()) {benefits.add("Fridge");}});
        oven.addActionListener(e -> {if (oven.isSelected()) {benefits.add("Oven");}});
        kitchenware.addActionListener(e -> {if (kitchenware.isSelected()) {benefits.add("Kitchenware");}});
        balcony.addActionListener(e -> {if (balcony.isSelected()) {benefits.add("Balcony");}});
        yard.addActionListener(e -> {if (yard.isSelected()) {benefits.add("Yard");}});
        parkprive.addActionListener(e -> {if (parkprive.isSelected()) {benefits.add("Free Private Parking");}});
        parkout.addActionListener(e -> {if (parkout.isSelected()) {benefits.add("Parking Outside");}});
    }

    public void buildFrame(String user) {
        setContentPane(providerpanel);
        setTitle("Provider Profile");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 800);
        this.setLocationRelativeTo(null);
        setResizable(false);

        //PROFILE
        int[] p=new int[200];
        int lng=0;
        String[] userids= new Provider().findmyids(user);
        String[] reviews=new Provider().findreviews(userids);
        for (int i = 0; reviews[i] != null; i++) {
            int[] p0 = new int[20];
            int a1 = 0;
            String[] r=reviews[i].split("/");
            for (int j = 2; j < r.length; j = j + 2) {
                p0[a1] = Integer.parseInt(r[j]);
                a1++;
            }
            for (int k = 0; k < a1; k++) {
                p[lng] = p0[k];
                lng++;
            }
        }
        int[] p1=new Provider().calculate(p);
        n= p1[0];

        avg=p1[0]/(p1[1]*1.0);
        String str = String. format("%.1f",avg);

        labelA.setText(String.valueOf(n));
        labelB.setText(str);

        int a=0;
        for(int i=0;userids[i]!=null;i++)//1,2,3
        {

            String[] myAcc=new Provider().showAccommodations(userids[i]);
            String average= new Provider().onlyAvg(userids[i]);
            String data = myAcc[2]+" / "+myAcc[3]+" / "+myAcc[4]+" / "+average ;
            listelement[a]=data;
            a++;
        }

        list1.setListData(listelement);

        //ADD
        addcheckboxes();
        buttonListener = e -> {
            String infos=" ";
            if(!text1.getText().equals("") && !text2.getText().equals("") && !text3.getText().equals("")){
                if(benefits.isEmpty()){ benefits.add(" ");}

                new Provider().firstInfo(user, text1.getText(), (String) box1.getSelectedItem(), text2.getText(), benefits);
                infos=text3.getText();

                new Provider().addinfo(infos);
                JFrame jFrame = new JFrame();
                JOptionPane.showMessageDialog(jFrame, "The accommodation was successfully added!");
            }
            else {
                JFrame jFrame = new JFrame();
                JOptionPane.showMessageDialog(jFrame, "Please fill all the required boxes");
            }
        };
        button.addActionListener(buttonListener);

        setVisible(true);
    }
}

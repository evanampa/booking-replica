package gui;

import api.Provider;
import api.User;
import api.ViewAcc;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class userMain extends JFrame {
     JTabbedPane tabbedPane1;
     JTextField name;
     JTextField location;
     JPanel userPanel;
     JButton searchButton;
     JCheckBox view, hairdryer, washingmachine, dryer, tv, fireplace, ac, centralHeating, wifi, ethernet, kitchen;
     JCheckBox fridge, oven, kitchenware, balcony, yard, parkprive, parkout;
     JComboBox typeAccomodation, comboBox1;
     JScrollPane results;
     JList list1;
     JLabel labelchang1, typeAcc, labelchang2, adressAcc, labelchang3, reviews;
     JTextPane textPaneParagraph;
     JLabel rateLabel;
     JTextPane leaveReview;
     JTextPane textPaneProvides;
     JPanel JPanel2;
     JButton saveRatingButton;
     JTextPane textPaneReviews;
     JLabel reviewStatics;
     JLabel labelStatics1;
     JLabel numberReviews;
     JLabel labelStatics2;
     JLabel averageNumber;
    JButton logOutButton;
    DefaultListModel lm = null;
     JPopupMenu popup = new JPopupMenu();
     int flag = 0;
     int temp =0, temp2=0;

    ArrayList<String> benefits = new ArrayList<String>();
    String[] listelement = new String[100];

    public void viewAccomodation(String r) throws IOException {

        JPanel2.revalidate();
        JPanel2.repaint();

        this.setLocationRelativeTo(null);
        setResizable(false);

        String[] row=r.split("/");
        String[] info=new ViewAcc().findFull(row[0]);
        String id=info[0], user=info[1],name=info[2],type=info[3],location=info[4],benefits=info[5];

        labelchang1.setText(name);
        labelchang2.setText(type);
        labelchang3.setText(location);

        rateLabel.setText("Rate " + name);

        String[] paragraph=new ViewAcc().showParagraph(id);
        textPaneParagraph.setText(paragraph[1]);
        textPaneProvides.setText(benefits);

        String avg = new User().findReview(id);
        String count = new User().findReviewCount(id);

        averageNumber.setText(avg);
        numberReviews.setText(count);

        String[] reviews=new ViewAcc().findreview(id);

        String fulltext = " ";
        int j=1;
        String[] rp=new ViewAcc().findrp(id);
        if(reviews != null){
            for (int i = 1; i < reviews.length; i = i + 2) {
                String firstname = new ViewAcc().findname(reviews[i]);
                if (rp == null) {
                    fulltext = fulltext + "\n" + firstname + " rated this accommodation with " + reviews[i + 1] + " stars:\n" + "\t" + " " + "\n";
                } else {
                    fulltext = fulltext + "\n" + firstname + " rated this accommodation with " + reviews[i + 1] + " stars:\n" + "\t" + rp[j] + "\n";
                }
                j++;
            }
        }

        textPaneReviews.setText(fulltext);

    }

    public void addPopup(){

        JMenuItem popview = new JMenuItem("View Accommodation");
        popup.add(popview);
        popview.addActionListener(e -> { tabbedPane1.setSelectedIndex(1);
            String value = (String) list1.getSelectedValue();
            try {
                viewAccomodation((String) list1.getSelectedValue());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        }  );


    }


    private void clearFields(){
        name.setText(null);
        location.setText(null);
        typeAccomodation.setSelectedIndex(0);

        view.setSelected(false);
        hairdryer.setSelected(false);
        washingmachine.setSelected(false);
        dryer.setSelected(false);
        tv.setSelected(false);
        fireplace.setSelected(false);
        ac.setSelected(false);
        ethernet.setSelected(false);
        oven.setSelected(false);
        yard.setSelected(false);
        centralHeating.setSelected(false);
        wifi.setSelected(false);
        fridge.setSelected(false);
        kitchen.setSelected(false);
        kitchenware.setSelected(false);
        balcony.setSelected(false);
        parkout.setSelected(false);
        parkprive.setSelected(false);

    }


    String row;
    int n=0,index=0;

    public userMain() {

            list1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    list1.setSelectedIndex(list1.locationToIndex(e.getPoint()));
                    row=list1.getSelectedValue().toString();
                    index=list1.getSelectedIndex();
                    if(SwingUtilities.isLeftMouseButton(e) && list1.locationToIndex(e.getPoint())==index){
                        if(!list1.isSelectionEmpty()){ popup.show(list1,e.getX(),e.getY());}
                    }
                    super.mouseClicked(e);
                }
            });

        //ADD

        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.isSelected()) {
                    benefits.add("View");
                }

            }
        });

        hairdryer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hairdryer.isSelected()) {
                    benefits.add("Hair Dryer");
                }

            }
        });

        washingmachine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (washingmachine.isSelected()) {
                    benefits.add("Washing Machine");
                }

            }
        });

        dryer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dryer.isSelected()) {
                    benefits.add("Dryer");
                }

            }
        });

        tv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tv.isSelected()) {
                    benefits.add("TV");
                }

            }
        });

        fireplace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fireplace.isSelected()) {
                    benefits.add("Fireplace");
                }

            }
        });

        ac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ac.isSelected()) {
                    benefits.add("AC");
                }

            }
        });

        centralHeating.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (centralHeating.isSelected()) {
                    benefits.add("Central Heating");
                }

            }
        });

        wifi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (wifi.isSelected()) {
                    benefits.add("WiFi");
                }

            }
        });

        ethernet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ethernet.isSelected()) {
                    benefits.add("Ethernet");
                }

            }
        });

        kitchen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (kitchen.isSelected()) {
                    benefits.add("Kitchen");
                }

            }
        });

        fridge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fridge.isSelected()) {
                    benefits.add("Fridge");
                }

            }
        });

        oven.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (oven.isSelected()) {
                    benefits.add("Oven");
                }

            }
        });

        kitchenware.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (kitchenware.isSelected()) {
                    benefits.add("Kitchenware");
                }

            }
        });

        balcony.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (balcony.isSelected()) {
                    benefits.add("Balcony");
                }

            }
        });

        yard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (yard.isSelected()) {
                    benefits.add("Yard");
                }

            }
        });

        parkprive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (parkprive.isSelected()) {
                    benefits.add("Free Private Parking");
                }

            }
        });

        parkout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (parkout.isSelected()) {
                    benefits.add("Parking Outside");
                }

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


    public void buildFrame(String username) {
        setContentPane(userPanel);
        setTitle("Home");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1150, 750);
        this.setLocationRelativeTo(null);
        setResizable(false);

        getRootPane().setDefaultButton(searchButton);

        addPopup();

        saveRatingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                if(leaveReview.getText().length()!=0)
                {
                    temp=1;
                    try {
                        new User().leaveReview(new User().findAccId((String) list1.getSelectedValue()), username, comboBox1.getSelectedItem().toString());
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }



                    if(leaveReview.getText().length()>500)
                    {
                        JFrame jFrame = new JFrame();
                        JOptionPane.showMessageDialog(jFrame, "Please leave a review under 500 characters.");
                    }
                    else
                    {

                        try {
                            new User().leaveReviewParag(new User().findAccId((String) list1.getSelectedValue()), leaveReview.getText());
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }

                        JFrame jFrame = new JFrame();
                        JOptionPane.showMessageDialog(jFrame, "The review has successfully added.");

                        leaveReview.setText(null);


                    }

                }


                if(temp==0 && list1.getSelectedValue() == null)
                {
                    //temp2=1;
                    JFrame jFrame = new JFrame();
                    JOptionPane.showMessageDialog(jFrame, "Choose an accommodation first!");
                }

                if(leaveReview.getText().length()==0 && list1.getSelectedValue() != null)
                {
                    JFrame jFrame = new JFrame();
                    JOptionPane.showMessageDialog(jFrame, "Please leave a review!");
                }


                }


        });



        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                int sumAcc = new User().findAccSum();

                if(name.getText().length()==0){

                    if(list1!=null)
                        list1.removeAll();

                    if(location.getText().length() != 0 && benefits.size()!=0)
                    {
                        flag=1;

                        for(int k=0;k<listelement.length;k++)
                        {
                            listelement[k] = null;
                        }

                        list1.setListData(listelement);


                        listelement[0]="";

                        int a=0;
                        String[] ids = new String[100];


                        ids = new User().findAddress(location.getText());
                        String[] types = new User().findType((String) typeAccomodation.getSelectedItem());
                        String[] bens = new User().findBenefits(benefits);

                        for(int i=0;i<new User().findAccSum();i++)
                        {
                            for (String type : types)
                                for (String ben : bens)
                                    if (ids[i] != null && ids[i].equals(type) && ids[i].equals(ben)) {
                                        String[] searchAcc = new User().showSearch2(ids[i]);

                                        String average = null;
                                        try {
                                            average = new User().findReview(ids[i]);
                                        } catch (IOException ioException) {
                                            ioException.printStackTrace();
                                        }

                                        String data = searchAcc[2] + " / " + searchAcc[3] + " / " + searchAcc[4] + " / Rated with " + average + " stars";
                                        listelement[a] = data;

                                        a++;
                                    }

                        }


                        list1.setListData(listelement);

                        if(listelement[0]=="")
                        {
                            JFrame jFrame = new JFrame();
                            JOptionPane.showMessageDialog(jFrame, "Zero results for this search. Please try again " +
                                    "with different options.");
                        }

                        clearFields();

                    }

                    if(location.getText().length() != 0)
                    {
                        flag=1;

                        for(int k=0;k<listelement.length;k++)
                        {
                            listelement[k] = null;
                        }

                        list1.setListData(listelement);

                        listelement[0]="";


                        int a=0;
                        String[] ids = new String[100];


                        ids = new User().findAddress(location.getText());
                        String[] types = new User().findType((String) typeAccomodation.getSelectedItem());

                        for(int i=0;i<new User().findAccSum();i++)
                        {
                            for (String type : types)
                                    if (ids[i] != null && ids[i].equals(type)) {
                                        String[] searchAcc = new User().showSearch2(ids[i]);

                                        String average = null;
                                        try {
                                            average = new User().findReview(ids[i]);
                                        } catch (IOException ioException) {
                                            ioException.printStackTrace();
                                        }

                                        String data = searchAcc[2] + " / " + searchAcc[3] + " / " + searchAcc[4] + " / Rated with " + average + " stars";
                                        listelement[a] = data;

                                        a++;
                                    }

                        }


                        list1.setListData(listelement);

                        if(listelement[0]=="")
                        {
                            JFrame jFrame = new JFrame();
                            JOptionPane.showMessageDialog(jFrame, "Zero results for this search. Please try again " +
                                    "with different options.");
                        }

                        clearFields();

                    }

                    if(flag==0){

                        JFrame jFrame = new JFrame();
                        JOptionPane.showMessageDialog(jFrame, "Please fill the location or the name of the " +
                                "accommodation you want.");
                    }

                    flag=0;

                }

                else
                {
                    for(int k=0;k<listelement.length;k++)
                    {
                        listelement[k] = null;
                    }

                    list1.setListData(listelement);

                    listelement[0]="";

                    int a=0;
                    String[] ids = new String[100];


                    ids = new User().findName(name.getText());
                    for(int i=0;i<new User().findAccSum();i++)
                    {

                        if (ids[i] != null) {
                            String[] searchAcc = new User().showSearch2(ids[i]);

                            String average = null;
                            try {
                                average = new User().findReview(ids[i]);
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }

                            String data = searchAcc[2] + " / " + searchAcc[3] + " / " + searchAcc[4] + " / Rated with " + average + " stars";
                            listelement[a] = data;

                            a++;
                        }

                    }

                    list1.setListData(listelement);
                    clearFields();

                    if(listelement[0]=="")
                    {
                        JFrame jFrame = new JFrame();
                        JOptionPane.showMessageDialog(jFrame, "Zero results for this search. Please try again " +
                                "with different options.");
                    }

                }


            }
        });


        setVisible(true);
    }

}

package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//main class to define the bicycle interface
public class Bicycle extends JFrame{

    private JTextField txtName = new JTextField(30);
    private JTextField txtNoOfDay = new JTextField(30);
    private JRadioButton Model1_rb = new JRadioButton("Model 1");
    private JRadioButton Model2_rb = new JRadioButton("Model 2");
    private JRadioButton Model3_rb = new JRadioButton("Model 3");
    private JCheckBox seniorCheckBox = new JCheckBox("Senior Citizen");
    private JCheckBox memberCheckBox = new JCheckBox("Member");
    private ImageIcon icon = new ImageIcon("");

    private JLabel output = new JLabel();
    private JButton btnCompute = new JButton("Compute");
    private JLabel imgIcon = new JLabel();



    public static void main(String[] args)
    {
	new Bicycle();
    }


    public Bicycle()
    {

        setLayout(new BorderLayout());
        setTitle("Bicycle Rental");
        setSize(600,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //To store name and number of days
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(3,3,15,15));
        topPanel.add(new JLabel("Customer Name:"));
        topPanel.add(txtName);
        topPanel.add(new JLabel("Number of days"));
        topPanel.add(txtNoOfDay);
        add(topPanel, BorderLayout.NORTH);


        //To store ratio buttons and check boxes

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2,4));
        imgIcon.setPreferredSize(new Dimension(600,600));
        imgIcon.setIcon(icon);
        centerPanel.add(imgIcon);
        ButtonGroup group = new ButtonGroup();
        group.add(Model1_rb);
        group.add(Model2_rb);
        group.add(Model3_rb);

        // add action listener to the radio buttons to update the model
        Model1_rb.addActionListener(new RadioListener());
        Model2_rb.addActionListener(new RadioListener());
        Model3_rb.addActionListener(new RadioListener());

        centerPanel.add(Model1_rb);
        centerPanel.add(Model2_rb);
        centerPanel.add(Model3_rb);
        centerPanel.add(new JLabel());
        centerPanel.add(seniorCheckBox);
        centerPanel.add(memberCheckBox);
        add(centerPanel, BorderLayout.CENTER);


        // To hold total and compute button on buttom
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(3,4,15,15));
        bottomPanel.add(new JLabel("Total"),SwingConstants.CENTER);
        bottomPanel.add(output);

        // add action listener to button to compute the cost
        btnCompute.addActionListener(new ButtonListener());
        bottomPanel.add(btnCompute);
        add(bottomPanel,BorderLayout.SOUTH);
        setVisible(true);

    }

    // class for Radio buttons

    private class RadioListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
           JRadioButton rad = (JRadioButton)e.getSource();
           if(rad== Model1_rb)
           {
               imgIcon.setIcon(new ImageIcon("model1.jpg"));
           }
            if(rad== Model2_rb)
            {
                imgIcon.setIcon(new ImageIcon("model2.png"));
            }
            if(rad== Model3_rb)
            {
                imgIcon.setIcon(new ImageIcon("model3.png"));
            }
        }
    }


    // class for the Button

    private class ButtonListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {

            //declaring required variables w.r.t discounts
            final double DISCOUNT1 = 0.08;
            final double DISCOUNT2 = 0.15;
            double discount = 0;
            double seniorDiscount = .10;
            double memberDiscount = .12;
            final int MODEL1 = 14;
            final int MODEL2 = 12;
            final int MODEL3 = 10;
            double cost=0;






            try {
                String name = txtName.getText();
                int numDays = Integer.parseInt(txtNoOfDay.getText());
                //find the discount percent
                if(numDays>=6 && numDays<=10)
                    discount=DISCOUNT1;
                else if (numDays>10)
                    discount=DISCOUNT2;

                //find the user selected radio button and calculate cost

                //calculating cost

                if(Model1_rb.isSelected())
                {
                    cost = numDays*MODEL1-(numDays*MODEL1*discount);
                }
                else if(Model2_rb.isSelected())
                {
                    cost = numDays*MODEL2-(numDays*MODEL2*discount);
                }
                else if(Model3_rb.isSelected())
                {
                    cost = numDays*MODEL3-(numDays*MODEL3*discount);
                }
                if (seniorCheckBox.isSelected())
                {

                    cost = cost - cost*seniorDiscount;
                }
                else if (memberCheckBox.isSelected())
                {
                    cost = cost - cost*memberDiscount;
                }
                else if (memberCheckBox.isSelected() && seniorCheckBox.isSelected())
                {
                    cost = cost - (cost*memberDiscount) - (cost*seniorDiscount);
                    //System.out.println("Both selected");
                }
                //display the output cost message
                output.setText(""+cost);
                JOptionPane.showMessageDialog(null,
                        "Name: "+name
                                +"\nNumber of Days: "+numDays
                        +"\nTotal cost,$: "+cost,
                                "Total Cost",JOptionPane.INFORMATION_MESSAGE);

            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null,
                        "Enter Valid input","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }




}

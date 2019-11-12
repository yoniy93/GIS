package model;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.io.File;

import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.Color;

public class AddNewSite extends JFrame {

    private static final long serialVersionUID = 1L;
    public static String getImagePath (String fileName){
        return new File("backgrounds/"+fileName).getAbsolutePath();}

    private ImageIcon imageForBG=new ImageIcon(getImagePath("pic2.jfif"));
    private JLabel backGround=new JLabel(imageForBG);
    JTextField textField=new JTextField();
    JLabel lblNewLabel = new JLabel("Help us Grow...");
    JLabel lblAddNewSport = new JLabel("Add New Sport Site:");
    JLabel lblType = new JLabel("TYPE");
    JLabel lblName = new JLabel("NAME");
    JLabel lblNewLabel_1 = new JLabel("PARKING ");
    JRadioButton rdbtnNewRadioButton = new JRadioButton("YES");
    JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("NO");
    JFormattedTextField nameFormattedTextField = new JFormattedTextField();
    JLabel lblNewLabel_2 = new JLabel("PHONE ");
    JButton btnSend = new JButton("SEND");
    final JComboBox<String> comboBox_1 = new JComboBox<String>();
    JButton btnBack = new JButton("CANCEL");
    ButtonGroup parkingGroup = new ButtonGroup();

    public AddNewSite() {

        this.setResizable(false);
        this.setBounds(100,100,511,367);
        addComponents();
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                new HomePage();
                e.getWindow().dispose();
            }
        });
        this.setVisible(true); }

    private boolean CheckAllFields()
    {
        if(nameFormattedTextField.getText().equals("") || textField.getText().equals("") ||
                (!rdbtnNewRadioButton.isSelected()&& !rdbtnNewRadioButton_1.isSelected()))
            return false;
        return true;
    }

    private void addComponents() {

        parkingGroup.add(rdbtnNewRadioButton);
        parkingGroup.add(rdbtnNewRadioButton_1);

        getContentPane().setBackground(Color.WHITE);
        getContentPane().setLayout(null);
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBackground(Color.BLACK);

        lblNewLabel.setFont(new Font("Stencil", Font.PLAIN, 27));
        lblNewLabel.setBounds(10, 11, 227, 27);
        getContentPane().add(lblNewLabel);
        lblAddNewSport.setForeground(Color.WHITE);


        lblAddNewSport.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
        lblAddNewSport.setBounds(10, 49, 175, 14);
        getContentPane().add(lblAddNewSport);
        lblType.setForeground(Color.WHITE);

        lblType.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
        lblType.setBounds(29, 93, 48, 14);
        getContentPane().add(lblType);
        lblName.setForeground(Color.WHITE);


        lblName.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
        lblName.setBounds(29, 130, 48, 14);
        getContentPane().add(lblName);


        textField.setBounds(87, 128, 87, 20);
        getContentPane().add(textField);
        textField.setColumns(10);
        lblNewLabel_1.setForeground(Color.WHITE);


        lblNewLabel_1.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
        lblNewLabel_1.setBounds(29, 165, 69, 14);
        getContentPane().add(lblNewLabel_1);


        rdbtnNewRadioButton.setBounds(105, 162, 62, 23);
        getContentPane().add(rdbtnNewRadioButton);


        rdbtnNewRadioButton_1.setBounds(169, 162, 62, 23);
        getContentPane().add(rdbtnNewRadioButton_1);
        lblNewLabel_2.setForeground(Color.WHITE);


        lblNewLabel_2.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
        lblNewLabel_2.setBounds(29, 200, 69, 14);
        getContentPane().add(lblNewLabel_2);


        nameFormattedTextField.setBounds(87, 198, 87, 20);
        getContentPane().add(nameFormattedTextField);

        btnSend.addActionListener(e->popUpMessage());

        btnSend.setBackground(Color.WHITE);
        btnSend.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 14));
        btnSend.setBounds(380, 283, 89, 23);
        getContentPane().add(btnSend);

        comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"", "Tennis Courts", "FootBall Courts", "Gym", "Pool", "Bycile routs "}));
        comboBox_1.setBounds(87, 90, 87, 22);
        getContentPane().add(comboBox_1);
        btnBack.setBackground(Color.WHITE);


        btnBack.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
        btnBack.setBounds(270, 283, 100, 23);
        getContentPane().add(btnBack);
        btnBack.addActionListener(e->backAction());

        getContentPane().add(backGround);
        backGround.setBounds(0,0,495,328);

    }

    private void backAction()
    {
        this.dispose();
        new HomePage();
    }

    private void popUpMessage() {
        if(CheckAllFields())
        {
            JOptionPane.showMessageDialog(this, "Request complete, thank you");
            //Enter to DB!!!!
            this.dispose();
            new HomePage();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Not All Fields Are Filled , Please Try Again");
        }

    }
}
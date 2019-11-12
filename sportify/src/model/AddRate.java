package model;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Vector;

public class AddRate extends JFrame
{
    private static final long serialVersionUID = 1L;

    public static String getImagePath (String fileName){
        return new File("backgrounds/"+fileName).getAbsolutePath();}
    StarRater starRater = new StarRater(5, 1, 1);
    ImageIcon imageForBG=new ImageIcon(getImagePath("pic1.jfif"));
    JLabel backGround=new JLabel(imageForBG);
    JButton addButton = new JButton("Add Your Rate");
    JLabel nameLabel=new JLabel("Name:");
    JTextArea textAreaName=new JTextArea();
    JLabel labelName = new JLabel("NAME");
    JFormattedTextField formattedTextField = new JFormattedTextField();
    JLabel lblTitle = new JLabel("REPORT FORM");
    JLabel labelChooseSite = new JLabel("CHOOSE SITE");
    JComboBox<String> comboBoxSites = new JComboBox<String>();
    JButton btnCancel = new JButton("CANCEL");
    JLabel labelRate = new JLabel("RATE");
    JLabel labelComment = new JLabel("COMMENT");
    JButton btnAdd = new JButton("ADD");
    JTextArea textArea = new JTextArea();
    JLabel lblTypeSite = new JLabel("Type");
    JComboBox comboBoxType = new JComboBox();

    public AddRate()
    {
        this.setResizable(false);
        this.setBounds(100,100,447,441);
        addComponent();
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                new HomePage();
                e.getWindow().dispose();
            }
        });
        addActionLisenners();
        setVisible(true);
    }

    public void addComponent()
    {
        getContentPane().setLayout(null);


        starRater.setBounds(117, 124, 105, 22);
        getContentPane().add(starRater);


        labelName.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
        labelName.setBackground(Color.GRAY);
        labelName.setBounds(47, 91, 60, 24);

        getContentPane().add(labelName);
        formattedTextField.setBounds(117, 93, 105, 20);

        getContentPane().add(formattedTextField);

        labelRate.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
        labelRate.setBounds(47, 126, 60, 18);
        getContentPane().add(labelRate);

        labelComment.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
        labelComment.setBounds(47, 237, 89, 14);
        getContentPane().add(labelComment);


        textArea.setBounds(139, 239, 200, 87);
        getContentPane().add(textArea);


        btnAdd.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
        btnAdd.setBounds(106, 357, 105, 23);
        getContentPane().add(btnAdd);
        btnAdd.addActionListener(e->AddComment());
        lblTitle.setFont(new Font("Aharoni", Font.BOLD, 31));
        lblTitle.setBounds(106, 25, 217, 33);

        getContentPane().add(lblTitle);
        labelChooseSite.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
        labelChooseSite.setBounds(47, 203, 92, 14);

        getContentPane().add(labelChooseSite);
        comboBoxSites.setEnabled(false);
        comboBoxSites.setBounds(150, 196, 115, 22);
        getContentPane().add(comboBoxSites);

        btnCancel.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
        btnCancel.setBounds(236, 357, 103, 23);
        getContentPane().add(btnCancel);
        btnCancel.addActionListener(e->cancelAction());

        getContentPane().add(lblTypeSite);
        comboBoxType.setModel(new DefaultComboBoxModel(new String[] {"", "FootBall Courts", "Tennis Courts", "Gym", "Pool"}));
        comboBoxType.setBounds(117, 157, 105, 22);
        comboBoxType.addActionListener(e->AddValuesToComboBox());
        getContentPane().add(comboBoxType);

        getContentPane().add(backGround);
        backGround.setBounds(0,0,431,402);
        lblTypeSite.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
        lblTypeSite.setBounds(47, 157, 48, 22);

    }

    private void AddValuesToComboBox()
    {
        String type= comboBoxType.getSelectedItem().toString();
        if(!type.equals(""))
        {

            if(type.equals("FootBall Courts"))
            {
                //take all football courts from DB by name
                Vector <String> footBallCourts=new Vector<String> ();
                for(int i=0;i<footBallCourts.size();i++)
                {
                    comboBoxSites.addItem(footBallCourts.get(i));
                }
            }
            else if(type.equals("Tennis Courts"))
            {
                //take all tennisCourts from DB by name
                Vector <String> tennisCourts=new Vector<String> ();
                for(int i=0;i<tennisCourts.size();i++)
                {
                    comboBoxSites.addItem(tennisCourts.get(i));
                }

            }
            else if (type.equals("Gym"))
            {
                //take all gym from DB by name
                Vector <String> gym =new Vector<String> ();
                for(int i=0;i<gym.size();i++)
                {
                    comboBoxSites.addItem(gym.get(i));
                }

            }
            else
            {
                //take all pools from DB by name
                Vector <String> pool =new Vector<String> ();
                for(int i=0;i<pool.size();i++)
                {
                    comboBoxSites.addItem(pool.get(i));
                }


            }
            comboBoxSites.setEnabled(true);
        }


    }

    private void cancelAction()
    {
        this.dispose();
        new HomePage();
    }

    public void addActionLisenners()
    {
        addButton.addActionListener(e->AddComment());
    }

    public boolean Check()
    {
        if(textAreaName.getText().equals("")|| textArea.getText().equals("")|| comboBoxSites.getSelectedIndex()==0 || comboBoxSites.getSelectedItem().toString().equals(""))
        {
            return false;
        }
        return true;

    }

    public void AddComment()
    {
        if(Check()) {
            JOptionPane.showMessageDialog(this, "Comment entered, thank you");
            this.dispose();
            new HomePage();
            //enter in DB!!
        }
        else
            JOptionPane.showMessageDialog(this, "One or more of the Fileds has not been filled correctly");

    }
}
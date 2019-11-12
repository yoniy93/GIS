package model;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class HomePage extends JFrame{

    private static final long serialVersionUID = 1L;
    public static String getImagePath (String fileName){
        return new File("backgrounds/"+fileName).getAbsolutePath();}

    ImageIcon imageForBG=new ImageIcon(getImagePath("pic.jpg"));
    JButton btnPhoto = new JButton("Photo Library");
    JLabel lblWelcomeToSportify = new JLabel("Welcome to SPORTIFY");
    JButton btnMap = new JButton("View map");
    JButton btnRate = new JButton("Give rate to site");
    JButton btnAddSite = new JButton("Add a new site");
    JLabel backGround=new JLabel(imageForBG);

    public HomePage() {
        this.setResizable(false);
        this.setBounds(100,100,451,323);
        addComponents();
        this.setVisible(true);

    }

    private void addComponents() {

        getContentPane().setLayout(null);

        lblWelcomeToSportify.setFont(new Font("MV Boli", Font.BOLD, 25));
        lblWelcomeToSportify.setBounds(66, 11, 312, 41);
        getContentPane().add(lblWelcomeToSportify);

        btnMap.setForeground(Color.WHITE);
        btnMap.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
        btnMap.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    File path=new File("map/index.html");
                    Desktop.getDesktop().browse((path.toURI().toURL()).toURI());
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
        });

        btnMap.setBackground(Color.DARK_GRAY);
        btnMap.setBounds(117, 189, 181, 23);
        getContentPane().add(btnMap);


        btnRate.setForeground(Color.WHITE);
        btnRate.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
        btnRate.setBackground(Color.DARK_GRAY);
        btnRate.addActionListener(e->ActionAddRate());

        btnRate.setBounds(117, 97, 181, 23);
        getContentPane().add(btnRate);

        btnAddSite.setForeground(Color.WHITE);
        btnAddSite.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
        btnAddSite.addActionListener(e->ActionAddNewSite());

        btnPhoto.setBackground(Color.DARK_GRAY);
        btnPhoto.setForeground(Color.WHITE);
        btnPhoto.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
        btnPhoto.setBounds(117, 236, 181, 23);
        getContentPane().add(btnPhoto);
        btnPhoto.addActionListener(e->ActionPhotos());

        btnAddSite.setBackground(Color.DARK_GRAY);
        btnAddSite.setBounds(117, 143, 181, 23);
        getContentPane().add(btnAddSite);
        backGround.setForeground(Color.DARK_GRAY);

        getContentPane().add(backGround);
        backGround.setBounds(0,0,435,284);

    }
    private void ActionPhotos()
    {
        this.dispose();
        new PhotoLibrary();
    }

    private void ActionAddNewSite()
    {
        this.dispose();
        new AddNewSite();
    }

    private void ActionAddRate()
    {
        this.dispose();
        new AddRate();
    }
}
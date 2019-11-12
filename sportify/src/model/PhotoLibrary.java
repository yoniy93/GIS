package model;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JEditorPane;

public class PhotoLibrary extends JFrame{


    private static final long serialVersionUID = 1L;
    JComboBox sitesComboBox = new JComboBox();
    JLabel chooseSiteLbl = new JLabel("SELECT");
    JLabel titleLbl = new JLabel("Sportify Photo Library");

    public PhotoLibrary() {

        getContentPane().setLayout(null);
        this.setResizable(false);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                new HomePage();
                e.getWindow().dispose();
            }
        });

        this.setVisible(true);
        setComponentSizes();
    }

    private void setComponentSizes()
    {
        sitesComboBox.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
        sitesComboBox.setBounds(111, 78, 118, 22);
        getContentPane().add(sitesComboBox);


        chooseSiteLbl.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
        chooseSiteLbl.setBounds(33, 82, 68, 14);
        getContentPane().add(chooseSiteLbl);


        titleLbl.setFont(new Font("Tempus Sans ITC", Font.BOLD, 18));
        titleLbl.setBounds(119, 24, 203, 22);
        getContentPane().add(titleLbl);
    }

}
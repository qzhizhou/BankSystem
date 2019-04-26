package gui;

import bank.atm.ATM;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {
    public MainFrame(){
        this.setTitle("Welcome to Zoey's Bank!");
        this.setSize(1000,750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        this.add(panel);
        placeComponents(panel);
        this.setVisible(true);
    }

    private void placeComponents(JPanel panel){
        panel.setLayout(null);

        JLabel userLabel = new JLabel("To make deposots, withdraw or request loads, click the " +
                "following button.");
        userLabel.setBounds(200,200,700,50);
        panel.add(userLabel);

        JLabel infoLabel = new JLabel("To get information of transactions, please click " +
                "the following button.");
        infoLabel.setBounds(200,360,700,50);
        panel.add(infoLabel);

        this.makedepositButton = new JButton("Operations");
        this.makedepositButton.setBounds(300, 260, 120, 25);
        this.makedepositButton.addActionListener(this);
        panel.add(this.makedepositButton);

        this.reportButton = new JButton("Get Transactions");
        this.reportButton.setBounds(300,400,200,25);
        this.reportButton.addActionListener(this);
        panel.add(this.reportButton);

        this.returnButton = new JButton("Retuen to Home Page.");
        this.returnButton.setBounds(300,600,200,25);
        this.returnButton.addActionListener(this);
        panel.add(this.returnButton);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource()==makedepositButton){
            this.dispose();
            new InputFrame();
        }
        if (e.getSource()==reportButton){
            this.dispose();
            new ReportFrame();
        }
        if(e.getSource()==returnButton){
            this.dispose();
            new InitialFrame();
        }
    }

    private JTextField userText;
    private JButton makedepositButton;
    private JButton reportButton;
    private JButton returnButton;
}

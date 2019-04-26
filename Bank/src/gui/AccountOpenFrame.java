package gui;

import bank.atm.ATM;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountOpenFrame extends JFrame implements ActionListener {
    public AccountOpenFrame(){
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

        JLabel userLabel = new JLabel("Please Input your name:");
        userLabel.setBounds(300,200,150,25);
        panel.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(475,200,165,25);
        panel.add(userText);

        this.createButton = new JButton("Create!");
        this.createButton.setBounds(300, 260, 80, 25);
        this.createButton.addActionListener(this);
        panel.add(this.createButton);

        this.returnButton = new JButton("Retuen the Home Page.");
        this.returnButton.setBounds(300,300,200,25);
        this.returnButton.addActionListener(this);
        panel.add(this.returnButton);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource()==createButton){
            String name = userText.getText();
            if (name == null) {
                JOptionPane.showMessageDialog(null,"Wrong Input!");
            }
            String id = ATM.openAccount(name);
            JOptionPane.showMessageDialog(null,
                    "Your Account has been created!\n" +
                            "Please Remember Your ID: " + id +"\n" +
                            "PassWord by default is your ID number.");
        }
        if(e.getSource()==returnButton){
            this.dispose();
            new InitialFrame();
        }
    }

    private JTextField userText;
    private JButton createButton;
    private JButton returnButton;
}

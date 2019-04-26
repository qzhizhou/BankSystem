package gui;

import bank.atm.ATM;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePasswordFrame extends JFrame implements ActionListener {
    public ChangePasswordFrame(){
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

        JLabel idLabel = new JLabel("ID: ");
        idLabel.setBounds(300,200,150,25);
        panel.add(idLabel);

        JLabel oriLabel = new JLabel("Original Password: ");
        oriLabel.setBounds(300,250,150,25);
        panel.add(oriLabel);

        JLabel newPassLabel = new JLabel("New Password: ");
        newPassLabel.setBounds(300,300,150,25);
        panel.add(newPassLabel);

        this.idText = new JTextField(20);
        this.idText.setBounds(475,200,165,25);
        panel.add(this.idText);

        this.oriText = new JTextField(20);
        this.oriText.setBounds(475,250,165,25);
        panel.add(this.oriText);

        this.newPassText = new JTextField(20);
        this.newPassText.setBounds(475,300,165,25);
        panel.add(this.newPassText);

        this.changePasswordButton = new JButton("Confirm!");
        this.changePasswordButton.setBounds(300, 350, 100, 25);
        this.changePasswordButton.addActionListener(this);
        panel.add(this.changePasswordButton);

        this.returnButton = new JButton("Return to Home Page.");
        this.returnButton.setBounds(300, 400, 200, 25);
        this.returnButton.addActionListener(this);
        panel.add(this.returnButton);

    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource()==changePasswordButton){
            String id = this.idText.getText();
            String oriP = this.oriText.getText();
            String newP = this.newPassText.getText();
            if (ATM.changePassWord(id,oriP,newP)){
                JOptionPane.showMessageDialog(null,
                        "Password is changed successfully!");
            } else JOptionPane.showMessageDialog(null,
                    "Id or original password are not correct!");
        }

        if (e.getSource()==returnButton){
            this.dispose();
            new InitialFrame();
        }
    }
    private JButton changePasswordButton;
    private JButton returnButton;
    private JTextField idText;
    private JTextField oriText;
    private JTextField newPassText;
}

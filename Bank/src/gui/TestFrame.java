package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestFrame extends JFrame implements ActionListener{
    public TestFrame(){
        this.setTitle("test");
        this.setSize(1000,750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        this.add(panel);
        placeComponents(panel);
        this.setVisible(true);
    }

    private void placeComponents(JPanel panel){
        panel.setLayout(null);


        JLabel testLabel = new JLabel("TEIEHFWLIBNKJWBKWB");
        testLabel.setBounds(300,500,200,50);
        panel.add(testLabel);

        this.makeDepositButton = new JButton("Deposit");
        this.setBounds(200,200,80,50);
        this.makeDepositButton.addActionListener(this);
        panel.add(this.makeDepositButton);

    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == this.makeDepositButton){
            new InputFrame();
        }
    }

    private JButton getSavingInfoButton;
    private JButton getChekingInfoButton;
    private JButton makeDepositButton;
}

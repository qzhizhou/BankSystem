package gui;

import bank.atm.ATM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ReportFrame extends JFrame implements ActionListener {
    private final String[] INFO = {"id: ", "name: ","dollar: ","RMB: ", "Euro: "};
    JTextField textField;
    JTextArea textArea;
    JScrollPane jScrollPane;
    JButton getSavingButton;
    JButton getCheckingButton;
    JButton getTransactionButton;
    JButton returnButton;

    public ReportFrame(){
        this.setTitle("Report Information");
        this.setSize(1000,750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        this.add(panel);
        placeComponents(panel);
        this.setVisible(true);
    }

    private void placeComponents(JPanel panel){
        panel.setLayout(null);

        textArea = new JTextArea();
        textArea.setBounds(0, 0,1000,680);
        jScrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        panel.add(textArea);
        panel.add(jScrollPane);

        getSavingButton = new JButton("Saving Account");
        getCheckingButton = new JButton("Checking Account");
        getTransactionButton = new JButton("Transaction");
        returnButton = new JButton("Return");

        getCheckingButton.setBounds(50,700,200,25);
        getSavingButton.setBounds(250,700,200,25);
        getTransactionButton.setBounds(450,700,150,25);
        returnButton.setBounds(600,700,150,25);

        getSavingButton.addActionListener(this);
        getCheckingButton.addActionListener(this);
        getTransactionButton.addActionListener(this);
        returnButton.addActionListener(this);

        panel.add(getSavingButton);
        panel.add(getCheckingButton);
        panel.add(getTransactionButton);
        panel.add(returnButton);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource()==getSavingButton){
            List<String> list = ATM.getSavingAccountInfo();
            int i = 0;
            for (String s : list){
                textArea.append(INFO[i] + s + "\n");
                i++;
            }
        }

        if (e.getSource()==getCheckingButton){
            List<String> list = ATM.getCheckingAccountInfo();
            int i = 0;
            for (String s : list){
                textArea.append(INFO[i] + s + "\n");
                i++;
            }
        }

        if (e.getSource()==getTransactionButton){
            List<String> list = ATM.getTransactionInfo();
            for (String s : list){
                textArea.append(s + "\n");
            }
        }

        if (e.getSource()==returnButton){
            this.dispose();
            new MainFrame();
        }
    }
}

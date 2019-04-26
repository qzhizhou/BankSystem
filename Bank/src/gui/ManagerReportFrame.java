package gui;

import bank.atm.ATM;
import bank.employee.Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class ManagerReportFrame extends JFrame implements ActionListener {
    private final String[] INFO = {"id: ", "name: ","dollar: ","RMB: ", "Euro: "};
    JTextField textField;
    JTextArea textArea;
    JScrollPane jScrollPane;
    JButton getAllTransactionsButton;
    JButton getCertainTransactionButton;
    JButton getCustmerInfoButton;
    JButton getProfitsButton;
    JButton returnButton;
    String id;

    public ManagerReportFrame(){
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
        textArea.setBounds(0, 0,1000,670);
        jScrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        panel.add(textArea);
        panel.add(jScrollPane);

        JLabel idLabel = new JLabel("Id: ");
        idLabel.setBounds(50,670,80,25);
        panel.add(idLabel);
        textField = new JTextField("0");
        textField.setBounds(130,670,150,25);
        panel.add(textField);

        getAllTransactionsButton = new JButton("All Transactions");
        getCertainTransactionButton = new JButton("Certain Transaction");
        getCustmerInfoButton = new JButton("Customer Info");
        getProfitsButton = new JButton("Profits");
        returnButton = new JButton("Return");

        getCertainTransactionButton.setBounds(50,700,200,25);
        getAllTransactionsButton.setBounds(250,700,200,25);
        getCustmerInfoButton.setBounds(450,700,200,25);
        getProfitsButton.setBounds(650,700,150,25);
        returnButton.setBounds(800,700,150,25);

        getAllTransactionsButton.addActionListener(this);
        getCertainTransactionButton.addActionListener(this);
        getCustmerInfoButton.addActionListener(this);
        getProfitsButton.addActionListener(this);
        returnButton.addActionListener(this);

        panel.add(getAllTransactionsButton);
        panel.add(getCertainTransactionButton);
        panel.add(getCustmerInfoButton);
        panel.add(getProfitsButton);
        panel.add(returnButton);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        this.id = textField.getText();
        if (e.getSource()==getAllTransactionsButton){
            Map<String,List<String>> logs = Manager.getAllTransactions();
            for (Map.Entry<String ,List<String>> entry : logs.entrySet()){
                List<String> list = entry.getValue();
                for (String s : list){
                    textArea.append(s + "\n");
                }
            }
        }

        if (e.getSource()==getCertainTransactionButton){
            List<String> list = Manager.getTransactions(id);
            if (list==null) JOptionPane.showMessageDialog(null,
                    "Input id doesn't exist.");
            else {
                for (String s : list){
                    textArea.append(s + "\n");
                }
            }
        }

        if (e.getSource()==getCustmerInfoButton){
            Map<String,String> customerInfo = Manager.getCustomerInfo();
            for (Map.Entry<String,String> entry : customerInfo.entrySet()){
                textArea.append("Customer id: " + entry.getKey() + "\n");
            }
        }

        if (e.getSource()==getProfitsButton){
            List<Double> profits = Manager.getProfits();
            double sum = 0;
            for (double num : profits){
                sum += num;
            }
            textArea.append("Today's income is " + String.valueOf(sum) + " dollars.\n");
        }

        if (e.getSource()==returnButton){
            this.dispose();
            new MainFrame();
        }
    }
}

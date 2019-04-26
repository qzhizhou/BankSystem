package gui;

import bank.Bank;
import bank.atm.ATM;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputFrame extends JFrame implements ActionListener {
    public InputFrame(){
        this.setTitle("Welcome to Zoey's Bank");
        this.setSize(1000,750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        this.add(panel);
        placeComponents(panel);
        this.setVisible(true);
    }

    private void placeComponents(JPanel panel){
        panel.setLayout(null);

        JLabel amountLabel = new JLabel("Amount: ");
        amountLabel.setBounds(250,350,80,25);
        panel.add(amountLabel);
        amountText = new JTextField("0");
        amountText.setBounds(300,300,150,25);
        panel.add(amountText);

        dollarButton = new JRadioButton("Dollar");
        rmbButton = new JRadioButton("RMB");
        euroButton = new JRadioButton("Euro");
        depositButton = new JButton("Deposit");
        withdrawalButton = new JButton("Withdrawal");
        requestLoanButton = new JButton("Request Loans");
        repayLoanButton = new JButton("Repay Loans");
        closeAccountButton = new JButton("Close Your Account");
        checkingButton = new JRadioButton("select checking account");
        savingButton = new JRadioButton("select saving account");
        returnButton = new JButton("Return to Main Page");


        dollarButton.setBounds(250,400,80,25);
        rmbButton.setBounds(350,400,80,25);
        euroButton.setBounds(450,400,80,25);
        depositButton.setBounds(250,600,80,25);
        withdrawalButton.setBounds(350,600,150,25);
        requestLoanButton.setBounds(500,600,150,25);
        repayLoanButton.setBounds(650,600,150,25);
        closeAccountButton.setBounds(250,650,200,25);
        checkingButton.setBounds(250,500,200,25);
        savingButton.setBounds(500,500,200,25);
        returnButton.setBounds(250,700,150,25);


        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(dollarButton);
        buttonGroup.add(rmbButton);
        buttonGroup.add(euroButton);
        dollarButton.setSelected(true);

        ButtonGroup buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(checkingButton);
        buttonGroup1.add(savingButton);
        savingButton.setSelected(true);

        dollarButton.addActionListener(this);
        rmbButton.addActionListener(this);
        euroButton.addActionListener(this);
        depositButton.addActionListener(this);
        withdrawalButton.addActionListener(this);
        requestLoanButton.addActionListener(this);
        repayLoanButton.addActionListener(this);
        closeAccountButton.addActionListener(this);
        checkingButton.addActionListener(this);
        savingButton.addActionListener(this);
        returnButton.addActionListener(this);


        panel.add(dollarButton);
        panel.add(rmbButton);
        panel.add(euroButton);
        panel.add(depositButton);
        panel.add(withdrawalButton);
        panel.add(requestLoanButton);
        panel.add(repayLoanButton);
        panel.add(checkingButton);
        panel.add(savingButton);
        panel.add(returnButton);
        panel.add(closeAccountButton);

    }

    @Override
    public void actionPerformed(ActionEvent e){

        amount = amountText.getText();

        if (euroButton.isSelected()) currency = "Euro";
        else if (rmbButton.isSelected()) currency = "RMB";
        else currency = "Dollar";

        if (savingButton.isSelected()) choice = "1";
        else choice = "0";

        if (e.getSource()==depositButton){
            if (ATM.makeDeposit(choice,amount,currency)){
                JOptionPane.showMessageDialog(null,"You have deposited "+
                        amount + currency);
            }else JOptionPane.showMessageDialog(null,"Did you enter the right amount?");
        }

        if (e.getSource()==withdrawalButton){
            if (ATM.withdrawal(choice,amount,currency)){
                JOptionPane.showMessageDialog(null,"You have withdrawed "
                + amount+currency + " and this costs you "+ ATM.getCONSTANTCOST() + currency);
            } else JOptionPane.showMessageDialog(null,"Your current balance is not allowed" +
                    "to withdraw such amount.");
        }

        if (e.getSource()==requestLoanButton){
            if (ATM.requestLoans(amount)){
                JOptionPane.showMessageDialog(null,"You have requested " +
                        "loans in terms of " + amount + "dollars.");
            }else JOptionPane.showMessageDialog(null,"You are not allowed to request such " +
                    "amount of dollars since your deposit is half lower than your request amount.");
        }

        if (e.getSource()==repayLoanButton){
            if (ATM.repayLoan(amount)){
                JOptionPane.showMessageDialog(null, "You have paid off"
                + amount + " dollars.");
            } else{
                JOptionPane.showMessageDialog(null,"The current interest  rate is: " +
                        String.valueOf(Bank.getInterest()) + " Please prepare enough to pay off your debts.");
            }
        }

        if (e.getSource()==closeAccountButton){
            int[] moneyRemain = new int[3];
            if (ATM.closeAccount(moneyRemain)){
                JOptionPane.showMessageDialog(null, "You have closed your account."+
                        "And " + String.valueOf(moneyRemain[0])+" dollars, " +String.valueOf(moneyRemain[1])+ " RMB, "
                + String.valueOf(moneyRemain[2]) + " Euro are returned to you.");
                this.dispose();
                new InitialFrame();
            } else {
                JOptionPane.showMessageDialog(null, "Your current balance is not " +
                        "allowed to close your account.");
            }
        }
        if (e.getSource()==returnButton){
            this.dispose();
            new MainFrame();
        }
    }

    JButton depositButton;
    JButton withdrawalButton;
    JButton requestLoanButton;
    JButton repayLoanButton;
    JButton closeAccountButton;
    JRadioButton dollarButton;
    JRadioButton rmbButton;
    JRadioButton euroButton;
    JRadioButton checkingButton;
    JRadioButton savingButton;
    JButton returnButton;
    String amount;
    String currency;
    String choice = "0";
    JTextField amountText;
}

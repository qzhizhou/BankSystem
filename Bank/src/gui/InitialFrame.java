package gui;

import bank.Bank;
import bank.atm.ATM;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InitialFrame extends JFrame implements ActionListener{
    private JButton loginButton;
    private JButton openAccountButton;
    private JButton managerButton;
    private JButton changePasswordButton;
    private String id;
    private String password;
    private JTextField userText;
    private JPasswordField passwordText;

    public InitialFrame(){
        this.setTitle("Welcome to Zoey's Bank");
        this.setSize(1000,750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        this.add(panel);
        placeComponents(panel);
        this.setVisible(true);
    }

    private void placeComponents(JPanel panel) {

        /*
         * 这边设置布局为 null
         */
        panel.setLayout(null);

        // 创建 JLabel
        JLabel userLabel = new JLabel("User:");
        /* 这个方法定义了组件的位置。
         * setBounds(x, y, width, height)
         * x 和 y 指定左上角的新位置，由 width 和 height 指定新的大小。
         */
        userLabel.setBounds(300,60,80,25);
        panel.add(userLabel);

        /*
         * 创建文本域用于用户输入
         */
        userText = new JTextField(20);
        userText.setBounds(400,60,165,25);
        panel.add(userText);

        // 输入密码的文本域
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(300,100,80,25);
        panel.add(passwordLabel);

        /*
         *这个类似用于输入的文本域
         * 但是输入的信息会以点号代替，用于包含密码的安全性
         */
        passwordText = new JPasswordField(20);
        passwordText.setBounds(400,100,165,25);
        panel.add(passwordText);

        // 创建登录按钮
        this.loginButton = new JButton("login");
        this.loginButton.setBounds(300, 150, 80, 25);
        this.loginButton.addActionListener(this);
        panel.add(this.loginButton);

        this.openAccountButton = new JButton("Open Personal Account Here!");
        this.openAccountButton.setBounds(300, 200, 300, 30);
        this.openAccountButton.addActionListener(this);
        panel.add(this.openAccountButton);

        this.changePasswordButton = new JButton("Change your password here.");
        this.changePasswordButton.setBounds(300,260,300,30);
        this.changePasswordButton.addActionListener(this);
        panel.add(this.changePasswordButton);

        this.managerButton = new JButton("Manager Login Entrance");
        this.managerButton.setBounds(300,310,300,30);
        this.managerButton.addActionListener(this);
        panel.add(this.managerButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.loginButton)
        {
            this.id = userText.getText();
            this.password = new String(passwordText.getPassword());
            if (ATM.login(this.id,this.password)){
                this.dispose();
                new MainFrame();
            }else{
                JOptionPane.showMessageDialog(null,"Wrong id or password!");
            }
        }

        if (e.getSource()==this.openAccountButton){
            this.dispose();
            new AccountOpenFrame();
        }

        if (e.getSource()==this.changePasswordButton){
            this.dispose();
            new ChangePasswordFrame();
        }

        if (e.getSource()==this.managerButton){
            this.dispose();
            new ManagerFrame();
        }
    }
}

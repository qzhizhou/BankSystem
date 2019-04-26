package gui;

import bank.Bank;
import bank.atm.ATM;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerFrame extends JFrame implements ActionListener{
    private JButton loginButton;
    private JButton openAccountButton;
    private JButton changePasswordButton;
    private String id;
    private String password;
    private JTextField userText;
    private JPasswordField passwordText;

    public ManagerFrame(){
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

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.loginButton)
        {
            this.id = userText.getText();
            this.password = new String(passwordText.getPassword());
            if (ATM.managerLogin(this.id,this.password)){
                this.dispose();
                new ManagerReportFrame();
            }else{
                JOptionPane.showMessageDialog(null,"Wrong id or password!");
            }
        }

    }
}

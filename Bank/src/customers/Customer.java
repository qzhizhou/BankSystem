package customers;

import bank.atm.ATM;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private String id;
    private String passWord;
    private List<String> savingAccountInfo;
    private List<String> checkingAccountInfo;

    public boolean login(String id, String passWord){
        return ATM.login(id, passWord);
    }

    public void openAccount(String name){
        this.id = ATM.openAccount(name);
        this.passWord = this.id;
    }

    public boolean changePassword(String id, String originPassword, String newPassword){
        return ATM.changePassWord(id, originPassword, newPassword);
    }

//    public boolean closeAccount(String id){
//        return ATM.closeAccount(id);
//    }

//    public List<String> getSavingAccountInfo(String id) {
//        this.savingAccountInfo = ATM.getSavingAccountInfo(id);
//        return this.savingAccountInfo;
//    }
//
//    public List<String> getCheckingAccountInfo(String id) {
//        this.checkingAccountInfo = ATM.getCheckingAccountInfo(id);
//        return this.checkingAccountInfo;
//    }
//
//    public boolean makeDeposit(String id, String money, String currency){
//        return ATM.makeDeposit(id, money, currency);
//    }

    public Customer(String name){
        this.name = name;
        savingAccountInfo = new ArrayList<>();
        checkingAccountInfo = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getPassWord(){
        return passWord;
    }
}

package bank.atm;

import bank.Bank;
import java.util.*;

public class ATM extends Bank{
    private static String name;
    private static String currentId;
    private static String currentManager;
    private static final Integer CONSTANTCOST = 5;

    public static boolean login(String inputId, String password){
        String p = Bank.customerInfo.get(inputId);
        if(p == null) return false;
        if (!p.equals(password)) return false;
        currentId = inputId;
        return true;
    }

    public static boolean managerLogin(String name, String password){
        String p = Bank.emploteeInfo.get(name);
        if (p==null) return false;
        if (!p.equals(password)) return false;
        currentManager = name;
        return true;
    }

    /**
     * List<String> info contains id, name, US dollar, RMB, Euro
     * password by default is the same as id
     */
    public static String openAccount(String name){
        List<String> info = new ArrayList<>();
        int num = (int) (Math.random() * 10000);
        String id = String.valueOf(num);
        info.add(id);
        info.add(name);
        // by default it cost five dollars to open an account
        info.add("-5");
        for (int i = 0 ; i < 2; i++){
            info.add("0");
        }
        //records logs
        String date = new Date().toString();
        String data = date+ ": Customer: " + name + " id: " + id + " creates an account.";
        List<String> list = new ArrayList<>();
        list.add(data);

        Bank.checkingAccountInfo.put(id, info);
        Bank.savingAccountInfo.put(id,info);
        Bank.customerInfo.put(id, id);
        Bank.logs.put(id,list);
        Bank.profits.add(Double.valueOf(CONSTANTCOST));

        return id;
    }

    public static boolean changePassWord(String id, String originPassword, String newPassword){
        String p = Bank.customerInfo.get(id);
        if(p == null) return false;
        if (!p.equals(originPassword)) return false;
        Bank.customerInfo.put(id,newPassword);
        return true;
    }

    // by default it costs 5 dallars to close account and can only be paid in dollar
    public static boolean closeAccount(int[] moneyRemain){
        List<String> mic = getMoneyInCheckingAccount();
        List<String> mis = getMoneyInSavingAccount();
        int sum = 0;
        int dollars = Integer.valueOf(mis.get(2)) + Integer.valueOf(mic.get(2));
        int RMB = Integer.valueOf(mic.get(3)) + Integer.valueOf(mis.get(3));
        int Euro = Integer.valueOf(mic.get(4)) + Integer.valueOf(mis.get(4));
        if(dollars > CONSTANTCOST){
            Bank.savingAccountInfo.remove(currentId);
            Bank.checkingAccountInfo.remove(currentId);
            moneyRemain[0] = dollars-5;
            moneyRemain[1] = RMB;
            moneyRemain[2] = Euro;

            //records logs:
            List<String> list = logs.get(currentId);
            String date = new Date().toString();
            String data = date+"Customer id: " + currentId + " closes the account.";
            list.add(data);
            Bank.logs.put(currentId, list);
            Bank.profits.add(Double.valueOf(CONSTANTCOST));

            return true;
        }
        return false;
    }

    public static List<String> getSavingAccountInfo(){
        return Bank.savingAccountInfo.get(currentId);
    }

    public static List<String> getCheckingAccountInfo(){
        return Bank.checkingAccountInfo.get(currentId);
    }

    public static List<String> getTransactions(){return Bank.logs.get(currentId);}


    public static boolean makeDeposit(String choice, String deposit, String currency){
        /*
        currency by default is dollars
        if choice is 0, means customer wants to make deposit into checking account
        if choice is 1, means .... into saving account
         */
        int index = 2;
        if(currency.equals("RMB")) index = 3;
        if(currency.equals("Euro")) index = 4;
        Integer num = Integer.valueOf(deposit);
        if (num < 0) return false;
        Integer newBalance = 0;
        Integer originBalance = 0;
        if (choice.equals("1")){
           originBalance  = Integer.valueOf(Bank.savingAccountInfo.get(currentId).get(index));
        } else originBalance  = Integer.valueOf(Bank.checkingAccountInfo.get(currentId).get(index));

        if (originBalance == null) newBalance = num;
        else newBalance = originBalance + num;

        if (choice.equals("1")) {
            Bank.savingAccountInfo.get(currentId).set(index, String.valueOf(newBalance));
        } else if (newBalance < CONSTANTCOST) return false;
        else {
            Bank.checkingAccountInfo.get(currentId).set(index, String.valueOf(newBalance-CONSTANTCOST));
            Bank.profits.add(Double.valueOf(CONSTANTCOST));
        }

        //records logs:
        String date = new Date().toString();
        String data = date + " Cutmoer id: " + currentId + " adds deposits: " + newBalance + currency;
        List<String> list = logs.get(currentId);
        list.add(data);
        Bank.logs.put(currentId, list);

        List<String> check = getSavingAccountInfo();
        for (String s : check){
            System.out.println(s);
        }

        return true;
    }


    public static boolean withdrawal(String choice, String amount, String currency){
        /*
        currency by default is dollars
        it costs 10% of the withdrawal
         */
        int index = 2;
        if(currency.equals("RMB")) index = 3;
        if(currency.equals("Euro")) index = 4;
        List<String> list = new ArrayList<>();

        if (choice.equals("1")) {
            list = getMoneyInSavingAccount();
        } else list = getMoneyInCheckingAccount();

        Integer currentBalance = Integer.valueOf(list.get(index));
        if (currentBalance < Integer.valueOf(amount) + CONSTANTCOST) return false;
        String newBalance = String.valueOf(currentBalance-Integer.valueOf(amount)-CONSTANTCOST);
        list.set(index,newBalance);

        if (choice.equals("1")) {
            Bank.savingAccountInfo.put(currentId, list);
        } else Bank.checkingAccountInfo.put(currentId,list);

        //records logs:
        String date = new Date().toString();
        String data = date + " Customer id: " + currentId + " withdraws " +
                amount+currency;
        List<String> logList = logs.get(currentId);
        logList.add(data);
        Bank.logs.put(currentId, logList);
        Bank.profits.add(Double.valueOf(CONSTANTCOST));


        System.out.println("withdrawal");
        for (String s : list){
            System.out.println(s);
        }
        return true;
    }


    public static boolean transferFromSavingToChecking(String amount, String currency){
        int index = 2;
        if(currency.equals("RMB")) index = 3;
        if(currency.equals("Euro")) index = 4;
        Integer transferAmount = Integer.valueOf(amount);
        List<String> list = Bank.savingAccountInfo.get(currentId);
        Integer balance = Integer.valueOf(list.get(index));
        List<String> list1 = Bank.checkingAccountInfo.get(currentId);
        Integer cbalance = Integer.valueOf(list.get(index));
        if (balance < transferAmount) return false;
        list.set(index, String.valueOf(balance-transferAmount));
        Bank.savingAccountInfo.put(currentId,list);
        list1.set(index,String.valueOf(cbalance+transferAmount));
        Bank.checkingAccountInfo.put(currentId, list1);

        //records logs:
        String date = new Date().toString();
        String data = date + " Customer id: " + currentId + " transfers " + amount+currency
                + "from saving account to checking.";
        List<String> log = logs.get(currentId);
        log.add(data);
        Bank.logs.put(currentId,log);

        return true;
    }

    public static boolean transferFromCheckingToSaving(String amount, String currency){
        int index = 2;
        if(currency.equals("RMB")) index = 3;
        if(currency.equals("Euro")) index = 4;
        Integer transferAmount = Integer.valueOf(amount);
        List<String> list = Bank.savingAccountInfo.get(currentId);
        Integer balance = Integer.valueOf(list.get(index));
        List<String> list1 = Bank.checkingAccountInfo.get(currentId);
        Integer cbalance = Integer.valueOf(list.get(index));
        if (cbalance < transferAmount + CONSTANTCOST) return false;
        list.set(index, String.valueOf(balance+transferAmount-CONSTANTCOST));
        Bank.savingAccountInfo.put(currentId,list);
        list1.set(index,String.valueOf(cbalance-transferAmount-CONSTANTCOST));
        Bank.checkingAccountInfo.put(currentId, list1);

        //records logs:
        String date = new Date().toString();
        String data = date + " Customer id: " + currentId + " transfers "
                + String.valueOf(transferAmount-CONSTANTCOST)
                +currency + " from saving account to checking.";
        List<String> log = Bank.logs.get(currentId);
        log.add(data);
        Bank.logs.put(currentId,log);

        return true;
    }

    public static List<String> getTransactionInfo(){
        return logs.get(currentId);
    }

    /**
     * List<String> loans: dollar</>
     * @param amount
     * @param currency
     */
    public static boolean requestLoans(String amount){
        Integer dollarInChecking = Integer.valueOf(Bank.checkingAccountInfo.get(currentId).get(2));
        Integer dollarInSaving = Integer.valueOf(Bank.savingAccountInfo.get(currentId).get(2));
        Integer requestAmount = Integer.valueOf(amount);
        if (requestAmount > (dollarInChecking + dollarInSaving) / 2) return false;
        String loan = Bank.loanInfo.get(currentId);
        if (loan == null) {
            Bank.loanInfo.put(currentId,amount);
        }else {
            String newAmount = String.valueOf(Double.valueOf(loan)+requestAmount);
            Bank.loanInfo.put(currentId,loan);
        }

        //records log:
        String date = new Date().toString();
        String data = date + " Customer id: " + currentId + " requests loans of "
                + amount+ " dollars.";
        List<String> log = Bank.logs.get(currentId);
        log.add(data);
        Bank.logs.put(currentId,log);

        return true;
    }

    public static boolean repayLoan(String amount){
        Double cash = Double.valueOf(amount);
        String loan = Bank.loanInfo.get(currentId);

        System.out.print("loans:" +  loan);

        if (loan==null || Double.valueOf(loan) == 0) return false;
        if (cash < Double.valueOf(loan)* Bank.interest) return false;
        Bank.loanInfo.put(currentId,"0");
        Double profit = Double.valueOf(loan)*2 - cash;

        //records log:
        String date = new Date().toString();
        String data = date + " Customer id: " + currentId + " repays loan of "
                + amount+ " dollars.";
        List<String> log = logs.get(currentId);
        log.add(data);
        Bank.logs.put(currentId,log);
        Bank.profits.add(profit);

        return true;
    }

    public static Integer getCONSTANTCOST() {
        return CONSTANTCOST;
    }

    public ATM(String name){
        super(name);
    }

    public ATM(){}

    private static List<String> getMoneyInCheckingAccount(){
        List<String> list = Bank.checkingAccountInfo.get(currentId);
        return list;
    }

    private static List<String> getMoneyInSavingAccount(){
        List<String> list = Bank.savingAccountInfo.get(currentId);
        return list;
    }
}

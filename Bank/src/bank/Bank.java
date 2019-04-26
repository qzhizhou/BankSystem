package bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    private String name;
    protected static Map<String, List<String>> checkingAccountInfo;
    protected static Map<String, List<String>> savingAccountInfo;
    protected static Map<String, String> customerInfo;
    protected static Map<String, String> emploteeInfo;
    protected static Map<String, List<String>> logs;
    protected static Map<String, String> loanInfo;
    protected static List<Double> profits;
    protected static Double interest;

    public Bank(String name){
        this.name = name;
        checkingAccountInfo = new HashMap<>();
        savingAccountInfo = new HashMap<>();
        customerInfo = new HashMap<>();
        emploteeInfo = new HashMap<>();
        logs = new HashMap<>();
        loanInfo = new HashMap<>();
        profits = new ArrayList<>();
        interest = 2.0;
    }

    public Bank(){

    }

    protected List<List<String>> getDailyReport(){
        List<List<String>> report = new ArrayList<>();
        for (Map.Entry<String,List<String>> entry : logs.entrySet()){
            report.add(entry.getValue());
        }
        return report;
    }

    public static void setInterest(Double interest) {
        Bank.interest = interest;
    }

    public static Double getInterest() {
        return interest;
    }
}

package bank.employee;

import bank.Bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Manager extends Employee {

    public static Map<String, List<String>> getAllTransactions(){
        return Bank.logs;
    }

    public static List<String> getTransactions(String id){
        return Bank.logs.get(id);
    }

    public static Map<String, String> getCustomerInfo(){
        return Bank.customerInfo;
    }

    public static List<Double> getProfits(){
        return Bank.profits;
    }

    public Manager(){
        super();
    }

    public Manager(String name) {
        super(name);
   }

}

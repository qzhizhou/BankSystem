package bank.employee;

import bank.Bank;

public class Employee extends Bank {
    protected String name;

    public Employee(String name){
        this.name = name;
        Bank.emploteeInfo.put(name,"100");
    }

    public Employee() {}
}

import bank.Bank;
import bank.employee.Manager;
import gui.InitialFrame;

public class Main {
    public static void main(String[] args)
    {
        // create an virtual bank
        Bank bank = new Bank("Zoey's Bank");
        //assign a manager
        Manager manager = new Manager("Christine");
        // user interface
        InitialFrame initialFrame = new InitialFrame();
    }
}

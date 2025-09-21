import java.util.Scanner;
import java.util.Date;

public class ATM {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Account[] accounts = new Account[10];
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account(i, 100);
        }
        while (true) {
            System.out.print("Enter an id: ");
            int id = input.nextInt();
            if (id < 0 || id > 9) continue;
            while (true) {
                System.out.println("Main menu");
                System.out.println("1: check balance");
                System.out.println("2: withdraw");
                System.out.println("3: deposit");
                System.out.println("4: exit");
                System.out.print("Enter a choice: ");
                int choice = input.nextInt();
                if (choice == 1) {
                    System.out.println("The balance is " + accounts[id].getBalance());
                } else if (choice == 2) {
                    System.out.print("Enter an amount to withdraw: ");
                    double amount = input.nextDouble();
                    accounts[id].withdraw(amount);
                } else if (choice == 3) {
                    System.out.print("Enter an amount to deposit: ");
                    double amount = input.nextDouble();
                    accounts[id].deposit(amount);
                } else if (choice == 4) {
                    break;
                }
            }
        }
    }
}

class Account {
    private int id;
    private double balance;
    private static double annualInterestRate;
    private Date dateCreated;

    public Account() {
        this(0, 0);
    }

    public Account(int id, double balance) {
        this.id = id;
        this.balance = balance;
        this.dateCreated = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public static double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public static void setAnnualInterestRate(double annualInterestRate) {
        Account.annualInterestRate = annualInterestRate;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public double getMonthlyInterestRate() {
        return annualInterestRate / 12;
    }

    public double getMonthlyInterest() {
        return balance * getMonthlyInterestRate() / 100;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }

    public void deposit(double amount) {
        balance += amount;
    }
}
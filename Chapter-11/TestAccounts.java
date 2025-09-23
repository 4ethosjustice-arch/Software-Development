class Account {
    private int accountNumber;
    protected double balance;

    public Account(int accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account: number=" + accountNumber + ", balance=" + balance;
    }
}

class SavingsAccount extends Account {
    public SavingsAccount(int accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public String toString() {
        return "SavingsAccount: balance=" + balance;
    }
}

class CheckingAccount extends Account {
    private double overdraftLimit;

    public CheckingAccount(int accountNumber, double balance, double overdraftLimit) {
        super(accountNumber, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public String toString() {
        return "CheckingAccount: balance=" + balance + ", overdraftLimit=" + overdraftLimit;
    }
}

public class TestAccounts {
    public static void main(String[] args) {
        Account acc = new Account(1, 500);
        SavingsAccount savings = new SavingsAccount(2, 1000);
        CheckingAccount checking = new CheckingAccount(3, 750, 200);

        System.out.println(acc);
        System.out.println(savings);
        System.out.println(checking);
    }
}
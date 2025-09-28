import java.io.*;

public class Exercise17_07 {
    public static void main(String[] args) throws FileNotFoundException {
        Loan loan1 = new Loan();
        Loan loan2 = new Loan(1.8, 10, 10000);

        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("Exercise17_07.dat"))) {
            output.writeObject(loan1);
            output.writeObject(loan2);
        } catch (IOException ex) {
            System.out.println("File could not be opened");
        }

        outputData("Exercise17_07.dat");
    }

    public static void outputData(String filename) {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(filename))) {
            while (true) {
                try {
                    Loan loan = (Loan) input.readObject();
                    System.out.println("Total loan amount: " + loan.getLoanAmount());
                } catch (EOFException e) {
                    break;  // End of file reached
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Error reading from file: " + ex.getMessage());
        }
    }
}

class Loan implements Serializable {
    private static final long serialVersionUID = 1L;

    private double annualInterestRate;
    private int numberOfYears;
    private double loanAmount;
    private java.util.Date loanDate;

    public Loan() {
        this(2.5, 1, 1000);
    }

    public Loan(double annualInterestRate, int numberOfYears, double loanAmount) {
        this.annualInterestRate = annualInterestRate;
        this.numberOfYears = numberOfYears;
        this.loanAmount = loanAmount;
        this.loanDate = new java.util.Date();
    }

    public double getAnnualInterestRate() { return annualInterestRate; }
    public void setAnnualInterestRate(double annualInterestRate) { this.annualInterestRate = annualInterestRate; }

    public int getNumberOfYears() { return numberOfYears; }
    public void setNumberOfYears(int numberOfYears) { this.numberOfYears = numberOfYears; }

    public double getLoanAmount() { return loanAmount; }
    public void setLoanAmount(double loanAmount) { this.loanAmount = loanAmount; }

    public double getMonthlyPayment() {
        double monthlyInterestRate = annualInterestRate / 1200;
        return loanAmount * monthlyInterestRate / (1 - Math.pow(1 / (1 + monthlyInterestRate), numberOfYears * 12));
    }

    public double getTotalPayment() { return getMonthlyPayment() * numberOfYears * 12; }
    public java.util.Date getLoanDate() { return loanDate; }
}
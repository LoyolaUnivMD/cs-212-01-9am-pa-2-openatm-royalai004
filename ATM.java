import java.util.Scanner;
import java.util.ArrayList;

class Account {
    String accountNum;
    String name;
    double balance;
    String[][] transactions;
    //Initializes Account Details
    public Account(String accountNum, String name) { 
        this.name = name;
        this.accountNum = accountNum;
        this.balance = 0;
        transactions = new String[5][2];
    }
    //Checks if name given is the same as the name holding the account
    public boolean accountIsOwner(String nameIn) {
        return name.equals(nameIn);
    }
    //Getters for Account Details
    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public String[][] getTransactions() {
        return transactions;
    }
    
    public String getAccountNum() {
        return accountNum;
    }
    //Gets and checks the amount the user inputs for a transaction
    //While the input isn't valid, collect a new one.
    double getInputAmount(String action, Scanner input) {
        double amount = 0;
        boolean counter = true;
        while (counter) {
            System.out.println("How much would you like to " + action + "?"); 
            String amountString = input.next();
            try {
                amount = Double.parseDouble(amountString);
                if (amount < 0) {
                    System.out.println("Invalid amount. Try again: ");
                } else {
                    counter = false;
                }
            } catch (NumberFormatException ignored) {
                System.out.println("Invalid amount. Try again: ");
            }
        }
        return amount;
    }
    //Updates the transaction list
    //When the amount of transactions is over the limit, delete the oldest transaction and add the newest one
    void updateTransactions(String action, String amount) {
        String[] temp = {action, amount};
        for (int i = transactions.length - 1; i > 0; i--) {
            transactions[i] = transactions[i - 1];
        }
        transactions[0] = temp;
    }
    //Method to deposit a given amount
    void deposit(Scanner input) {
        double deposit = getInputAmount("deposit", input);
        balance += deposit;
        System.out.println("Your new balance is: " + balance);
        updateTransactions("D", String.valueOf(deposit));
    }
    //Method to withdraw a given amount
    void withdraw(Scanner input) {
        double withdraw = getInputAmount("withdraw", input);
        while ((balance - withdraw) < 0) {
            System.out.println("You can't withdraw more than you have. Please enter a valid withdraw amount.");
            withdraw = getInputAmount("withdraw", input);
        }
        balance -= withdraw;
        System.out.println("Your new balance is: " + balance);
        updateTransactions("W", String.valueOf(withdraw));
    }
    //Method to view recent transactions
    void viewTransactions() {
        for (String[] transaction : transactions) {
            if (transaction[0] != null && transaction[1] != null) {
                if (transaction[0].equals("D")) {
                    System.out.println("You deposited " + transaction[1] + " dollars.");
                } else {
                    System.out.println("You withdrew " + transaction[1] + " dollars.");
                }
            }
        }
    }
    //Method to view user's account statistics.
    void statistics() {
        double min = Double.parseDouble(transactions[0][1]);
        double max = Double.parseDouble(transactions[0][1]);
        double total = 0;
        int count = 0;
        //If transaction size is smaller than the minimum or larger than the maximum, update the corresponding variable.
        for (String[] transaction : transactions) {
            if (transaction[1] != null) {
                double amount = Double.parseDouble(transaction[1]);
                if (amount < min) {
                    min = amount;
                }
                if (amount > max) {
                    max = amount;
                }
                total += amount;
                count++;
            }
        }
        double average = total / count;
        System.out.println("Your current balance is " + balance);
        System.out.println("Your minimum transaction size of the last five transactions is " + min);
        System.out.println("Your maximum transaction size of the last five transactions is " + max);
        System.out.println("Your average transaction size is " + average);
    }
    //Processes user's choice of action through a menu
    //While user has not chosen to exit, run the corresponding method or make them enter a valid input 
    public void processTransactions(Scanner input) {
        String choice = "";
        while (!choice.equals("exit")) {
            System.out.println("What would you like to do, " + name + ": (Enter either 'deposit', 'withdraw', 'statistics', 'transactions', or 'exit'.)");
            choice = input.next().toLowerCase().trim();
            if (choice.equals("exit")) {
                break;
            }
            switch (choice) {
                case "deposit":
                    deposit(input);
                    break;
                case "withdraw":
                    withdraw(input);
                    break;
                case "statistics":
                    statistics();
                    break;
                case "transactions":
                    viewTransactions();
                    break;
                default:
                    System.out.println("That was not a valid option, please try again");
            }
        }
    }
}
//Main method
public class ATM {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Account> ACCOUNTS = new ArrayList<>(); //Initializes object list of accounts
        System.out.println("Welcome to the Open Atm!");
        System.out.println("Please enter your full name or 'exit' to exit."); 
        ArrayList <String> accNums = new ArrayList <>(); //Initializes list of already saved account numbers
        String name = in.next();
        while (!name.equals("exit")) {
            System.out.println("Please enter your account number: ");
            String accountNum = in.next();
            Account currentAccount = null; // Sets currentAccount to a null value as a placeholder
            boolean ownerCheck = false; // Initializes boolean that checks the owner to false
            boolean accountCheck = false; // Initializes boolean that checks the account number to false
            for (Account account : ACCOUNTS) {
                if (account.getAccountNum().equals(accountNum)) {
                    if (account.accountIsOwner(name)) { //If correct owner and account number, run the program
                        ownerCheck = true;
                        currentAccount = account;
                        currentAccount.processTransactions(in); 
                        break;
                    } else {
                        System.out.println("Account not found or owner's name does not match. Please try again.");
                        break;
                    }
                }
            }
            for (String num : accNums) {  // Loops through array of account numbers to check for a given one
                if (num.equals(accountNum)){
                    accountCheck = true;
                    break;
                }
            }
            if (!ownerCheck && !accountCheck) { //Creates a new account if information does not already exist and runs the program
                currentAccount = new Account(accountNum, name);
                ACCOUNTS.add(currentAccount);
                accNums.add(accountNum);
                currentAccount.processTransactions(in);
            }
            System.out.print("Please enter your full name or 'exit' to exit: "); 
            name = in.next();
        }
    }
}

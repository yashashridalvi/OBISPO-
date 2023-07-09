import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Transaction {
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }
    public String getType() {
        return type;
    }
    public double getAmount() {
        return amount;
    }
}
class Account {
    private String userId;
    private String userPin;
    private double balance;
    private List<Transaction> transactionHistory;

    public Account(String userId, String userPin) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = 0;
        this.transactionHistory = new ArrayList<>();
    }
    public String getUserId() {
        return userId;
    }
    public boolean validatePin(String userPin) {
        return this.userPin.equals(userPin);
    }
    public double getBalance() {
        return balance;
    }
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactionHistory.add(new Transaction("\n\u001B[31mWithdrawal \u001B[0m", amount));
            System.out.println("\n\u001B[1mSuccessfully withdrawn " + amount + " units." + "\u001B[0m");
        } else {
            System.out.println("\n\u001B[1m\u001B[31mInsufficient funds.\u001B[0m" + "\u001B[0m");
        }
    }
    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add(new Transaction("\n\u001B[32mDeposit \u001B[0m", amount));
        System.out.println("\u001B[1m\u001B[32m\"Successfully deposited " + amount + " units." + "\u001B[0m");
    }
    public void transfer(Account recipient, double amount) {
        if (amount <= balance) {
            balance -= amount;
            recipient.deposit(amount);
            transactionHistory.add(new Transaction("\n\u001B[1m\u001B[33m\"Transfer to "  + "\u001B[0m" + recipient.getUserId(), amount));
            System.out.println("\u001B[1m\u001B[32mSuccessfully transferred " + amount + " units to " + recipient.getUserId() + "." + "\u001B[0m");
        } else {
            System.out.println("\u001B[1m\u001B[31mInsufficient funds." + "\u001B[0m");
        }
    }
    public void displayTransactionHistory() {
        System.out.println("\u001B[1m\u001B[45mTransaction History:" + "\u001B[0m");
        for (Transaction transaction : transactionHistory) {
            System.out.println(transaction.getType() + " - " + transaction.getAmount() + " units");
        }
    }
}
class ATM {
    private List<Account> accounts;
    private Scanner scanner;
    public ATM() {
        this.accounts = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void start() {
        System.out.println("\u001B[1m\u001B[44m***---Welcome to the ATM!---***\u001B[0m\n" +"\u001B[0m");

        while (true) {
            System.out.print("\u001B[1mEnter user ID (or 'quit' to exit): " + "\u001B[0m");
            String userId = scanner.nextLine();

            if (userId.equalsIgnoreCase("quit")) {
                System.out.println("\u001B[1m\u001B[35m Thank you for using the ATM. Goodbye!" + "\u001B[0m");
                break;
            }

            System.out.print("\u001B[1mEnter PIN: " + "\u001B[0m");
            String userPin = scanner.nextLine();

            Account account = findAccount(userId);

            if (account != null && account.validatePin(userPin)) {
                System.out.println("\n\u001B[1m\u001B[32mLogin successful!" + "\u001B[0m");
                performTransactions(account);
            } else {
                System.out.println("\u001B[1m\\u001B[31mInvalid user ID or PIN. Please try again."+ "\u001B[0m") ;
            }
        }
    }

    private Account findAccount(String userId) {
        for (Account account : accounts) {
            if (account.getUserId().equals(userId)) {
                return account;
            }
        }
        return null;
    }

    private void performTransactions(Account account) {
        while (true) {
            System.out.println("\n\u001B[1m\u001B[4m\u001B[34mSelect an operation:\n");
            System.out.println("1. View Balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. View Transaction History");
            System.out.println("6. Logout" + "\u001B[0m");

            System.out.print("\n\u001B[1m\u001B[4mEnter your choice: " + "\u001B[1m" + "\n");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.println("\u001B[1m\u001B[32mYour balance: \u001B[32m" + account.getBalance() + " units" + "\u001B[0m");
                    break;
                case 2:
                    System.out.print("\u001B[1m\u001B[4mEnter amount to withdraw: " + "\u001B[0m");
                    double withdrawAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline character
                    account.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.print("\n\u001B[1m\u001B[4mEnter amount to deposit: \u001B[0m" + "\u001B[0m");
                    double depositAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline character
                    account.deposit(depositAmount);
                    break;
                case 4:
                    System.out.print("\n\u001B[1m\u001B[4mEnter recipient's user ID: \u001B[0m" + "\u001B[0m");
                    String recipientId = scanner.nextLine();
                    Account recipientAccount = findAccount(recipientId);
                    if (recipientAccount != null) {
                        System.out.print("\n\u001B[1m\u001B[4mEnter amount to transfer: \u001B[0m" + "\u001B[0m");
                        double transferAmount = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline character
                        account.transfer(recipientAccount, transferAmount);
                    } else {
                        System.out.println("\n\u001B[31mRecipient not found. \u001B[0m]");
                    }
                    break;
                case 5:
                    account.displayTransactionHistory();
                    break;
                case 6:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("\u001B[31mInvalid choice. Please try again.\u001B[0m");
            }
        }
    }
}

public class ATM_system {
    public static void main(String[] args) {
        ATM atm = new ATM();

        // Create and add some example accounts
        Account account1 = new Account("user1", "1234");
        Account account2 = new Account("user2", "5678");
        atm.addAccount(account1);
        atm.addAccount(account2);

        atm.start();
    }
}
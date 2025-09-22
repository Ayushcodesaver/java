 import java.util.Scanner;

public class assigment {
	public static void main(String[] args) {
		UserInterface ui = new UserInterface();
		ui.mainMenu();
	}
}



class Account {
	private int accountNumber;
	private String accountHolderName;
	private double balance;
	private String email;
	private String phoneNumber;

	public Account(int accountNumber, String accountHolderName, double balance, String email, String phoneNumber) {
		this.accountNumber = accountNumber;
		this.accountHolderName = accountHolderName;
		this.balance = balance;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void deposit(double amount) {
		if (amount > 0) {
			balance += amount;
			System.out.println("Deposit successful. New balance: " + balance);
		} else {
			System.out.println("Invalid deposit amount.");
		}
	}

	public void withdraw(double amount) {
		if (amount > 0 && amount <= balance) {
			balance -= amount;
			System.out.println("Withdrawal successful. New balance: " + balance);
		} else if (amount > balance) {
			System.out.println("Insufficient balance.");
		} else {
			System.out.println("Invalid withdrawal amount.");
		}
	}

	public void displayAccountDetails() {
		System.out.println("Account Number: " + accountNumber);
		System.out.println("Account Holder Name: " + accountHolderName);
		System.out.println("Balance: " + balance);
		System.out.println("Email: " + email);
		System.out.println("Phone Number: " + phoneNumber);
	}

	public void updateContactDetails(String email, String phoneNumber) {
		this.email = email;
		this.phoneNumber = phoneNumber;
		System.out.println("Contact details updated successfully.");
	}
}

class UserInterface {
	private Account[] accounts;
	private int accountCount;
	private Scanner scanner;
	private final int MAX_ACCOUNTS = 100;

	public UserInterface() {
		accounts = new Account[MAX_ACCOUNTS];
		accountCount = 0;
		scanner = new Scanner(System.in);
	}

	public void createAccount() {
		if (accountCount >= MAX_ACCOUNTS) {
			System.out.println("Cannot create more accounts.");
			return;
		}
		System.out.print("Enter Account Number: ");
		int accNum = scanner.nextInt();
		scanner.nextLine(); // consume newline
		System.out.print("Enter Account Holder Name: ");
		String name = scanner.nextLine();
		System.out.print("Enter Initial Balance: ");
		double balance = scanner.nextDouble();
		scanner.nextLine();
		System.out.print("Enter Email: ");
		String email = scanner.nextLine();
		System.out.print("Enter Phone Number: ");
		String phone = scanner.nextLine();
		accounts[accountCount++] = new Account(accNum, name, balance, email, phone);
		System.out.println("Account created successfully.");
	}

	private Account findAccount(int accNum) {
		for (int i = 0; i < accountCount; i++) {
			if (accounts[i].getAccountNumber() == accNum) {
				return accounts[i];
			}
		}
		return null;
	}

	public void performDeposit() {
		System.out.print("Enter Account Number: ");
		int accNum = scanner.nextInt();
		System.out.print("Enter Amount to Deposit: ");
		double amount = scanner.nextDouble();
		Account acc = findAccount(accNum);
		if (acc != null) {
			acc.deposit(amount);
		} else {
			System.out.println("Account not found.");
		}
	}

	public void performWithdrawal() {
		System.out.print("Enter Account Number: ");
		int accNum = scanner.nextInt();
		System.out.print("Enter Amount to Withdraw: ");
		double amount = scanner.nextDouble();
		Account acc = findAccount(accNum);
		if (acc != null) {
			acc.withdraw(amount);
		} else {
			System.out.println("Account not found.");
		}
	}

	public void showAccountDetails() {
		System.out.print("Enter Account Number: ");
		int accNum = scanner.nextInt();
		Account acc = findAccount(accNum);
		if (acc != null) {
			acc.displayAccountDetails();
		} else {
			System.out.println("Account not found.");
		}
	}

	public void updateContact() {
		System.out.print("Enter Account Number: ");
		int accNum = scanner.nextInt();
		scanner.nextLine();
		Account acc = findAccount(accNum);
		if (acc != null) {
			System.out.print("Enter New Email: ");
			String email = scanner.nextLine();
			System.out.print("Enter New Phone Number: ");
			String phone = scanner.nextLine();
			acc.updateContactDetails(email, phone);
		} else {
			System.out.println("Account not found.");
		}
	}

	public void mainMenu() {
		while (true) {
			System.out.println("\n--- Banking Application Menu ---");
			System.out.println("1. Create Account");
			System.out.println("2. Deposit");
			System.out.println("3. Withdraw");
			System.out.println("4. Show Account Details");
			System.out.println("5. Update Contact Details");
			System.out.println("6. Exit");
			System.out.print("Enter your choice: ");
			int choice = scanner.nextInt();
			switch (choice) {
				case 1:
					createAccount();
					break;
				case 2:
					performDeposit();
					break;
				case 3:
					performWithdrawal(); 
					break;
				case 4:
					showAccountDetails();
					break;
				case 5:
					updateContact();
					break;
				case 6:
					System.out.println("Exiting application. Goodbye!");
					return;
				default:
					System.out.println("Invalid choice. Please try again.");
			}
		}
	}
}



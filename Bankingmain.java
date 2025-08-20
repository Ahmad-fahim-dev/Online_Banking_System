import java.sql.SQLOutput;
import java.util.Scanner;

public class Bankingmain {

    public static <createaccount> void main(String[] args) {
        System.out.println("***** Welcome to Online Banking System *****");
        System.out.println("*****Enter the option you want");
        System.out.println("CHOOSE 1 FOR CREATING NEW ACCOUNT");
        System.out.println("CHOOSE 2 FOR DEPOSIT");
        System.out.println("CHOOSE 3 FOR WITHDRAWAL");
        System.out.println("CHOOSE 4 FOR BALANCE CHECKING");
        Scanner input = new Scanner(System.in);
        int option = input.nextInt();
        if (option == 1) {
            new CreateAccount();
        } else if (option == 2) {
            new Deposit();
        } else if (option == 3) {
            new Withdrawal();
        } else if (option == 4) {
            System.out.println("Welcome to the Banking System!");
            System.out.println("Select an option:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdrawal");
            System.out.println("3. Check Balance");

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    new Deposit();
                    BalanceCheck balanceCheck = new BalanceCheck();
                    break;

                case 2:
                    new Withdrawal();
                    new BalanceCheck();
                    break;

                case 3:
                    new BalanceCheck();
                    break;

                default:
                    System.out.println("Invalid option.");
            }

        } else {
            System.out.println("******  SELECT THE  CORRECT  OPTION  ******");
        }

    }
}
import java.util.Scanner;
import java.sql.*;

public class Deposit {
    Scanner input = new Scanner(System.in);

    String email;
    String password;
    double amount;

    // MySQL connection config
    final String URL = "jdbc:mysql://localhost:3306/bankingsystem";
    final String USER = "root";
    final String DB_PASSWORD = "iamkhanbb";

    // Constructor
    public Deposit() {
        System.out.print("Enter your email: ");
        email = input.nextLine();

        System.out.print("Enter your password: ");
        password = input.nextLine();

        if (verifyAccount(email, password)) {
            System.out.print("Enter amount to deposit: ");
            amount = input.nextDouble();
            makeDeposit(email, amount);
        } else {
            System.out.println("❌ Invalid email or password. Deposit not allowed.");
        }
    }

    // Method to verify if account exists
    boolean verifyAccount(String email, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, DB_PASSWORD);

            String query = "SELECT * FROM account WHERE email = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            boolean exists = rs.next();
            conn.close();
            return exists;

        } catch (Exception e) {
            System.out.println("Database Error: " + e.getMessage());
            return false;
        }
    }

    // Method to perform the deposit
    void makeDeposit(String email, double amount) {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, DB_PASSWORD);

            String insert = "INSERT INTO deposit (email, amount) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(insert);
            stmt.setString(1, email);
            stmt.setDouble(2, amount);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Deposit successful. Amount: $" + amount);
            } else {
                System.out.println("❌ Deposit failed.");
            }

            conn.close();
        } catch (Exception e) {
            System.out.println("Deposit Error: " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        new Deposit();
    }
}

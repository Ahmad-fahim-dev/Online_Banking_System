import java.util.Scanner;
import java.sql.*;

public class Withdrawal {
    Scanner input = new Scanner(System.in);

    String email;
    String password;
    double amount;

    // MySQL connection config
    final String URL = "jdbc:mysql://localhost:3306/bankingsystem";
    final String USER = "root";
    final String DB_PASSWORD = "iamkhanbb";

    // Constructor
    public Withdrawal() {
        System.out.print("Enter your email: ");
        email = input.nextLine();

        System.out.print("Enter your password: ");
        password = input.nextLine();

        if (verifyAccount(email, password)) {
            double currentBalance = getBalance(email);
            System.out.println("Current balance: $" + currentBalance);

            System.out.print("Enter amount to withdraw: ");
            amount = input.nextDouble();

            if (amount <= currentBalance) {
                makeWithdrawal(email, amount);
            } else {
                System.out.println("❌ Insufficient balance.");
            }
        } else {
            System.out.println("❌ Invalid email or password. Withdrawal not allowed.");
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

    // Method to calculate current balance
    double getBalance(String email) {
        double deposits = 0.0;
        double withdrawals = 0.0;

        try {
            Connection conn = DriverManager.getConnection(URL, USER, DB_PASSWORD);

            // Total Deposits
            String depQuery = "SELECT SUM(amount) FROM deposit WHERE email = ?";
            PreparedStatement depStmt = conn.prepareStatement(depQuery);
            depStmt.setString(1, email);
            ResultSet depRs = depStmt.executeQuery();
            if (depRs.next()) {
                deposits = depRs.getDouble(1);
            }

            // Total Withdrawals
            String withQuery = "SELECT SUM(amount) FROM withdrawal WHERE email = ?";
            PreparedStatement withStmt = conn.prepareStatement(withQuery);
            withStmt.setString(1, email);
            ResultSet withRs = withStmt.executeQuery();
            if (withRs.next()) {
                withdrawals = withRs.getDouble(1);
            }

            conn.close();

        } catch (Exception e) {
            System.out.println("Balance Check Error: " + e.getMessage());
        }

        return deposits - withdrawals;
    }

    // Method to perform the withdrawal
    void makeWithdrawal(String email, double amount) {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, DB_PASSWORD);

            String insert = "INSERT INTO withdrawal (email, amount) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(insert);
            stmt.setString(1, email);
            stmt.setDouble(2, amount);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Withdrawal successful. Amount: $" + amount);
            } else {
                System.out.println("❌ Withdrawal failed.");
            }

            conn.close();
        } catch (Exception e) {
            System.out.println("Withdrawal Error: " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        new Withdrawal();
    }
}

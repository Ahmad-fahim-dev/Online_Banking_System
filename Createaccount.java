import java.util.Scanner;
import java.sql.*;

class CreateAccount {
    Scanner input = new Scanner(System.in);
    String name;
    String email;
    String password;

    // MySQL connection details
    String url = "jdbc:mysql://localhost:3306/bankingsystem";
    String user = "root";
    String dbPassword = "iamkhanbb";

    CreateAccount() {
        System.out.print("Enter new your name: ");
        name = input.nextLine();

        System.out.print("Enter new your email: ");
        email = input.nextLine();

        System.out.print("Create new your password: ");
        password = input.nextLine();

        saveToDatabase();
    }

    void saveToDatabase() {
        try {
            // Load MySQL JDBC Driver (optional with modern JDBC, but safe)
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(url, user, dbPassword);
            String sql = "INSERT INTO account (account_name, email, password) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password); // For real apps, hash this first

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("\n***** ACCOUNT CREATED SUCCESSFULLY *****");
                display();
            } else {
                System.out.println("Failed to create account.");
            }

            conn.close();
        } catch (Exception e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }

    void display() {
        System.out.println("\nYOUR NAME IS     : " + name);
        System.out.println("YOUR EMAIL IS    : " + email);
        System.out.println("YOUR PASSWORD IS : " + password);
    }

    public static void main(String[] args) {
        new CreateAccount();
    }
}

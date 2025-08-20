import java.util.Scanner;

class Transaction
 { 
    Scanner input;
    String TransactionID;
    String TransactionAmount;
    int ToAccountNumber;
     int TransactionTypes;
    
    Transaction()
    {
        this.input= new Scanner(System.in);
        System.out.println(" ENTER YOUR TRANSACTIONID");
        this.TransactionID =this.input.nextLine();
        System.out.println("ENTER YOUR TRANSACTION AMOUNT");
        this.TransactionAmount=this.input.nextLine();
        System.out.println("ENTER TRANSACTION TYPE");
        System.out.println("ENTER 1 FOR DEPOSIT");
        System.out.println("ENTER 2 FOR WITHDRAW ");
        this.TransactionTypes = this.input.nextInt();
        
        if(TransactionTypes==1){
        System.out.println("ENTER ACCOUNT NUMBER YOU WANT TO  DEPOSIT");
        this.ToAccountNumber = this.input.nextInt();
        System.out.println("DEPOSIT SUCESSFULLY");
        if(TransactionTypes==2)
        {
        System.out.println("WITHDRAW SUCESSFULLY");
        }
    }
}
void display() {
    System.out.println("==== TRANSACTION DETAILS ====");
    System.out.println("Transaction ID        : " + TransactionID);
    System.out.println("Transaction Amount    : " + TransactionAmount);
        if (TransactionTypes == 1) {
        System.out.println("Transaction Type      : Deposit");
        System.out.println("To Account Number     : " + ToAccountNumber);
    } else if (TransactionTypes == 2) {
        System.out.println("Transaction Type      : Withdraw");
    } else {
        System.out.println("Transaction Type      : Invalid");
    }
    System.out.println("==============================");
}
}
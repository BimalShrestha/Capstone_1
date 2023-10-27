package org.example;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {
    TransactionManager tm = new TransactionManager();
    Ledger l = new Ledger();
    Scanner scanner = new Scanner(System.in);
    public void homeScreen(){

        tm.writeExistingFinancialTransaction();
        String userInput;
        while(true) {

            try {
                displayHomeScreenOption();
                userInput = scanner.nextLine().toLowerCase();
                switch (userInput) {
                    case "d":
                        while(true) {
                            System.out.println("Whats the amount");
                            if (scanner.hasNextDouble()) {

                                double amount = scanner.nextDouble();
                                if (amount > 0) {
                                    tm.addDepositOrPayment(amount);
                                    scanner.nextLine();
                                    break;
                                } else {
                                    System.out.println("Enter a positive amount for deposit");
                                }


                            }
                            else{
                                System.out.println("Invalid input. please enter again");
                                scanner.nextLine();//consume the invalid input
                            }
                        }
                        break;


                    case "p":
                        while(true) {
                            System.out.println("Whats the amount");
                            if (scanner.hasNextDouble()) {

                                double amount = scanner.nextDouble();
                                if (amount < 0) {
                                    tm.addDepositOrPayment(amount);
                                    scanner.nextLine();
                                    break;
                                } else {
                                    System.out.println("Enter a negative amount for deposit");
                                }


                            }
                            else{
                                System.out.println("Invalid input. please enter again");
                                scanner.next();//consume the invalid input
                            }
                        }
                        break;
                    case "l":
                            ledgerOption();

                        break;
                    case "x":
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Please kindly chose the correct option");
                        break;

                }
            }
            catch(Exception ex){
                    System.out.println("Not the correct input format please input in correct format");
            }
        }
    }
    private void displayHomeScreenOption(){
        System.out.println("Welcome to BuckAndBear's Accounting ledger Application");
        System.out.println("Please Chose an option:");
        System.out.println("D) Add deposit \nP) Make Payment(Debit) \nL) Ledger \nX) Exit");
    }
    private void displayLedgerOption(){
        System.out.println("Chose the following ledger option");
        System.out.println("A) Display All \nD) Display Deposits \nP) Display Payments \nR) Display Reports \nH) Home ");
    }
    private void displayReportOption(){
        System.out.println("Chose the following option:");
        System.out.println("1) Month To Date");
        System.out.println("2) Previous Month");
        System.out.println("3) Year To Date");
        System.out.println("4) Previous Year");
        System.out.println("5) Search by Vendor");
        System.out.println("6) Custom Search");
        System.out.println("0) Back");
    }
    public void ledgerOption(){
        Scanner scanner = new Scanner(System.in);


        while(true){
            try{
                displayLedgerOption();
                String userInput = scanner.nextLine();
                switch(userInput.toLowerCase()){
                    case "a":
                        l.displayLedger(tm.transactions);
                        break;
                    case "d":
                            l.displayDeposits(tm.transactions);
                        break;
                    case "p":
                            l.displayPayments(tm.transactions);
                        break;
                    case "r":
                        reports();
                        break;
                    case "h":
                        System.out.println("You're going back to the home page");
                        return;
                    default:
                        System.out.println("Enter a valid input");
                        break;
                }
            }
            catch(Exception exception){
                System.out.println("Enter a valid input");
            }
        }

    }
    public void reports() {
        reportPage:while (true) {
            try {
                displayReportOption();
                int userInput = scanner.nextInt();
                switch (userInput) {
                    case 1:
                        l.searchMTD(tm.transactions);
                        continue reportPage;
                    case 2:
                        l.searchPreviousMonth(tm.transactions);
                        continue reportPage;
                    case 3:
                        l.searchYearToDate(tm.transactions);
                        continue reportPage;
                    case 4:
                       l.searchPreviousYear(tm.transactions);
                        continue reportPage;
                    case 5:
                        System.out.println("Enter the vendor name: ");
                        scanner.nextLine();
                        String vendor = scanner.nextLine();
                        l.searchByVendor(tm.transactions,vendor);
                        continue reportPage;
                    case 6:

                        l.customSearch(tm.transactions);
                        continue reportPage;
                    case 0:
                        System.out.println("Going back to ledger Home page");
                        scanner.nextLine();
                        return;
                    default:
                        System.out.println("Enter a valid output");
                        break;
                }

            } catch (InputMismatchException exception) {
                System.out.println("Enter the correct input");
            }
            scanner.nextLine();

        }
    }
}

package org.example;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Ledger {
    public void displayLedger(ArrayList<Transaction> displayLedgerTransaction){
        for (Transaction t : displayLedgerTransaction){
            System.out.println(t);
        }
    }
    public void displayDeposits(ArrayList<Transaction> displayDepositsOnly){
        for (Transaction t : displayDepositsOnly){
            if(t.getAmount()>0){
                System.out.println(t);
            }
        }
    }
    public void displayPayments(ArrayList<Transaction> displayPaymentOnly){
        for (Transaction t : displayPaymentOnly){
            if(t.getAmount()<0){
                System.out.println(t);
            }
        }
    }

    public void searchMTD(ArrayList<Transaction> mtdTransactions){
       for(Transaction t: mtdTransactions){
           if(YearMonth.now().equals(YearMonth.from(t.getDate()))){
            System.out.println(t);
           }
       }

    }
    public void searchPreviousMonth(ArrayList<Transaction> pmTransactions){
        for(Transaction t: pmTransactions){
            if(!t.getDate().isBefore(YearMonth.now().minusMonths(1).atDay(1)) && !t.getDate().isAfter((YearMonth.now().minusMonths(1).atEndOfMonth()))){
            System.out.println(t);
            }
        }
    }
    public void searchYearToDate(ArrayList<Transaction> ytdTransactions){
        for(Transaction t: ytdTransactions){
            if(!t.getDate().isBefore(LocalDate.of(LocalDate.now().getYear(),1,1))){//isBefore is the key to this
                System.out.println(t);
            }
        }
    }
    public void searchPreviousYear(ArrayList<Transaction> pyTransactions){
        for(Transaction t: pyTransactions){
            if (t.getDate().getYear() == (LocalDate.now().getYear() -1)){
                System.out.println(t);
            }
        }
    }
    public void searchByVendor(ArrayList<Transaction> vendorTransactions, String vendor){
        for(Transaction t: vendorTransactions){
            if(t.getVendor().equalsIgnoreCase(vendor)){
                System.out.println(t);
            }
            else{
                System.out.println("we don't have that vendor name");
                break;
            }
        }
    }
    public void customSearch(ArrayList<Transaction> customTransactionSearch){
        List<Transaction> matchingTransactions = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            try {
                System.out.println("Enter your start date in yyyy-mm-dd format");
                String sd = scanner.nextLine();
                System.out.println("Enter your end date in yyyy-mm-dd format");
                String ed = scanner.nextLine();
                System.out.println("Enter a user input that matches description or vendor name");
                String userInput = scanner.nextLine();
                System.out.println("Enter the amount ");
                double userAmountInput = scanner.nextDouble();
                scanner.nextLine();
                LocalDate startDate = LocalDate.parse(sd);
                LocalDate endDate = LocalDate.parse(ed);

                for (Transaction transaction : customTransactionSearch) {
                    LocalDate transactionDate = transaction.getDate();
                    if ((transactionDate.isEqual(startDate) || transactionDate.isEqual(endDate) || (transactionDate.isAfter(startDate) && transactionDate.isBefore(endDate))) || userInput.equalsIgnoreCase(transaction.getDescription())
                            || userInput.equalsIgnoreCase(transaction.getVendor()) || userAmountInput == transaction.getAmount()) {
                        matchingTransactions.add(transaction);
                    }
                }
                for (Transaction matchedTransaction : matchingTransactions) {
                    System.out.println(matchedTransaction);
                }
            } catch (DateTimeParseException e) {
                System.out.println("Not the correct input format");
            } catch (InputMismatchException e) {
                System.out.println("Enter a double input");
                scanner.nextLine();
            }
            break;

        }

    }

}

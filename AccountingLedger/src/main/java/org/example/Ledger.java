package org.example;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

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

}

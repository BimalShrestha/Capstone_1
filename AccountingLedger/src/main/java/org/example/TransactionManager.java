package org.example;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class TransactionManager {
    public ArrayList<Transaction> transactions = new ArrayList<>();

    public void writeExistingFinancialTransaction(){
        transactions.add(new Transaction(LocalDate.of(2022,4,15), LocalTime.of(10,13,25),"erogonomic keyboard","amazon",-89.50));
        transactions.add(new Transaction(LocalDate.of(2018,10,11), LocalTime.of(11,15, 0),"Invoice 1001 paid","Joe",1500.00));
        transactions.add(new Transaction(LocalDate.of(2021,12,22), LocalTime.of(8,11,59),"Invoice 1002 paid","Suarez",20000.50));
        transactions.add(new Transaction(LocalDate.of(2023,9,18), LocalTime.of(3,5,22),"Invoice 1003 paid","Kawhi",151545.35));
        transactions.add(new Transaction(LocalDate.of(2023,10,11), LocalTime.of(2,4,18),"Invoice 1004 paid","MJ",46352.25));

        try {
            FileWriter fileWriter = new FileWriter("src/main/resources/ledger.csv");
            fileWriter.write("date|time|description|vendor|amount\n");
            for(Transaction t: transactions){
                String ledger = String.format("%s|%s|%s|%s|%.2f \n", t.getDate(), t.getTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")), t.getDescription(), t.getVendor(), t.getAmount());
                fileWriter.write(ledger);
            }
            fileWriter.close();

        }
        catch(IOException exception){
            System.out.println("Sorry couldn't write that to the file");
        }
    }
    public void writeNewFinancialTransaction(Transaction transaction){
        try {
            FileWriter fileWriter = new FileWriter("src/main/resources/ledger.csv", true);
            String s = String.format("%s|%s|%s|%s|%.2f\n",transaction.getDate(),transaction.getTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")),transaction.getDescription(),transaction.getVendor(),transaction.getAmount());
            fileWriter.write(s);
            fileWriter.close();

        }
        catch (IOException exception){
            System.out.println("File cannot be written");
        }
    }
    public void addDepositOrPayment(double amount){
        Scanner scanner = new Scanner(System.in);
            try {
                LocalDate date = LocalDate.now();
                LocalTime time = LocalTime.now();
                System.out.println("Whats the description of this transaction");
                String description = scanner.nextLine();
                System.out.println("What is the name of the vendor?");
                String vendor = scanner.nextLine();

                Transaction depositPaymentTransaction = new Transaction(date, time, description, vendor, amount);
                transactions.add(depositPaymentTransaction);


                writeNewFinancialTransaction(depositPaymentTransaction);
                System.out.println("Transaction added:"+depositPaymentTransaction);
            } catch (Exception exception) {
                System.out.println("Enter correct input");
            }


    }

    public void readLedger(){
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/ledger.csv");
            Scanner scanner = new Scanner(fis);
            scanner.nextLine();
            while(scanner.hasNextLine()){
                String input = scanner.nextLine();
                String [] rowArray = input.split("\\|");
                Transaction transaction = new Transaction(LocalDate.parse(rowArray[0]),LocalTime.parse(rowArray[1]),rowArray[2],rowArray[3],Double.parseDouble(rowArray[4]));
                transactions.add(transaction);

            }
        }
        catch(FileNotFoundException exception){
            System.out.println("Sorry file not found");
        }
    }

    }






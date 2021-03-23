package com.komalnayangalla_c0779428;

import java.util.*;
import java.io.*;

class User{
    float balance;
    String accNo;

    User()
    {
        balance = 0.0f;
        accNo = "0000";
        String fileName = accNo+".txt";
        try {
            FileWriter fileWriter = new FileWriter(fileName,false);
            fileWriter.write(balance+"\n");
            fileWriter.close();
        }
        catch(Exception e)
        {
            System.out.println("Error while writing to file ...");
        }
    }

    User(String accNo)
    {
        this.accNo = accNo;
        String fileName = accNo+".txt";
        File file = new File(fileName);
        //System.out.println("File Exists? "+file.exists());
        if(!file.exists()) {
            try {
                FileWriter fileWriter = new FileWriter(fileName, false);
                balance = 0.0f;
                fileWriter.write(balance + "\n");
                fileWriter.close();
            } catch (Exception e) {
                System.out.println("Error while writing to file ...");
            }
        }
        else{
            try {
                Scanner reader = new Scanner(file);
                balance = Float.parseFloat(reader.nextLine());
                reader.close();
            }
            catch (Exception e)
            {
                System.out.println("Error while reading from file ...");
            }
        }
    }

    User(float balance,String accNo)
    {
        this.balance = balance;
        this.accNo = accNo;
        String fileName = accNo+".txt";
        try {
            FileWriter fileWriter = new FileWriter(fileName,false);
            fileWriter.write(balance+"\n");
            fileWriter.close();
        }
        catch(Exception e)
        {
            System.out.println("Error while writing to file ...");
        }
    }

    public void checkBalance() {
        String fileName = accNo+".txt";
        File file = new File(fileName);
        try {
            Scanner reader = new Scanner(file);
            float bal = Float.parseFloat(reader.nextLine());
            System.out.println("Your Account Balance : "+bal);
        }
        catch (Exception e)
        {
            System.out.println("Error while Reading File...");
        }
    }

    public void deposit(float amount)
    {
        String fileName = accNo+".txt";
        try {
            FileWriter fw = new FileWriter(fileName,false);
            balance += amount;
            fw.write(balance+"\n");
            fw.close();
            System.out.println("Amount "+amount+" successfully deposited..");
            checkBalance();
        }
        catch (Exception e)
        {
            System.out.println("Error while Reading File...");
        }

    }

    public void withdraw(float amount)
    {
        String fileName = accNo+".txt";
        if(amount > balance)
        {
            System.out.println("INSUFFICIENT FUNDS..!");
        }
        else {
            try {
                FileWriter fw = new FileWriter(fileName, false);
                balance -= amount;
                fw.write(balance + "\n");
                fw.close();
                System.out.println("Amount "+amount+" successfully withdrawn..");
                checkBalance();
            } catch (Exception e) {
                System.out.println("Error while Reading File...");
            }
        }
    }


}

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the account Number : ");
        String accNo = sc.nextLine();
        User user = new User(accNo);
        while (true)
        {
            System.out.println("\n\n------------- MENU -------------");
            System.out.println("1.) Deposit");
            System.out.println("2.) Withdraw");
            System.out.println("3.) Check Balance");
            System.out.println("4.) Exit");
            System.out.print("\n\n Enter your choice --> ");
            try {
                int ch = sc.nextInt();
                System.out.println("\n--------------------------------");
                if(ch==1)
                {
                    System.out.print("\nEnter the amount to deposit : ");
                    float amount = sc.nextFloat();
                    user.deposit(amount);
                }
                else if(ch==2)
                {
                    System.out.print("\nEnter the amount to withdraw : ");
                    float amount = sc.nextFloat();
                    user.withdraw(amount);

                }
                else if(ch==3)
                {
                    user.checkBalance();
                }
                else if(ch==4)
                {
                    System.exit(1);
                }
                else {
                    throw new Exception("Invalid Choice");
                }
            }
            catch (Exception e)
            {
                System.out.println("\nEnter a valid choice!!");
            }
        }

    }
}

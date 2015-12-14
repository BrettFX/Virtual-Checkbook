package allen.brett.checkbook;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	
	public static final String PATH = "./balance/balance.txt";	

	public static void main(String[] args) throws IOException{
		
		double interest = 0.045;
		double newFileInput = 0.0, newFileOutput = 0.0;
		char[] choice = {'\0'};
		
		//Getting input from balance.txt
		try
		{
			ReadFile beginningBal = new ReadFile(PATH);
			
			beginningBal.setNumLines();
			
			String[] fileInput = beginningBal.OpenFile();
			
			newFileInput = Double.parseDouble(fileInput[0]);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		Scanner input = new Scanner(System.in);
		
		//balance arg will change to value of newFileInput
		Account checking = new Account(interest, newFileInput);		
		
		do{			
			displayMenu();
			choice[0] = input.next().charAt(0);
			
			//Validate choice
			while(Character.toUpperCase(choice[0]) < 'A' || Character.toUpperCase(choice[0]) > 'G'){
				System.out.print("Please make a choice in the range A - G: ");
				displayMenu();
				choice[0] = input.next().charAt(0);
			}
			
			//Process selection
			switch(choice[0]){
			case 'a':
			case 'A':
				System.out.println("The current balance is $" + checking.getBalance());
				break;
			case 'b':
			case 'B':
				System.out.println("There have been " + checking.getTransactions() + " transaction(s)");
				break;
			case 'c':
			case 'C':
				System.out.println("Interest earned for this period: $" + checking.getInterest());
				break;
			case 'd':
			case 'D':
				makeDeposit(checking, input);
				break;
			case 'e':
			case 'E':
				withdraw(checking, input);
				break;
			case 'f':
			case 'F':
				checking.calcInterest();
				System.out.println("Interest added.");
				break;
			case 'g':
			case 'G': //write to output and call it a day
				newFileOutput = checking.getBalance();
				
				try
				{
					WriteFile endingBal = new WriteFile(PATH);
					endingBal.write(newFileOutput);
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
				
				System.out.println("Process Completed . . .");
				break;
			default:
				break;
			}
		}while(Character.toUpperCase(choice[0]) != 'G');
	}
	
	public static void displayMenu(){
		System.out.println("\n\t\tMENU");
		System.out.println("-----------------------------------------");
		System.out.println("A) Display account balance");
		System.out.println("B) Display number of transactions");
		System.out.println("C) Display interest earned for this period");
		System.out.println("D) Make deposit");
		System.out.println("E) Make withdrawal");
		System.out.println("F) Add interest for this period");
		System.out.println("G) Exit\n");
		System.out.print(">>> ");
	}
	
	public static void makeDeposit(Account acct, Scanner input){
		double dollars;
		
		System.out.print("Enter the amount of the deposit: $");
		dollars = input.nextDouble();
		
		acct.makeDeposit(dollars);
	}
	
	public static void withdraw(Account acct, Scanner input){
		double dollars;
		
		System.out.print("Enter the amount of the withdrawal: $");
		dollars = input.nextDouble();
		
		if(!acct.withdraw(dollars)){
			System.err.println("ERROR: Withdrawal amount too large.\n");
		}
	}
}

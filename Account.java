package allen.brett.checkbook;

public class Account {
	
	private double balance;
	private double interestRate;
	private double interest;
	private int transactions;
	
	public Account(double iRate, double bal){
		balance = bal;
		interestRate = iRate;
		interest = 0;
		transactions = 0;
	}
	
	public void setInterestRate(double iRate){
		interestRate = iRate;
	}
	
	public void makeDeposit(double amount){
		balance += amount;
		transactions++;
	}
	
	public boolean withdraw(double amount){
		if(balance < amount){
			return false;
		}else{
			balance -= amount;
			transactions++;
			return true;
		}
	}
	
	public void calcInterest(){
		interest = balance * interestRate;
		balance += interest;
	}
	
	public double getInterestRate(){
		return interestRate;
	}
	
	public double getBalance(){
		return balance;
	}
	
	public double getInterest(){
		return interest;
	}
	
	public int getTransactions(){
		return transactions;
	}
}

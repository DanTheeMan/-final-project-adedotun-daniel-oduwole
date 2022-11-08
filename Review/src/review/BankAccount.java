/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package review;

/**
 *
 * @author leoli
 */
public abstract class BankAccount {
    private int accountNumber;
    private float balance;
    private Customer customer;

    public BankAccount(int accountNumber, float balance, Customer customer) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customer = customer;
    }
    
    public BankAccount(){
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public float getBalance() {
        return balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public void deposit(float amount){
        balance+=amount;
        
    }
    
    public abstract void withdraw(float amount) throws Exception;
    
    
    public void transfer(BankAccount other, float amount) throws Exception{
        try {
            this.withdraw(amount);
            other.deposit(amount);
        } catch (Exception e) {
            System.out.println("Insufficient funds");
            throw e;
        }
    }
    
}

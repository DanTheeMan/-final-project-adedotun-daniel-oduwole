/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package review;

/**
 *
 * @author leoli
 */
public class SavingsAccount extends BankAccount {
    private static float ACTIVITY_PENALTY = 5;
    private static int ACTIVITY_LIMIT = 10;
    private float minBalance;
    private float withdrawCount;

    public SavingsAccount(float minBalance, float withdrawCount, int accountNumber, float balance, Customer customer) {
        super(accountNumber, balance, customer);
        this.minBalance = minBalance;
        this.withdrawCount = withdrawCount;
    }

    

    public float getMinBalance() {
        return minBalance;
    }

    public float getWithdrawCount() {
        return withdrawCount;
    }

    public void setMinBalance(float minBalance) {
        this.minBalance = minBalance;
    }

    public void setWithdrawCount(float withdrawCount) {
        this.withdrawCount = withdrawCount;
    }
    
    public void withdraw(float amount) throws Exception {
        float fee = 0;
        
        if(getBalance() < minBalance && withdrawCount > ACTIVITY_LIMIT){
            fee = ACTIVITY_PENALTY;
            
        }
        
        if((amount+fee)<=getBalance()){
            setBalance(getBalance()-(amount+fee));
            
        }
        else{
            throw new Exception();
        }
    }
}

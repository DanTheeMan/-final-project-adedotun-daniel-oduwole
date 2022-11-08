/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package review;

/**
 *
 * @author leoli
 */
public class ChequingAccount extends BankAccount {
    private float overDraftPenalty;
    private float overDraftLimit;

    public ChequingAccount(float overDraftPenalty, float overDraftLimit, int accountNumber, float balance, Customer customer) {
        super(accountNumber, balance, customer);
        this.overDraftPenalty = overDraftPenalty;
        this.overDraftLimit = overDraftLimit;
    }

    public ChequingAccount() {
    }

    public float getOverDraftPenalty() {
        return overDraftPenalty;
    }

    public float getOverDraftLimit() {
        return overDraftLimit;
    }

    public void setOverDraftPenalty(float overDraftPenalty) {
        this.overDraftPenalty = overDraftPenalty;
    }

    public void setOverDraftLimit(float overDraftLimit) {
        this.overDraftLimit = overDraftLimit;
    }
    
    public void withdraw(float amount) throws Exception{
        if(amount<=getBalance()){
            setBalance(getBalance() - amount);
            
        }
        else{
            if(amount <=getBalance()+overDraftLimit){
                setBalance(getBalance() - (amount + overDraftPenalty));
            }
            else{
                throw new Exception();
            }
        }
    }
    
}

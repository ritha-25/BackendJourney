public class Account {
    private String accountNumber;
    private double balance;
    private User owner;
    private double dailyWithdrawl;
    public Account(String AccountNumber,
     double balance,
   User owner){
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.owner = owner;
        this.dailyWithdrawl = 0;
    }
    public void deposit(double amount){
        if(amount<=0){
            System.out.println("The amount are incoorrect");
            return;
        }
        balance+=amount;
        System.out.println("you deposited $"+amount+"and your new bakence is "+balance);

    }
    public abstract boolean withdrw(double amount);
    public double getBalance()          { return balance; }
    public String getAccountNumber()    { return accountNumber; }
    public User getOwner()          { return owner; }
    protected void setBalance(double b) { this.balance = b; }





}

public class Main {
    public static void main(String[] args) {
        User prince = new User("p09", "prince", "098");
        Account savings = new SavingAccount("sa90", 1000.0, prince, 0.05);

        Atm atm = new Atm();
        atm.login(savings, "098");
        atm.checkBalance();
        atm.deposit(200);
        atm.withdrawal(300);
        atm.checkBalance();
        ((SavingAccount) savings).addInterest();
        atm.checkBalance();
    }
}
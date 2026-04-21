public class Main {
    public static void main(String[] args) {

        try {

            User prince = new User("p09", "Prince", "098");
            Account savings = new SavingAccount("sa90", 1000.0, prince, 0.05);

            Atm atm = new Atm();
            atm.login(savings, "098");
            atm.checkBalance();
            atm.deposit(200);
            atm.withdrawal(300);
            atm.checkBalance();
            ((SavingAccount) savings).addInterest();
            atm.checkBalance();

            System.out.println("\n Testing Unchecked + Custom Exceptions ");
            atm.deposit(-50);
            atm.withdrawal(9999);

            System.out.println("\n Current Account with Overdraft ");
            User nzaramba = new User("p10", "nzaramba", "456");
            Account current = new CurrentAccount("ca01", 500.0, nzaramba, 200.0);

            Atm atm2 = new Atm();
            atm2.login(current, "456");
            atm2.checkBalance();
            atm2.withdrawal(650);
            atm2.checkBalance();
            atm2.withdrawal(200);

            System.out.println("Testing Invalid Account Creation ");
            Account bad = new SavingAccount("", -500.0, prince, 0.05);

        } catch (BankException e) {
            System.out.println("System error: " + e.getMessage());
        }
    }
}
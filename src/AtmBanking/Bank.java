package AtmBanking;

import java.io.*;
import java.util.*;

public class Bank {

    private Map<String, Account> accounts = new HashMap<>();
    private Map<String, User> users = new HashMap<>();
    private static final String DATA_DIR = "data";
    private static final String ACCOUNTS_FILE;
    private static final String USERS_FILE;

    static {
        String basePath = System.getProperty("user.dir");
        ACCOUNTS_FILE = basePath + File.separator + "data" + File.separator + "accounts.txt";
        USERS_FILE = basePath + File.separator + "data" + File.separator + "users.txt";
    }

    public Bank() {
        createDirectoryIfNeeded();
        loadData();
    }

    private void createDirectoryIfNeeded() {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }


    private void loadData() {
        users = loadUsers();
        accounts = loadAccounts();
    }

    private Map<String, User> loadUsers() {
        Map<String, User> loadedUsers = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    User user = new User(parts[0], parts[1], parts[2]);
                    loadedUsers.put(parts[0], user);
                }
            }
            System.out.println("Loaded " + loadedUsers.size() + " users from records.");
        } catch (FileNotFoundException e) {
            System.out.println("No existing user records found. Starting fresh.");
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
        return loadedUsers;
    }


    private Map<String, Account> loadAccounts() {
        Map<String, Account> loadedAccounts = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ACCOUNTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String accNum = parts[0];
                    String type = parts[1];
                    double balance = Double.parseDouble(parts[2]);
                    String ownerId = parts[3];

                    User owner = users.get(ownerId);
                    if (owner != null) {
                        try {
                            Account account = null;
                            if ("SavingAccount".equals(type) && parts.length >= 5) {
                                double rate = Double.parseDouble(parts[4]);
                                account = new SavingAccount(accNum, balance, owner, rate);
                            } else if ("CurrentAccount".equals(type) && parts.length >= 5) {
                                double overdraft = Double.parseDouble(parts[4]);
                                account = new CurrentAccount(accNum, balance, owner, overdraft);
                            }
                            if (account != null) {
                                loadedAccounts.put(accNum, account);
                            }
                        } catch (BankException e) {
                            System.out.println("Could not load account " + accNum + ": " + e.getMessage());
                        }
                    }
                }
            }
            System.out.println("Loaded " + loadedAccounts.size() + " accounts from records.");
        } catch (FileNotFoundException e) {
            System.out.println("No existing account records found. Starting fresh.");
        } catch (IOException e) {
            System.out.println("Error loading accounts: " + e.getMessage());
        }
        return loadedAccounts;
    }


    public void saveData() {
        saveUsers();
        saveAccounts();
    }


    private void saveUsers() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(USERS_FILE))) {
            for (User user : users.values()) {
                writer.println(user.getUserId() + "," + user.getName() + "," + user.getPin());
            }
            System.out.println("User records saved.");
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }


    private void saveAccounts() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ACCOUNTS_FILE))) {
            for (Account acc : accounts.values()) {
                StringBuilder sb = new StringBuilder();
                sb.append(acc.getAccountNumber()).append(",");
                sb.append(acc.getClass().getSimpleName()).append(",");
                sb.append(acc.getBalance()).append(",");
                sb.append(acc.getOwner().getUserId()).append(",");

                if (acc instanceof SavingAccount) {
                    sb.append(((SavingAccount) acc).getInterestRate());
                } else if (acc instanceof CurrentAccount) {
                    sb.append(((CurrentAccount) acc).getOverdraftLimit());
                }
                writer.println(sb.toString());
            }
            System.out.println("Account records saved.");
        } catch (IOException e) {
            System.out.println("Error saving accounts: " + e.getMessage());
        }
    }


    public void addUser(User user) {
        users.put(user.getUserId(), user);
        System.out.println("Welcome, " + user.getName() + "! Your ID is: " + user.getUserId());
        saveData();
    }

    public User getUser(String userId) {
        return users.get(userId);
    }


    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
        System.out.println("Account " + account.getAccountNumber() + " created for " + account.getOwner().getName());
        saveData();
    }


    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public void removeAccount(String accountNumber) {
        accounts.remove(accountNumber);
        System.out.println("Account " + accountNumber + " has been closed.");
        saveData();
    }

    public void listAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts in the system yet.");
            return;
        }
        System.out.println("\n Your Bank Accounts ");
        for (Account account : accounts.values()) {
            System.out.println("Account: " + account.getAccountNumber()
                    + " | Owner: " + account.getOwner().getName()
                    + " | Type: " + account.getClass().getSimpleName()
                    + " | Balance: FRW" + String.format("%.2f", account.getBalance()));
        }
    }

    public Map<String, Account> getAllAccounts() {
        return accounts;
    }

    public int getAccountCount() {
        return accounts.size();
    }

    public int getUserCount() {
        return users.size();
    }
}
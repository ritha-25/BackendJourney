package AtmBanking;

public class Transaction<T> {
    private T transactionId;
    private String type;
    private double amount;
    private String description;
    private String timestamp;

    public Transaction(T transactionId, String type, double amount, String description) {
        this.transactionId = transactionId;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.timestamp = java.time.LocalDateTime.now().toString();
    }

    public T getTransactionId() {
        return transactionId;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + transactionId +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
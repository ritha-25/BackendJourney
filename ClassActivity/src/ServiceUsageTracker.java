import java.util.Scanner;

public class ServiceUsageTracker {

    private static final int totalUnits = 70;

    private static int usedUnits = 0;

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int userChoice;

        System.out.println("Total available units: " + totalUnits);

        do {

            System.out.println("\nPlease select an option:");
            System.out.println("1. Consume Service Units");
            System.out.println("2. Check Remaining Units");
            System.out.println("3. View Usage Percentage");
            System.out.println("4. Reset Usage");
            System.out.println("0. Exit");
            System.out.print("Your choice: ");

            userChoice = input.nextInt();

            switch(userChoice) {
                case 1:
                    System.out.print("Enter units to consume: ");
                    double unitsToConsume = input.nextDouble();
                    consumeUnits(unitsToConsume);
                    break;

                case 2:
                    checkRemainingUnits();
                    break;

                case 3:
                    calculateUsagePercentage();
                    break;

                case 4:
                    resetUsage();
                    break;

                case 0:
                    System.out.println("Thank you for using the system!");
                    break;

                default:
                    System.out.println("Invalid option. Please enter 0-4");
            }

        } while(userChoice != 0);

        input.close();
    }

    public static void consumeUnits(double units) {
        int remaining = totalUnits - usedUnits;

        if(units < 0) {
            System.out.println("Error: Cannot use negative units!");
            return;
        }

        if(units > remaining) {
            System.out.println("Error: Only " + remaining + " units remaining!");
            return;
        }

        usedUnits += units;
        System.out.println("Success! Used " + units + " units.");
        System.out.println("Remaining: " + (totalUnits - usedUnits) + " units");
    }

    public static void checkRemainingUnits() {
        int remaining = totalUnits - usedUnits;
        System.out.println("Remaining units: " + remaining);
    }

    public static void calculateUsagePercentage() {

        if(totalUnits == 0) {
            System.out.println("Error: No units available!");
            return;
        }

        double percentage = ((double) usedUnits / totalUnits) * 100;
        System.out.println("Usage percentage: " + percentage + "%");
    }

    public static void resetUsage() {
        usedUnits = 0;
        System.out.println("Usage has been reset to zero.");
    }
}
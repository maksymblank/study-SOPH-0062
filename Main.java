import java.util.Scanner;

public class Main {
    static final String[] DAYS = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    static Scanner input = new Scanner(System.in);

    // function to read the symbol from the user
    static String readSymbol() {
        String symbol = "";
        while (symbol.isEmpty()) {
            System.out.print("Enter the ticker symbol: ");
            symbol = input.nextLine().trim().toUpperCase();
            if (symbol.isEmpty()) {
                System.out.println("Symbol cannot be empty");
            }
        }
        return symbol;
    }

    // function to read the prices from the user and put them in array of 5 
    static double[] readPrices() {
        double[] prices = new double[5];
        for (int i = 0; i <= 4; i++) {
            boolean valid = false;
            while (!valid) {
                System.out.print("Enter the closing price for " + DAYS[i] + ": ");
                String entry = input.nextLine().trim();

                // try/catch is needed to handle strings
                // tested with -46, 1.2.3, abc, and 0
                try {
                    double price = Double.parseDouble(entry);
                    if (price <= 0) {
                        System.out.println("Price must be greater than 0");
                    } else {
                        prices[i] = price;
                        valid = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a number.");
                }
            }
        }
        return prices;
    }

    // function to calculate the average
    static double calcAverage(double[] prices) {
        double sum = 0;
        for (double price : prices) {
            sum = sum + price;
        }
        return sum / prices.length;
    }

    // function to calculate the highest 
    static int calcHigh(double[] prices) {
        int high = 0;
        for (int i = 1; i <= 4; i++) {
            if (prices[i] > prices[high]) {
                high = i;
            }
        }
        return high;
    }

    // function to calculate the lowest price
    static int calcLow(double[] prices) {
        int low = 0;
        for (int i = 1; i <= 4; i++) {
            if (prices[i] < prices[low]) {
                low = i;
            }
        }
        return low;
    }

    // just a helper to append + / -
    static String sign(double number) {
        if (number > 0) {
            return "+";
        } else if (number < 0) {
            return "-";
        }
        return "";
    }

    // helper to append $ for moneys and + / -
    static String money(double number) {
        String s = sign(number);
        String formatted = String.format("%.2f", Math.abs(number));
        return s + "$" + formatted;
    }

    // function to calculate the move
    static int calcMove(double[] prices) {
        int moveIndex = -1;
        double biggest = 0;
        for (int i = 1; i <= 4; i++) {
            double size = Math.abs(prices[i] - prices[i - 1]);
            if (size > biggest) {
                biggest = size;
                moveIndex = i;
            }
        }
        return moveIndex;
    }

    public static void main(String[] args) {
        int counter = 0;
        boolean keepGoing = true;

        while (keepGoing) {
            String symbol = readSymbol();
            double[] prices = readPrices();

            double average = calcAverage(prices);
            int high = calcHigh(prices);
            int low = calcLow(prices);
            double weeklyChange = prices[4] - prices[0];
            double weeklyPercent = weeklyChange / prices[0] * 100;
            int move = calcMove(prices);

            String trend = "FLAT";
            if (weeklyChange > 0) {
                trend = "UP";
            } else if (weeklyChange < 0) {
                trend = "DOWN";
            }

            System.out.println("=== Weekly Summary for " + symbol + " ===");
            System.out.println("Average: $" + String.format("%.2f", average));
            System.out.println("High: $" + String.format("%.2f", prices[high]) + " (" + DAYS[high] + ")");
            System.out.println("Low: $" + String.format("%.2f", prices[low]) + " (" + DAYS[low] + ")");
            System.out.println("Weekly Change: " + money(weeklyChange));
            System.out.println("Weekly Percent: " + sign(weeklyPercent) + String.format("%.2f", Math.abs(weeklyPercent)) + "%");
            if (move == -1) {
                System.out.println("Move: No change");
            } else {
                System.out.println("Move: " + money(prices[move] - prices[move - 1]) + " (" + DAYS[move] + ")");
            }
            System.out.println("Trend: " + trend);
            System.out.println("===================================");

            counter = counter + 1;

            System.out.print("Analyze another stock? (y/n) ");
            String response = input.nextLine().trim().toLowerCase();
            keepGoing = response.equals("y");
        }
        System.out.println("Thank you for using the stock analyzer!");
    }
}

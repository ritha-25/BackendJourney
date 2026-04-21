public class TemperatureConverter {

    public static void main(String[] args) {
        double[] temperatures = {0, 25, 37};

        for (int i = 0; i < temperatures.length; i++) {

            if (temperatures[i] < -273.15) {
                System.out.println("Error: " + temperatures[i] + "°C is below absolute zero!");
            } else {

                double fahrenheit = (temperatures[i] * 9 / 5) + 32;
                System.out.println(temperatures[i] + "°C is " + fahrenheit + "°F");
            }
        }
    }
}
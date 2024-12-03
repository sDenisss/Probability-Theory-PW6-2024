public class Main {
    static double[] numbers = { 0.83, 0.73, -0.48, 0.00, -1.35, 1.59, 0.31, 0.17, 0.59, -0.45, -0.03, 0.73, -0.59,
            -1.59,
            0.38, 1.49, 0.14, -0.62, -1.59, 1.45 };

    public static void main(String[] args) {

        // -----------
        Methods.statisticalCaracteristics(numbers);
        Methods.findIntervalStatisticalRange(numbers, numbers.length);

    }

    public static double[] getNumbers() {
        return numbers;
    }
}

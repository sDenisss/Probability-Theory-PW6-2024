import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Methods {
    static double[] sheduleArrX;
    static double[] sheduleArrY;

    public static void findIntervalStatisticalRange(double[] numbers, int n) {
        Arrays.sort(numbers);
        double h = Math.round((numbers[n - 1] - numbers[0]) / (1 + Math.log(n) / Math.log(2)) * 10) / 10.0;

        double start = numbers[0] - h / 2;
        double finish = start + h;

        List<Double> centers = new ArrayList<>();
        List<Double> frequencies = new ArrayList<>();

        int num = 0; // Частота
        int total = numbers.length;
        int currentIndex = 0;

        List<Double> sheduleX = new ArrayList<>();
        List<Double> sheduleY = new ArrayList<>();

        // Формируем интервалы
        while (start < numbers[numbers.length - 1]) {
            while (currentIndex < numbers.length && numbers[currentIndex] < finish) {
                num++;
                currentIndex++;
            }

            double center = (start + finish) / 2;
            double frequency = (double) num / total;

            centers.add(center);
            frequencies.add(frequency);

            sheduleX.add(start); // Границы интервалов
            sheduleX.add(finish);
            sheduleY.add(frequency);

            System.out.printf("[%.2f, %.2f): частота: %d, частотность: %.2f%n", start, finish, num, frequency);

            start = finish;
            finish = start + h;
            num = 0;
        }

        // sheduleX.add(start);
        // Заполняем массивы для передачи в класс Histogram
        sheduleArrX = sheduleX.stream().mapToDouble(Double::doubleValue).toArray();
        sheduleArrY = sheduleY.stream().mapToDouble(Double::doubleValue).toArray();

        setHisPolArrX(sheduleArrX);
        setHisPolArrY(sheduleArrY);
    }

    public static void statisticalCaracteristics(double[] numbers) {
        System.out.println("Исходные данные:");
        for (double d : numbers) {
            System.out.print(d + " ");
        }

        Arrays.sort(numbers);

        System.out.println("\nВариационный ряд:");
        for (double number : numbers) {
            System.out.print(number + " ");
        }

        System.out.println("\nПервая порядковая статистика: " + numbers[0]);
        System.out.println("Первая порядковая статистика: " + numbers[numbers.length - 1]);
        double razmah = numbers[numbers.length - 1] - numbers[0];
        System.out.println("Размах Выборки: " + razmah);

        double summ = 0;
        for (double d : numbers) {
            summ += d;
        }
        double mathWait = (1 / (double) numbers.length) * summ;
        System.out.print("Математическое ожидание: ");
        System.out.printf("%.5f", mathWait);

        double summ2 = 0;
        for (int i = 0; i < numbers.length; i++) {
            summ2 += Math.pow(numbers[i] - mathWait, 2);
        }
        double standard_deviation = Math.sqrt((1 / (double) numbers.length) * (summ2));
        System.out.print("\nСреднеквадратичное отклонение: ");
        System.out.printf("%.5f", standard_deviation);

        double summ3 = 0;
        for (int i = 0; i < numbers.length; i++) {
            summ3 += Math.pow(numbers[i] - mathWait, 2);
        }
        double fixed_choice_deviation = Math.sqrt((1 / (double) (numbers.length - 1)) * (summ3));
        System.out.print("\nИсправленное выборочное среднеквадратичное отклонение: ");
        System.out.printf("%.5f", fixed_choice_deviation);

        // ---------------
        double summ4 = 0;
        double roundedNumber = 0;
        double[] roundedNumbers = new double[numbers.length];
        double frequency = 1;
        int newLenghth = 0;
        System.out.println("\nF:");
        System.out.printf("Для x <= %.2f: 0,00%n", numbers[0]);
        for (int i = 1; i < numbers.length;) {
            if (numbers[i - 1] == numbers[i]) {
                frequency = 2;
                summ4 += (frequency / numbers.length);
                roundedNumber = Math.round(summ4 * 100.0) / 100.0; // Округление до сотых
                System.out.printf("Для %.2f < x <= %.2f: %.2f%n", numbers[i - 1], numbers[i + 1], roundedNumber);
                newLenghth += 2;
                i += 2;
            } else {
                frequency = 1;
                summ4 += (frequency / numbers.length);
                roundedNumber = Math.round(summ4 * 100.0) / 100.0; // Округление до сотых
                System.out.printf("Для %.2f < x <= %.2f: %.2f%n", numbers[i - 1], numbers[i], roundedNumber);
                newLenghth++;
                i++;
            }

        }
        System.out.println("Для " + numbers[numbers.length - 1] + " < x: " + "1,00");

        roundedNumbers[0] = 0.0;
        for (int i = 1; i < roundedNumbers.length; i++) {
            roundedNumbers[i] = roundedNumbers[i - 1] + 0.05;
        }
        roundedNumbers[numbers.length - 1] = 1.0;

        sheduleArrX = new double[newLenghth];
        sheduleArrY = new double[newLenghth];
        setSheduleArrX(numbers);
        setSheduleArrY(roundedNumbers);
    }

    public static double[] getSheduleArrX() {
        return sheduleArrX;
    }

    public static double[] getSheduleArrY() {
        return sheduleArrY;
    }

    public static void setHisPolArrX(double[] sheduleArrX) {
        Methods.sheduleArrX = sheduleArrX;
    }

    public static void setHisPolArrY(double[] sheduleArrY) {
        Methods.sheduleArrY = sheduleArrY;
    }

    public static void setSheduleArrX(double[] sheduleArrX) {
        Methods.sheduleArrX = sheduleArrX;
    }

    public static void setSheduleArrY(double[] sheduleArrY) {
        Methods.sheduleArrY = sheduleArrY;
    }
}

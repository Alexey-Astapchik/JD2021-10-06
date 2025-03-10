package by.it.chumak.jd01_05;

import static java.lang.Math.*;

public class TaskA {
    public static void main(String[] args) {
        step1();
        step2();
        step3();
    }

    private static void step3() {
        final double X = 12.1;

        for (double a = -5; a <= 12; a = a + 3.75) {
            double y  = exp(a*X) - 3.45 * a;
            System.out.printf("При a=%f y=%f\n", a, y);
        }
    }

    private static void step2() {
        double a = 1.21;
        double b = 0.371;
        double y = tan(pow(a + b, 2)) - pow(a + 1.5, 1 / 3.0) + a * pow(b, 5) - b / log(pow(a, 2));

        System.out.printf("При a=%f b=%f y=%f\n", a, b, y);
    }

    private static void step1() {
        double a = 756.13;
        double x = 0.3;
        double z, part1, part2, part3;

        part1 = cos(pow((x * x) + (PI / 6), 5));
        part2 = sqrt(x * pow(a, 3));
        part3 = log(abs((a - 1.12 * x) / 4));

        z = part1 - part2 - part3;

        System.out.printf("При x=%f a=%f z=%f\n", x, a, z);
    }
}

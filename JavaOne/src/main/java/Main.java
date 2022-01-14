public class Main {
    public static void main(String[] args) {
        System.out.println(isAmountWithin(5, 10));
        printPositiveOrNegative(-1);
        System.out.println(isNegative(-1));
        printStringNTimes("GeekBrains", 3);
        System.out.println(isLeapYear(2060));
    }

    public static boolean isAmountWithin(int numberOne, int numberTwo) {
        int sum = numberOne + numberTwo;
        return sum >= 10 && sum <= 20;
    }

    public static void printPositiveOrNegative(int number) {
        System.out.println(number >= 0 ? "положительное" : "отрицательное");
    }

    public static boolean isNegative(int number) {
        return number < 0;
    }

    public static void printStringNTimes(String string, int times) {
        for (int i = 0; i < times; i++) {
            System.out.println(string);
        }
    }

    public static boolean isLeapYear(int year) {
        if (year % 400 == 0) {
            return true;
        } else if (year % 100 == 0) {
            return false;
        } else return year % 4 == 0;
    }
}

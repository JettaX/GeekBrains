public class HomeWorkApp {
    public static void main(String[] args) {
        HomeWorkApp homeWorkApp = new HomeWorkApp();
        homeWorkApp.printThreeWords();
        homeWorkApp.checkSumSign();
        homeWorkApp.printColor();
        homeWorkApp.compareNumbers();
    }

    public void printThreeWords() {
        System.out.println("Orange\nBanana\nApple");
    }

    public void checkSumSign() {
        int firstNumber = 1;
        int secondNumber = 2;

        System.out.println(firstNumber + secondNumber >= 0 ? "Сумма положительная" : "Сумма отрицательная");
    }

    public void printColor() {
        int value = 100;

        if (value <= 0) {
            System.out.println("Красный");
        } else if (value > 0 && value < 100) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }

    public void compareNumbers() {
        int a = 3;
        int b = 3;
        System.out.println(a >= b ? "a >= b" : "a < b");
    }
}

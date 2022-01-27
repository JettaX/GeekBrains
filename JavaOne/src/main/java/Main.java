public class Main {
    public static void main(String[] args) {
        Plate plate = new Plate(50);
        Cat[] arrayCats = new Cat[] {
                new Cat("Barsik", 10),
                new Cat("Murzik", 13),
                new Cat("Basya", 33),
                new Cat("Vaska", 14),
                new Cat("Stepan", 11),
                new Cat("Grisha", 13),

        };
        plate.info();
        for (Cat cat: arrayCats) {
            cat.printSatiety();
        }

        System.out.println("__________________________________________");
        for (Cat cat: arrayCats) {
            cat.eat(plate);
            plate.info();
        }

        System.out.println("__________________________________________");
        for (Cat cat: arrayCats) {
            cat.printSatiety();
        }

        plate.addFood(100);

        System.out.println("__________________________________________");
        for (Cat cat: arrayCats) {
            cat.eat(plate);
            plate.info();
        }

        System.out.println("__________________________________________");
        plate.info();
        for (Cat cat: arrayCats) {
            cat.printSatiety();
        }
    }
}

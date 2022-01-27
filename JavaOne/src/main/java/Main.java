public class Main {
    public static void main(String[] args) {
        Animal Stich = new Animal("Стич");
        Animal bobik = new Dog("Бобик");
        Animal Tuz = new Dog("Туз");
        Animal Vasek = new Cat("Васёк");
        Animal Basya = new Cat("Бася");
        Animal Alik = new Cat("Алик");
        Animal Gus = new Animal("Гус");

        bobik.run(500);
        Tuz.swim(400);
        Vasek.swim(100);
        Basya.run(900);
        Alik.run(100);
        Stich.run(5000);
        Gus.run(765);

        System.out.print("кошек " + new Dog().getCount());
        System.out.print(" собак " + new Cat().getCount());
        System.out.print(" остальных " + (new Animal().getCount()));
    }
}

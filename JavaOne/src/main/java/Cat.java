public class Cat {
    private String name;
    private int appetite;
    private boolean satiety = false;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }

    public void printSatiety() {
        if (satiety) {
            System.out.println(name + " isn't hungry");
        } else {
            System.out.println(name + " is hungry");
        }
    }

    public void eat(Plate p) {
        if (!satiety && p.decreaseFood(appetite)) {
            satiety = true;
        }
    }
}
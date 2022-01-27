public class Animal {
    private String name;
    static int count = 0;

    public Animal(String name) {
        this.name = name;
        count++;
    }

    public Animal() {}

    public int getCount() {
        return count;
    }

    public void run(int lengthDistance) {
        System.out.println(name + " пробежал " + lengthDistance + " м");
    }

    public void swim(int lengthDistance) {
        System.out.println(name + " не умеет плавать");
    }
}

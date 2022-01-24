public class Animal {
    String name;
    static int count = 0;

    public Animal(String name) {
        this.name = name;
        count++;
    }

    public int getCount() {
        return count;
    }

    public Animal() {}

    public void run(int lengthDistance) {
        System.out.println(name + " пробежал " + lengthDistance + " м");
    }

    public void swim(int lengthDistance) {
        System.out.println(name + " не умеет плавать");
    }
}

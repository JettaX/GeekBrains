public class Cat extends Animal {
    private String name;
    static int count = 0;

    public Cat(String name) {
        this.name = name;
        count++;
    }

    public Cat() {}

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public void run(int lengthDistance) {
        if (lengthDistance > 200) {
            lengthDistance = 200;
        }
        System.out.println(name + " пробежал " + lengthDistance + " м");
    }

    @Override
    public void swim(int lengthDistance) {
        System.out.println(name + " не умеет плавать");
    }
}

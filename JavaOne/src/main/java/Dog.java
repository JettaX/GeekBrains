public class Dog extends Animal {
    private String name;
    static int count = 0;

    public Dog(String name) {
        this.name = name;
        count++;
    }

    public Dog() {}

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public void run(int lengthDistance) {
        if (lengthDistance > 500) {
            lengthDistance = 500;
        }
        System.out.println(name + " пробежал " + lengthDistance + " м");
    }

    @Override
    public void swim(int lengthDistance) {
        if (lengthDistance > 10) {
            lengthDistance = 10;
        }
        System.out.println(name + " проплыл " + lengthDistance + " м");
    }
}

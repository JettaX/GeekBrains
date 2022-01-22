public class Employee {
    String name;
    String surname;
    String patronymic;
    String position;
    String email;
    String phoneNumber;
    int salary;
    int age;

    public Employee(String name, String surname, String patronymic, String position, String email, String phoneNumber, int salary, int age) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.age = age;
    }

    public Employee(String name, String surname, String position, String email, String phoneNumber, int salary, int age) {
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.age = age;
        this.patronymic = "";
    }

    @Override
    public String toString() {
        return  "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                (!patronymic.equals("") ? ", patronymic='" + patronymic + '\'' : "") +
                ", position='" + position + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", salary=" + salary +
                ", age=" + age;
    }
}

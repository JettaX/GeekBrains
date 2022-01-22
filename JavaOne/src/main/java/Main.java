public class Main {
    public static void main(String[] args) {
        Employee[] employees = new Employee[]{
                new Employee("Hema", "Somani", "HR", "HemaSomani-1991@gmail.com", "329145823", 70000, 31),
                new Employee("Charlie", "Vandervort", "Architect", "CharlieVandervort-helpMe@gmail.com", "767267884234",
                        240000, 47),
                new Employee("Shaun", "Allie", "Software engineer", "ShaunAllie@yahoo.com", "986012354",
                        170000, 59),
                new Employee("Nikolay", "Zablud", "Sergeevich", "Software engineer", "Nikolay2000@mail.ru",
                        "7827253672", 15000, 30),
                new Employee("Dina", "Misnikova", "Nikolaevna", "Team lead", "DinaPrinceskaLove@yandex.ru",
                        "75938463", 55000, 46),
        };

        for (Employee employee : employees) {
            if (employee.age > 40) {
                System.out.println(employee);
            }
        }
    }
}

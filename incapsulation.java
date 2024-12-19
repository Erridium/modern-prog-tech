public class Person {
    // переменные объявлены private для ограничения прямого доступа
    private String name;
    private int age;
    
    // Конструктор
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Геттеры и сеттеры для доступа к переменным
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 0 && age <= 150) { // Проверка корректности возраста
            this.age = age;
        } else {
            System.out.println("Некорректный возраст");
        }
    }
}

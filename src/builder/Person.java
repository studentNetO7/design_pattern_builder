package builder;

import java.util.Objects;
import java.util.OptionalInt;

public class Person {
    protected final String name;
    protected final String surname;
    private Integer age;// используем Integer для удобства проверки на факт того, что возраст не был установлен
    private String address;

    public Person(String name, String surname) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым или null");
        }
        if (surname == null || surname.isEmpty()) {
            throw new IllegalArgumentException("Фамилия не может быть пустым или null");
        }
        this.name = name;
        this.surname = surname;
    }

    public Person(String name, String surname, int age) {
        this(name, surname);
        setAge(age);// выносим проверку возраста в отдельный метод
    }

    private void setAge(int age) {
        if (age < 0) { // оповещаем о неправильном указании возраста
            throw new IllegalArgumentException("Возраст не может быть отрицательным");
        }
        if (this.age == null) { // Устанавливаем только если возраст неизвестен
            this.age = age;
        }
    }

    public boolean hasAge() {
        return this.age != null;
    }

    public boolean hasAddress() {
        return address != null && !address.trim().isEmpty();// удаляем пробелы перед проверкой на содержимое сроки
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public OptionalInt getAge() {
        return age != null ? OptionalInt.of(age) : OptionalInt.empty();
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String city) {
        this.address = city;
    }

    public void happyBirthday() {
        if (this.age != 0) {
            this.age++;
        }
    }

    public PersonBuilder newChildBuilder() {
        PersonBuilder childBuilder = new PersonBuilder();// создаю экземпляр
        childBuilder.setSurname(this.surname); // даю ребенку фамилию родителя
        childBuilder.setAge(0); // возраст всегда 0 при прождении
        childBuilder.setAddress(this.address);// указываю для ребенка адресс родителя
        return childBuilder; // возвращает билдер для ребенка
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + (age != null ? age : "неизвестен") +
                ", address='" + (address != null ? address : "неизвестен") + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(surname, person.surname) && Objects.equals(age, person.age) && Objects.equals(address, person.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, age, address);
    }
}


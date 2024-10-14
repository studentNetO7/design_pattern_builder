package builder;

public class PersonBuilder {
    private String name;
    private String surname;
    private Integer age; // Используем Integer для возможности отсутствия возраста
    private String address;

    public PersonBuilder setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");// делаю вид сообщения другим
        }
        this.name = name;
        return this; // Возвращаем текущий объект для цепочки вызовов
    }

    public PersonBuilder setSurname(String surname) {
        if (surname == null || surname.trim().isEmpty()) {
            throw new IllegalArgumentException("Surname cannot be null or empty");// делаю вид сообщения другим
        }
        this.surname = surname;
        return this; // Возвращаем текущий объект для цепочки вызовов
    }

    public PersonBuilder setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age can not be negative");
        }
        this.age = age;
        return this;
    }

    public PersonBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public Person build() {
        if (name == null) {
            throw new IllegalArgumentException("Name must be provided");
        }
        if (surname == null) {
            throw new IllegalArgumentException("Surname must be provided");
        }
        Person person = new Person(name, surname, age);// создаю обьект по конструктору
        if (address != null) {
            person.setAddress(address);// добавляю адресс если он есть
        }
        return person;
    }
}



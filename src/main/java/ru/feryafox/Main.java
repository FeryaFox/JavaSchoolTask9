package ru.feryafox;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

class Person {
    private final String name;
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class Main {
    public static void main(String[] args) {


        List<Person> someCollection = Arrays.asList(
                new Person("Alice", 25),
                new Person("Bob", 18),
                new Person("Charlie", 30)
        );

        Map<String, Person> result = Streams.of(someCollection)
                .filter(p -> p.getAge() > 20)
                .transform(p -> new Person(p.getName(), p.getAge() + 30))
                .toMap(Person::getName, Function.identity());

        result.forEach((key, value) -> System.out.println(key + " -> " + value));
    }
}
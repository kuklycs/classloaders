package model;

import factory.Printable;

public class Person implements Printable {
    private final String name;
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String getAsString() {
        return "PersonTwo shows only name: " + name;
    }
}


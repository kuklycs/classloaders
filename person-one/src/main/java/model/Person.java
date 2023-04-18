package model;

import print.PrintVersion;
import print.Printable;
import print.PrintableFactory;


public class Person implements Printable {

    static {
        PrintableFactory.addPrintableImpl(PrintVersion.V1, new Person());
    }

    private final String name;
    private final int age;

    public Person() {
        this.name = "Martin - default";
        this.age = 99;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public Person createPerson(String name, int age) {
        return new Person(name, age);
    }

    @Override
    public String getAsString() {
        return "PersonOne has name: " + name + " and age: " + age;
    }


}

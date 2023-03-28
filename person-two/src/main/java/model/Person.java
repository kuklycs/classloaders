package model;


import print.PrintVersion;
import print.Printable;
import print.PrintableFactory;

public class Person implements Printable {

    //FIXME second mistake, static initializer
    static {
        PrintableFactory.addPrintableImpl(PrintVersion.V2, new Person());
    }

    private final String name;
    private final int age;

    public Person() {
        this.name = "Honza - default";
        this.age = 10;
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
        return "PersonTwo shows only name: " + name;
    }
}


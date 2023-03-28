package print;

public interface Printable {

    Printable createPerson(String name, int age);

    String getAsString();
}

package factory;

public interface PrintableFactory {
    Printable create(String name, int age) throws CreationException;
}

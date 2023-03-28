package print;

import java.util.HashMap;
import java.util.Map;


public class PrintableFactory {

    private final Map<PrintVersion, Printable> printableHashMap = new HashMap<>();
    private static volatile PrintableFactory instance;

    private PrintableFactory() {
        System.out.println("Instantiated factory singleton");
    }

    public void addPrintableImpl(PrintVersion version, Printable printable) {
        System.out.println("Adding implementation dynamically with version: " + version);
        printableHashMap.put(version, printable);
    }

    public Printable getPrintableImpl(PrintVersion version, String name, int age) {
        return printableHashMap.get(version).createPerson(name, age);
    }

    public static PrintableFactory getInstance() {
        if (instance == null) {
            synchronized(PrintableFactory.class) {
                if (instance == null) {
                    instance = new PrintableFactory();
                }
            }
        }
        return instance;
    }
}

package print;

import java.util.HashMap;
import java.util.Map;


//TODO No builder, just static?
public final class PrintableFactory {
    private static final Map<PrintVersion, Printable> printableHashMap = new HashMap<>();

    public static void addPrintableImpl(PrintVersion version, Printable printable) {
        System.out.println("Adding implementation dynamically with version: " + version);
        printableHashMap.put(version, printable);
    }

    public static Printable getPrintableImpl(PrintVersion version, String name, int age) {
        return printableHashMap.get(version).createPerson(name, age);
    }
}

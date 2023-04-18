package classloaders;

import factory.*;

public class Demo {
    public static void main(String[] args) throws CreationException {
        LazyResourceLoader.test();
        PrintableProvider printableProvider = new PrintableProvider();

        PrintableFactory factoryV1 = printableProvider.getPrintableFactory(PrintVersion.V1);
        Printable first = factoryV1.create("Martin", 28);
        System.out.println("Printable getAsString(): " + first.getAsString());

        PrintableFactory factoryV2 = printableProvider.getPrintableFactory(PrintVersion.V2);
        Printable second = factoryV2.create("Honza", 40);
        System.out.println("Printable getAsString(): " + second.getAsString());
    }
}

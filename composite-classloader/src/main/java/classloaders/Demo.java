package classloaders;

import print.PrintVersion;
import print.Printable;
import print.PrintableFactory;

import java.net.URL;

public class Demo {
    public static void main(String[] args) throws ClassNotFoundException {
        URL personOneUrl = Demo.class.getClassLoader().getResource("person-one-1.0-SNAPSHOT.jar");
        URL personTwoUrl = Demo.class.getClassLoader().getResource("person-two-1.0-SNAPSHOT.jar");

        //FIXME first mistake, factory is singleton
        PrintableFactory factory = PrintableFactory.getInstance();

        CompositeClassLoader compositeClassLoader = new CompositeClassLoader(personOneUrl, personTwoUrl);


        //FIXME second mistake, loading all classes for all versions to invoke Class.forName and initialize static initializers
        compositeClassLoader.loadClass("model.Person");

        compositeClassLoader.setVersion(PrintVersion.V2);

        compositeClassLoader.loadClass("model.Person");


        // Now this is a result which I wanted to achieve but not nice yet
        Printable first = factory.getPrintableImpl(PrintVersion.V1, "Martin", 27);
        System.out.println(first.getAsString());

        Printable second = factory.getPrintableImpl(PrintVersion.V2, "Honza", 40);
        System.out.println(second.getAsString());
    }
}

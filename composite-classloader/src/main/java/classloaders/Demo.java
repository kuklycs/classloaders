package classloaders;

import print.PrintVersion;
import print.Printable;
import print.PrintableFactory;

public class Demo {
    public static void main(String[] args) throws ClassNotFoundException {

        //Test that nothing is loaded
        LazyResourceLoader.test();
        System.out.println("Accessed method, but no resources are loaded");

        CompositeClassLoader compositeClassLoader = new CompositeClassLoader();

        //FIXME first mistake, loading all classes for all versions to invoke Class.forName and initialize static initializers
        compositeClassLoader.loadClass("model.Person");

        // Now this is a result which I wanted to achieve but not nice yet
        Printable first = PrintableFactory.getPrintableImpl(PrintVersion.V1, "Martin", 27);
        System.out.println(first.getAsString());


        compositeClassLoader.setVersion(PrintVersion.V2);

        //have to load class for PrintVersion V2 otherwise will fail
        compositeClassLoader.loadClass("model.Person");

        Printable second = PrintableFactory.getPrintableImpl(PrintVersion.V2, "Honza", 40);
        System.out.println(second.getAsString());
    }
}

package classloaders;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

public class Demo {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        URL personOneUrl = Demo.class.getClassLoader().getResource("PersonOne-1.0-SNAPSHOT.jar");
        URL personTwoUrl = Demo.class.getClassLoader().getResource("PersonTwo-1.0-SNAPSHOT.jar");

        CompositeClassLoader compositeClassLoader = new CompositeClassLoader(personOneUrl, personTwoUrl);

        // use first implementation
        Class<?> personOne = compositeClassLoader.loadClass("model.Person");
        Constructor<?> constructorOne = personOne.getConstructor(String.class, Integer.TYPE);
        Printable instanceOne = (Printable) constructorOne.newInstance("Honza", 25);
        System.out.println(instanceOne.getAsString());

        // use second implementation
        compositeClassLoader.setUseSecond(true);

        Class<?> personTwo = compositeClassLoader.loadClass("model.Person");
        Constructor<?> constructorTwo = personTwo.getConstructor(String.class, Integer.TYPE);
        Printable instanceTwo = (Printable) constructorTwo.newInstance("Honza", 25);
        System.out.println(instanceTwo.getAsString());
    }
}

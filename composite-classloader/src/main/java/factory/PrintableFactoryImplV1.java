package factory;


import classloaders.LazyResourceLoader;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;

public class PrintableFactoryImplV1 implements PrintableFactory {
    @Override
    public Printable create(String name, int age) throws CreationException {
        System.out.println("Call create() on factoryV1");
        URL[] resource = LazyResourceLoader.getFirstResourceOnDemand();

        try (URLClassLoader classLoader = new URLClassLoader(resource)) {
            Class<?> clz = classLoader.loadClass("model.Person");
            Constructor<?> constructor = clz.getConstructor(String.class, Integer.TYPE);
            return (Printable) constructor.newInstance(name, age);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException | IOException e) {
            throw new CreationException("Could not create Printable instance", e);
        }
    }
}

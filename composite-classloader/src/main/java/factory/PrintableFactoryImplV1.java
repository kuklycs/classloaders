package factory;


import classloaders.LazyResourceLoader;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;

public class PrintableFactoryImplV1 implements PrintableFactory {
    private Constructor<?> loadedConstructor;

    @Override
    public Printable create(String name, int age) throws CreationException {
        System.out.println("Call create() on factoryV1");
        if (loadedConstructor == null) {
            URL[] resource = LazyResourceLoader.getFirstResourceOnDemand();
            loadedConstructor = getConstructor(resource);
        }

        try {
            return (Printable) loadedConstructor.newInstance(name, age);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new CreationException("Could not create Printable instance", e);
        }
    }

    private Constructor<?> getConstructor(URL[] resource) throws CreationException {
        try (URLClassLoader classLoader = new URLClassLoader(resource)) {
            Class<?> clz = classLoader.loadClass("model.Person");
            return clz.getConstructor(String.class, Integer.TYPE);
        } catch (ClassNotFoundException | NoSuchMethodException | IOException e) {
            throw new CreationException("Could not create Printable instance", e);
        }
    }
}

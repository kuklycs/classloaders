package classloaders;

import java.net.URL;
import java.net.URLClassLoader;

public class CompositeClassLoader extends ClassLoader {
    private final URLClassLoader firstClassLoader;
    private final URLClassLoader secondClassLoader;
    private boolean useSecond = false;

    public CompositeClassLoader(URL firstUrl, URL secondUrl) {
        super();
        this.firstClassLoader = new URLClassLoader(new URL[] {firstUrl});
        this.secondClassLoader = new URLClassLoader(new URL[] {secondUrl});
    }

    public void setUseSecond(boolean useSecond) {
        this.useSecond = useSecond;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (!useSecond) {
            try {
                return firstClassLoader.loadClass(name);
            } catch (ClassNotFoundException ex) {
                System.out.println("First class loader failed to load class: " + name);
            }
        } else {
            try {
                return secondClassLoader.loadClass(name);
            } catch (ClassNotFoundException ex) {
                System.out.println("Second class loader failed to load class: " + name);
            }
        }
        return super.loadClass(name);
    }
}

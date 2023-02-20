package classloaders;

import java.net.URL;
import java.net.URLClassLoader;

public class CompositeClassLoader extends ClassLoader {
    private final URLClassLoader firstClassLoader;
    private final URLClassLoader secondClassLoader;
    private boolean useSecond = false;

    public CompositeClassLoader(URL firstImplUrl, URL secondImplUrl) {
        super();
        this.firstClassLoader = new URLClassLoader(new URL[] {firstImplUrl}, this);
        this.secondClassLoader = new URLClassLoader(new URL[] {secondImplUrl}, this);
    }

    public void setUseSecond(boolean useSecond) {
        this.useSecond = useSecond;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        try {
            return super.loadClass(name);
        } catch (ClassNotFoundException ex) {
            // expected, go through
        }
        return !useSecond ? firstClassLoader.loadClass(name) : secondClassLoader.loadClass(name);
    }
}

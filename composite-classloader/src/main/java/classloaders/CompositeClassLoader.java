package classloaders;

import print.PrintVersion;
import java.net.URLClassLoader;

public class CompositeClassLoader extends ClassLoader {
    private PrintVersion version = PrintVersion.V1;
    private URLClassLoader firstClassLoader;
    private URLClassLoader secondClassLoader;

    public CompositeClassLoader() {
        super();
    }

    public void setVersion(PrintVersion version) {
        this.version = version;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        System.out.println("Loading class for name: " + name);
        switch (version) {
            case V1 -> {
                if (firstClassLoader == null) {
                    firstClassLoader = new URLClassLoader(LazyResourceLoader.getFirstResourceOnDemand());
                }
                return Class.forName(name, true, firstClassLoader);
            }
            case V2 -> {
                if (secondClassLoader == null) {
                    secondClassLoader = new URLClassLoader(LazyResourceLoader.getSecondResourceOnDemand());
                }
                return Class.forName(name, true, secondClassLoader);
            }
        }
        return super.loadClass(name);
    }
}

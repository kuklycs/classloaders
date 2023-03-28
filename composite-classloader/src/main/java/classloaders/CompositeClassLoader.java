package classloaders;

import print.PrintVersion;
import print.PrintableFactory;

import java.net.URL;
import java.net.URLClassLoader;

public class CompositeClassLoader extends ClassLoader {
    private final URLClassLoader firstClassLoader;
    private final URLClassLoader secondClassLoader;

    private PrintVersion version = PrintVersion.V1;

    public CompositeClassLoader(URL firstImplUrl, URL secondImplUrl) {
        super();
        this.firstClassLoader = new URLClassLoader(new URL[] {firstImplUrl});
        this.secondClassLoader = new URLClassLoader(new URL[] {secondImplUrl});
    }

    public void setVersion(PrintVersion version) {
        this.version = version;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        switch (version) {
            case V1 -> {
                return Class.forName(name, true, firstClassLoader);
            }
            case V2 -> {
                return Class.forName(name, true, secondClassLoader);
            }
        }
        throw new ClassNotFoundException("Did not find a class, should not happen");
    }
}

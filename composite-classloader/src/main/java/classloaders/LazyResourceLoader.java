package classloaders;

import java.net.URL;

public class LazyResourceLoader {
    private static class FirstResource {
        static {
            System.out.println("First resource accessed, lazy loaded URL");
        }
        static final URL resource = LazyResourceLoader.class.getClassLoader().getResource("person-one-1.0-SNAPSHOT.jar");
    }

    private static class SecondResource {
        static {
            System.out.println("Second resource accessed, lazy loaded URL");
        }
        static final URL resource = LazyResourceLoader.class.getClassLoader().getResource("person-two-1.0-SNAPSHOT.jar");
    }

    public static void test() {
        System.out.println("Inside LazyResourceLoader test()");
    }

    public static URL[] getFirstResourceOnDemand() {
        return new URL[] {FirstResource.resource};
    }

    public static URL[] getSecondResourceOnDemand() {
        return new URL[] {SecondResource.resource};
    }
}

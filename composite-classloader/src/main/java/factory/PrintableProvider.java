package factory;

import java.util.Map;
public class PrintableProvider {
    private final Map<PrintVersion, factory.PrintableFactory> factoryMap = Map.of(
            PrintVersion.V1, new PrintableFactoryImplV1(),
            PrintVersion.V2, new PrintableFactoryImplV2()
    );
    public PrintableFactory getPrintableFactory(PrintVersion version) {
        return factoryMap.get(version);
    }

}

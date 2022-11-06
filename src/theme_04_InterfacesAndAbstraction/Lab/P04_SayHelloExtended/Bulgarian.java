package theme_04_InterfacesAndAbstraction.Lab.P04_SayHelloExtended;

public class Bulgarian extends BasePerson {

    protected Bulgarian(String name) {
        super(name);
    }

    @Override
    public String sayHello() {
        return "Здравей";
    }
}

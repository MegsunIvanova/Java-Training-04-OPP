package theme_04_InterfacesAndAbstraction.Lab.P04_SayHelloExtended;

public class European extends BasePerson {

    private String name;

    protected European(String name) {
        super(name);
    }

    @Override
    public String sayHello() {
        return "Hello";
    }
}

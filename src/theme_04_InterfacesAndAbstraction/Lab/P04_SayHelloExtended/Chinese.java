package theme_04_InterfacesAndAbstraction.Lab.P04_SayHelloExtended;

public class Chinese extends BasePerson {
    private String name;

    protected Chinese(String name) {
        super(name);
    }

    @Override
    public String sayHello() {
        return "Djydjybydjy";
    }
}

package theme_04_InterfacesAndAbstraction.Lab.P04_SayHelloExtended;

public abstract class BasePerson implements Person {

    private String name;

    protected BasePerson(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

}

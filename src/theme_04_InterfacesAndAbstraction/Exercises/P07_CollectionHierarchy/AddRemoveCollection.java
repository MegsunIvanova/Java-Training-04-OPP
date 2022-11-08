package theme_04_InterfacesAndAbstraction.Exercises.P07_CollectionHierarchy;

public class AddRemoveCollection extends Collection implements AddRemovable {

    @Override
    public int add(String string) {
        return super.add(0, string);
    }

    @Override
    public String remove() {
        return super.remove();
    }
}

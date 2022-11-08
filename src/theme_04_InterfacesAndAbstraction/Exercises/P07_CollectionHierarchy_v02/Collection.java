package theme_04_InterfacesAndAbstraction.Exercises.P07_CollectionHierarchy_v02;

import java.util.ArrayList;
import java.util.List;

public abstract class Collection {

    private int maxSize;

    protected List<String> items;

    public Collection() {

        items = new ArrayList<>();
        this.maxSize = 100;
    }

}

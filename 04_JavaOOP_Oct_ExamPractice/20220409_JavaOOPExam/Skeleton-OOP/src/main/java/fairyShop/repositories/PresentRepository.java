package fairyShop.repositories;

import fairyShop.models.Helper;
import fairyShop.models.Present;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class PresentRepository<T> implements Repository<Present> {

    private Collection<Present> presents;

    public PresentRepository() {
        presents = new ArrayList<>();
    }

    @Override
    public void add(Present present) {
        presents.add(present);
    }

    @Override
    public boolean remove(Present present) {
        return presents.remove(present);
    }

    @Override
    public Present findByName(String name) {
        return presents.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Collection getModels() {
        return Collections.unmodifiableCollection(presents);
    }


}

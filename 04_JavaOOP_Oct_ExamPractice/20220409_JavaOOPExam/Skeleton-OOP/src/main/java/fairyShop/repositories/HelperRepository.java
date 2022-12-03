package fairyShop.repositories;

import fairyShop.models.Helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class HelperRepository <T> implements Repository <Helper> {
    private Collection<Helper> helpers;

    public HelperRepository() {
        helpers = new ArrayList<>();
    }

    @Override
    public void add(Helper helper) {
        helpers.add(helper);
    }

    @Override
    public boolean remove(Helper helper) {
        return helpers.remove(helper);
    }

    @Override
    public Helper findByName(String name) {
       return this.helpers.stream()
               .filter(helper -> helper.getName().equals(name))
               .findFirst()
               .orElse(null);
    }

    @Override
    public Collection getModels() {
        return Collections.unmodifiableCollection(helpers);
    }
}

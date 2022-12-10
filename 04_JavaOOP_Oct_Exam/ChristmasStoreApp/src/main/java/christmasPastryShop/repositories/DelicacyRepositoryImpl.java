package christmasPastryShop.repositories;

import christmasPastryShop.entities.delicacies.interfaces.Delicacy;
import christmasPastryShop.repositories.interfaces.DelicacyRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class DelicacyRepositoryImpl implements DelicacyRepository<Delicacy> {
    private Map<String, Delicacy> models;

    public DelicacyRepositoryImpl() {
        this.models = new LinkedHashMap<>();
    }

    @Override
    public Delicacy getByName(String name) {
        return this.models.get(name);
    }

    @Override
    public Collection<Delicacy> getAll() {
        return Collections.unmodifiableCollection(models.values());
    }

    @Override
    public void add(Delicacy delicacy) {
        this.models.put(delicacy.getName(), delicacy);
    }
}

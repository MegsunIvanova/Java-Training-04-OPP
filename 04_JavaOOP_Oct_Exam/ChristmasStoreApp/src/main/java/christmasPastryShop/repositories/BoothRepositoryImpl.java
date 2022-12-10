package christmasPastryShop.repositories;

import christmasPastryShop.entities.booths.interfaces.Booth;
import christmasPastryShop.entities.cocktails.interfaces.Cocktail;
import christmasPastryShop.repositories.interfaces.BoothRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class BoothRepositoryImpl implements BoothRepository<Booth> {
    private Map<Integer, Booth> models;

    public BoothRepositoryImpl() {
        this.models = new LinkedHashMap<>();
    }

    @Override
    public Booth getByNumber(int number) {
        return this.models.get(number);
    }

    @Override
    public Collection<Booth> getAll() {
        return Collections.unmodifiableCollection(models.values());
    }

    @Override
    public void add(Booth booth) {
        this.models.put(booth.getBoothNumber(), booth);
    }
}

package christmasPastryShop.repositories;

import christmasPastryShop.entities.cocktails.interfaces.Cocktail;
import christmasPastryShop.entities.delicacies.interfaces.Delicacy;
import christmasPastryShop.repositories.interfaces.CocktailRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class CocktailRepositoryImpl implements CocktailRepository<Cocktail> {
    private Map<String, Cocktail> models;

    public CocktailRepositoryImpl() {
        this.models = new LinkedHashMap<>();
    }

    @Override
    public Cocktail getByName(String name) {
        return this.models.get(name);
    }

    @Override
    public Collection<Cocktail> getAll() {
        return Collections.unmodifiableCollection(models.values());
    }

    @Override
    public void add(Cocktail cocktail) {
        this.models.put(cocktail.getName(), cocktail);
    }
}

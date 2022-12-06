package glacialExpedition.models.states;

import glacialExpedition.common.DataValidator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static glacialExpedition.common.ExceptionMessages.*;

public class StateImpl implements State {
    private String name;
    private List<String> exhibits;

    public StateImpl(String name) {
        this.name = name;
        exhibits = new ArrayList<>();
    }

    private void setName(String name) {
        DataValidator.ensureNotNullOrEmptyString(name, STATE_NAME_NULL_OR_EMPTY);
        this.name = name;
    }

    @Override
    public Collection<String> getExhibits() {
        return this.exhibits;
    }

    @Override
    public String getName() {
        return this.name;
    }
}

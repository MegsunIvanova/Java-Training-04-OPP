package glacialExpedition.models.mission;

import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.states.State;

import java.util.Collection;
import java.util.Optional;

public class MissionImpl implements Mission {

    @Override
    public void explore(State state, Collection<Explorer> explorers) {
        for (Explorer explorer : explorers) {

            Collection<String> exhibits = state.getExhibits();

            while (explorer.canSearch() && exhibits.iterator().hasNext()) {
                explorer.search();
                String currentExhibit =exhibits.iterator().next();
                explorer.getSuitcase().getExhibits().add(currentExhibit);
                exhibits.remove(currentExhibit);
            }

            if (exhibits.isEmpty()) {
                break;
            }
        }
    }

}

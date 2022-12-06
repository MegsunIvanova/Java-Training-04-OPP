package glacialExpedition.core;

import glacialExpedition.models.explorers.AnimalExplorer;
import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.explorers.GlacierExplorer;
import glacialExpedition.models.explorers.NaturalExplorer;
import glacialExpedition.models.mission.Mission;
import glacialExpedition.models.mission.MissionImpl;
import glacialExpedition.models.states.State;
import glacialExpedition.models.states.StateImpl;
import glacialExpedition.repositories.ExplorerRepository;
import glacialExpedition.repositories.Repository;
import glacialExpedition.repositories.StateRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static glacialExpedition.common.ConstantMessages.*;
import static glacialExpedition.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private Repository<Explorer> explorerRepository;
    private Repository<State> stateRepository;
    private int exploredStates;

    public ControllerImpl() {
        explorerRepository = new ExplorerRepository();
        stateRepository = new StateRepository();
    }

    @Override
    public String addExplorer(String type, String explorerName) {
        Explorer explorer;

        switch (type) {
            case "AnimalExplorer":
                explorer = new AnimalExplorer(explorerName);
                break;
            case "GlacierExplorer":
                explorer = new GlacierExplorer(explorerName);
                break;
            case "NaturalExplorer":
                explorer = new NaturalExplorer(explorerName);
                break;
            default:
                throw new IllegalArgumentException(EXPLORER_INVALID_TYPE);
        }

        this.explorerRepository.add(explorer);

        return String.format(EXPLORER_ADDED, type, explorerName);
    }

    @Override
    public String addState(String stateName, String... exhibits) {
        State state = new StateImpl(stateName);

        Arrays.stream(exhibits).forEach(e -> state.getExhibits().add(e));
//        state.getExhibits().addAll(List.of(exhibits));

        this.stateRepository.add(state);

        return String.format(STATE_ADDED, stateName);
    }

    @Override
    public String retireExplorer(String explorerName) {
        Explorer explorerForRemove = explorerRepository.byName(explorerName);

        if (explorerForRemove == null) {
            throw new IllegalArgumentException(String.format(EXPLORER_DOES_NOT_EXIST, explorerName));
        }

        explorerRepository.remove(explorerForRemove);

        return String.format(EXPLORER_RETIRED, explorerName);
    }

    @Override
    public String exploreState(String stateName) {
        State state = stateRepository.byName(stateName);

        List<Explorer> suitableExplorers = this.explorerRepository
                .getCollection()
                .stream()
                .filter(explorer -> explorer.getEnergy() > 50)
                .collect(Collectors.toList());

        if (suitableExplorers.isEmpty()) {
            throw new IllegalArgumentException(STATE_EXPLORERS_DOES_NOT_EXISTS);
        }

        Mission mission = new MissionImpl();

        mission.explore(state, suitableExplorers);

        this.exploredStates++;

        //there is no definition for retired explorer -> whose energy is zero or less or equal to 50
        List<Explorer> retiredExplorers = suitableExplorers.stream()
                .filter(explorer -> !explorer.canSearch())
                .collect(Collectors.toList());

        //it is not defined: maybe retired explorers should be removed from repository
//        retiredExplorers.stream().forEach(explorerRepository::remove);

        return String.format(STATE_EXPLORER, stateName, retiredExplorers.size());
    }

    @Override
    public String finalResult() {
        StringBuilder result = new StringBuilder();

        result.append(String.format(FINAL_STATE_EXPLORED, exploredStates)).append(System.lineSeparator());
        result.append(FINAL_EXPLORER_INFO).append(System.lineSeparator());

        result.append(explorerRepository.getCollection().stream()
                .map(this::getExplorerFinalResult)
                .collect(Collectors.joining(System.lineSeparator())));

        return result.toString();
    }

    private String getExplorerFinalResult(Explorer explorer) {
        StringBuilder out = new StringBuilder();
        out.append(String.format(FINAL_EXPLORER_NAME, explorer.getName())).append(System.lineSeparator());
        out.append(String.format(FINAL_EXPLORER_ENERGY, explorer.getEnergy())).append(System.lineSeparator());

        String exhibits;
        if (explorer.getSuitcase().getExhibits().isEmpty()) {
            exhibits = "None";
        } else {
            exhibits = String.join(", ", explorer.getSuitcase().getExhibits());
        }

        out.append(String.format(FINAL_EXPLORER_SUITCASE_EXHIBITS, exhibits));

        return out.toString();
    }


}

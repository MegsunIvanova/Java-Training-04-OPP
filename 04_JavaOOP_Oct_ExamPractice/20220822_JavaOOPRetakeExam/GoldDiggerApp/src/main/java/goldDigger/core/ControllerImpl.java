package goldDigger.core;

import goldDigger.common.ConstantMessages;
import goldDigger.common.ExceptionMessages;
import goldDigger.models.discoverer.*;
import goldDigger.models.operation.*;
import goldDigger.models.spot.*;
import goldDigger.repositories.*;

import java.util.List;
import java.util.stream.Collectors;

import static goldDigger.common.ConstantMessages.*;
import static goldDigger.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private Repository<Discoverer> discoverersRepository;
    private Repository<Spot> spotsRepository;
    private int inspectedSpotCount;

    public ControllerImpl() {
        discoverersRepository = new DiscovererRepository();
        spotsRepository = new SpotRepository();
    }

    @Override
    public String addDiscoverer(String kind, String discovererName) {
        Discoverer discoverer;
        switch (kind) {
            case "Anthropologist":
                discoverer = new Anthropologist(discovererName);
                break;
            case "Archaeologist":
                discoverer = new Archaeologist(discovererName);
                break;
            case "Geologist":
                discoverer = new Geologist(discovererName);
                break;
            default:
                throw new IllegalArgumentException(DISCOVERER_INVALID_KIND);
        }

        discoverersRepository.add(discoverer);

        return String.format(DISCOVERER_ADDED, kind, discovererName);
    }

    @Override
    public String addSpot(String spotName, String... exhibits) {
        Spot spot = new SpotImpl(spotName);

        spot.getExhibits().addAll(List.of(exhibits));

        this.spotsRepository.add(spot);

        return String.format(SPOT_ADDED, spotName);
    }

    @Override
    public String excludeDiscoverer(String discovererName) {
        Discoverer discoverer = discoverersRepository.byName(discovererName);

        if (discoverer == null) {
            String errorMessage = String.format(DISCOVERER_DOES_NOT_EXIST, discovererName);
            throw new IllegalArgumentException(errorMessage);
        }

        discoverersRepository.remove(discoverer);
        return String.format(ConstantMessages.DISCOVERER_EXCLUDE, discovererName);
    }

    @Override
    public String inspectSpot(String spotName) {
        List<Discoverer> suitableDiscoverers = this.discoverersRepository.getCollection()
                .stream()
                .filter(discoverer -> discoverer.getEnergy() > 45)
                .collect(Collectors.toList());

        if (suitableDiscoverers.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.SPOT_DISCOVERERS_DOES_NOT_EXISTS);
        }

        Spot spot = spotsRepository.byName(spotName);

        Operation operation = new OperationImpl();
        operation.startOperation(spot, suitableDiscoverers);

        long excludedDiscoverers = suitableDiscoverers.stream()
                .filter(discoverer -> discoverer.getEnergy() == 0)
                .count();

        this.inspectedSpotCount++;

        return String.format(ConstantMessages.INSPECT_SPOT, spotName, excludedDiscoverers);
    }

    @Override
    public String getStatistics() {
        StringBuilder out = new StringBuilder();
        out.append(String.format(FINAL_SPOT_INSPECT, inspectedSpotCount)).append(System.lineSeparator());
        out.append(FINAL_DISCOVERER_INFO).append(System.lineSeparator());

        for (Discoverer discoverer : discoverersRepository.getCollection()) {
            out.append(String.format(FINAL_DISCOVERER_NAME, discoverer.getName())).append(System.lineSeparator());
            out.append(String.format(FINAL_DISCOVERER_ENERGY, discoverer.getEnergy())).append(System.lineSeparator());

            String exhibits = discoverer.getMuseum().getExhibits().isEmpty() ? "None" :
                    String.join(FINAL_DISCOVERER_MUSEUM_EXHIBITS_DELIMITER, discoverer.getMuseum().getExhibits());

            out.append(String.format(FINAL_DISCOVERER_MUSEUM_EXHIBITS, exhibits)).append(System.lineSeparator());
        }

        return out.toString().trim();
    }
}

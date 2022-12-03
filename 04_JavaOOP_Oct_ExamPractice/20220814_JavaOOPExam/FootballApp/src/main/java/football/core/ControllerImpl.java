package football.core;


import football.common.ConstantMessages;
import football.common.ExceptionMessages;
import football.entities.field.ArtificialTurf;
import football.entities.field.Field;
import football.entities.field.NaturalGrass;
import football.entities.player.Men;
import football.entities.player.Player;
import football.entities.player.Women;
import football.entities.supplement.Liquid;
import football.entities.supplement.Powdered;
import football.entities.supplement.Supplement;
import football.repositories.SupplementRepository;
import football.repositories.SupplementRepositoryImpl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private SupplementRepository supplementRepository;
    private Map<String, Field> fields;

    public ControllerImpl() {
        this.supplementRepository = new SupplementRepositoryImpl();
        this.fields = new LinkedHashMap<>();
    }

    @Override
    public String addField(String fieldType, String fieldName) {

        Field field;

        switch (fieldType) {
            case "ArtificialTurf":
                field = new ArtificialTurf(fieldName);
                break;
            case "NaturalGrass":
                field = new NaturalGrass(fieldName);
                break;
            default:
                throw new NullPointerException(ExceptionMessages.INVALID_FIELD_TYPE);
        }

        this.fields.put(fieldName, field);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FIELD_TYPE, fieldType);
    }

    @Override
    public String deliverySupplement(String type) {

        Supplement supplement;

        switch (type) {
            case "Liquid":
                supplement = new Liquid();
                break;
            case "Powdered":
                supplement = new Powdered();
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_SUPPLEMENT_TYPE);
        }

        this.supplementRepository.add(supplement);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE, type);
    }

    @Override
    public String supplementForField(String fieldName, String supplementType) {

        Supplement supplement = this.supplementRepository.findByType(supplementType);

        if (supplement == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_SUPPLEMENT_FOUND, supplementType));
        }

        supplementRepository.remove(supplement);

        this.fields.get(fieldName).addSupplement(supplement);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SUPPLEMENT_IN_FIELD, supplementType, fieldName);
    }

    @Override
    public String addPlayer(String fieldName, String playerType, String playerName, String nationality, int strength) {

        Player player;

        switch (playerType) {
            case "Men":
                player = new Men(playerName, nationality, strength);
                break;
            case "Women":
                player = new Women(playerName, nationality, strength);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_TYPE);
        }

        Field field = fields.get(fieldName);

        String output;

        if (!isSuitableTerrainForPlayer(playerType, field)) {
            output = ConstantMessages.FIELD_NOT_SUITABLE;
        } else {
            field.addPlayer(player);
            output = String.format(ConstantMessages.SUCCESSFULLY_ADDED_PLAYER_IN_FIELD, playerType, fieldName);
        }

        return output;

    }

    private boolean isSuitableTerrainForPlayer(String playerType, Field field) {
        String fieldType = field.getClass().getSimpleName();

        boolean canPlay = playerType.equals("Women") && fieldType.equals("ArtificialTurf");

        if (!canPlay) {
            canPlay = playerType.equals("Men") && fieldType.equals("NaturalGrass");
        }

        return canPlay;
    }

    @Override
    public String dragPlayer(String fieldName) {
        Field field = fields.get(fieldName);
        int numberOfPlayers = field.getPlayers().size();
        field.drag();

        return String.format(ConstantMessages.PLAYER_DRAG, numberOfPlayers);
    }

    @Override
    public String calculateStrength(String fieldName) {

        Field field = fields.get(fieldName);

        int sumOfPlayersStrength = field.getPlayers().stream()
                .mapToInt(Player::getStrength)
                .sum();

        return String.format(ConstantMessages.STRENGTH_FIELD, fieldName, sumOfPlayersStrength);
    }

    @Override
    public String getStatistics() {
        return fields.values().stream()
                .map(Field::getInfo)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}

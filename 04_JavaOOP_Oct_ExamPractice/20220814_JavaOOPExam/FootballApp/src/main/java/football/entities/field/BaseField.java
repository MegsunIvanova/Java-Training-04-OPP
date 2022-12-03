package football.entities.field;

import football.common.ConstantMessages;
import football.common.ExceptionMessages;
import football.common.Utilities;
import football.entities.player.Player;
import football.entities.supplement.Supplement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public abstract class BaseField implements Field {
    private String name;
    private int capacity;
    private Collection<Supplement> supplements;
    private Collection<Player> players;

    protected BaseField(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        this.supplements = new ArrayList<>();
        this.players = new ArrayList<>();
    }

    private void setName(String name) {
        if (Utilities.isNullOrEmptyString(name)) {
            throw new NullPointerException(ExceptionMessages.FIELD_NAME_NULL_OR_EMPTY);
        }

        this.name = name;
    }

    @Override
    public int sumEnergy() {
        return this.supplements.stream()
                .mapToInt(Supplement::getEnergy)
                .sum();
    }

    @Override
    public void addPlayer(Player player) {
        if (this.players.size() == capacity) {
            throw new IllegalStateException(ExceptionMessages.FIELD_NOT_ENOUGH_CAPACITY);
        }

        this.players.add(player);
    }

    @Override
    public void removePlayer(Player player) {
        this.players.remove(player);
    }

    @Override
    public void addSupplement(Supplement supplement) {
        this.supplements.add(supplement);
    }

    @Override
    public void drag() {
        players.forEach(Player::stimulation);
    }

    @Override
    public String getInfo() {
        StringBuilder out = new StringBuilder();
        String type = this.getClass().getSimpleName();
        out.append(String.format(ConstantMessages.FIELD_INFO_FIRST_LINE, this.name, type)).append(System.lineSeparator());
        StringBuilder players = new StringBuilder(this.players.isEmpty() ? "none" : this.players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(" ")));

        out.append(String.format(ConstantMessages.FIELD_INFO_SECOND_LINE, players)).append(System.lineSeparator());
        out.append(String.format(ConstantMessages.FIELD_INFO_THIRD_LINE, this.supplements.size())).append(System.lineSeparator());
        out.append(String.format(ConstantMessages.FIELD_INFO_FOURTH_LINE, this.sumEnergy()));

        return out.toString();
    }

    @Override
    public Collection<Player> getPlayers() {
        return this.players;
    }

    @Override
    public Collection<Supplement> getSupplements() {
        return this.supplements;
    }

    @Override
    public String getName() {
        return this.name;
    }
}

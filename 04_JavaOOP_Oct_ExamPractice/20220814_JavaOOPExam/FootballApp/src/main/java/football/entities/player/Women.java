package football.entities.player;

public class Women extends BasePlayer {
    private static final double WOMEN_INITIAL_KG = 60.00;
    private static final int WOMEN_STRENGTH_STIMULATION_INCREASE = 115;

    public Women(String name, String nationality, int strength) {
        super(name, nationality, WOMEN_INITIAL_KG, strength);
    }

    @Override
    public void stimulation() {
        int newStrength = this.getStrength() + WOMEN_STRENGTH_STIMULATION_INCREASE;
        setStrength(newStrength);
    }
}

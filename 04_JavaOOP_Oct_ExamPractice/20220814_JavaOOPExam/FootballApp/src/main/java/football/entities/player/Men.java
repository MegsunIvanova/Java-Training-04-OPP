package football.entities.player;

public class Men extends BasePlayer{
    private static final double MAN_INITIAL_KG = 85.50;
    private static final int MAN_STRENGTH_STIMULATION_INCREASE = 145;

    public Men(String name, String nationality, int strength) {
        super(name, nationality, MAN_INITIAL_KG, strength);
    }

    @Override
    public void stimulation() {
        int newStrength = this.getStrength() + MAN_STRENGTH_STIMULATION_INCREASE;
        setStrength(newStrength);
    }
}

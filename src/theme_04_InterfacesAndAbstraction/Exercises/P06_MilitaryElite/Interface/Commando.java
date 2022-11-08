package theme_04_InterfacesAndAbstraction.Exercises.P06_MilitaryElite.Interface;

import theme_04_InterfacesAndAbstraction.Exercises.P06_MilitaryElite.SoldiersAndOthers.Mission;

import java.util.Collection;

public interface Commando {

    void addMission (Mission mission);

    Collection<Mission> getMissions();

}

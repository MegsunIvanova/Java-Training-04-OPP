package theme_04_InterfacesAndAbstraction.Exercises.P06_MilitaryElite.Interface;

import theme_04_InterfacesAndAbstraction.Exercises.P06_MilitaryElite.SoldiersAndOthers.Repair;

import java.util.Collection;

public interface Engineer {

    void addRepair(Repair repair);

    Collection<Repair> getRepairs();
}

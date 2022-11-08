package theme_04_InterfacesAndAbstraction.Exercises.P06_MilitaryElite.SoldiersAndOthers;

import theme_04_InterfacesAndAbstraction.Exercises.P06_MilitaryElite.Interface.Private;

public class PrivateImpl extends SoldierImpl implements Private {

    private double salary;

    public PrivateImpl(int id, String firstName, String lastName, double salary) {
        super(id, firstName, lastName);
        this.salary = salary;
    }

    @Override
    public double getSalary() {
        return this.salary;
    }

    @Override
    public String toString() {
        return String.format("%s Salary: %.2f", super.toString(), salary);
    }
}

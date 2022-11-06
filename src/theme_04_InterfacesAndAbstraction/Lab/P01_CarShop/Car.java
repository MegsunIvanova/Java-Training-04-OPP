package theme_04_InterfacesAndAbstraction.Lab.P01_CarShop;

import java.io.Serializable;

public interface Car extends Serializable {

    int TIRES = 4;

    String getModel();

    String getColor();

    Integer getHorsePower();

    String getCountry();

}

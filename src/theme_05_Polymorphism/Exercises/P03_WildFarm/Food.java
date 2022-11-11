package theme_05_Polymorphism.Exercises.P03_WildFarm;

public abstract class Food {

    private Integer quantity;

    public Food(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    private void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

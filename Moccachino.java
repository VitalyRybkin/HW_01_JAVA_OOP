public class Moccachino extends Coffee {

    private Double milk_consumption = 150.0;
    private Double coffee_consumption = 80.0;

    public Moccachino(String name, Integer volume, Double price) {
        super(name, volume, price);
    }

    @Override
    public Double getMilk_consumption() {
        return milk_consumption;
    }

    @Override
    public Double getCoffee_consumption() {
        return coffee_consumption;
    }
}

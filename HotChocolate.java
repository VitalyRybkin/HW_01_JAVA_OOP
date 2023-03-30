public class HotChocolate extends Coffee {

    private Double milk_consumption = 120.0;
    private Double coffee_consumption = 100.0;

    public HotChocolate(String name, Integer volume, Double price) {
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
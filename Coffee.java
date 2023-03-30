public class Coffee {
    private final String name;
    private Double price;
    private final Integer volume;
    private Double milk_consumption = 100.0;
    private Double coffee_consumption = 100.0;

    public Coffee(String name, Integer volume, Double price) {
        this.name = name;
        this.price = price;
        this.volume = volume;
    }

    public Double getCoffee_consumption() {
        return coffee_consumption;
    }

    public Double getMilk_consumption() {
        return milk_consumption;
    }

    public Integer getVolume() {
        return volume;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        StringBuilder coffee_list = new StringBuilder();
        coffee_list.append(this.name).append(", ")
                .append(this.volume).append(" ml").append(", ")
                .append("Price: ").append(this.price).append("$");
        return coffee_list.toString();
    }
}

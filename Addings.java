public class Addings {
    private final String adding_name;
    private final Integer adding_dosing;
    private Integer adding_quantity;

    public Addings(String adding_name, Integer adding_quantity, Integer adding_dosing) {
        this.adding_name = adding_name;
        this.adding_quantity = adding_quantity;
        this.adding_dosing = adding_dosing;
    }

    public Integer getAdding_dosing() {
        return adding_dosing;
    }

    public String getAdding_name() {
        return adding_name;
    }

    public Integer getAdding_quantity() {
        return adding_quantity;
    }

    public void setAdding_quantity(Integer quantity) {
        this.adding_quantity -= quantity;
    }

    public void addAdding_quantity(Integer quantity) {
        this.adding_quantity += quantity;
    }

    @Override
    public String toString() {
        return "Addings{" +
                "adding_name='" + adding_name + '\'' +
                ", adding_quantity=" + adding_quantity +
                '}';
    }
}

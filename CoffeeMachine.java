import java.util.*;

public class CoffeeMachine {

    private final List<Coffee> coffee_list = new ArrayList<>();

    private final List<Addings> addings_list = new ArrayList<>();
    private Double total_cash = 0.0;
    private Double milk_available = 5000.0;
    private Double coffee_available = 5000.0;

    public List<Addings> getAddings_list() {
        return addings_list;
    }

    public List<Coffee> getCoffee_list() {
        return coffee_list;
    }

    public void setMilk_available(Double add_milk) {
        this.milk_available += add_milk;
    }

    public void setCoffee_available(Double add_coffee) {
        this.coffee_available += add_coffee;
    }

    public Double getMilk_available() {
        return milk_available;
    }

    public Double getCoffee_available() {
        return coffee_available;
    }

    public Double getTotal_cash() {
        return total_cash;
    }

    public void Encashment(Double cash) {
        this.total_cash -= cash;
    }

    public CoffeeMachine AddProduct(Coffee type) {
        coffee_list.add(type);
        return this;
    }

    public CoffeeMachine AddAdding(Addings type) {
        addings_list.add(type);
        return this;
    }

    public Map<Integer, String> FillUserOutputDict(Map<Integer, String> user_dict, String search_field, String param) {
        int counter = 1;
        user_dict.clear();
        if (Objects.equals(search_field, "name")) {
            for (Coffee item : coffee_list) {
                if (!user_dict.containsValue(item.getName())) {
                    user_dict.put(counter, item.getName());
                    counter++;
                }
            }
            user_dict.put(counter, "Back");
        }

        if (Objects.equals(search_field, "volume")) {
            for (Coffee item : coffee_list) {
                if (Objects.equals(item.getName(), param) && !user_dict.containsValue(Integer.toString(item.getVolume()))) {
                    user_dict.put(counter, Integer.toString(item.getVolume()));
                    counter++;
                }
            }
            user_dict.put(counter, "Back");
        }

        if (Objects.equals(search_field, "adding_name")) {
            for (Addings item : addings_list) {
                if (!user_dict.containsValue(item.getAdding_name())) {
                    user_dict.put(counter, item.getAdding_name());
                    counter++;
                }
            }
            user_dict.put(counter, "Back");
            counter++;
            user_dict.put(counter, "Next");
        }

        return user_dict;
    }

    public void SellCoffee(String coffee, String volume, List<String> addings) {
        for (Coffee item : coffee_list) {
            if (Objects.equals(item.getName(), coffee) && Objects.equals(item.getVolume(), Integer.parseInt(volume))) {
                milk_available -= item.getMilk_consumption();
                coffee_available -= item.getCoffee_consumption();
                total_cash += item.getPrice();
                String order = "\nYour order: " + item.getName() + " (" +
                        item.getVolume() + " ml)" +
                        " for " +
                        item.getPrice() + "$ ";
                System.out.print(order);
                if (!addings.isEmpty()) {
                    System.out.println("(" +
                            Arrays.toString(addings.toArray()).replaceAll("[\\[\\]]", "") +
                            ")");
                }
            }
        }
    }

    public void ReduceAddings(List<String> substraction_list) {
        for (String item : substraction_list) {
            for (Addings add : addings_list) {
                if (Objects.equals(item, add.getAdding_name())) {
                    add.setAdding_quantity(add.getAdding_dosing());
                }
            }
        }
    }


    @Override
    public String toString() {
        StringBuilder res_str = new StringBuilder();
        for (Coffee item : coffee_list) {
            res_str.append(item).append("\n");
        }
        return res_str.toString();
    }

    public String printAddings() {
        StringBuilder res_str = new StringBuilder();
        res_str.append("Addings: \n");
        for (Addings item : addings_list) {
            res_str.append(item.getAdding_name()).append(": ").append(item.getAdding_quantity()).append(" g\n");
        }
        return res_str.toString();
    }
}

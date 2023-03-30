import java.util.*;

public class Main {
    public static void main(String[] args) {
        CoffeeMachine vending_machine = new CoffeeMachine();
        vending_machine.AddProduct(new Cappuccino("Cappuccino", 150, 0.99))
                .AddProduct(new Cappuccino("Cappuccino", 200, 1.19))
                .AddProduct(new Cappuccino("Cappuccino", 300, 1.39))
                .AddProduct(new Americano("Americano", 150, 0.89))
                .AddProduct(new Americano("Americano", 200, 0.99))
                .AddProduct(new Americano("Americano", 300, 1.09))
                .AddProduct(new Moccachino("Moccachino", 150, 1.19))
                .AddProduct(new Moccachino("Moccachino", 200, 1.29))
                .AddProduct(new Moccachino("Moccachino", 300, 1.39))
                .AddProduct(new HotChocolate("Hot chocolate", 150, 0.99))
                .AddProduct(new HotChocolate("Hot chocolate", 200, 1.09))
                .AddProduct(new HotChocolate("Hot chocolate", 300, 1.29));

        vending_machine.AddAdding(new Addings("Cinnamon", 100, 5))
                .AddAdding(new Addings("Sugar", 500, 10))
                .AddAdding(new Addings("Syrup", 200, 15))
                .AddAdding(new Addings("Milk", 2000, 100));

        System.out.println("Coffee machine is now working...");

        Map<Integer, String> user_output_dict = new HashMap<>();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            user_output_dict.clear();
            if (vending_machine.getCoffee_available() < 300 || vending_machine.getMilk_available() < 300) {
                user_output_dict.put(1, "Order (unavailable)");
            } else {
                user_output_dict.put(1, "Order");
            }
            user_output_dict.put(2, "Service");
            user_output_dict.put(3, "Quit");
            System.out.println("\nChoose operation: ");
            PrintOutput(user_output_dict);
            System.out.print(">> ");
            String choice_input = scanner.next();

            int user_choice;
            user_choice = CheckUserChoice(user_output_dict, choice_input);

            switch (user_choice) {

                /* ********* CREATING ORDER ********* */

                case 1 -> {
                    while (true) {
                        boolean exit_loop = false;
                        if (vending_machine.getCoffee_available() < 300 || vending_machine.getMilk_available() < 300) {
                            System.out.println("Service unavailable! Not enough ingredients!");
                            break;
                        }
                        String search_field = "name";
                        String search_coffee = "";
                        user_output_dict = vending_machine.FillUserOutputDict(user_output_dict, search_field, search_coffee);

                        System.out.println("Choose one of our delicious coffees:");
                        PrintOutput(user_output_dict);
                        System.out.print(">> ");
                        choice_input = scanner.next();

                        user_choice = CheckUserChoice(user_output_dict, choice_input);
                        if (Objects.equals(user_output_dict.get(user_choice), "Back")) break;


                        search_coffee = user_output_dict.get(user_choice);
                        while (true) {
                            search_field = "volume";
                            user_output_dict = vending_machine.FillUserOutputDict(user_output_dict, search_field, search_coffee);
                            System.out.println("Choose cup size:");
                            PrintOutput(user_output_dict);
                            System.out.print(">> ");
                            choice_input = scanner.next();

                            user_choice = CheckUserChoice(user_output_dict, choice_input);
                            if (Objects.equals(user_output_dict.get(user_choice), "Back")) break;
                            String search_volume = user_output_dict.get(user_choice);

                            search_field = "adding_name";
                            user_output_dict = vending_machine.FillUserOutputDict(user_output_dict, search_field, search_coffee);
                            List<String> substract_addings = new ArrayList<>();
                            while (true) {
                                System.out.println("Choose adding:");
                                PrintOutput(user_output_dict);
                                System.out.print(">> ");
                                choice_input = scanner.next();

                                user_choice = CheckUserChoice(user_output_dict, choice_input);
                                if (Objects.equals(user_output_dict.get(user_choice), "Back")) break;
                                else if (Objects.equals(user_output_dict.get(user_choice), "Next")) {
                                    vending_machine.SellCoffee(search_coffee, search_volume, substract_addings);
                                    vending_machine.ReduceAddings(substract_addings);
                                    exit_loop = true;
                                    break;
                                } else {
                                    substract_addings.add(user_output_dict.get(user_choice));
                                    user_output_dict.remove(user_choice);
                                    System.out.println("You've added - " + substract_addings);
                                }
                            }
                            if (exit_loop) break;
                        }
                        if (exit_loop) break;
                    }

                }

                /* ********* SERVICE COMMANDS ********* */

                case 2 -> {
                    String password = "123";
                    while (true) {
                        System.out.println("Type password: ");
                        System.out.print(">> ");
                        if (!Objects.equals(scanner.next(), password)) {
                            System.out.println("Wrong password!");
                        } else break;
                    }
                    while (true) {
                        user_output_dict.clear();
                        user_output_dict.put(1, "Machine info");
                        user_output_dict.put(2, "Menu");
                        user_output_dict.put(3, "Change price");
                        user_output_dict.put(4, "Add component");
                        user_output_dict.put(5, "Add adding");
                        user_output_dict.put(6, "Encashment");
                        user_output_dict.put(7, "Back");
                        System.out.println("\nChoose operation: ");
                        PrintOutput(user_output_dict);
                        System.out.print(">> ");
                        choice_input = scanner.next();

                        user_choice = CheckUserChoice(user_output_dict, choice_input);
                        if (Objects.equals(user_output_dict.get(user_choice), "Back")) break;

                        /* ********* ENCASHMENT ********* */

                        if (Objects.equals(user_output_dict.get(user_choice), "Encashment")) {
                            if (vending_machine.getTotal_cash() <= 0) {
                                System.out.println("Total cash: " + vending_machine.getTotal_cash() + "$");
                            } else {
                                double cash;
                                while (true) {
                                    System.out.println("Total cash: " + vending_machine.getTotal_cash() + "$");
                                    System.out.println("Enter an amount or 0: ");
                                    System.out.print(">> ");
                                    choice_input = scanner.next();
                                    try {
                                        cash = Double.parseDouble(choice_input);
                                        break;
                                    } catch (NumberFormatException e) {
                                        System.out.println("Something went wrong! Try again!");
                                    }
                                }
                                vending_machine.Encashment(cash);
                                System.out.println("Total cash: " + vending_machine.getTotal_cash() + "$");
                            }
                        }

                        /* ********* ADD COMPONENTS ********* */

                        if (Objects.equals(user_output_dict.get(user_choice), "Add component")) {
                            while (true) {
                                System.out.println("Milk: " + vending_machine.getMilk_available() + " ml");
                                System.out.println("Coffee: " + vending_machine.getCoffee_available() + " g");
                                user_output_dict.clear();
                                user_output_dict.put(1, "Milk");
                                user_output_dict.put(2, "Coffee");
                                user_output_dict.put(3, "Back");

                                System.out.println("Choose component:");
                                PrintOutput(user_output_dict);
                                System.out.print(">> ");
                                choice_input = scanner.next();

                                user_choice = CheckUserChoice(user_output_dict, choice_input);
                                if (Objects.equals(user_output_dict.get(user_choice), "Back")) break;

                                double component_amount;
                                while (true) {
                                    System.out.println("Enter an amount or type 0: ");
                                    System.out.print(">> ");
                                    choice_input = scanner.next();
                                    try {
                                        component_amount = Integer.parseInt(choice_input);
                                        break;
                                    } catch (NumberFormatException e) {
                                        System.out.println("Something went wrong! Try again!");
                                    }
                                }

                                if (Objects.equals(user_output_dict.get(user_choice), "Milk")) {
                                    vending_machine.setMilk_available(component_amount);
                                }
                                if (Objects.equals(user_output_dict.get(user_choice), "Coffee")) {
                                    vending_machine.setCoffee_available(component_amount);
                                }
                                System.out.println("Milk: " + vending_machine.getMilk_available() + " ml");
                                System.out.println("Coffee: " + vending_machine.getCoffee_available() + " g");
                                break;
                            }
                        }

                        /* ********* ADD ADDING ********* */

                        if (Objects.equals(user_output_dict.get(user_choice), "Add adding")) {
                            System.out.println(vending_machine.printAddings());
                            while (true) {
                                int counter = 1;
                                user_output_dict.clear();
                                for (Addings item : vending_machine.getAddings_list()) {
                                    user_output_dict.put(counter, item.getAdding_name());
                                    counter++;
                                }
                                user_output_dict.put(counter, "Back");

                                System.out.println("Choose adding:");
                                PrintOutput(user_output_dict);
                                System.out.print(">> ");
                                choice_input = scanner.next();

                                user_choice = CheckUserChoice(user_output_dict, choice_input);
                                if (Objects.equals(user_output_dict.get(user_choice), "Back")) break;

                                int adding_amount;
                                while (true) {
                                    System.out.println("Enter an amount or type 0: ");
                                    System.out.print(">> ");
                                    choice_input = scanner.next();
                                    try {
                                        adding_amount = Integer.parseInt(choice_input);
                                        break;
                                    } catch (NumberFormatException e) {
                                        System.out.println("Something went wrong! Try again!");
                                    }
                                }

                                for (Addings item : vending_machine.getAddings_list()) {
                                    if (Objects.equals(item.getAdding_name(), user_output_dict.get(user_choice))) {
                                        item.addAdding_quantity(adding_amount);
                                    }
                                }
                                System.out.println(vending_machine.printAddings());
                                break;
                            }
                        }

                        /* ********* CHANGE PRICE ********* */

                        if (Objects.equals(user_output_dict.get(user_choice), "Change price")) {
                            while (true) {
                                int counter = 1;
                                user_output_dict.clear();
                                for (Coffee item : vending_machine.getCoffee_list()) {
                                    String dict_string;
                                    dict_string = item.getName() + ", " + item.getVolume() + " ml, " + item.getPrice() + "$";
                                    user_output_dict.put(counter, dict_string);
                                    counter++;
                                }
                                user_output_dict.put(counter, "Back");

                                System.out.println("Choose product:");
                                PrintOutput(user_output_dict);
                                System.out.print(">> ");
                                choice_input = scanner.next();

                                user_choice = CheckUserChoice(user_output_dict, choice_input);
                                if (Objects.equals(user_output_dict.get(user_choice), "Back")) break;

                                String[] product_to_change_price = user_output_dict.get(user_choice).split("[ ,$]");

                                double new_price;
                                while (true) {
                                    System.out.println("Enter new price: ");
                                    System.out.print(">> ");
                                    choice_input = scanner.next();
                                    try {
                                        new_price = Double.parseDouble(choice_input);
                                        break;
                                    } catch (NumberFormatException e) {
                                        System.out.println("Something went wrong! Try again!");
                                    }
                                }

                                for (Coffee item : vending_machine.getCoffee_list()) {
                                    if (Objects.equals(item.getName(), product_to_change_price[0])
                                            && Objects.equals(item.getVolume(), Integer.parseInt(product_to_change_price[2]))) {
                                        item.setPrice(new_price);
                                        System.out.println("Price changed: ");
                                        System.out.println(item.getName() + ", " + item.getVolume() + " ml, " + item.getPrice() + "$\n");
                                    }
                                }
                                break;
                            }
                        }

                        /* ********* PRINT INFO ********* */

                        if (Objects.equals(user_output_dict.get(user_choice), "Machine info")) {
                            System.out.println("Machine total info:");
                            System.out.println("Cash: " + vending_machine.getTotal_cash() + "$");
                            System.out.println("Milk: " + vending_machine.getMilk_available() + " ml");
                            System.out.println("Coffee: " + vending_machine.getCoffee_available() + " g");
                            System.out.println(vending_machine.printAddings());
                        }

                        /* ********* PRINT MENU ********* */

                        if (Objects.equals(user_output_dict.get(user_choice), "Menu")) {
                            System.out.println("Machine menu:");
                            System.out.println(vending_machine);
                        }
                    }
                }

                /* ********* EXIT ********* */

                case 3 -> System.exit(0);
            }
        }
    }


    public static void PrintOutput(Map<Integer, String> user_dict) {
        user_dict.forEach((key, value) -> System.out.println(key + ". " + value));
    }

    public static Integer CheckUserChoice(Map<Integer, String> user_dict, String input) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(input);
                if (!user_dict.containsKey(choice)) {
                    System.out.println("Wrong input! Try again!");
                    input = scanner.next();
                } else break;
            } catch (NumberFormatException e) {
                System.out.println("Something went wrong! Try again!");
                input = scanner.next();
            }
        }
        return choice;
    }
}

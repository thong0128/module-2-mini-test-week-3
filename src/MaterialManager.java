import material.CrispyFlour;
import material.Material;
import material.Meat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class MaterialManager {
    public static void main(String[] args) {
        ArrayList<Material> materialsList = new ArrayList<>();
        Material flour1 = new CrispyFlour("01","CF1", LocalDate.of(2022,12, 10),135,5.5);
        Material flour2 = new CrispyFlour("02","CF2", LocalDate.of(2023,1, 25),128,7);
        Material flour3 = new CrispyFlour("03","CF4", LocalDate.of(2023,2, 12),80,15);
        Material flour4 = new CrispyFlour("04","CF5", LocalDate.of(2023,5, 25),100,10);
        Material flour5 = new CrispyFlour("05","CF6", LocalDate.of(2023,10, 28),95,8);

        Material meat1 = new Meat("06","M1",LocalDate.of(2023, 12, 15), 135,5);
        Material meat2 = new Meat("07","M2",LocalDate.of(2023, 12, 18), 120,8);
        Material meat3 = new Meat("08","M3",LocalDate.of(2023, 12, 20), 130,10);
        Material meat4 = new Meat("09","M4",LocalDate.of(2023, 12, 21), 125,20);
        Material meat5 = new Meat("10","M5",LocalDate.of(2023, 12, 14), 122,18);

        materialsList.add(flour1);
        materialsList.add(flour2);
        materialsList.add(flour3);
        materialsList.add(flour4);
        materialsList.add(flour5);

        materialsList.add(meat1);
        materialsList.add(meat2);
        materialsList.add(meat3);
        materialsList.add(meat4);
        materialsList.add(meat5);


    }
    public static String stringInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(input);
        return input;
    }
    public static LocalDate dateInput(String userInput) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyy");
        LocalDate date = LocalDate.parse(userInput, dateFormat);
        System.out.println(date);
        return date ;
    }
    public static int intInput() {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        System.out.println(input);
        return input;
    }
    public static double doubleInput() {
        Scanner scanner = new Scanner(System.in);
        double input = scanner.nextDouble();
        System.out.println(input);
        return input;
    }
    public static Material inputCrispyFlour() {
        TempMaterial temp = getTempMaterial();
        System.out.print("Enter quantity: ");
        double quantity = doubleInput();
        Material cf = new CrispyFlour(temp.id, temp.name, temp.date, temp.cost, quantity);
        System.out.println(cf);
        return cf;
    }
    public static Material inputMeat() {
        TempMaterial temp = getTempMaterial();
        System.out.print("Enter weight: ");
        double weight = doubleInput();
        Material meat = new Meat(temp.id, temp.name, temp.date, temp.cost, weight);
        System.out.println(meat);
        return meat;
    }

    private static TempMaterial getTempMaterial() {
        System.out.print("Enter id: ");
        String id = stringInput();
        System.out.print("Enter name: ");
        String name = stringInput();
        System.out.print("Enter date (dd/MM/yyy): ");
        LocalDate date = dateInput(stringInput());
        System.out.print("Enter cost: ");
        int cost = intInput();
        return new TempMaterial(id, name, date, cost);
    }

    private static class TempMaterial {
        public final String id;
        public final String name;
        public final LocalDate date;
        public final int cost;
        public TempMaterial(String id, String name, LocalDate date, int cost) {
            this.id = id;
            this.name = name;
            this.date = date;
            this.cost = cost;
        }
    }

    public static void changeMaterial(Material material) {
        if (material instanceof CrispyFlour) {
            commonChange(material);
            System.out.println("Quantity: " + ((CrispyFlour) material).getQuantity() + ". Enter new quantity:");
            ((CrispyFlour) material).setQuantity(doubleInput());
            System.out.println(material);
        } else if (material instanceof Meat) {
            commonChange(material);
            System.out.println("Weight: " + ((Meat) material).getWeight() + ". Enter new weight:");
            ((Meat) material).setWeight(doubleInput());
            System.out.println(material);
        }
    }

    private static void commonChange(Material material) {
        System.out.println("ID: " + material.getId() + ". Enter new id:");
        material.setId(stringInput());
        System.out.println("Name: " + material.getName() + ". Enter new name:");
        material.setName(stringInput());
        System.out.println("Date: " + material.getManufacturingDate() + ". Enter new date (dd/MM/yyy):");
        material.setManufacturingDate(dateInput(stringInput()));
        System.out.println("Cost: " + material.getCost() + ". Enter new cost:");
        material.setCost(intInput());
    }
}

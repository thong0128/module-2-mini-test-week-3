import material.CrispyFlour;
import material.Material;
import material.Meat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
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

        mainMenu(materialsList);
    }
    public static void mainMenu(ArrayList<Material> materialsList) {
        int myChoice;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("-----Material Manager-----");
            System.out.println("1.Add material");
            System.out.println("2.Modify material");
            System.out.println("3.Remove material");
            System.out.println("4.Total cost with discount today");
            System.out.println("5.Sort by cost");
            System.out.println("6.Difference between discount and no discount today");
            System.out.println("0.Exit");

            System.out.print("Enter choice: ");
            myChoice = scanner.nextInt();
            switch (myChoice) {
                case 1:
                    addMaterialMenu(materialsList);
                    break;
                case 2:
                    modifyMenu(materialsList);
                    break;
                case 3:
                    removeMenu(materialsList);
                    break;
                case 4:
                    System.out.println("Total cost with discount: " + getTotalRealMoney(materialsList));
                    break;
                case 5:
                    System.out.println("List before sorting: ");
                    showList(materialsList);
                    System.out.println("\n List after sorting: ");
                    sortByCost(materialsList);
                    showList(materialsList);
                    break;
                case 6:
                    System.out.println("Difference between discount and no discount today: " + (getTotalCost(materialsList) - getTotalRealMoney(materialsList)));
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Invalid input!");
            }
        }
    }

    private static void removeMenu(ArrayList<Material> materialsList) {
        int myChoice = -1;
        Scanner scanner = new Scanner(System.in);
        while (myChoice != 0) {
            System.out.println("-----Remove Menu-----");
            System.out.println("1.Remove by index");
            System.out.println("0.Back");

            System.out.print("Enter choice: ");
            myChoice = scanner.nextInt();

            switch (myChoice) {
                case 1:
                    indexCheck(materialsList);
                    System.out.println("Material: " + materialsList.get(myChoice));
                    System.out.println("Are you sure to delete? y/n?");
                    while (true) {
                        Scanner input = new Scanner(System.in);
                        String ans = input.nextLine();
                        if (ans.equalsIgnoreCase("y")) {
                            materialsList.remove(myChoice);
                            break;
                        } else if (ans.equalsIgnoreCase("n")) {
                            mainMenu(materialsList);
                            break;
                        }else {
                            System.out.println("Sorry, I didn't catch that. Please answer y/n");
                        }
                    }
                    break;
                case 0:
                    mainMenu(materialsList);
                    break;
                default:
                    System.out.println("Invalid input!");
            }
        }
    }

    public static void addMaterialMenu(ArrayList<Material> materialsList) {
        int myChoice = -1;
        Scanner scanner = new Scanner(System.in);
        while (myChoice != 0) {
            System.out.println("-----Select Type-----");
            System.out.println("1.Crispy Flour");
            System.out.println("2.Meat");
            System.out.println("0.Back");

            System.out.print("Enter choice: ");
            myChoice = scanner.nextInt();
            switch (myChoice) {
                case 1:
                    materialsList.add(inputCrispyFlour());
                    mainMenu(materialsList);
                    break;
                case 2:
                    materialsList.add(inputMeat());
                    mainMenu(materialsList);
                    break;
                case 0:
                    mainMenu(materialsList);
                    break;
                default:
                    System.out.println("Invalid input!");
            }
        }
    }
    public static void modifyMenu(ArrayList<Material> materialsList) {
        int myChoice = -1;
        Scanner scanner = new Scanner(System.in);
        while (myChoice != 0) {
            System.out.println("-----Modify Menu-----");
            System.out.println("1.Modify by index");
            System.out.println("0.Back");

            System.out.print("Enter choice: ");
            myChoice = scanner.nextInt();

            switch (myChoice) {
                case 1:
                    indexCheck(materialsList);
                    modifyByIndex(materialsList, materialsList.get(myChoice));
                    break;
                case 0:
                    mainMenu(materialsList);
                    break;
                default:
                    System.out.println("Invalid input!");
            }
        }
    }

    private static void indexCheck(ArrayList<Material> materialsList) {
        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter index: ");
            int index = input.nextInt();
            if (index < 0 || index >= materialsList.size()) System.out.println("Invalid index!");
            else break;
        }
    }

    private static void modifyByIndex(ArrayList<Material> materialsList, Material material) {
        int myChoice = -1;
        Scanner scanner = new Scanner(System.in);
        while (myChoice != 0) {
            System.out.println("-----Modify Menu-----");
            System.out.println(material);
            System.out.println("1.Modify id");
            System.out.println("2.Modify name");
            System.out.println("3.Modify manufacturing date");
            System.out.println("4.Modify cost");
            System.out.println("5.Modify quantity/weight");
            System.out.println("0.Back");

            System.out.print("Enter choice: ");
            myChoice = scanner.nextInt();

            switch (myChoice) {
                case 1:
                    modifyID(material);
                    break;
                case 2:
                    modifyName(material);
                    break;
                case 3:
                    modifyManuDate(material);
                    break;
                case 4:
                    modifyCost(material);
                    break;
                case 5:
                    modifyQuantityWeight(material);
                    break;
                case 0:
                    modifyMenu(materialsList);
                    break;
                default:
                    System.out.println("Invalid input!");
            }
        }
    }

    public static String stringInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    public static LocalDate dateInput(String userInput) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyy");
        return LocalDate.parse(userInput, dateFormat);
    }
    public static int intInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    public static double doubleInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextDouble();
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

    public static void modifyQuantityWeight(Material material) {
        if (material instanceof CrispyFlour) {
            System.out.println("Quantity: " + ((CrispyFlour) material).getQuantity() + ". Enter new quantity:");
            ((CrispyFlour) material).setQuantity(doubleInput());
            System.out.println(material);
        } else if (material instanceof Meat) {
            System.out.println("Weight: " + ((Meat) material).getWeight() + ". Enter new weight:");
            ((Meat) material).setWeight(doubleInput());
            System.out.println(material);
        }
    }

    private static void modifyCost(Material material) {
        System.out.println("Cost: " + material.getCost() + ". Enter new cost:");
        material.setCost(intInput());
    }

    private static void modifyManuDate(Material material) {
        System.out.println("Date: " + material.getManufacturingDate() + ". Enter new date (dd/MM/yyy):");
        material.setManufacturingDate(dateInput(stringInput()));
    }

    private static void modifyName(Material material) {
        System.out.println("Name: " + material.getName() + ". Enter new name:");
        material.setName(stringInput());
    }

    private static void modifyID(Material material) {
        System.out.println("ID: " + material.getId() + ". Enter new id:");
        material.setId(stringInput());
    }
    private static double getTotalRealMoney(ArrayList<Material> materialsList) {
        double sum = 0;
        for (Material material : materialsList) {
            if (material instanceof CrispyFlour) sum += ((CrispyFlour)material).getRealMoney();
            if (material instanceof Meat) sum += ((Meat)material).getRealMoney();
        }
        return sum;
    }
    private static void sortByCost(ArrayList<Material> materialsList) {
        Comparator<Material> comparator = Comparator.comparingInt(Material::getCost);
        materialsList.sort(comparator);
    }

    private static void showList(ArrayList<Material> materialsList) {
        for (int i = 0; i < materialsList.size(); i++) {
            System.out.println((i+1) + "." + materialsList.get(i));
        }
    }

    private static double getTotalCost(ArrayList<Material> materialsList) {
        double sum = 0;
        for (Material material : materialsList) {
            sum += material.getAmount();
        }
        return sum;
    }
}

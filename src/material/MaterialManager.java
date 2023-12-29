package material;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MaterialManager implements Serializable {
    private List<Material> materialsList = new ArrayList<>();
    private String path = null;
    public MaterialManager() {
    }

    public MaterialManager(String path) throws ClassNotFoundException {
        this.path = path;
        this.materialsList = readDataFromFile();
    }

    public MaterialManager(List<Material> list, String path) {
        this.materialsList = list;
        this.path = path;
        writeDataToFile();
    }
    private String autoIdSetter() {
        Set<Integer> idList = new HashSet<>();
        for (Material material : materialsList) {
            idList.add(Integer.parseInt(material.getId()));
        }
        int i = 1;
        while (!idList.add(i)){
            i++;
        }
        return String.valueOf(i);
    }
    private boolean idCheck(String id) {
        Set<Integer> idList = new HashSet<>();
        for (Material material : materialsList) {
            idList.add(Integer.parseInt(material.getId()));
        }
        return idList.add(Integer.parseInt(id));
    }

    public List<Material> getMaterialsList() {
        return materialsList;
    }

    public void setMaterialsList(List<Material> materialsList) {
        this.materialsList = materialsList;
        writeDataToFile();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
        writeDataToFile();
    }

    public void mainMenu() {
        int myChoice;
        while (true) {
            System.out.println("-----Material Manager-----");
            System.out.println("1.Add material");
            System.out.println("2.Modify material");
            System.out.println("3.Remove material");
            System.out.println("4.Total cost with discount today");
            System.out.println("5.Sort by cost");
            System.out.println("6.Difference between discount and no discount today");
            System.out.println("7.Show list");
            System.out.println("0.Exit");

            System.out.print("Enter choice: ");
            myChoice = intInput();
            switch (myChoice) {
                case 1:
                    addMaterialMenu();
                    break;
                case 2:
                    modifyMenu();
                    break;
                case 3:
                    removeMenu();
                    break;
                case 4:
                    System.out.println("Total cost with discount: " + getTotalRealMoney());
                    break;
                case 5:
                    System.out.println("List before sorting: ");
                    showList();
                    System.out.println("\n List after sorting: ");
                    sortByCost();
                    writeDataToFile();
                    showList();
                    break;
                case 6:
                    System.out.println("Difference between discount and no discount today: " + (getTotalCost() - getTotalRealMoney()));
                    break;
                case 7:
                    showList();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Invalid input!");
            }
        }
    }
    private void addMaterialMenu() {
        int myChoice = -1;
        while (myChoice != 0) {
            System.out.println("-----Select Type-----");
            System.out.println("1.Crispy Flour");
            System.out.println("2.Meat");
            System.out.println("0.Back");

            System.out.print("Enter choice: ");
            myChoice = intInput();
            switch (myChoice) {
                case 1:
                    addCrispyFlour();
                    writeDataToFile();
                    mainMenu();
                    break;
                case 2:
                    addMeat();
                    writeDataToFile();
                    mainMenu();
                    break;
                case 0:
                    mainMenu();
                    break;
                default:
                    System.out.println("Invalid input!");
            }
        }
    }


    private TempMaterial getTempMaterial() {
        String id = autoIdSetter();
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
    private void addCrispyFlour() {
        TempMaterial tempMaterial = getTempMaterial();
        System.out.print("Enter quantity: ");
        double quantity = doubleInput();
        Material cf = new CrispyFlour(tempMaterial.id, tempMaterial.name, tempMaterial.date, tempMaterial.cost, quantity);
        System.out.println(cf);
        materialsList.add(cf);
    }
    private void addMeat() {
        TempMaterial tempMaterial = getTempMaterial();
        System.out.print("Enter weight: ");
        double weight = doubleInput();
        Material meat = new Meat(tempMaterial.id, tempMaterial.name, tempMaterial.date, tempMaterial.cost, weight);
        System.out.println(meat);
        materialsList.add(meat);
    }
    private static String stringInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    private static LocalDate dateInput(String userInput) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyy");
        return LocalDate.parse(userInput, dateFormat);
    }
    private static int intInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    private static double doubleInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextDouble();
    }
    private void modifyMenu() {
        int myChoice = -1;
        while (myChoice != 0) {
            System.out.println("-----Modify Menu-----");
            System.out.println("1.Modify by index");
            System.out.println("0.Back");

            System.out.print("Enter choice: ");
            myChoice = intInput();

            switch (myChoice) {
                case 1:
                    int index = indexCheck();
                    modifyByIndex(index);
                    break;
                case 0:
                    mainMenu();
                    break;
                default:
                    System.out.println("Invalid input!");
            }
        }
    }
    private int indexCheck() {
        while (true) {
            System.out.print("Enter index: ");
            int index = intInput();
            if (index < 0 || index >= materialsList.size()) {
                System.out.println("Invalid index!");
            }else {
                return index;
            }
        }
    }
    private void modifyByIndex(int index) {
        Material material = materialsList.get(index);
        int myChoice = -1;
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
            myChoice = intInput();

            switch (myChoice) {
                case 1:
                    modifyID(material);
                    writeDataToFile();
                    break;
                case 2:
                    modifyName(material);
                    writeDataToFile();
                    break;
                case 3:
                    modifyManuDate(material);
                    writeDataToFile();
                    break;
                case 4:
                    modifyCost(material);
                    writeDataToFile();
                    break;
                case 5:
                    modifyQuantityWeight(material);
                    writeDataToFile();
                    break;
                case 0:
                    modifyMenu();
                    break;
                default:
                    System.out.println("Invalid input!");
            }
        }
    }
    private void modifyID(Material material) {
        String id;
        do {
            System.out.println("ID: " + material.getId() + ". Enter new id:");
            id = stringInput();
            if (!idCheck(id)) System.out.println("Id already taken, please enter again");
        }while (!idCheck(id));
        material.setId(id);
    }
    private void modifyName(Material material) {
        System.out.println("Name: " + material.getName() + ". Enter new name:");
        material.setName(stringInput());
    }
    private void modifyManuDate(Material material) {
        System.out.println("Date: " + material.getManufacturingDate() + ". Enter new date (dd/MM/yyy):");
        material.setManufacturingDate(dateInput(stringInput()));
    }
    private void modifyCost(Material material) {
        System.out.println("Cost: " + material.getCost() + ". Enter new cost:");
        material.setCost(intInput());
    }
    private void modifyQuantityWeight(Material material) {
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
    private void removeMenu() {
        int myChoice = -1;
        while (myChoice != 0) {
            System.out.println("-----Remove Menu-----");
            System.out.println("1.Remove by index");
            System.out.println("0.Back");

            System.out.print("Enter choice: ");
            myChoice = intInput();

            switch (myChoice) {
                case 1:
                    int index = indexCheck();
                    System.out.println("Material: " + materialsList.get(index));
                    System.out.println("Are you sure to delete? y/n?");
                    while (true) {
                        String ans = stringInput();
                        if (ans.equalsIgnoreCase("y")) {
                            materialsList.remove(index);
                            writeDataToFile();
                            break;
                        } else if (ans.equalsIgnoreCase("n")) {
                            mainMenu();
                            break;
                        }else {
                            System.out.println("Sorry, I didn't catch that. Please answer y/n");
                        }
                    }
                    break;
                case 0:
                    mainMenu();
                    break;
                default:
                    System.out.println("Invalid input!");
            }
        }
    }
    public double getTotalRealMoney() {
        double sum = 0;
        for (Material material : materialsList) {
            if (material instanceof CrispyFlour) sum += ((CrispyFlour)material).getRealMoney();
            if (material instanceof Meat) sum += ((Meat)material).getRealMoney();
        }
        return sum;
    }
    public void showList() {
        for (int i = 0; i < materialsList.size(); i++) {
            System.out.println((i+1) + "." + materialsList.get(i));
        }
    }
    public void sortByCost() {
        Comparator<Material> comparator = Comparator.comparingInt(Material::getCost);
        materialsList.sort(comparator);
    }
    public double getTotalCost() {
        double sum = 0;
        for (Material material : materialsList) {
            sum += material.getAmount();
        }
        return sum;
    }
    public void writeDataToFile() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
            oos.writeObject(materialsList);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private List<Material> readDataFromFile() {
        List<Material> materials = new ArrayList<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            materials = (List<Material>) ois.readObject();
        }catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return materials;
    }
}

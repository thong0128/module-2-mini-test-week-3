import material.*;

import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
//        List<Material> list = new ArrayList<>();
//        Material flour1 = new CrispyFlour("12","fl1", LocalDate.of(2022,12, 10),135,5.5);
//        Material flour2 = new CrispyFlour("15","fl2", LocalDate.of(2023,1, 25),128,7);
//        Material flour3 = new CrispyFlour("22","fl3", LocalDate.of(2023,2, 12),80,15);
//        Material flour4 = new CrispyFlour("18","fl4", LocalDate.of(2023,5, 25),100,10);
//        Material flour5 = new CrispyFlour("25","fl5", LocalDate.of(2023,10, 28),95,8);
//
//        Material meat1 = new Meat("50","m1",LocalDate.of(2023, 12, 15), 135,5);
//        Material meat2 = new Meat("52","m2",LocalDate.of(2023, 12, 18), 120,8);
//        Material meat3 = new Meat("55","m3",LocalDate.of(2023, 12, 20), 130,10);
//        Material meat4 = new Meat("56","m4",LocalDate.of(2023, 12, 21), 125,20);
//        Material meat5 = new Meat("58","m5",LocalDate.of(2023, 12, 14), 122,18);
//
//        list.add(flour1);
//        list.add(flour2);
//        list.add(flour3);
//        list.add(flour4);
//        list.add(flour5);
//        list.add(meat1);
//        list.add(meat2);
//        list.add(meat3);
//        list.add(meat4);
//        list.add(meat5);
//        MaterialManager materialManager = new MaterialManager(list,"MaterialList.mat");
        try {
            MaterialManager materialManager = new MaterialManager("MaterialList.mat");
            materialManager.mainMenu();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

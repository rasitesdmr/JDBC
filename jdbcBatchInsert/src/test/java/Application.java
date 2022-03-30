import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        Personnel personnel1 = new Personnel(8, "Servet", "Akçagunay", 1999, "478965412");
        Personnel personnel2 = new Personnel(9, "Çilem", "Akçay", 1998, "789546214");
        Personnel personnel3 = new Personnel(10, "Emre Ayberk", "Akfırat", 1997, "689874563");
        Personnel personnel4 = new Personnel(11, "Ercüment", "Akıncılar", 1996, "486578956");
        Personnel personnel5 = new Personnel(12, "Zümra", "Çelık", 1995, "325478914");
        Personnel personnel6 = new Personnel(13, "Miraç", "Demiriz", 1994, "142536789");

        List<Personnel> personnels = new ArrayList<>();

        personnels.add(personnel1);
        personnels.add(personnel2);
        personnels.add(personnel3);
        personnels.add(personnel4);
        personnels.add(personnel5);
        personnels.add(personnel6);

        DBProcesses.saveListPersonnel(personnels);
    }


}

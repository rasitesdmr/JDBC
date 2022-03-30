import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Personnel> personnels = DBProcesses.findPersonels();
        personnels.forEach(System.out::println);

    }
}

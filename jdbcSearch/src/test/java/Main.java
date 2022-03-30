public class Main {
    public static void main(String[] args) {
        Personnel personnel = DBProcesses.findPersonnelById(30);
        System.out.println(personnel);
    }
}

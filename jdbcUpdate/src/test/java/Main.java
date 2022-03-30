public class Main {
    public static void main(String[] args) {
        Personnel personnel1 = new Personnel(13,"**********","**********",1994,"**********");
        personnel1.setYearOfBirth(2006);
        DBProcesses.updatePersonel(personnel1);
    }
}

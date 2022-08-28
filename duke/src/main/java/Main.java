import duke.Duke;

public class Main {
    public static void main(String[] args) {
        String fileName = "duke.txt";
        Duke duke = new Duke(fileName);
        duke.run();
    }
}

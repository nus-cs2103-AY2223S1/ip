import java.util.Scanner;

public class Duke {
    private Scanner inputScanner;

    public static final String PATH = "./data/";

    public Duke() {}

    public void run() {
        Scanner scanner = new Scanner(System.in);
        Ui ui = new Ui(scanner);
        ui.userInterface();

    }


}

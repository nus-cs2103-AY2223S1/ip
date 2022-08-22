import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private ArrayList<Task> taskList;
    private Scanner scanner;

    public Duke() {
        this.taskList = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        Ui ui = new Ui(scanner);
        ui.userInterface();

    }


}

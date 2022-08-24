import java.util.Scanner;
import duke.task.*;
import duke.helper.*;

public class Duke {
    /**
     * Main class used to handle inputs
     */
    private TaskList list;
    private Storage storage;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        list = new TaskList(storage.load());
    }

    /**
     * This method runs the duke program
     */
    public void run() {
        Ui.welcome();

        String in = "";
        Scanner sc = new Scanner(System.in);

        while (true) {
            in = sc.nextLine();
            if (in.equals("bye")) {
                break;
            } else {
                Parser.parse(in, list);
            }
        }
        Ui.bye();
    }
    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}

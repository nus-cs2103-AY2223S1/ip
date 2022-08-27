package Duke;
import java.util.Scanner;


/**
 * Duke is the main class that will save and run the program
 *
 * @author Fang Yiye
 */
public class Duke {
    private TaskList taskList;
    private static Ui ui;
    private Storage storage;
    private String filePath;
    private static Scanner sc;


    /**
     * Constructor of Duke to initialise ui, storage and scanner
     *
     */
    public Duke() {
        ui = new Ui();
        this.sc = new Scanner(System.in);
        this.taskList = new TaskList();
        filePath = "/Users/yiye/Desktop/cs2103Projects/ip/Data/duke.txt";
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            ui.loadingError();
            taskList = new TaskList();
        }
    }

    public static void main(String[] args) throws DukeException {
        Duke duke = new Duke();
        Parser parser = new Parser(duke, ui);
        duke.run(parser);
    }


    /**
     * run the program
     *
     * @param parser
     * @throws DukeException by the parser
     */
    public void run(Parser parser) throws DukeException {
        ui.greet();
        while (sc.hasNextLine()) {
            parser.parse(sc.nextLine());
            storage.writeTasks(taskList);
        }
    }
}

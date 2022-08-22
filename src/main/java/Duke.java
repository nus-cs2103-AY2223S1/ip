import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;

public class Duke {
    private static ArrayList<Task> inputs = new ArrayList<>();
//  private static Storage storage = new Storage("./data/duke.txt");
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor for a Duke instance.
     *
     * @param filePath the path to the file for data storage
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        this.taskList = new TaskList(this.storage.getTasks(), this.ui, this.storage);
        this.ui.updateTaskList(this.taskList);
    }

    /**
     * The main application.
     */
    public void run() {
        this.ui.printGreeting();

        boolean exit = false;
        while (!exit) {
            try {
                exit = this.ui.handleInput();
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }


    /**
     * The main program loop.
     *
     * @param args
     */
    public static void main(String[] args) {
        Duke duke = new Duke("./data/duke.txt");
        duke.run();
    }
}

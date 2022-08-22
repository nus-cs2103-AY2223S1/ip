package duke;

import java.util.ArrayList;

/**
 * This class encapsulates the Duke chatbot.
 *
 * @author Andrew Lo Zhi Sheng
 * @version CS2103T AY22/23 Semester 1
 */
public class Duke {
    private static ArrayList<Task> inputs = new ArrayList<>();
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor for a duke.Duke instance.
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
        String filePath = System.getProperty("user.home") + "/duke.txt";
        Duke duke = new Duke(filePath);
        duke.run();
    }
}

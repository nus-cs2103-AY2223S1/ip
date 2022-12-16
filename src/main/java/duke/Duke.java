package duke;
import duke.command.Command;
import duke.exception.DukeException;

/**
 * Class to encapsulate Task monitoring program in Duke object.
 */
public class Duke {
    private static String filePath = "data/list.txt";
    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    private Parser parser;
    private TaskList newlyAddedTasks;

    /**
     * Constructor for Duke.
     *
     * The file path where file to read from /write to is stored.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList();
        storage.loadStorage(filePath, tasks);
        parser = new Parser();
        newlyAddedTasks = new TaskList();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    private void run() {
        ui.showWelcome();


        boolean isExit = false;
        while (!isExit) {
            String fullInput = ui.readCommand();
            try {
                Command command = parser.parse(fullInput);
                String reply = command.execute(tasks, ui, storage, newlyAddedTasks);
                Ui.printString(reply);
                ui.printBlankLine();
                isExit = command.isExit();
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
        storage.writeFromTaskListToFile(filePath, tasks, false);
        assert isExit : "You should be exiting the program";

    }

    /**
     * Get Duke's response to user input.
     * @param input User's input.
     * @return Duke's response.
     */
    public String getResponse(String input) {
        try {
            return parser.parse(input).execute(tasks, ui, storage, newlyAddedTasks);
        } catch (DukeException e) {
            return e.getMessage();
        }

    }

    /**
     * Write the tasks in the archive and file storing list of tasks.
     */
    public void writeAndCloseFile() {
        storage.writeFromTaskListToFile(filePath, tasks, false);
        storage.writeFromTaskListToFile("data/archive.txt", newlyAddedTasks, true);
    }

}

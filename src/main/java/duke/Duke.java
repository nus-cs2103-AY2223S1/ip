package duke;
import duke.command.Command;
import duke.exception.DukeException;

/**
 * Class to simulate the entire program.
 */
public class Duke {
    private static String filePath = "data/list.txt";
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    /**
     * Constructor for Duke.
     *
     * @param filePath The file path where file to read from /write to is stored.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList();
        storage.loadStorage(filePath, tasks);

    }

    public static void main(String[] args) {
        new Duke(Duke.filePath).run();
    }

    private void run() {
        ui.showWelcome();
        Parser parser = new Parser();


        boolean isExit = false;
        while (!isExit) {
            String fullInput = ui.readCommand();
            try {
                Command command = parser.parse(fullInput);
                command.execute(tasks, ui, storage);
                ui.printBlankLine();
                isExit = command.isExit();
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
        storage.writeToTaskList(filePath, tasks);
        System.out.println("Bye. Hope to see you again soon!");
    }
}

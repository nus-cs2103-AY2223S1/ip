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
                String reply = command.execute(tasks, ui, storage);
                Ui.printString(reply);
                ui.printBlankLine();
                isExit = command.isExit();
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
        assert isExit : "You should be exiting the program";
        storage.writeToTaskList(filePath, tasks);
    }

    public String getResponse(String input) {
        try {
            return parser.parse(input).execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }

    }

}

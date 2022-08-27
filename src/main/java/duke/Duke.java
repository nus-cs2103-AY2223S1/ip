package duke;

import duke.command.Command;
import duke.command.LoadDataCommand;
import duke.command.SaveDataCommand;
import duke.command.WelcomeCommand;

/**
 * Represents a Duke bot. A Duke object contains its storage, task list, ui, parser, folder path and file path.
 */
public class Duke {

    /** Storage to handle file loading and saving */
    private Storage storage;
    /** Task list to handle task related functionalities */
    private TaskList tasks;
    /** Ui to handle interactions with the user */
    private Ui ui;
    /** Parser to parse the user inputs */
    private Parser parser;
    /** Folder path of the data file */
    private String folderPath;
    /** File path of the data file */
    private String filePath;

    /**
     * Creates a Duke object.
     *
     * @param folderPath folder path of the data file.
     * @param filePath file path of the data file.
     */
    public Duke(String folderPath, String filePath) {
        this.storage = new Storage();
        this.tasks = new TaskList();
        this.ui = new Ui();
        this.parser = new Parser();
        this.folderPath = folderPath;
        this.filePath = filePath;
    }

    /**
     * Runs the functionalities of the Duke bot.
     */
    public void run() {
        try {
            new LoadDataCommand(folderPath, filePath).execute(tasks, ui, storage);
            new WelcomeCommand().execute(tasks, ui, storage);
            while (ui.hasNext()) {
                try {
                    String inputs = ui.readCommand();
                    Command command = parser.parse(inputs);
                    command.execute(tasks, ui, storage);
                    new SaveDataCommand(filePath).execute(tasks, ui, storage);
                } catch (DukeException e) {
                    ui.showError(e.getMessage());
                }
            }
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Main function to run Duke.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        Duke duke = new Duke("./data", "./data/duke.txt");
        duke.run();
    }
}

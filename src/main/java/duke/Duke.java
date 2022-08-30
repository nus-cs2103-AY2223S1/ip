package duke;

import duke.command.Command;

/**
 * Main class that is executed.
 */
public class Duke {

    public static final String FOLDER_LOCATION = "data";
    public static final String FILE_LOCATION = "data/duke.txt";
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Creates a new Duke Object, initializing Ui, Storage and TaskList.
     *
     * @param filePath Path to save file.
     * @param folderPath Path to folder containing save file.
     */
    public Duke(String filePath, String folderPath) {
        ui = new Ui();
        storage = new Storage(filePath, folderPath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.print(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Executes main loop of program.
     */
    public void run() {

        ui.printGreetings();

        while (true) {
            String fullCommand = ui.readCommand();
            try {
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                if (c.isExit()) {
                    break;
                }
                storage.save(tasks);
            } catch (Exception e) {
                ui.print(e.getMessage());
            }
        }
        ui.close();
    }

    /**
     * Main method for Duke.
     *
     * @param args Command Line arguments.
     */
    public static void main(String[] args) {
        new Duke(FILE_LOCATION, FOLDER_LOCATION).run();
    }
}

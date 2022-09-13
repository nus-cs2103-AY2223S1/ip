package duke;


import duke.command.Command;
import duke.oop.Parser;
import duke.oop.Storage;
import duke.oop.TaskList;
import duke.oop.Ui;

public class Duke {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Initializes the Duke chatbot.
     *
     * @param filePath Specifies the path to save the content.
     */

    public Duke(String filePath) {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage(filePath);
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, storage, ui);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Starts the program
     */
    public void run() {
        ui.greetings();
        boolean isExit = false;
        assert this.storage != null && this.tasks != null && this.ui != null : "The Duke object is not initialized!";

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                ui.printMessage(command.execute(tasks, storage, ui));
                isExit = command.isExit;
            } catch (DukeException e) {
                ui.printMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("src/main/java/duke.txt").run();
    }
}

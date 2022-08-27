package duke;

import command.Command;
import exception.DukeException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * <h1>Duke class</h1>
 * Main class of the chat bot that links the
 * Storage, TaskList and Ui together.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a Duke object.
     *
     * @param folderPath folder path the output is to be saved at.
     * @param filename file name the output is to be saved as.
     */
    public Duke(String folderPath, String filename) {
        storage = new Storage(folderPath, filename);
        tasks = new TaskList();
        ui = new Ui();
    }

    private void startChatBot() {
        ui.greet();
        try {
            tasks = new TaskList(storage.readSavedTasks());
        } catch (DukeException e) {
            ui.sayErrorMessage(e.getMessage());
        }
        Parser parser = new Parser(tasks, ui);
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = parser.parse(fullCommand);
                command.execute();
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.sayErrorMessage(e.getMessage());
            }
        }
        try {
            storage.writeToFile(tasks);
            ui.sayGoodbye();
        } catch (DukeException e) {
            ui.sayErrorMessage(e.getMessage());
        }
    }

    /**
     * Main class to run the chat bot.
     *
     * @param args
     */
    public static void main(String[] args) {
        Duke uncleCheong = new Duke("data", "duke.txt");
        uncleCheong.startChatBot();
    }
}

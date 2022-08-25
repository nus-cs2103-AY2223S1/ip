package duke;

import java.util.ArrayList;
import java.util.List;

import duke.command.Command;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the main functions of Duke chatbot and initializes require variables.
 */
public class Duke {
    private List<Task> list = new ArrayList<>();

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initialises Duke chatbot.
     * @param filePath directory of where tasks are saved.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            System.out.println("list size-" + tasks.listSize());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * Runs the interaction between user and Duke chatbot.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}

package duke;

import java.util.ArrayList;

import duke.commands.Command;
import duke.tasks.Task;

/**
 * duke.Duke Program for tracking Tasks.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * duke.Duke constructor.
     *
     * @param filePath filePath of file to store Task information.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            ArrayList<Task> taskList = storage.loadTaskList();
            tasks = new TaskList(taskList, storage.generateTaskListQuickFind(taskList));
        } catch (DukeException e) {
            ui.printError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Parses user input to String.
     *
     * @param input user input.
     * @return String of command output or error.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String output = c.execute(tasks, ui, storage);
            return output;
        } catch (DukeException e) {
            return ui.printError(e);
        }
    }
}

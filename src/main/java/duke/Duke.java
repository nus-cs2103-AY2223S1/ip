package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * The main class of the Duke application.
 *
 * @author Elbert Benedict
 */
public class Duke {
    private TaskList taskList;

    /**
     * Contructs a new Duke instance.
     */
    public Duke() {
        try {
            taskList = Storage.getSavedTasks();
        } catch (DukeException e) {
            taskList = new TaskList();
        }
    }

    /**
     * Returns response messsage for user input.
     *
     * @param input the user input.
     * @return response message.
     */
    public String getResponse(String input) {
        try {
            Command parsed = Parser.parseUserInput(input);
            return parsed.executeAndGetResponse(taskList);
        } catch (DukeException e) {
            return Ui.getDukeErrorMessage(e);
        }
    }
}

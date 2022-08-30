package duke;

import duke.command.Command;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Main class of Duke, a personal assistant chatbot.
 */
public class Duke {
    public static final String PATH_TO_DATA_DIRECTORY = "./data/";
    public static final String TASK_LIST_STORAGE_NAME = "duke.txt";
    private final Storage storage;
    private TaskList taskList;

    /**
     * Constructor for {@code Duke}.
     */
    public Duke() {
        storage = new Storage(PATH_TO_DATA_DIRECTORY, TASK_LIST_STORAGE_NAME);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            Ui.showError(e);
            taskList = new TaskList();
        }
        Command.setTaskList(taskList);
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parseCommand(input);
            String message = c.execute();
            storage.writeTaskListToStorage(taskList);
            return message;
        } catch (DukeException e) {
            return Ui.showError(e);
        }
    }

    public boolean isExitCommand(String input) {
        try {
            Command c = Parser.parseCommand(input);
            return c.isExit();
        } catch (DukeException e) {
            return false;
        }
    }
}

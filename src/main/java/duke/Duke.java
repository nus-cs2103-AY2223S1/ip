package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * A chat bot.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor for <code>Duke</code>
     * @param filePath
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.getResponse(tasks, storage);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public void exit() {
        storage.storeTasks(tasks);
    }

    public static void main(String[] args) {
    }
}

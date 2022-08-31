package duke;

import duke.command.Command;
import duke.command.Parser;
import duke.exception.DukeException;
import duke.gui.Response;
import duke.gui.ResponseType;
import duke.task.TaskList;

/**
 * Main class of Duke.
 */
public class Duke {
    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;

    /**
     * Constructs a duke chatbot.
     */
    public Duke() throws DukeException {
        storage = new Storage("data", "duke.txt");
        ui = new Ui();
        try {
            tasks = new TaskList(storage.retrieveFile());
        } catch (DukeException e) {
            tasks = new TaskList();
            throw e;
        }
    }

    public String greet() {
        return String.format("Hello, I'm Duke! %s What can I do for you?", tasks.numberOfTasks());
    }

    public static Response reply(String userInput) {
        try {
            Command cmd = Parser.parse(userInput);
            String message = cmd.execute(tasks, storage);

            if (cmd instanceof duke.command.ExitCommand) {
                return new Response(ResponseType.QUIT, message);
            } else {
                return new Response(ResponseType.STANDARD, message);
            }
        } catch (DukeException e) {
            return new Response(ResponseType.ERROR, e.getMessage());
        }
    }
}

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
    public Duke() {
        storage = new Storage("data", "duke.txt");
        ui = new Ui();
        tasks = new TaskList(storage.retrieveFile());
    }

    public String greet() {
        return String.format("blob blob, it’s bobo the bot; %s ", tasks.numberOfTasks());
    }

    public static Response reply(String userInput) {
        try {
            Command cmd = Parser.parse(userInput);
            Response response = cmd.execute(tasks, storage);
            return response;
        } catch (DukeException e) {
            return new Response(ResponseType.ERROR, e.getMessage());
        }
    }
}

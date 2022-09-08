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

    /**
     * Constructs a duke chatbot.
     */
    public Duke() {
        storage = new Storage("data", "duke.txt");
        tasks = new TaskList(storage.retrieveFile());
    }

    /**
     * Greets the user upon the start of the program.
     *
     * @return Greeting and task overview string.
     */
    public String greet() {
        return String.format("blob blob, it’s bobo the bot; %s ", tasks.numberOfTasks());
    }

    /**
     * Replies to a user input by parsing and executing the user command.
     *
     * @param userInput The user input.
     * @return A Response from parsing and executing the user input command.
     */
    public static Response<?> reply(String userInput) {
        try {
            Command cmd = Parser.parse(userInput);
            Response<?> response = cmd.execute(tasks, storage);
            return response;
        } catch (DukeException e) {
            return new Response<>(ResponseType.ERROR, e.getMessage());
        }
    }
}

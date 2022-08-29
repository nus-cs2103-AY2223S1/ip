package duke;

import duke.command.Command;
import duke.command.WelcomeCommand;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Duke is the class that represents the chat-bot.
 */
public class Duke {
    private static final String TASKS_STORAGE_PATH = "./data/duke.txt";
    private TaskList taskList;


    /**
     * Constructs a new Duke object.
     */
    public Duke() {
        this.taskList = new TaskList(TASKS_STORAGE_PATH);
    }


    /**
     * Returns the welcome message string.
     *
     * @return The String to greet the user.
     */
    public static String greetUser() {
        try {
            Command welcomeCommand = new WelcomeCommand();
            Response response = welcomeCommand.action();
            return response.toString();
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Performs an action in response to the command and return the response.
     *
     * @return The Response to be displayed.
     */
    public Response getResponse(String input) {
        try {
            Command command = Parser.parseCommand(input, taskList);
            Response response = command.action();
            taskList.saveTasks();
            return response;
        } catch (DukeException e) {
            return new Response(e.getMessage());
        }
    }
}

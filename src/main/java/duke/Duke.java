package duke;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.WelcomeCommand;
import duke.exception.DukeException;
import duke.task.TasksList;


/**
 * Represents the chatbot.
 */
public class Duke {
    private static final String TASKS_STORAGE_PATH = "./data/duke.txt";
    private TasksList tasksList;

    /**
     * Creates a new Duke instance.
     */
    public Duke() {
        this.tasksList = new TasksList(Duke.TASKS_STORAGE_PATH);
    }

    /**
     * Returns the welcome message string.
     * @return The welcome message.
     */
    public static String greetUser() {
        try {
            Command welcomeCommand = new WelcomeCommand();
            Response welcomeResponse = welcomeCommand.execute();
            return welcomeResponse.toString();
        } catch (DukeException exception) {
            return exception.getMessage();
        }
    }

    /**
     * Perform an execution based on the command and return the corresponding response.
     *
     * @param userInput The input by the user.
     * @return The corresponding response to the command.
     */
    public Response getResponse(String userInput) {
        try {
            Command command = Parser.handleCommand(userInput, this.tasksList);
            Response response = command.execute();
            tasksList.saveTasks();

            return response;
        } catch (DukeException exception) {
            return new Response(exception.getMessage());
        }
    }
}


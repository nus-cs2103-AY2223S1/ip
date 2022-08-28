package skylark.skylark;

import java.io.File;

import skylark.command.Command;
import skylark.task.TaskList;

/** Represents the main entry point of Skylark. */
public class Skylark {

    /** Text that is shown to the user when they first access Skylark. */
    public static final String TEXT_HELLO = "Hello, I am Skylark, how can I help you today?";

    /** Represents the TaskList object that stores the list of Tasks created by the user. */
    private final TaskList taskList;

    /**
     * Represents a Skylark object. <br><br>
     * Initialises the scanner to retrieve user input from the command line,
     * and creates a new TaskList object to read the current list of the tasks stored in a file path
     * specified. <br><br>
     * Prints a hello message to the user.
     */
    public Skylark() {
        String filePath = System.getProperty("user.dir")
                + File.separator + "data" + File.separator + "text.txt";
        this.taskList = new TaskList(filePath);
    }

    /**
     * Parses the command input and creates the relevant Command object. <br><br>
     * Runs the command by executing the run function of the Command object.
     *
     * @param input Input from the user.
     * @param taskList TaskList object.
     * @return Result of the command.
     * @throws SkylarkException If there is an exception that occurred when running the command.
     */
    private String response(String input, TaskList taskList) throws SkylarkException {
        Command command = Command.createCommand(input);

        return command.run(taskList);
    }

    /**
     * Returns the response from the user after parsing the command.
     *
     * @param input Input from the user.
     * @return Result of the command.
     */
    public String getResponse(String input) {
        try {
            return response(input, this.taskList);
        } catch (SkylarkException skylarkException) {
            return skylarkException.toString();
        }
    }
}

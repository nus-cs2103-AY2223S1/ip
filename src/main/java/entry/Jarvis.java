package entry;

import commands.CommandResponse;
import commands.CommandRunner;
import exceptions.DukeException;
import exceptions.StorageException;
import input.Input;
import output.OutputLogger;
import task.TaskModel;

/**
 * Class that is a glue between main logic and UI classes
 */
public class Jarvis {
    private TaskModel taskModel;
    private CommandRunner cmdRunner;

    /**
     * Initialise Jarvis instance
     * @throws StorageException if there was any issue initialising storage
     */
    public Jarvis() throws StorageException {
        taskModel = new TaskModel();
        cmdRunner = new CommandRunner(taskModel);
    }

    /**
     * Returns a CommandResponse given the input message from the user
     * @param userInput Input message from the user
     * @return Appropriate response or error from a Command
     */
    public CommandResponse getResponse(String userInput) {
        try {
            CommandResponse res = cmdRunner.run(Input.newInput(userInput));
            return res;
        } catch (DukeException e) {
            return new CommandResponse(e.getMessage());
        }
    }

    /**
     * Returns the welcome message from the chatbot
     * @return Welcome message
     */
    public String getWelcome() {
        return OutputLogger.getIntroduction();
    }

    /**
     * Saves data to disk
     * @throws DukeException if there were issues saving data
     */
    public void save() throws DukeException {
        taskModel.save();
    }
}

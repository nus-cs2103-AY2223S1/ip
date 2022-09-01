package duke.command;

import duke.gui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Class which responds to an unknown command.
 */
public class UnknownCommand extends Command {

    private String userInput;

    /**
     * Public constructor that stores the input from the user.
     *
     * @param userInput the String input by the user
     */
    public UnknownCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the command which responds to an unknown command. Function does not alter anything else.
     *
     * @param ui the ui class that prints text in a readable format
     * @param storage the storage object which handles reading and writing of data
     * @param taskList the list of tasks currently stored
     * @return a String which replies to the user
     */
    @Override
    public String runCommand(Ui ui, Storage storage, TaskList taskList) {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}

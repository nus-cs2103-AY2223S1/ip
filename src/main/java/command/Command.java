package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * This class gets the specific command from the Parser class and
 * executes the specific command to do the tasks required
 */
public abstract class Command {
    private boolean isExit;

    /**
     * Constructor that initializes whether the program should exit or not
     */
    public Command() {
        this.isExit = false;
    }

    /**
     * Sets the program to exit after user uses the exit command
     */
    public void setExit() {
        this.isExit = true;
    }

    /**
     * Checks if the program should exit or not
     *
     * @return true if program should exit and false if program should not exit
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Executes the task according to the command made by the user in the child classes
     *
     * @param taskList which contains the current task list
     * @param ui which handles the user interface
     * @param storage which handles the saving and loading of file
     * @throws DukeException which handles all the errors that could possibly happen
     *         if the program could not do the task
     * @return string that will be printed in the UI
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}

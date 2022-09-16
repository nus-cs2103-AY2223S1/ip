package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Encapsulates the logic for the UnmarkCommand when the user wants to unmark a certain task as
 * not completed.
 *
 * @author bensohh
 * @version CS2103T AY 22/23 Sem 1 (G01)
 */
public class UnmarkCommand extends Command {
    private int taskNumber;

    /**
     * Creates an instance of UnmarkCommand.
     *
     * @param taskNumber Integer representing the task number to delete
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the UnmarkCommand by unmarking the task specified as not completed by removing
     * the 'X' symbol. Storing the updated tasks by writing to the txt file.
     *
     * @param tasks List that keeps track of the tasks added/removed
     * @param ui Ui that handles the interaction with user inputs
     * @param storage Storage that handles the writing/reading of data from a txt file
     * @return String that represents the response of ChatBot after executing the command
     * @throws DukeException if the specified task is invalid
     * @throws IOException if an error occurred while writing data to the txt file
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (this.taskNumber > tasks.size() || this.taskNumber < 1) {
            String errorMessage = "________________________________________\n"
                    + "OOPS!!! There is no such task number!\n"
                    + "________________________________________";
            throw new DukeException(errorMessage);
        }

        tasks.getTask(this.taskNumber - 1).markTaskUndone();
        storage.writeToFile(tasks);
        return this
                + "  "
                + tasks.getTask(this.taskNumber - 1).toString()
                + "\n________________________________________";
    }

    /**
     * Gives a String representation of successfully executing the UnmarkCommand.
     *
     * @return String to notify the user that the specified task has been unmarked
     */
    @Override
    public String toString() {
        return "________________________________________\n"
                + "Alright! I have marked this task as not done yet:\n";
    }

    /**
     * Checks if the Object o is the same as an instance of UnmarkCommand.
     *
     * @param o Object to be compared against an instance of UnmarkCommand
     * @return true if the Object is an instance of UnmarkCommand and both have the same task number, else
     *     return false
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o instanceof UnmarkCommand) {
            UnmarkCommand t = (UnmarkCommand) o;
            return t.taskNumber == this.taskNumber;
        }

        return false;
    }
}

package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;


/**
 * Encapsulates the logic for the MarkCommand when the user wants to mark a certain task as done.
 */
public class MarkCommand extends Command {
    private int taskNumber;

    /**
     * Constructor to create an instance of MarkCommand.
     *
     * @param taskNumber Integer representing the task number to mark
     */
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the MarkCommand by marking the task specified as completed with an 'X'.
     * Stores the updated tasks by writing to the txt file.
     *
     * @param tasks List that keeps track of the tasks added/removed
     * @param ui Ui that handles the interaction with user inputs
     * @param storage Storage that handles the writing/reading of data from a txt file
     * @throws DukeException if the specified task is invalid
     * @throws IOException if an error occurred while writing data to the txt file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (this.taskNumber > tasks.size() || this.taskNumber < 1) {
            String errorMessage = "__________________________________________________\n"
                    + "OOPS!!! There is no such task number!";
            throw new DukeException(errorMessage);
        }

        tasks.getTask(this.taskNumber - 1).markTaskDone();
        System.out.println(this);
        System.out.println("  " + tasks.getTask(this.taskNumber - 1).toString());
        storage.writeToFile(tasks);
    }

    /**
     * Checks if it is the exit command in order to exit loop.
     *
     * @return false since a MarkCommand does not end the ChatBot
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * String representation of successfully executing the MarkCommand.
     *
     * @return String to notify the user that the specified task has been marked
     */
    @Override
    public String toString() {
        return "__________________________________________________\n"
                + "Good Job! I have marked this task as done:";
    }

    /**
     * Overridden equals method to check if the Object o is the same as an instance of MarkCommand.
     *
     * @param o Object to be compared against an instance of MarkCommand
     * @return true if the Object is an instance of MarkCommand and both have the same task number, else
     *     return false
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o instanceof MarkCommand) {
            MarkCommand t = (MarkCommand) o;
            return t.taskNumber == this.taskNumber;
        }

        return false;
    }
}

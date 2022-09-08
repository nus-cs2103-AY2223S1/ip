package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Encapsulates the logic behind deleting a task from the TaskList.
 *
 * @author bensohh
 * @version CS2103T AY 22/23 Sem 1 (G01)
 */
public class DeleteCommand extends Command {
    private int taskNumber;

    /**
     * Constructor to create an instance of DeleteCommand.
     *
     * @param taskNumber Integer representing the task number to delete
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the DeleteCommand and removes the corresponding task from the TaskList.
     * Stores the updated TaskList into a txt file under data/tasks.txt
     *
     * @param tasks List that keeps track of the tasks added/removed
     * @param ui Ui that handles the interaction with user inputs
     * @param storage Storage that handles the writing/reading of data from a txt file
     * @return String that represents the response of ChatBot after executing the command
     * @throws DukeException if the TaskList is empty or an invalid task number in user's input
     * @throws IOException if an error occurs while writing to a txt file
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        //Exception: Throw an error when user tries to delete from an empty list
        if (tasks.isEmpty()) {
            String errorMessage = "________________________________________\n"
                    + "OOPS!!! There are no task left to be deleted!\n"
                    + "________________________________________";
            throw new DukeException(errorMessage);
        }

        //Exception: Throw an error when the second half after "delete" keyword is greater than task_list
        if (taskNumber > tasks.size() || taskNumber < 1) {
            String errorMessage = "________________________________________\n"
                    + "OOPS!!! There is no such task number!\n"
                    + "________________________________________";;
            throw new DukeException(errorMessage);
        }
        String taskDescription = tasks.getTask(taskNumber - 1).toString();
        String respondMessage = this
                + "  "
                + taskDescription
                + "\n Now you have "
                + (tasks.size() - 1)
                + " tasks in the list."
                + "\n________________________________________";
        Parser.setTaskDescription(taskDescription);
        tasks.delete(this.taskNumber - 1);
        storage.writeToFile(tasks);
        return respondMessage;
    }

    /**
     * String representation of successfully executing the DeleteCommand.
     *
     * @return String to notify the user that a task has been removed
     */
    @Override
    public String toString() {
        return "________________________________________\n"
                + "Noted. I have removed this task:\n";
    }

    /**
     * Overridden equals method to check if 2 instances of DeleteCommand are the same.
     *
     * @param o Object to be compared against an instance of DeleteCommand
     * @return true if both are instances of DeleteCommand and they contain the same task number else false
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o instanceof DeleteCommand) {
            DeleteCommand t = (DeleteCommand) o;
            return t.taskNumber == this.taskNumber;
        }

        return false;
    }
}

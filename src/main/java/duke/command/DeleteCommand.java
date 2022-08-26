package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * DeleteCommand encapsulates the logic behind deleting a task from the TaskList.
 */
public class DeleteCommand extends Command {
    private int taskNumber;

    /**
     * Constructor to create an instance of DeleteCommand.
     *
     * @param taskDetails a String representation containing details about the task
     * @return an instance of DeleteCommand
     */
    public DeleteCommand(String taskDetails) {
        this.taskNumber = Integer.parseInt(taskDetails);
    }

    /**
     * Executes the DeleteCommand and removes the corresponding task from the TaskList.
     * The updated TaskList is then stored into a txt file under data/tasks.txt
     *
     * @param tasks a list that keeps track of the tasks added/removed
     * @param ui ui that handles the interaction with user inputs
     * @param storage storage that handles the writing/reading of data from a txt file
     * @throws DukeException if the TaskList is empty or an invalid task number in user's input
     * @throws IOException if an error occurs while writing to a txt file
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        //Exception: Throw an error when user tries to delete from an empty list
        if (tasks.isEmpty()) {
            String errorMessage = "__________________________________________________\n"
                    + "OOPS!!! There are no task left to be deleted!\n"
                    + "__________________________________________________";
            throw new DukeException(errorMessage);
        }

        //Exception: Throw an error when the second half after "delete" keyword is greater than task_list
        if (taskNumber > tasks.size() || taskNumber < 1) {
            String errorMessage = "__________________________________________________\n"
                    + "OOPS!!! There is no such task number!\n"
                    + "__________________________________________________";
            throw new DukeException(errorMessage);
        }
        System.out.println(this);
        System.out.println("  " + tasks.getTask(taskNumber - 1).toString());
        tasks.delete(this.taskNumber - 1);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        storage.writeToFile(tasks);
    }

    /**
     * Check if it is the exit command in order to exit loop
     *
     * @return false since a DeleteCommand does not end the ChatBot
     */
    public boolean isExit() {
        return false;
    }

    /**
     * A String representation of successfully executing the DeleteCommand.
     *
     * @return a String to notify the user that a task has been removed
     */
    @Override
    public String toString() {
        return "__________________________________________________\n"
                + "Noted. I have removed this task:";
    }

    /**
     * Overridden equals method to check if 2 instances of DeleteCommand are the same.
     *
     * @param o Object o be compared against an instance of DeleteCommand
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

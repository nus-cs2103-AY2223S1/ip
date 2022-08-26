package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Insert Javadocs
 */
public class DeleteCommand extends Command {
    private int taskNumber;

    public DeleteCommand(String taskDetails) {
        this.taskNumber = Integer.parseInt(taskDetails);
    }

    /**
     * Insert Javadocs
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

    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return "__________________________________________________\n"
                + "Noted. I have removed this task:";
    }

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

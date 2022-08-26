package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;



/**
 * Insert Javadocs
 */
public class UnmarkCommand extends Command {
    private int taskNumber;
    public UnmarkCommand(String taskDetails) {
        this.taskNumber = Integer.parseInt(taskDetails);
    }

    /**
     * Insert Javadocs
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (this.taskNumber > tasks.size() || this.taskNumber < 1) {
            String errorMessage = "__________________________________________________\n"
                    + "OOPS!!! There is no such task number!";
            throw new DukeException(errorMessage);
        }

        tasks.getTask(this.taskNumber - 1).markTaskUndone();
        System.out.println(this);
        System.out.println("  " + tasks.getTask(this.taskNumber - 1).toString());
        storage.writeToFile(tasks);
    }

    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return "__________________________________________________\n"
                + "Alright! I have marked this task as not done yet:";
    }

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

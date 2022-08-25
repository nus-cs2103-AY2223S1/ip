package duke.command;

import duke.util.Storage;
import duke.util.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;

import java.io.IOException;

public class UnmarkCommand extends Command {
    private int taskNumber;
    public UnmarkCommand(String taskDetails) {
        this.taskNumber = Integer.parseInt(taskDetails);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (this.taskNumber > tasks.size() || this.taskNumber < 1) {
            String error_msg = "__________________________________________________\n" +
                    "OOPS!!! There is no such task number!";
            throw new DukeException(error_msg);
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
                + "Alright! I have marked this task as not done yet:" ;
    }
}

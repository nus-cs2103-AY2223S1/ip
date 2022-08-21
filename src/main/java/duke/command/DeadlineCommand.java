package duke.command;

import duke.exception.InvalidDescriptionException;
import duke.exception.InvalidTimeException;
import duke.task.TaskList;
import duke.task.Deadline;
import duke.Ui;
import duke.Storage;

public class DeadlineCommand extends Command {
    String desc;
    String time;

    public DeadlineCommand(String desc, String time) throws InvalidDescriptionException, InvalidTimeException {
        if (desc.isEmpty()) {
            throw new InvalidDescriptionException();
        }
        if (time.isEmpty()) {
            throw new InvalidTimeException();
        }

        this.desc = desc;
        this.time = time;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Deadline deadline = new Deadline(this.desc, this.time);
        taskList.addTask(deadline);
        storage.saveTaskFile(taskList);
    }
}

package seedu.duke.command;

import seedu.duke.Duke;
import seedu.duke.DukeException;
import seedu.duke.TaskList;
import seedu.duke.Ui.Ui;
import seedu.duke.task.DeadlineTask;
import seedu.duke.task.EventTask;
import seedu.duke.task.Task;

public class EditTimeCommand extends Command {
    private int index;
    private String newTime;

    public EditTimeCommand(int index, String newTime) {
        this.index = index;
        this.newTime = newTime;
    }

    @Override
    public String execute(TaskList list) throws DukeException {
        Task task = list.get(index);
        String type = task.getType();
        if (type.equals("[D]")) {
            DeadlineTask deadline = (DeadlineTask) task;
            deadline.editTime(newTime);
        } else if (type.equals("[E]")) {
            EventTask event = (EventTask) task;
            event.editTime(newTime);
        } else {
            throw new DukeException("This task does not have a date to edit!");
        }
        return Ui.timeEdited(index + 1, task);
    }
}

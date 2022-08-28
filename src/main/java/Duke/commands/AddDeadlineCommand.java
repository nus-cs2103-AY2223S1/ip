package Duke.commands;

import Duke.exceptions.DukeException;
import Duke.storage.Storage;
import Duke.tasks.Deadline;
import Duke.tasks.TaskList;
import Duke.ui.UI;

public class AddDeadlineCommand extends Command {

    private final String input;

    public AddDeadlineCommand(String input) throws DukeException {
        if (!checkValid(input)) throw new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
        if (!input.contains("/by")) throw new DukeException(" ☹ OOPS!!! Please enter in the format : \n" +
                "deadline {task description} /by {date/time}");
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        String taskDesc = input.substring(9, input.indexOf('/') - 1);
        String date = input.substring(input.indexOf('/') + 3);
        Deadline deadline = new Deadline(taskDesc, date);
        taskList.addTask(deadline);
        ui.addTaskMessage(deadline, taskList.size());
        storage.store(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

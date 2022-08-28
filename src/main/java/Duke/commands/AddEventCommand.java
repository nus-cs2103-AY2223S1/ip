package Duke.commands;

import Duke.exceptions.DukeException;
import Duke.storage.Storage;
import Duke.tasks.Event;
import Duke.tasks.TaskList;
import Duke.ui.UI;

import java.time.LocalDateTime;

public class AddEventCommand extends Command {

    private final String input;

    public AddEventCommand(String input) throws DukeException {
        if (!checkValid(input))
            throw new DukeException(" ☹ OOPS!!! The description of a event cannot be empty.");
        if (!input.contains("/at")) throw new DukeException(" ☹ OOPS!!! Please enter in the format : \n" +
                "     event {task description} /at {day / date : YYYY-MM-DD / time : no spaces}");
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        String taskDesc = input.substring(6, input.indexOf('/') - 1);
        String deadline = input.substring(input.indexOf('/') + 3);
        String[] deadlineArray = deadline.split(" ");
        if (isDate(deadlineArray[0])) {
            LocalDateTime ld = LocalDateTime.parse(deadlineArray[0]);
            deadline = ld.format(DTF) + "  " + ld.getDayOfWeek() + " " + deadlineArray[1];
        }
        Event event = new Event(taskDesc, deadline);
        taskList.addTask(event);
        ui.addTaskMessage(event, taskList.size());
        storage.store(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

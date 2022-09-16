package duke.command;

import duke.oop.Storage;
import duke.oop.TaskList;
import duke.oop.Ui;
import duke.task.Event;

public class AddEventCommand extends Command{

    private String eventTask;
    private String eventTime;

    public AddEventCommand(String eventTask, String eventTime) {
        this.eventTask = eventTask;
        this.eventTime = eventTime;
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        Event newEvent = new Event(eventTask, eventTime);
        taskList.getTasks().add(newEvent);
        storage.update((taskList.getTasks()));
        return "Got it. I've added this task:\n" + newEvent.toString()
                + "\nNow you have" + taskList.getTasks().size() + " tasks in the list.";
    }
}

package Command;

import duke.*;

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
        return "\"Got it. I've added this task:\" + \"\\n\" + tasks.get(numOfTasks - 1).toString()\n" +
                "                \"\\n\" + \"Now you have \" + numOfTasks + \" tasks in the list.\"";
    }
}

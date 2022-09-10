package bobthebot.command;

import bobthebot.tasks.Event;
import bobthebot.tasks.Task;
import bobthebot.tasks.ToDoList;
import bobthebot.utils.Ui;

public class EventCommand extends Command {
    private String taskName;
    private String eventDate;
    private ToDoList list;

    public EventCommand(String taskName, String eventDate, ToDoList list) {
        super("event");
        this.taskName = taskName;
        this.eventDate = eventDate;
        this.list = list;
    }

    @Override
    public String execute() {
        Task event = new Event(taskName, eventDate);
        list.addTask(event);
        return Ui.taskAddedMessage(event, list);
    }
}

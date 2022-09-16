package bestie.command;

import bestie.tasks.Event;
import bestie.tasks.Task;
import bestie.tasks.ToDoList;
import bestie.utils.Ui;

/**
 * Event Command class representing event command executed by the user.
 */
public class EventCommand extends Command {
    private String taskName;
    private String eventDate;
    private ToDoList list;

    /**
     * Constructs event command.
     */
    public EventCommand(String taskName, String eventDate, ToDoList list) {
        super("event");
        this.taskName = taskName;
        this.eventDate = eventDate;
        this.list = list;
    }

    /**
     * Executes the event command by creating a new event instance and adding it to the list.
     *      Gives the user information about the event added.
     *
     * @return String representing the information about the added event.
     */
    @Override
    public String execute() {
        Task event = new Event(taskName, eventDate);
        list.addTask(event);
        return Ui.taskAddedMessage(event, list);
    }
}

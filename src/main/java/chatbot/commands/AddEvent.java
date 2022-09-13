package chatbot.commands;

import java.time.LocalDate;

import chatbot.tasks.Task;
import chatbot.tasks.TaskList;
import chatbot.ui.Response;
import chatbot.ui.UI;

/**
 * Represents the command to be executed by the chatbot which adds
 * an Event type task to the todo list.
 */
public class AddEvent implements Command {
    private String taskName;
    private LocalDate date;
    private String[] tags;

    /**
     * The constructor of the AddEvent command.
     *
     * @param taskName Name of the event
     * @param date Date of the event.
     */
    public AddEvent(String taskName, LocalDate date, String[] tags) {
        this.taskName = taskName;
        this.date = date;
        this.tags = tags;
    }

    @Override
    public void execute(TaskList todos, UI ui) {
        Task event = todos.addEvent(this.taskName, this.date, this.tags);
        ui.add(event, todos.getNumberOfTasks());
    }

    @Override
    public String execute(TaskList todos, Response resp) {
        Task event = todos.addEvent(this.taskName, this.date, this.tags);
        return resp.add(event, todos.getNumberOfTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

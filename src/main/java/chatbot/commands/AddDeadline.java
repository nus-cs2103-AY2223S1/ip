package chatbot.commands;

import java.time.LocalDate;

import chatbot.tasks.Task;
import chatbot.tasks.TaskList;
import chatbot.ui.Response;
import chatbot.ui.UI;

/**
 * Represents the command to be executed by the chatbot which adds
 * a Deadline type task to the todo list.
 */
public class AddDeadline implements Command {
    private String taskName;
    private LocalDate date;
    private String[] tags;

    /**
     * The constructor of the AddDeadline command.
     *
     * @param taskName Name of the Deadline type task.
     * @param date Date of the deadline.
     */
    public AddDeadline(String taskName, LocalDate date, String[] tags) {
        this.taskName = taskName;
        this.date = date;
        this.tags = tags;
    }

    @Override
    public void execute(TaskList todos, UI ui) {
        Task deadline = todos.addDeadline(this.taskName, this.date, this.tags);
        ui.add(deadline, todos.getNumberOfTasks());
    }

    @Override
    public String execute(TaskList todos, Response resp) {
        Task deadline = todos.addDeadline(this.taskName, this.date, this.tags);
        return resp.add(deadline, todos.getNumberOfTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

package commands;

import java.time.LocalDate;

import data.Deadline;
import data.Task;
import data.TaskList;
import storage.Storage;

/**
 * Command to create a deadline.
 */
public class DeadlineCommand extends Command {
    private final String title;
    private final LocalDate dateBy;

    /**
     * Command to create a deadline with title and dateBy.
     * @param title Title of task.
     * @param dateBy Deadline of task.
     */
    public DeadlineCommand(String title, LocalDate dateBy) {
        this.title = title;
        this.dateBy = dateBy;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        Task task = tasks.add(new Deadline(title, false, dateBy));
        storage.save(tasks);
        return "Got it. I've added this task: \n"
                + "  " + task + "\n"
                + "Now you have " + tasks.getSize() + " task" + (tasks.getSize() == 1 ? "" : "s") + " in the list.";
    }
}

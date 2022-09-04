package commands;

import data.Event;
import data.Task;
import data.TaskList;
import storage.Storage;

import java.time.LocalDate;

public class EventCommand extends Command {
    private final String title;
    private final LocalDate dateAt;

    public EventCommand(String title, LocalDate dateAt) {
        this.title = title;
        this.dateAt = dateAt;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        Task task = tasks.add(new Event(title, false, dateAt));
        storage.save(tasks);
        return "Got it. I've added this task: \n" +
                "  " + task + "\n" +
                "Now you have " + tasks.getSize() + " task" + (tasks.getSize() == 1 ? "" : "s") + " in the list.";
    }
}

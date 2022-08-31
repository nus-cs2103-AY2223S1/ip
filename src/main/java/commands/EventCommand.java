package commands;

import data.Event;
import data.Task;
import data.TaskList;
import storage.Storage;
import ui.Ui;

import java.time.LocalDate;

public class EventCommand extends Command {
    private final String title;
    private final LocalDate dateAt;

    public EventCommand(String title, LocalDate dateAt) {
        this.title = title;
        this.dateAt = dateAt;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.add(new Event(title, false, dateAt));
        ui.printMultiMsg(new String[] {
                "Got it. I've added this task:",
                "  " + task,
                "Now you have " + tasks.getSize() + " task" + (tasks.getSize() == 1 ? "" : "s") + " in the list."
        });
        storage.save(tasks);
    }
}

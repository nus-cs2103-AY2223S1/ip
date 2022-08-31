package commands;

import data.Deadline;
import data.Task;
import data.TaskList;
import storage.Storage;
import ui.Ui;

import java.time.LocalDate;

public class DeadlineCommand extends Command {
    private final String title;
    private final LocalDate dateBy;

    public DeadlineCommand(String title, LocalDate dateBy) {
        this.title = title;
        this.dateBy = dateBy;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.add(new Deadline(title, false, dateBy));
        ui.printMultiMsg(new String[]{
                "Got it. I've added this task:",
                "  " + task,
                "Now you have " + tasks.getSize() + " task" + (tasks.getSize() == 1 ? "" : "s") + " in the list."
        });
        storage.save(tasks);
    }
}

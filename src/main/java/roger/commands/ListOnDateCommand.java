package roger.commands;

import java.time.LocalDate;
import java.util.List;

import roger.Storage;
import roger.TaskList;
import roger.Ui;
import roger.tasks.Task;


public class ListOnDateCommand extends ListCommand {
    protected LocalDate date;

    public ListOnDateCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.show("Nephew have to do these things on " + date.toString() + ":");
        List<Task> filtered = tasks.filter(date);
        for (Task task: filtered) {
            ui.show(String.valueOf(tasks.getTaskNum(task)) + ". " + task.toString());
        }
    }
}

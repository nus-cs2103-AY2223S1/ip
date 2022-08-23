package blink.command;

import blink.Storage;
import blink.TaskList;
import blink.Ui;
import blink.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;

public class FilterCommand extends Command{

    private LocalDate date;

    public FilterCommand(String input) {
        LocalDate date = LocalDate.parse(input);
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> filteredList = tasks.filter(date);
        ui.showFilter(filteredList, this.date);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

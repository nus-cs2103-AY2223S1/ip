package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.stream.Collectors;

import duke.exception.DukeException;

import duke.storage.Storage;

import duke.task.Task;
import duke.task.TaskList;

import duke.ui.Ui;

public class OnDateCommand extends Command {
    private LocalDate date;

    public OnDateCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        String formattedDate = date.format(DateTimeFormatter.ofPattern("E, d MMM yyyy"));
        ArrayList<Task> filteredTasks = taskList.getTasks().stream().filter(task -> task.isOn(date))
                .collect(Collectors.toCollection(ArrayList::new));
        if (filteredTasks.size() > 0) {
            System.out.println(String.format("\t Here are the tasks you have on %s:", formattedDate));
            for (int i = 0; i < filteredTasks.size(); i++) {
                System.out.println("\t " + (i + 1) + ". " + filteredTasks.get(i));
            }
        } else {
            System.out.println(String.format("\t You have no tasks on %s", formattedDate));
        }
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}

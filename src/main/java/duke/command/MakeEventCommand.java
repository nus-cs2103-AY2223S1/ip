package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Event;

import java.time.LocalDate;

public class MakeEventCommand extends Command {
    private String description;
    private LocalDate time;

    public MakeEventCommand(String description, LocalDate time) {
        this.description = description;
        this.time = time;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Event newTask = new Event(this.description, this.time);
        taskList.addTask(newTask);
        ui.showTaskAdded(newTask, taskList.getTaskListLength());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

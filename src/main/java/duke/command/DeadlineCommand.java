package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command {

    String name;
    LocalDateTime time;

    public DeadlineCommand(String name, LocalDateTime time) {
        this.name = name;
        this.time = time;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(tasks.createTask('D', name, time));
        ui.printAddTask(name, tasks.size());
    }

}

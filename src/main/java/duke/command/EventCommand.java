package duke.command;

import java.time.LocalDateTime;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class EventCommand extends Command {

    private String name;
    private LocalDateTime time;

    public EventCommand(String name, LocalDateTime time) {
        this.name = name;
        this.time = time;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(tasks.createTask('E', name, time));
        ui.printAddTask(name, tasks.size());
    }

}

package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.Event;

public class EventCommand extends Command {
    private String task;
    private String on;
    
    public EventCommand(String task, String on) {
        super();
        this.task = task;
        this.on = on;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(new Event(task, on));
        ui.printAddTaskSuccessfully(tasks);
    }
}

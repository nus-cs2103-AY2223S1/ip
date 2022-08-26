package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.Deadline;

public class DeadlineCommand extends Command {
    private String task;
    private String by;
    
    public DeadlineCommand(String task, String by) {
        super();
        this.task = task;
        this.by = by;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(new Deadline(task, by));
        ui.printAddTaskSuccessfully(tasks);
    }
}

package duke.command;

import duke.Deadline;
import duke.DukeException;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

public class AddDeadlineCommand extends Command {
    private String description;
    private boolean isDone;
    private String by;
    private String time;

    public AddDeadlineCommand(String description, boolean isDone, String by, String time) {
        this.description = description;
        this.isDone = isDone;
        this.by = by;
        this.time = time;
    }

    public AddDeadlineCommand(String description, String[] msg) {
        this.description = description;
        this.isDone = false;
        this.by = msg[1];
        this.time = msg[2];
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        try {
            Task task = new Deadline(description, isDone, by, time);
            list.addTask(task);
            ui.addMessage(task);
            ui.countMessage(list);
            storage.save(list);
        } catch (DukeException e) {
            ui.showText(e.toString());
        }
    }
}

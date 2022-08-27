package duke.command;

import duke.DukeException;
import duke.Event;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;


public class AddEventCommand extends Command {
    private String description;
    private boolean isDone;
    private String at;
    private String time;

    public AddEventCommand(String description, boolean isDone, String at, String time) {
        this.description = description;
        this.isDone = isDone;
        this.at = at;
        this.time = time;
    }

    public AddEventCommand(String description, String[] msg) {
        this.description = description;
        this.isDone = false;
        this.at = msg[1];
        this.time = msg[2];
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        try {
            Task task = new Event(description, isDone, at, time);
            list.addTask(task);
            ui.addMessage(task);
            ui.countMessage(list);
            storage.save(list);
        } catch (DukeException e) {
            ui.showText(e.toString());
        }
    }
}

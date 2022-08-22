package Commands;

import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;
import Tasks.Event;

public class EventCommand extends Command{

    private String description;
    private String at;

    public EventCommand(String description, String at) {
        this.description = description;
        this.at = at;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        Event event = new Event(this.description, this.at);
        taskList.addToList(event);
        storage.save(taskList);
        ui.printAddTask(event, taskList.getSize());
    }
}

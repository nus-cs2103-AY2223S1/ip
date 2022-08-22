package blink.command;

import blink.BlinkException;
import blink.Storage;
import blink.TaskList;
import blink.Ui;
import blink.task.Events;

public class EventCommand extends Command {
    private String desc;
    private String date;

    public EventCommand(String input) {
        String[] info = input.split("/at");
        if (info.length != 2) {
            throw new BlinkException("Error in command for event");
        }
        this.desc = info[0];
        this.date = info[1];
    }


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Events event = tasks.addEvent(this.desc, this.date);
        ui.addTask(tasks, event);
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

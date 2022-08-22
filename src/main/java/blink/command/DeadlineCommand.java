package blink.command;

import blink.*;
import blink.task.Deadlines;

public class DeadlineCommand extends Command{
    private String desc;
    private String date;

    public DeadlineCommand(String input) {
        String[] info = input.split("/by");
        if (info.length != 2) {
            throw new BlinkException("Error in command for deadline");
        }
        this.desc = info[0];
        this.date = info[1];
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Deadlines event = tasks.addDeadline(this.desc, this.date);
        ui.addTask(tasks, event);
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

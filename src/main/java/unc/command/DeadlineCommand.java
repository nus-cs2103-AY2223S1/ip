package unc.command;

import unc.*;
import unc.task.Deadline;

public class DeadlineCommand extends Command {
    private final String description;

    public DeadlineCommand(String input) {
        this.description = input;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws UncException {
        String[] phrases = description.split(" /by ", 2);
        Deadline newDeadline = new Deadline(phrases[0], phrases[1]);
        taskList.add(newDeadline);
        ui.addDeadline(taskList, newDeadline);
        storage.save(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

package uwu.command;

import uwu.Storage;

import uwu.task.TaskList;

import uwu.Ui;

public class ExitCommand extends Command {
    public void execute (TaskList tasks, Ui ui, Storage storage) {
        ui.leaveChat();
    };

    public boolean isExit() {
        return true;
    };
}

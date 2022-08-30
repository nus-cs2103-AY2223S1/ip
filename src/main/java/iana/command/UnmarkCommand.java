package iana.command;

import iana.main.Storage;
import iana.main.TaskList;
import iana.main.Ui;

public class UnmarkCommand extends Command {
    public String taskNum;

    public UnmarkCommand(String taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int taskNumber = Integer.parseInt(this.taskNum) - 1;
            tasks.unmark(taskNumber);
            String unmarkedMsg = "Task unmarked! Remember to mark it once completed! ^_^\n";
            ui.echo(String.format("%s\t   %s", unmarkedMsg, tasks.printTaskString(taskNumber)));
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.echo(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
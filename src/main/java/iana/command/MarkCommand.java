package iana.command;

import iana.main.Storage;
import iana.main.TaskList;
import iana.main.Ui;

public class MarkCommand extends Command {
    public String taskNum;
    
    public MarkCommand(String taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int taskNumber = Integer.parseInt(this.taskNum) - 1;
            tasks.mark(taskNumber);
            String markedMsg = "Nice! I've marked this task as done:\n";
            ui.echo(String.format("%s\t   %s", markedMsg, tasks.printTaskString(taskNumber)));
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.echo(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
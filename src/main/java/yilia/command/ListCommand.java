package yilia.command;

import yilia.Storage;
import yilia.Ui;
import yilia.task.TaskList;

public class ListCommand extends Command {
    public ListCommand(boolean isExit) {
        super(isExit);
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + "." + tasks.get(i));
        }
    }
}
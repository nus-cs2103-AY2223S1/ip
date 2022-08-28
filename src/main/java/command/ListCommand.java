package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;
import exception.DukeException;

public class ListCommand extends Command {
    protected String commandLine;

    public ListCommand(String commandLine) {
        this.commandLine = commandLine;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            if (taskList.size() == 0) {
                throw new DukeException("There are no tasks in your list. :)");
            } else {
                System.out.println("Here are the tasks in your list:");
                taskList.forEach();
                System.out.println();
            }
        } catch (DukeException e) {
            System.out.println(e.toString());
        }
    }
}

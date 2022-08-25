package duke.command;

import duke.command.Command;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(String index) {
        this.index = Integer.parseInt(index);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws NullPointerException,
            IndexOutOfBoundsException, NumberFormatException {
            Task task = taskList.getTask(index - 1);
            task.unDone();
            storage.saveTasks(taskList);
            ui.repeater(task + " unmarked");
    }
}

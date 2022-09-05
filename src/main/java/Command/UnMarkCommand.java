package Command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class UnMarkCommand extends Command{

    private int indexToUnMark;

    public UnMarkCommand(int inputNumber) {
        this.indexToUnMark = inputNumber - 1;
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        taskList.get(this.indexToUnMark).mark();
        return "It's Okay boy. I've unmarked this task as undone:\n" + taskList.get(this.indexToUnMark).toString();
    }
}

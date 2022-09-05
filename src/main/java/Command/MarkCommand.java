package Command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class MarkCommand extends Command {

    private int indexToMark;

    public MarkCommand(int inputNumber) {
        this.indexToMark = inputNumber - 1;
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        taskList.get(this.indexToMark).mark();
        return "Good job! I've marked this task as done:\n" + taskList.get(indexToMark - 1).toString();
    }
}

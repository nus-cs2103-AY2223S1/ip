package duke.command;

import duke.oop.Storage;
import duke.oop.TaskList;
import duke.oop.Ui;

public class UnMarkCommand extends Command{

    private int indexToUnMark;

    public UnMarkCommand(int inputNumber) {
        this.indexToUnMark = inputNumber - 1;
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        taskList.get(this.indexToUnMark).unMark();
        storage.update((taskList.getTasks()));
        return "Sure! I've unmarked this task as undone:\n" + taskList.get(this.indexToUnMark).toString();
    }
}

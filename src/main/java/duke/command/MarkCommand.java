package duke.command;

import duke.oop.Storage;
import duke.oop.TaskList;
import duke.oop.Ui;

public class MarkCommand extends Command {

    private int indexToMark;

    public MarkCommand(int inputNumber) {
        this.indexToMark = inputNumber - 1;
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        taskList.get(this.indexToMark).mark();
        storage.update((taskList.getTasks()));
        return "Good job! I've marked this task as done:\n" + taskList.get(indexToMark).toString();
    }
}

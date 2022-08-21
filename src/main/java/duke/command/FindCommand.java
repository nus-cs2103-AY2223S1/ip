package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.InvalidIndexException;
import duke.task.TaskList;

public class FindCommand extends Command {
    private String text;

    public FindCommand(String text){
        this.text = text;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println(taskList.findTask(text));
    }
}

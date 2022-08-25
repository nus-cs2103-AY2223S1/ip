package command;

import tasklist.TaskList;
import util.Storage;
import util.Ui;

public class ListAllTasksCommand extends Command {
    @Override
    public void execute(TaskList list, Storage storage) {
        System.out.println(Ui.formatParagraph(
                list.toString()
        ));
    }
}

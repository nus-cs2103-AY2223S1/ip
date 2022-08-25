package command;

import tasklist.TaskList;
import util.Storage;
import util.Ui;

public class DeleteTaskCommand extends Command {
    private final int index;

    public DeleteTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList list, Storage storage) {
        String deletedItem = "  " + list.deleteItem(index);
        String deleteText = "Noted. I've removed this task:";
        String endLine = String.format(
                "Now you have %d tasks in the list.",
                list.getTaskCount());
        System.out.println(Ui.formatLinesIntoParagraph(
                deleteText,
                deletedItem,
                endLine
        ));
    }
}

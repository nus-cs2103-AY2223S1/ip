package command;

import tasklist.TaskList;
import util.Storage;
import util.Ui;

public class MarkTaskCommand extends Command {
    private final int index;

    public MarkTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList list, Storage storage) {
        String markedItem = "  " + list.markItem(index);
        String markedText = "Nice! I've marked this task as done:";
        System.out.println(Ui.formatLinesIntoParagraph(
                markedText,
                markedItem
        ));
    }
}

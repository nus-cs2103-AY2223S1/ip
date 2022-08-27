package command;

import tasklist.TaskList;
import util.Storage;
import util.Ui;

public class UnmarkTaskCommand extends Command {
    private final int index;

    public UnmarkTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList list, Storage storage) {
        String unmarkedItem = "  " + list.unmarkItem(index);
        String unmarkedText = "OK, I've marked this task as not done yet:";
        System.out.println(Ui.formatLinesIntoParagraph(
                unmarkedText,
                unmarkedItem
        ));
    }
}

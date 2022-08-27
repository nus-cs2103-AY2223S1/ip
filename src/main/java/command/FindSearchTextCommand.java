package command;

import tasklist.TaskList;
import util.Storage;
import util.Ui;

import java.util.ArrayList;

/**
 * Represents a task to be executed that searches and returns a task in the internal
 * duke list based on search text
 *
 * @author Bryan Lim Jing Xiang
 */
public class FindSearchTextCommand extends Command {
    private final String searchText;

    /**
     * @param searchText Searches duke list for any tasks that contains the
     *                   search text
     */
    public FindSearchTextCommand(String searchText) {
        this.searchText = searchText;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList list, Storage storage) {
        ArrayList<String> matchedTasks = list.find(searchText);

        if (matchedTasks.size() == 0) {
            System.out.println(Ui.formatLinesIntoParagraph(
                    "Sorry but there is no matching task in the list!"
            ));
            return;
        }

        String res = "";
        for (String match : matchedTasks) {
            res += Ui.formatLine(match);
        }
        System.out.println(Ui.formatParagraph(res));
    }
}

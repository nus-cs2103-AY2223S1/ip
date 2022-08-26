package command;

import tasklist.TaskList;
import util.Storage;
import util.Ui;

import java.util.ArrayList;

public class FindSearchTextCommand extends Command {
    private final String searchText;

    public FindSearchTextCommand(String searchText) {
        this.searchText = searchText;
    }

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

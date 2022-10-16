package command;

import duke.TaskList;

/**
 *  A class which encapsulates the find command of Duke.
 *  @author  Chen Guanzhou
 *  @version v2
 */
public class FindCommand extends Command {
    String searchWord;
    TaskList currList;

    public FindCommand(String searchWord, TaskList currList) {
        this.searchWord = searchWord;
        this.currList = currList;
    }

    /**
     * Executes the find command and returns the items matching with the keyword.
     * @return Duke's response to be passed into the Dialog Box.
     */
    @Override
    public String execute() {
        String result = "Here are the matching tasks:\n";
        TaskList matched = currList.getMatchingItems(this.searchWord);
        for (int i = 0; i < matched.getLength(); i++) {
            result += i + 1 + ". " + matched.getTaskAt(i).toString() + "\n";
        }
        return result;
    }
}

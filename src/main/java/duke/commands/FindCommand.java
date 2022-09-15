package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;

public class FindCommand extends Command {

    private final String restOfInputString;

    public FindCommand(String restOfInputString) {
        this.restOfInputString = restOfInputString;
    }
    /**
     * Returns a String response from Duke which is the task marked as complete.
     * @param taskList List of tasks passed in to duke
     * @param storage Storage object to handle loading / saving from and to files
     * @return task that was marked as complete in String format
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {

        String[] searchTerms = this.restOfInputString.split(" ");
        String found = taskList.search(searchTerms);
        if (found.equals("")) {
            return "Hmm... I couldn't find any tasks matching your queries :(";
        }
        String res = "Here are the matching tasks in your list that I found!\n";
        res += found;
        return res;
    }
}
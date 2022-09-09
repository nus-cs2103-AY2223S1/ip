package duke.command;

import java.util.ArrayList;

import duke.common.Messages;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;
import duke.util.TaskList;

/**
 * Represents a command to find tasks containing the keyword; subclass of Command.
 * @author Huang Yuchen
 * @author hyuchen@u.nus.edu
 */
public class FindCommand extends Command {
    private final ArrayList<String> words;

    /**
     * Constructor for FindCommand.
     *
     * @param words the remaining user input after the first keyword
     */
    public FindCommand(ArrayList<String> words) {
        this.words = words;
    }

    /**
     * Executes the command for "find" keyword.
     * This is the main way for outputting bot replies.
     *
     * @param storage the storage object
     * @param tasklist the task list object
     * @param ui the user interface object
     */
    @Override
    public String execute(Storage storage, TaskList tasklist, Ui ui) {
        String inputToFind = String.join(" ", words);
        StringBuilder output = new StringBuilder();
        TaskList foundTasksList = new TaskList();
        for (Task task : tasklist.tasks) {
            if (task.getTaskName().contains(inputToFind)) {
                foundTasksList.addTask(task);
            }
        }
        if (foundTasksList.tasks.size() == 0) {
            output.append(Messages.SPACER).append("\n")
                    .append("I couldn't find matching tasks T^T\n")
                    .append(Messages.SPACER);
        } else {
            output.append(Messages.SPACER).append("\n")
                    .append("These are the tasks you are searching for ^3^:\n")
                    .append(foundTasksList.printList()).append("\n")
                    .append(Messages.SPACER);
        }
        return output.toString();
    }
}

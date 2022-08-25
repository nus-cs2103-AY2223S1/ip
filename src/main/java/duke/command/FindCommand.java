package duke.command;

import java.util.ArrayList;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Find Tasks in the TaskList depending on the relevant keyword given.
 */
public class FindCommand extends Command {
    public static final boolean IS_EXIT = false;
    public final String keyword;

    /**
     * Constructs a FindCommand instance with the provided whole keyword input by the user.
     *
     * @param keyword keyword is to be searched from the TaskList.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Iterate the TaskList by each character to search for a match of the keyword,
     * then at the end, prints the Tasks consisting of the keyword.
     *
     * @param taskList the TaskList to be searched.
     * @param ui the Ui to assist with printing of the list.
     * @param storage unused in FindCommand.
     * @throws StringIndexOutOfBoundsException if the index exceeds the String length.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> outputList = new ArrayList<Task>();
        int keywordLength = this.keyword.length();
        try {
            for (Task task : taskList.getList()) {
                String taskString = task.toString();
                int i = 0;
                while (i < taskString.length() - keywordLength) {
                    String taskSubstr = taskString.substring(i, i + keywordLength);
                    if (taskSubstr.equals(this.keyword)) {
                        outputList.add(task);
                        break;
                    }
                    i++;
                }
            }
            TaskList outputTaskList = new TaskList(outputList);
            ui.printList(outputTaskList);
        } catch (StringIndexOutOfBoundsException ex) {
            System.out.println("Oops! String Index Out Of Bounds");
        }
    }

    /**
     * Returns false as Find is not a terminating Command.
     *
     * @return false.
     */
    public boolean isExit() {
        return this.IS_EXIT;
    }

}

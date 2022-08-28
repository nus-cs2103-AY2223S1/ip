package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A Command that finds the tasks in the task list with a description that matches a given keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    private FindCommand(String command, String keyword) {
        super(command);
        this.keyword = keyword;
    }

    /**
     * Factory method taking in input string from user.
     * Keyword is taken as the string after "find" with leading and trailing whitespace trimmed.
     * Throws IllegalArgument Exception if no keyword is given.
     *
     * @param command input string from user.
     * @return FindCommand instance that finds matching tasks from the task list when executed.
     * @throws IllegalArgumentException if input string from user is invalid.
     */
    public static FindCommand of(String command) throws IllegalArgumentException {
        String keyword = command.replace("find", "").trim();
        if (keyword.isEmpty()) {
            throw new IllegalArgumentException("🙁 OOPS!!! Provide a keyword to find tasks.\n");
        } else {
            return new FindCommand(command, keyword);
        }
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        return;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        TaskList matchingTasks = taskList.search(this.keyword);
        String s = matchingTasks.toString();

        if (s.isEmpty()) {
            ui.println("🙁 OOPS!!! There are no matching tasks in your list.");
        } else {
            ui.printWithDivider(String.format("Here are the matching tasks in your list:\n%s", s));
        }
    }
}

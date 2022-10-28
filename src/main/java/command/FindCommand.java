package command;

import java.util.HashSet;

import henry.Task;
import henry.TaskList;

/**
 * Responsible for finding tasks given a search term.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    private static final String MESSAGE_SUCCESS = "I've found these matching tasks:\n %1$s";
    private final HashSet<String> termsToFind;

    /**
     * Creates a new FindCommand with the given search terms.
     *
     * @param searchTerms a variable amount of search terms
     */
    public FindCommand(String... searchTerms) {
        termsToFind = getCleanedArgs(searchTerms);
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(String.format(MESSAGE_SUCCESS, appendToStringBuilder(taskList)));
    }

    private HashSet<String> getCleanedArgs(String[] args) {
        HashSet<String> temp = new HashSet<>();
        for (String arg : args) {
            temp.add(arg.trim());
        }
        return temp;
    }

    private String appendToStringBuilder(TaskList taskList) {
        StringBuilder sb = new StringBuilder("\n");
        final int[] i = {1};
        taskList.stream().filter(this::isMatch).forEach(task -> {
            sb.append(" ").append(i[0]++).append(". ").append(task).append("\n");
        });
        return sb.toString().trim();
    }

    private boolean isMatch(Task task) {
        for (String term : termsToFind) {
            if (!task.getDescription().contains(term)) {
                return false;
            }
        }
        return true;
    }
}

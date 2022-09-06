package command;

import henry.Task;

/**
 * Responsible for finding tasks given a search term.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    private static final String MESSAGE_SUCCESS = "I'VE FOUND THESE MATCHING TASKS:\n %1$s";
    private final String[] termsToFind;

    /**
     * Creates a new FindCommand with the given search terms.
     *
     * @param searchTerms a variable amount of search terms
     */
    public FindCommand(String... searchTerms) {
        int argsLength = searchTerms.length;
        termsToFind = new String[argsLength];
        for (int i = 0; i < termsToFind.length; i++) {
            termsToFind[i] = searchTerms[i].toLowerCase().trim();
        }
    }

    @Override
    public CommandResult execute() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        final int[] i = {1};
        taskList.forEach(task -> appendToStringBuilder(sb, task, i[0]++));
        return new CommandResult(String.format(MESSAGE_SUCCESS, sb.toString().trim()));
    }

    private void appendToStringBuilder(StringBuilder sb, Task task, int index) {
        if (isMatch(task)) {
            sb.append(" ").append(index).append(task).append("\n");
        }
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

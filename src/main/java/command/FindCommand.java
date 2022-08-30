package command;

import java.util.List;

import henry.Task;

/**
 * Responsible for finding tasks given a search term.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    private static final String MESSAGE_SUCCESS = "I'VE FOUND THESE MATCHING TASKS:\n\t\t\t %1$s";
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
            termsToFind[i] = searchTerms[i].toLowerCase();
        }
    }

    @Override
    public CommandResult execute() {
        List<Task> tasks = taskList.getTasks();
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        int i = 1;
        boolean flag;
        for (Task task : tasks) {
            flag = true;
            for (String term : termsToFind) {
                if (!task.toSimpleString().contains(term)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                sb.append(" ").append(i).append(". ").append(task).append("\n");
                i++;
            }
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, sb.toString().trim()));
    }
}

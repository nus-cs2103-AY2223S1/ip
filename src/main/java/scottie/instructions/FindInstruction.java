package scottie.instructions;

import java.util.List;
import java.util.Map;

import scottie.tasks.Task;
import scottie.tasks.TaskList;
import scottie.ui.Ui;

/**
 * Encapsulates the find instruction which is used to
 * find a task which matches the searched text.
 */
public class FindInstruction extends Instruction {
    private static final String MISSING_SEARCH_TERM_MESSAGE = "Um... what exactly did you want to find?";
    private static final String NO_MATCHES_MESSAGE = "Hmm... looks like there aren't any matching tasks.";
    private static final String TASKS_FOUND_MESSAGE = "Ok, I've found %s in your list:";

    /**
     * Constructs a FindInstruction with the given arguments.
     *
     * @param mainArgument The main argument provided to this instruction.
     * @param flagArgumentsMap The map between flags and values provided to
     *                         this instruction.
     */
    FindInstruction(String mainArgument, Map<String, String> flagArgumentsMap) {
        super(mainArgument, flagArgumentsMap);
    }

    /**
     * Finds a task from the given TaskList which matches the
     * searched text.
     * The main argument of this instruction is the text that will be
     * searched inside the descriptions of the tasks.
     * Every matching task is displayed in a list to the user.
     * The search is case-insensitive.
     *
     * @param taskList The TaskList to find the task from.
     * @param ui The Ui used to display messages to the user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        if (!this.hasMainArgument()) {
            ui.showError(MISSING_SEARCH_TERM_MESSAGE);
            return;
        }
        List<Task> matchingTasks = taskList.filterTasks(this.getMainArgument());
        if (matchingTasks.size() == 0) {
            ui.showMessages(NO_MATCHES_MESSAGE);
        } else {
            ui.showFormattedMessage(TASKS_FOUND_MESSAGE, matchingTasks.size() == 1 ? "this task" : "these tasks");
            ui.showOrderedList(matchingTasks);
        }
    }
}

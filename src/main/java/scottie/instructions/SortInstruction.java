package scottie.instructions;

import java.util.Map;

import scottie.tasks.TaskList;
import scottie.ui.Ui;

/**
 * Encapsulates the sort instruction which is used to
 * sort the tasks in some order.
 */
public class SortInstruction extends Instruction {
    private static final String SORT_BY_DESCRIPTION_MESSAGE =
            "Ok, I've sorted your tasks in%s alphabetical order.";
    private static final String SORT_BY_DATE_MESSAGE =
            "Ok, I've sorted your tasks in%s chronological order.";
    private static final String INVALID_SORT_KEY_MESSAGE = "Sorry, I don't know how to sort by ";

    /**
     * Constructs a SortInstruction with the given arguments.
     *
     * @param mainArgument The main argument provided to this instruction.
     * @param flagArgumentsMap The map between flags and values provided to
     *                         this instruction.
     */
    SortInstruction(String mainArgument, Map<String, String> flagArgumentsMap) {
        super(mainArgument, flagArgumentsMap);
    }

    /**
     * Sorts the tasks in the given TaskList.
     * The "by" flag argument of this instruction is used to decide what
     * attribute to sort by. If the "by" flag is not provided, the tasks
     * are sorted by their descriptions by default. If the "desc" flag is
     * specified, the tasks are sorted in reverse order.
     *
     * @param taskList The TaskList to sort.
     * @param ui The Ui used to display messages to the user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        String sortKey = this.getFlagArgument("by");
        if (sortKey == null) {
            sortKey = "";
        }
        boolean isReversed = this.getFlagArgument("desc") != null;
        switch (sortKey) {
        case "":
        case "name":
        case "description":
        case "desc":
            taskList.sortByDescription(isReversed);
            ui.showFormattedMessage(SORT_BY_DESCRIPTION_MESSAGE, isReversed ? " reverse" : "");
            break;
        case "date":
            taskList.sortByDate(isReversed);
            ui.showFormattedMessage(SORT_BY_DATE_MESSAGE, isReversed ? " reverse" : "");
            break;
        default:
            ui.showMessages(INVALID_SORT_KEY_MESSAGE + sortKey);
            break;
        }
    }
}

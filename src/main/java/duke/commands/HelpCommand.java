package duke.commands;

import duke.utils.Storage;
import duke.utils.TaskList;


/**
 * Represents an executable command that prints all possible commands and their simple explanation.
 *
 * @author sikai00
 */
public class HelpCommand extends Command {
    /** Command identifier used by Parser **/
    public static final String COMMAND_WORD = "help";

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(TaskList taskList, Storage storage) {
        String intro = "'Where are you from? How do you not already know this?'\n\n";
        String description = "KarenBot is a rude, self entitled chatbot that will help you remember your tasks, "
                + "all for the low cost of having to deal with her attitude. "
                + "KarenBot is best suited for the quick typist, maximising "
                + "your productivity by spending less time on handling your tasks!\n\n";
        String addHelp = "Add a Todo:\n"
                + "    add todo <description>\n"
                + "Add a Deadline:\n"
                + "    add deadline <description> /by <yyyy-mm-dd HH:MM>\n"
                + "Add an Event:\n"
                + "    add event <description> /at <yyyy-mm-dd HH:MM>\n\n";
        String listHelp = "List all tasks:\n"
                + "    list\n\n";
        String markHelp = "Mark a task as done:\n"
                + "    mark <task index>\n";
        String unmarkHelp = "Mark a task as done:\n"
                + "    unmark <task index>\n\n";
        String findHelp = "Find a task:\n"
                + "    find <keyword>\n\n";
        String viewHelp = "View a task for a time period:\n"
                + "    view <today|tomorrow|week>\n\n";
        String exitHelp = "Exit KarenBot:\n"
                + "    exit";

        String completeResponse = intro + description + addHelp + listHelp + markHelp + unmarkHelp + findHelp
                + viewHelp + exitHelp;
        return new CommandResult(completeResponse);
    }
}

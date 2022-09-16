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
    public static final String INTRO = "'Where are you from? How do you not already know this?'\n";
    public static final String DESCRIPTION = "KarenBot is a rude, self entitled chat bot that will help you "
            + "remember your tasks, all for the low cost of having to deal with her attitude. "
            + "KarenBot is best suited for the quick typist, maximising "
            + "your productivity by spending less time on handling your tasks!\n";


    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(TaskList taskList, Storage storage) {
        return new CommandResult(
                INTRO + "\n"
                     + DESCRIPTION + "\n"
                     + AddCommand.MESSAGE_USAGE + "\n"
                     + ListCommand.MESSAGE_USAGE + "\n"
                     + MarkCommand.MESSAGE_USAGE + "\n"
                     + UnmarkCommand.MESSAGE_USAGE + "\n"
                     + DeleteCommand.MESSAGE_USAGE + "\n"
                     + FindCommand.MESSAGE_USAGE + "\n"
                     + ViewScheduleCommand.MESSAGE_USAGE + "\n"
                     + ExitCommand.MESSAGE_USAGE + "\n"
        );
    }
}

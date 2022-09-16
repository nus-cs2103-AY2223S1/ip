package commands;

import dukeegg.Storage;
import dukeegg.TaskList;
import ui.Ui;

/**
 * Returns all the commands available to the chatbot
 */
public class HelpCommand extends Command {
    public static final String SYNTAX = "help";

    /**
     * List of all possible commands to be printed when help command is called.
     */
    public static final String[] COMMANDS = new String[]{
            ByeCommand.SYNTAX,
            DeadlineCommand.SYNTAX,
            DeleteCommand.SYNTAX,
            EventCommand.SYNTAX,
            FindCommand.SYNTAX,
            HelpCommand.SYNTAX,
            ListCommand.SYNTAX,
            MarkCommand.SYNTAX,
            TagCommand.SYNTAX,
            TodoCommand.SYNTAX,
            UnmarkCommand.SYNTAX,
            UntagCommand.SYNTAX
    };

    /**
     * Generates all commands available to the chatbot.
     * <p>
     * {@inheritDoc}
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showAvailableCommands(COMMANDS);
    }

    public boolean isExit() {
        return false;
    }
}

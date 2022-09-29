package command;

import util.TextUtils;

/**
 * Handles the Help command, which prints useful
 * information about Henry to the UI
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    private final String helpWith;

    public HelpCommand() {
        this("");
    }

    public HelpCommand(String needHelpWith) {
        this.helpWith = needHelpWith;
    }

    @Override
    public CommandResult execute() {
        switch (helpWith) {
        case "todo":
            return new CommandResult(TextUtils.TODO_COMMAND_HELP);
        case "deadline":
            return new CommandResult(TextUtils.DEADLINE_COMMAND_HELP);
        case "event":
            return new CommandResult(TextUtils.EVENT_COMMAND_HELP);
        case "list":
            return new CommandResult(TextUtils.LIST_COMMAND_HELP);
        case "mark":
            return new CommandResult(TextUtils.MARK_COMMAND_HELP);
        case "unmark":
            return new CommandResult(TextUtils.UNMARK_COMMAND_HELP);
        case "delete":
            return new CommandResult(TextUtils.DELETE_COMMAND_HELP);
        case "find":
            return new CommandResult(TextUtils.FIND_COMMAND_HELP);
        case "teach":
            return new CommandResult(TextUtils.TEACH_COMMAND_HELP);
        case "tentative":
            return new CommandResult(TextUtils.TENTATIVE_COMMAND_HELP);
        case "help":
            return new CommandResult(TextUtils.HELP_COMMAND_HELP);
        case "bye":
            return new CommandResult(TextUtils.BYE_COMMAND_HELP);
        default:
            return new CommandResult("Here are the commands you can use:\n"
                                     + "1. todo <task name> - adds a todo task\n"
                                     + "2. deadline <task name> /by <date> - adds a deadline task\n"
                                     + "3. event <task name> /at <date> - adds an event task\n"
                                     + "4. list - lists all tasks\n"
                                     + "5. mark <task number> - marks a task as done\n"
                                     + "6. unmark <task number> - marks a task as not done\n"
                                     + "7. delete <task number> - deletes a task\n"
                                     + "8. find <keyword> - finds tasks with the keyword\n"
                                     + "9. teach <keyword> - teaches Henry a new word\n"
                                     + "10. tentative <task number> - marks a task as tentative\n"
                                     + "11. help - shows this help message\n"
                                     + "12. bye - exits Henry\n\n"
                                     + "Type \"help\"  <command name> for more information.");
        }
    }
}

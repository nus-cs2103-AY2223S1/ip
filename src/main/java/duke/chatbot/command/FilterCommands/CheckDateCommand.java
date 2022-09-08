package duke.chatbot.command.FilterCommands;

import java.time.LocalDate;
import java.util.function.Predicate;

import duke.chatbot.data.exception.InvalidInputException;
import duke.chatbot.data.task.Task;
import duke.chatbot.data.task.TimedTask;
import duke.chatbot.util.Parser;

/**
 * A command that prints a list of TimedTask that have the same date as the date argument input.
 *
 * @author jq1836
 */
public class CheckDateCommand extends FilterCommand {
    /**
     * The command word to invoke this command.
     */
    public static final String COMMAND_WORD = "check";

    public CheckDateCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    Predicate<Task> supplyCondition() throws InvalidInputException {
        LocalDate date = Parser.parseDate(arguments);
        return (task) -> {
            if (!(task instanceof TimedTask)) {
                return false;
            }
            TimedTask timedTask = (TimedTask) task;
            return timedTask.hasMatchingDate(date);
        };
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && obj instanceof CheckDateCommand;
    }
}

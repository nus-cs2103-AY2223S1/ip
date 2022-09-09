package duke.parser;

import duke.commands.tasks.AddDeadlineCommand;
import duke.domain.Deadline;
import duke.domain.task.Task;
import duke.exceptions.InvalidDateTimeException;
import duke.exceptions.InvalidTaskSpecificationException;
import duke.exceptions.ParseException;

/**
 * AddDeadlineParser Class
 */
public class AddDeadlineParser implements IParser<AddDeadlineCommand> {

    @Override
    public AddDeadlineCommand parse(String arguments) throws ParseException {
        if (arguments.contains(AddDeadlineCommand.SUBCOMMAND_WORD)) {
            String[] deadlineArgs = arguments.split(
                    AddDeadlineCommand.SUBCOMMAND_WORD);
            String deadlineTitle = deadlineArgs[0];
            String deadline = deadlineArgs[1];

            Task newDeadline;
            try {
                newDeadline = Task.of(
                        "D",
                        "0",
                        deadlineTitle,
                        deadline);
            } catch (InvalidDateTimeException | InvalidTaskSpecificationException e) {
                throw new ParseException(e.getMessage());
            }
            if (newDeadline instanceof Deadline) {
                Deadline castedNewDeadline = (Deadline) newDeadline;
                return new AddDeadlineCommand(castedNewDeadline);
            }
            throw new RuntimeException();

        } else {
            throw new ParseException(
                    "Deadlines need a /by command");
        }
    }
}

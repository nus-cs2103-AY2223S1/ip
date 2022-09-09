package duke.parser;

import duke.commands.tasks.AddEventCommand;
import duke.domain.Event;
import duke.domain.task.Task;
import duke.exceptions.InvalidDateTimeException;
import duke.exceptions.InvalidTaskSpecificationException;
import duke.exceptions.ParseException;

/**
 * AddEventParser Class
 */
public class AddEventParser implements IParser<AddEventCommand> {

    @Override
    public AddEventCommand parse(String arguments) throws ParseException {
        if (arguments.contains(AddEventCommand.SUBCOMMAND_WORD)) {
            String[] eventArgs = arguments.split(
                    AddEventCommand.SUBCOMMAND_WORD);
            String eventTitle = eventArgs[0];
            String eventSubArgs = eventArgs[1];

            Task newEvent;
            try {
                newEvent = Task.of(
                        "D",
                        "0",
                        eventTitle,
                        eventSubArgs);
            } catch (InvalidDateTimeException | InvalidTaskSpecificationException e) {
                throw new ParseException(e.getMessage());
            }
            if (newEvent instanceof Event) {
                Event castedNewEvent = (Event) newEvent;
                return new AddEventCommand(castedNewEvent);
            }
            throw new RuntimeException();

        } else {
            throw new ParseException(
                    "Events need a /by command");
        }
    }

}

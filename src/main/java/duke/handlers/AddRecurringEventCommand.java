package duke.handlers;

import duke.exceptions.DukeException;
import duke.models.RecurringEventParser;
import duke.models.RecurringEvent;
import duke.models.TaskList;

public class AddRecurringEventCommand implements DukeCommand {
    private final RecurringEventParser recurringEventParser =
            new RecurringEventParser();

    @Override
    public String run(TaskList taskList, String s) throws DukeException {
        RecurringEvent recurringEvent = recurringEventParser.parseRecurringEvent(s);
        taskList.addTask(recurringEvent);
        return "Added a recurring event: " + recurringEvent.toString();
    }
}

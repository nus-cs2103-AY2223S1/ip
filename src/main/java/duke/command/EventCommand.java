package duke.command;

import java.io.IOException;

import duke.Date;
import duke.DukeException;
import duke.TaskList;
import duke.task.Event;
import duke.utils.Parser;
import duke.utils.Storage;

/**
 * Handles the "event" command.
 * @author Jason
 */
public class EventCommand extends Command {
    private String[] commandDetails;

    public EventCommand(String[] commandDetails) {
        this.commandDetails = commandDetails;
    }

    /**
     * Handles an event task.
     * @param taskList TaskList to add to event to.
     * @param storage Storage to save new event task.
     * @return String message of running the "event" command.
     * @throws DukeException Event task has no description or improper syntax.
     */
    @Override
    public String run(TaskList taskList, Storage storage) throws DukeException, IOException {
        try {
            String[] eventDetails = commandDetails[1].split(" /at ", 2);
            int correctArrayLength = 2;
            if (eventDetails.length == correctArrayLength) {
                Date date = Parser.parseDate(eventDetails[1]);
                Event event = new Event(eventDetails[0], date);
                return taskList.addTask(event, storage);
            } else {
                throw new DukeException("\uD83D\uDE14 OOPS!!! Please follow the syntax for an "
                        + "'event' command: event [description] /at [date].");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("\uD83D\uDE14 OOPS!!! Please follow the syntax for an "
                    + "'event' command: event [description] /at [date].");
        }
    }
}

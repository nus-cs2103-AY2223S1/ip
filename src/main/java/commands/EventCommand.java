package commands;

import drivers.Storage;
import drivers.TaskList;
import drivers.Ui;
import exceptions.DeadlineEventNoArgException;
import exceptions.DeadlineEventNoTimingException;
import exceptions.DeadlineEventTimingOverflowException;
import exceptions.TumuException;
import tasks.Event;

/**
 * Class to be executed when an event command is issued
 * by the user.
 */
public class EventCommand extends Command {
    private final String body;

    /**
     * Constructor for the EventCmd class.
     * @param body The rest of the instruction issued by the user after command.
     */
    public EventCommand(String body) {
        this.body = body;
    }

    /**
     * Executes the command and gives the appropriate
     * feedback to the user.
     * @param tasks TaskList containing all the tasks currently available.
     * @param ui Specifies how the program interacts with the user.
     * @param storage Stores and retrieves data from a local .txt file.
     * @throws TumuException Parent exception for the program.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws TumuException {
        if (!body.contains("/at")) {
            throw new DeadlineEventNoTimingException("at");
        } else {
            String[] parse = body.split("/at");
            if (parse.length > 2) {
                throw new DeadlineEventTimingOverflowException();
            } else if (parse.length < 2 || parse[0].isBlank() || parse[1].isBlank()) {
                throw new DeadlineEventNoArgException();
            } else {
                return addTaskType(new Event(parse[0].trim(),
                        parse[1].replaceAll("\\s+", "")), storage, tasks, ui);
            }
        }
    }
}

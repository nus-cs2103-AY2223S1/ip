package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.EmptyEventException;
import duke.exception.NoDateException;
import duke.task.Event;
import duke.task.TaskList;

/**
 * A class to represent the event command.
 */
public class EventCommand extends Command {
    public static final String COMMAND = "event";
    private String desc;
    private String date;

    /**
     * Constructs a new EventCommand instance.
     *
     * @param description the description of the command.
     * @throws DukeException If description does not have a valid format.
     */
    public EventCommand(String description) throws DukeException {
        //some regex to parse the strings correctly
        //0th index: event, 1st index: date
        String[] splitted = description.split("\\s/at\\s", 2);
        String desc = splitted[0].trim();

        //No Description Given
        if (desc.equals("") || desc.startsWith("/at")) {
            throw new EmptyEventException();
        }

        //No Deadline Given
        if (splitted.length == 1) {
            throw new NoDateException();
        }

        String date = splitted[1].trim();

        this.desc = desc;
        this.date = date;
    }

    /**
     * Adds a new deadline instance to the list of tasks
     *     and returns a response message.
     *
     * @param taskList the current list of tasks.
     * @return the response message.
     * @throws DukeException If there is a problem executing the command.
     */
    @Override
    public String executeAndGetResponse(TaskList taskList) throws DukeException {
        Event task = new Event(desc, date);
        taskList.add(task);
        Storage.saveTasks(taskList);
        return Ui.getTaskAddedMessage(task, taskList.getSize());
    }
}

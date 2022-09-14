package duke.commands;

import duke.commons.Parser;
import duke.commons.Storage;
import duke.commons.TaskList;
import duke.commons.Ui;
import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

/**
 * This class performs update a task in TaskList with specifications Command.
 */
public class UpdateCommand implements Command {
    public static final String COMMAND_WORD = "update";
    private static final String MESSAGE_INVALID_DATE_SPECIFIER = "Task cannot be updated "
            + "with Date specifier: ";
    private static final String MESSAGE_INVALID_DATETIME_SPECIFIER = "Task cannot be updated "
            + "with DateTime specifier: ";
    private int index;
    /** Input details from user to be further broken down */
    private String input;

    /**
     * Constructor for class UpdateCommand.
     *
     * @param index Index of task to be updated.
     * @param input Input message to be further broken down.
     */
    public UpdateCommand(int index, String input) {
        this.index = index;
        this.input = input;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        Task task = taskList.getTask(index);
        //Safe to typecast if they are already an instance of the type of task.
        if (task instanceof ToDo) {
            updateToDo((ToDo) task);
        } else if (task instanceof Deadline) {
            updateDeadline((Deadline) task);
        } else if (task instanceof Event) {
            updateEvent((Event) task);
        }
        return Ui.formatUpdateMessage(index, task);
    }

    /**
     * Updates the ToDo task.
     *
     * @param toDo Todo task to update.
     * @throws DukeException If invalid specifier is used in updating ToDo task description.
     */
    private void updateToDo(ToDo toDo) throws DukeException {
        if (input.contains(Parser.DATE_SPECIFIER)) {
            throw new DukeException(MESSAGE_INVALID_DATE_SPECIFIER + Parser.DATE_SPECIFIER);
        } else if (input.contains(Parser.DATETIME_SPECIFIER)) {
            throw new DukeException(MESSAGE_INVALID_DATETIME_SPECIFIER + Parser.DATETIME_SPECIFIER);
        }
        toDo.updateDescription(input);
    }

    /**
     * Updates the Deadline task.
     * If the input does not contain a date specifier, only update description.
     *
     * @param deadline Deadline task to update.
     * @throws DukeException If invalid specifier is used in updating Deadline task description.
     */
    private void updateDeadline(Deadline deadline) throws DukeException {
        if (input.contains(Parser.DATETIME_SPECIFIER)) {
            throw new DukeException(MESSAGE_INVALID_DATETIME_SPECIFIER + Parser.DATETIME_SPECIFIER
                    + "\nUse: " + Parser.DATE_SPECIFIER);
        } else if (!input.contains(Parser.DATE_SPECIFIER)) {
            deadline.updateDescription(input.strip());
        } else {
            String[] descAndDate = input.split(Parser.DATE_SPECIFIER, 2);
            deadline.updateDate(Parser.parseLocalDate(descAndDate[1].strip()));
            deadline.updateDescription(descAndDate[0].strip());
        }
    }

    /**
     * Updates the Event task.
     * If the input does not contain datetime specifier, only update description.
     *
     * @param event Event task to update.
     * @throws DukeException If invalid specifier is used in updating Event task description.
     */
    private void updateEvent(Event event) throws DukeException {
        if (input.contains(Parser.DATE_SPECIFIER)) {
            throw new DukeException(MESSAGE_INVALID_DATE_SPECIFIER + Parser.DATE_SPECIFIER
                    + "\nUse: " + Parser.DATETIME_SPECIFIER);
        } else if (!input.contains(Parser.DATETIME_SPECIFIER)) {
            event.updateDescription(input.strip());
        } else {
            String[] descAndDateTime = input.split(Parser.DATETIME_SPECIFIER, 2);
            event.updateDateTime(Parser.parseLocalDateTime(descAndDateTime[1].strip()));
            event.updateDescription(descAndDateTime[0].strip());
        }
    }
}


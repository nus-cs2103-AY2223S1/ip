package command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import exception.DukeException;
import javafx.scene.layout.VBox;
import parser.DateTimeParser;
import storage.Storage;
import task.Event;
import task.TaskList;
import ui.DialogBox;
import ui.Ui;

/**
 * <h1>AddEventCommand class</h1>
 * Class that adds the generated Event to the TaskList
 */
public class AddEventCommand extends AddTaskCommand {
    private static final String EVENT_COMMAND_REGEX = " /at ";
    private static final String EMPTY_EVENT_RANGE_ERROR_MESSAGE = "Eh you never added the event range";
    private static final String INVALID_INPUT_EVENT_ERROR_MESSAGE = "Eh you never add a proper event date! \n"
            + "Your event date should be like this lah: Jan 21 2023 04:10 AM";

    /**
     * Creates the AddEventCommand
     *
     * @param tasks the list of Tasks.
     * @param input the input String from the user
     * @param ui the Ui object that handles the User Interface.
     */
    public AddEventCommand(TaskList tasks, String input, Ui ui) {
        super(tasks, input, ui);
    }

    /**
     * Adds the Event to the TaskList
     *
     * @param dialogContainer the VBox to add the dialog to.
     * @param userDialog the Dialog Box containing the user's input to be added to the Vbox.
     * @param storage the Storage to write the Tasks to an output file.
     * @throws DukeException Throws an error message indicating that the user's input is invalid
     */
    @Override
    public void execute(VBox dialogContainer, DialogBox userDialog, Storage storage) throws DukeException {
        String[] splitEvent = input.substring(5).split(EVENT_COMMAND_REGEX, 3);
        boolean isEmptyEvent = isEmptyDescription(splitEvent);
        boolean isIncorrectEventDate = isInvalidEvent(splitEvent);
        if (isEmptyEvent) {
            throw new DukeException(EMPTY_TASK_NAME_ERROR_MESSAGE);
        } else if (isIncorrectEventDate) {
            throw new DukeException(EMPTY_EVENT_RANGE_ERROR_MESSAGE);
        }
        assert splitEvent.length == 2 && !splitEvent[0].equals("") && !splitEvent[1].equals("");
        try {
            Event event = getEventFromInput(splitEvent);
            this.addTask(event);
            ui.printAddedTaskMessage(event, dialogContainer, userDialog);
            ui.printTaskCountMessage(tasks, dialogContainer);
        } catch (DateTimeParseException e) {
            throw new DukeException(INVALID_INPUT_EVENT_ERROR_MESSAGE);
        }
    }

    @Override
    protected boolean isEmptyDescription(String[] splitEvent) {
        return splitEvent[0].trim().equals("");
    }

    private Event getEventFromInput(String[] splitEvent) {
        LocalDateTime eventDateTime = DateTimeParser.changeStringToParsingDateTime(splitEvent[1].trim());
        return new Event(splitEvent[0].trim(), false, eventDateTime);
    }

    private boolean isInvalidEvent(String[] splitEvent) {
        return splitEvent.length != 2 || splitEvent[1].trim().equals("");
    }
}

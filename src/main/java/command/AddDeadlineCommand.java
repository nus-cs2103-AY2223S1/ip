package command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import exception.DukeException;
import javafx.scene.layout.VBox;
import parser.DateTimeParser;
import storage.Storage;
import task.Deadline;
import task.TaskList;
import ui.DialogBox;
import ui.Ui;

/**
 * <h1>AddDeadlineCommand class</h1>
 * Adds the generated Deadline to the TaskList
 */
public class AddDeadlineCommand extends AddTaskCommand {
    private static final String DEADLINE_COMMAND_REGEX = " /by ";
    private static final String EMPTY_DEADLINE_ERROR_MESSAGE = "Eh you never added a deadline";
    private static final String INVALID_INPUT_DEADLINE_ERROR_MESSAGE = "Eh you never add a proper deadline date! \n"
            + "Your deadline date should be like this lah: Jan 21 2023 04:10 AM";

    /**
     * Creates the AddDeadlineCommand object.
     *
     * @param tasks the list of Tasks.
     * @param input the input String from the user
     * @param ui the Ui object that handles the User Interface.
     */
    public AddDeadlineCommand(TaskList tasks, String input, Ui ui) {
        super(tasks, input, ui);
    }

    /**
     * Adds the Deadline to the TaskList
     *
     * @param dialogContainer the VBox to add the dialog to.
     * @param userDialog the Dialog Box containing the user's input to be added to the Vbox.
     * @param storage the Storage to write the Tasks to an output file.
     * @throws DukeException Throws an error message indicating that the user's input is invalid
     */
    @Override
    public void execute(VBox dialogContainer, DialogBox userDialog, Storage storage) throws DukeException {
        String[] splitDeadline = input.substring(8).split(DEADLINE_COMMAND_REGEX, 3);
        boolean isEmptyDeadline = isEmptyDescription(splitDeadline);
        boolean isIncorrectDeadlineDate = isInvalidDeadline(splitDeadline);
        if (isEmptyDeadline) {
            throw new DukeException(EMPTY_TASK_NAME_ERROR_MESSAGE);
        } else if (isIncorrectDeadlineDate) {
            throw new DukeException(EMPTY_DEADLINE_ERROR_MESSAGE);
        }
        assert splitDeadline.length == 2 && !splitDeadline[0].equals("") && !splitDeadline[1].equals("");
        try {
            Deadline deadline = getDeadlineFromInput(splitDeadline);
            this.addTask(deadline);
            ui.printAddedTaskMessage(deadline, dialogContainer, userDialog);
            ui.printTaskCountMessage(tasks, dialogContainer);
        } catch (DateTimeParseException e) {
            throw new DukeException(INVALID_INPUT_DEADLINE_ERROR_MESSAGE);
        }
    }

    @Override
    protected boolean isEmptyDescription(String[] splitDeadline) {
        return splitDeadline[0].trim().equals("");
    }

    private Deadline getDeadlineFromInput(String[] splitDeadline) {
        LocalDateTime deadlineDateTime = DateTimeParser.changeStringToParsingDateTime(splitDeadline[1].trim());
        return new Deadline(splitDeadline[0].trim(), false, deadlineDateTime);
    }

    private boolean isInvalidDeadline(String[] splitDeadline) {
        return splitDeadline.length != 2 || splitDeadline[1].trim().equals("");
    }
}

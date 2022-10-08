package command;

import java.time.LocalDate;
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
 * <h1>ViewScheduleCommand class</h1>
 * prints out the schedule at an input date using the ui.
 */
public class ViewScheduleCommand extends Command {
    private static final String VIEW_SCHEDULE_COMMAND_REGEX = " /at ";
    private static final String INVALID_DATE_TIME_ERROR_MESSAGE = "Eh you never add a proper date! \n"
            + "Your date and time should be like this lah: Jan 21 2023";

    /**
     * Creates the ViewScheduleCommand object
     *
     * @param tasks the list of Tasks.
     * @param input the input String from the user
     * @param ui the Ui object that handles the User Interface.
     */
    public ViewScheduleCommand(TaskList tasks, String input, Ui ui) {
        super(tasks, input, ui);
    }

    @Override
    public void execute(VBox dialogContainer, DialogBox userDialog, Storage storage) throws DukeException {
        String[] splitCommand = input.split(VIEW_SCHEDULE_COMMAND_REGEX, 2);
        boolean isEmptyDate = isEmptyDescription(splitCommand);
        if (isEmptyDate) {
            throw new DukeException(INVALID_DATE_TIME_ERROR_MESSAGE);
        }
        assert splitCommand.length == 2 && !splitCommand[0].equals("") && !splitCommand[1].equals("");
        try {
            LocalDate queriedLocalDateTime = DateTimeParser.changeStringToParsingDate(splitCommand[1]);
            TaskList required = getTasksAtLocalDate(queriedLocalDateTime);
            ui.printSchedule(required, dialogContainer, userDialog, queriedLocalDateTime);
        } catch (DateTimeParseException e) {
            throw new DukeException(INVALID_DATE_TIME_ERROR_MESSAGE);
        }
    }

    private boolean isEmptyDescription(String[] splitCommand) {
        return splitCommand.length == 1;
    }

    private TaskList getTasksAtLocalDate(LocalDate localDate) {
        TaskList requiredTaskList = new TaskList();
        int currentTaskSize = tasks.getSize();
        for (int i = 0; i < currentTaskSize; i++) {
            if (tasks.get(i) instanceof Event) {
                Event currentEvent = (Event) tasks.get(i);
                if (localDate.toString().equals(currentEvent.getLocalDateString())) {
                    requiredTaskList.add(currentEvent);
                }
            }
        }
        return requiredTaskList;
    }
}

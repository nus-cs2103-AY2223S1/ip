package duke.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DefaultCommand;
import duke.commands.DeleteCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.SearchCommand;
import duke.commands.TodoCommand;
import duke.commands.UnmarkCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.ui.Ui;

/**
 * Represents a parser to parse inputs from the user.
 */
public class Parser {
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Makes sense of the user input and returns the
     * corresponding command to be executed.
     *
     * @param input Input from the user.
     * @param ui Ui to interact with the user.
     * @return Command to be executed.
     */
    public static Command parse(String input, Ui ui) {
        String[] arr = input.split(" ", 2);
        String command = arr[0];

        switch (command) {
        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case MarkCommand.COMMAND_WORD:
            try {
                return new MarkCommand(Integer.parseInt(arr[1]) - 1);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                ui.invalidMarkInput();
            }
            return null;
        case UnmarkCommand.COMMAND_WORD:
            try {
                return new UnmarkCommand(Integer.parseInt(arr[1]) - 1);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                ui.invalidUnmarkInput();
            }
            return null;
        case TodoCommand.COMMAND_WORD:
            try {
                return new TodoCommand(new ToDo(arr[1]));
            } catch (IndexOutOfBoundsException e) {
                ui.invalidTaskInput(Task.TaskType.ToDo);
            }
            return null;
        case DeadlineCommand.COMMAND_WORD:
            try {
                String[] dl = arr[1].split(" /by ", 2);
                String[] datetime = dl[1].split(" ");
                LocalDate day = LocalDate.parse(datetime[0], dateFormatter);
                if (datetime.length == 1) {
                    return new DeadlineCommand(new Deadline(dl[0], day));
                } else {
                    return new DeadlineCommand(new Deadline(
                            dl[0], day, LocalTime.parse(datetime[1], timeFormatter)));
                }
            } catch (IndexOutOfBoundsException e) {
                ui.invalidTaskInput(Task.TaskType.Deadline);
            } catch (DateTimeParseException e) {
                ui.invalidDateTimeInput();
            }
            return null;
        case EventCommand.COMMAND_WORD:
            try {
                String[] info = arr[1].split(" /", 2);
                String[] timings = info[1].split(" ", 2);
                String[] dateTimeInfo = timings[1].split(" - ");
                LocalDateTime startDateTime = LocalDateTime.parse(dateTimeInfo[0], dateTimeFormatter);
                try {
                    LocalDateTime endDateTime = LocalDateTime.parse(dateTimeInfo[1], dateTimeFormatter);
                    return new EventCommand(new Event(info[0], timings[0], startDateTime, endDateTime));
                } catch (DateTimeParseException e) {
                    return new EventCommand(new Event(
                            info[0], timings[0], startDateTime, LocalTime.parse(dateTimeInfo[1], timeFormatter)));
                }
            } catch (IndexOutOfBoundsException e) {
                ui.invalidTaskInput(Task.TaskType.Event);
            } catch (DateTimeParseException e) {
                ui.invalidStartEndDateInput();
            }
            return null;
        case DeleteCommand.COMMAND_WORD:
            try {
                return new DeleteCommand(Integer.parseInt(arr[1]) - 1);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                ui.invalidDeleteInput();
            }
            return null;
        case SearchCommand.COMMAND_WORD:
            try {
                return new SearchCommand(LocalDate.parse(arr[1], dateFormatter));
            } catch (IndexOutOfBoundsException e) {
                ui.emptyDateInput();
            } catch (DateTimeParseException e) {
                ui.invalidDateInput();
            }
            return null;
        case FindCommand.COMMAND_WORD:
            try {
                return new FindCommand(arr[1]);
            } catch (IndexOutOfBoundsException e) {
                ui.emptyFindInput();
            }
            return null;
        default:
            return new DefaultCommand();
        }


    }
}

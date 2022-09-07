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
import duke.exception.DukeException;
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
     * @return Command to be executed.
     * @throws DukeException if the user has entered an invalid input.
     */
    public static Command parse(String input) throws DukeException {
        String[] arr = input.split(" ", 2);
        String command = arr[0];

        switch (command) {
        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case MarkCommand.COMMAND_WORD:
            try {
                int index = Integer.parseInt(arr[1]) - 1;
                return new MarkCommand(index);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                throw new DukeException(Ui.invalidMarkInput());
            }
        case UnmarkCommand.COMMAND_WORD:
            try {
                int index = Integer.parseInt(arr[1]) - 1;
                return new UnmarkCommand(index);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                throw new DukeException(Ui.invalidUnmarkInput());
            }
        case TodoCommand.COMMAND_WORD:
            try {
                String description = arr[1];
                return new TodoCommand(new ToDo(description));
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException(Ui.invalidTaskInput(Task.TaskType.ToDo));
            }
        case DeadlineCommand.COMMAND_WORD:
            try {
                String info = arr[1];

                String[] descriptionAndDateTime = info.split(" /by ", 2);
                String description = descriptionAndDateTime[0];

                String by = descriptionAndDateTime[1];
                String[] dateTime = by.split(" ");
                LocalDate day = LocalDate.parse(dateTime[0], dateFormatter);

                //check if time was provided
                if (dateTime.length == 1) {
                    return new DeadlineCommand(new Deadline(description, day));
                } else {
                    LocalTime time = LocalTime.parse(dateTime[1], timeFormatter);
                    return new DeadlineCommand(new Deadline(description, day, time));
                }
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException(Ui.invalidTaskInput(Task.TaskType.Deadline));
            } catch (DateTimeParseException e) {
                throw new DukeException(Ui.invalidDateTimeInput());
            }
        case EventCommand.COMMAND_WORD:
            try {
                String info = arr[1];

                String[] descriptionAndTimings = info.split(" /", 2);
                String description = descriptionAndTimings[0];

                String otherEventInfo = descriptionAndTimings[1];
                String[] prepAndTiming = otherEventInfo.split(" ", 2);
                String preposition = prepAndTiming[0];

                String timings = prepAndTiming[1];
                String[] startEndDateTimes = timings.split(" - ");
                LocalDateTime startDateTime = LocalDateTime.parse(startEndDateTimes[0], dateTimeFormatter);

                //assume user provided end date and time
                try {
                    LocalDateTime endDateTime = LocalDateTime.parse(startEndDateTimes[1], dateTimeFormatter);
                    return new EventCommand(new Event(description, preposition, startDateTime, endDateTime));
                //error indicates user only provided an end time
                } catch (DateTimeParseException e) {
                    LocalTime endTime = LocalTime.parse(startEndDateTimes[1], timeFormatter);
                    return new EventCommand(new Event(description, preposition, startDateTime, endTime));
                }
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException(Ui.invalidTaskInput(Task.TaskType.Event));
            } catch (DateTimeParseException e) {
                throw new DukeException(Ui.invalidStartEndDateInput());
            }
        case DeleteCommand.COMMAND_WORD:
            try {
                int index = Integer.parseInt(arr[1]) - 1;
                return new DeleteCommand(index);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                throw new DukeException(Ui.invalidDeleteInput());
            }
        case SearchCommand.COMMAND_WORD:
            try {
                LocalDate searchDate = LocalDate.parse(arr[1], dateFormatter);
                return new SearchCommand(searchDate);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException(Ui.emptyDateInput());
            } catch (DateTimeParseException e) {
                throw new DukeException(Ui.invalidDateInput());
            }
        case FindCommand.COMMAND_WORD:
            try {
                String keywords = arr[1];
                if (keywords.equals("")) {
                    throw new DukeException(Ui.emptyFindInput());
                }
                return new FindCommand(keywords);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException(Ui.emptyFindInput());
            }
        default:
            return new DefaultCommand();
        }


    }
}

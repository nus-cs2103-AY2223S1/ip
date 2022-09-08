package duke.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.AllTagsCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DefaultCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.SearchCommand;
import duke.command.TagCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;
import duke.command.UntagCommand;
import duke.exception.DukeException;
import duke.tag.Tag;
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
        case TagCommand.COMMAND_WORD:
            try {
                String tagInfo = arr[1];
                String[] taskAndTag = tagInfo.split(" #", 2);

                int index = Integer.parseInt(taskAndTag[0]) - 1;
                String tagName = "#" + taskAndTag[1];
                Tag tag = Tag.of(tagName);
                return new TagCommand(index, tag);
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new DukeException(Ui.invalidTagInput());
            }
        case UntagCommand.COMMAND_WORD:
            try {
                String tagInfo = arr[1];
                String[] taskAndTag = tagInfo.split(" #", 2);

                int index = Integer.parseInt(taskAndTag[0]) - 1;
                String tagName = "#" + taskAndTag[1];
                Tag tag = Tag.of(tagName);
                return new UntagCommand(index, tag);
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new DukeException(Ui.invalidTagInput());
            }
        case AllTagsCommand.COMMAND_WORD:
            return new AllTagsCommand();
        default:
            return new DefaultCommand();
        }
    }

    /**
     * Makes sense of the information about the ToDo that was saved
     * to a local file.
     *
     * @param info Information about the ToDo in text form.
     * @return A new ToDo task created from the information.
     */
    public static ToDo parseToDo(String[] info) {
        String description = info[2];
        ToDo toDo = new ToDo(description);

        //check for tags
        if (info.length > 3) {
            String allTags = info[3];
            saveTags(toDo, allTags);
        }

        return toDo;
    }

    /**
     * Makes sense of the information about the Deadline that was saved
     * to a local file.
     *
     * @param info Information about the Deadline in text form.
     * @return A new Deadline task created from the information.
     */
    public static Deadline parseDeadline(String[] info) {
        String description = info[2];
        LocalDate day = LocalDate.parse(info[3]);
        Deadline deadline;
        LocalTime time;

        if (info.length == 4) {
            deadline = new Deadline(description, day);
        //check if 5th information is time or the tags
        } else if (info.length == 5) {
            try {
                time = LocalTime.parse(info[4]);
                deadline = new Deadline(description, day, time);
            } catch (DateTimeParseException e) {
                deadline = new Deadline(description, day);
                String allTags = info[4];
                saveTags(deadline, allTags);
            }
        } else {
            time = LocalTime.parse(info[4]);
            deadline = new Deadline(description, day, time);
            String allTags = info[5];
            saveTags(deadline, allTags);
        }

        return deadline;
    }

    /**
     * Makes sense of the information about the Event that was saved
     * to a local file.
     *
     * @param info Information about the Event in text form.
     * @return A new Event task created from the information.
     */
    public static Event parseEvent(String[] info) {
        String description = info[2];
        String preposition = info[3];
        LocalDateTime startDateTime = LocalDateTime.parse(info[4]);
        Event event;
        LocalDateTime endDateTime;
        LocalTime endTime;

        try {
            endDateTime = LocalDateTime.parse(info[5]);
            event = new Event(
                    description, preposition, startDateTime, endDateTime);
        } catch (DateTimeParseException e) {
            endTime = LocalTime.parse(info[5]);
            event = new Event(
                    description, preposition, startDateTime, endTime);
        }

        //check for tags
        if (info.length > 6) {
            String allTags = info[6];
            saveTags(event, allTags);
        }

        return event;
    }

    private static void saveTags(Task task, String allTags) {
        String[] tagList = allTags.split(" ");

        for (String tagName : tagList) {
            Tag tag = Tag.of(tagName);
            task.addTag(tag);
        }
    }
}

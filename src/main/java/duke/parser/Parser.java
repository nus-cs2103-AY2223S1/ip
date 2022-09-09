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
import duke.command.TaggedByCommand;
import duke.command.TaskTagsCommand;
import duke.command.ToDoCommand;
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
        // splits input into the command and additional info, removing all white spaces between the two
        String[] arr = input.split("\\s+", 2);
        String command = arr[0];

        switch (command) {
        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case MarkCommand.COMMAND_WORD:
            return parseMarkInput(arr);
        case UnmarkCommand.COMMAND_WORD:
            return parseUnmarkInput(arr);
        case ToDoCommand.COMMAND_WORD:
            return parseToDoCommand(arr);
        case DeadlineCommand.COMMAND_WORD:
            return parseDeadlineCommand(arr);
        case EventCommand.COMMAND_WORD:
            return parseEventCommand(arr);
        case DeleteCommand.COMMAND_WORD:
            return parseDeleteCommand(arr);
        case SearchCommand.COMMAND_WORD:
            return parseSearchCommand(arr);
        case FindCommand.COMMAND_WORD:
            return parseFindCommand(arr);
        case TagCommand.COMMAND_WORD:
            return parseTagCommand(arr);
        case UntagCommand.COMMAND_WORD:
            return parseUntagCommand(arr);
        case AllTagsCommand.COMMAND_WORD:
            return new AllTagsCommand();
        case TaskTagsCommand.COMMAND_WORD:
            return parseTaskTagsCommand(arr);
        case TaggedByCommand.COMMAND_WORD:
            return parseTaggedByCommand(arr);
        default:
            return new DefaultCommand();
        }
    }

    /**
     * Parses the arguments for a "mark" command.
     *
     * @param inputArr Array containing the arguments.
     * @return A MarkCommand to be executed.
     * @throws DukeException if the user entered invalid arguments.
     */
    private static MarkCommand parseMarkInput(String[] inputArr) throws DukeException {
        try {
            int index = Integer.parseInt(inputArr[1]) - 1;
            return new MarkCommand(index);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException(Ui.invalidMarkInput());
        }
    }

    /**
     * Parses the arguments of an "unmark" command.
     *
     * @param inputArr Array containing the arguments.
     * @return A UnmarkCommand to be executed.
     * @throws DukeException if the user entered invalid arguments.
     */
    private static UnmarkCommand parseUnmarkInput(String[] inputArr) throws DukeException {
        try {
            int index = Integer.parseInt(inputArr[1]) - 1;
            return new UnmarkCommand(index);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException(Ui.invalidUnmarkInput());
        }
    }

    /**
     * Parses the arguments of a "todo" command.
     *
     * @param inputArr Array containing the arguments.
     * @return A ToDoCommand to be executed.
     * @throws DukeException if the user did not enter a description for the ToDo.
     */
    private static ToDoCommand parseToDoCommand(String[] inputArr) throws DukeException {
        try {
            String description = inputArr[1];
            return new ToDoCommand(new ToDo(description));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Ui.invalidTaskInput(Task.TaskType.ToDo));
        }
    }

    /**
     * Parses the arguments of a "deadline" command.
     *
     * @param inputArr Array containing the arguments.
     * @return A DeadlineCommand to be executed.
     * @throws DukeException if the user entered invalid information for the Deadline.
     */
    private static DeadlineCommand parseDeadlineCommand(String[] inputArr) throws DukeException {
        try {
            String info = inputArr[1];

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
    }

    /**
     * Parses the arguments of an "Event" command.
     *
     * @param inputArr Array containing the arguments.
     * @return An EventCommand to be executed.
     * @throws DukeException if the user entered invalid information for the Event.
     */
    private static EventCommand parseEventCommand(String[] inputArr) throws DukeException {
        try {
            String info = inputArr[1];

            String[] descriptionAndTimings = info.split(" /", 2);
            String description = descriptionAndTimings[0];

            String otherEventInfo = descriptionAndTimings[1];
            String[] prepAndTiming = otherEventInfo.split("\\s+", 2);
            String preposition = prepAndTiming[0];

            String timings = prepAndTiming[1];
            String[] startEndDateTimes = timings.split(" - ");
            LocalDateTime startDateTime = LocalDateTime.parse(startEndDateTimes[0], dateTimeFormatter);

            //assume user provided end date and time
            try {
                LocalDateTime endDateTime = LocalDateTime.parse(startEndDateTimes[1], dateTimeFormatter);
                return new EventCommand(new Event(description, preposition, startDateTime, endDateTime));
            } catch (DateTimeParseException e) { // error indicates user only provided an end time
                LocalTime endTime = LocalTime.parse(startEndDateTimes[1], timeFormatter);
                return new EventCommand(new Event(description, preposition, startDateTime, endTime));
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Ui.invalidTaskInput(Task.TaskType.Event));
        } catch (DateTimeParseException e) {
            throw new DukeException(Ui.invalidStartEndDateInput());
        }
    }

    /**
     * Parses the arguments for a "delete" command.
     *
     * @param inputArr Array containing the arguments.
     * @return A DeleteCommand to be executed.
     * @throws DukeException if the user entered invalid arguments.
     */
    private static DeleteCommand parseDeleteCommand(String[] inputArr) throws DukeException {
        try {
            int index = Integer.parseInt(inputArr[1]) - 1;
            return new DeleteCommand(index);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException(Ui.invalidDeleteInput());
        }
    }

    /**
     * Parses the arguments for a "search" command.
     *
     * @param inputArr Array containing the arguments.
     * @return A SearchCommand to be executed.
     * @throws DukeException if the user entered invalid arguments.
     */
    private static SearchCommand parseSearchCommand(String[] inputArr) throws DukeException {
        try {
            LocalDate searchDate = LocalDate.parse(inputArr[1], dateFormatter);
            return new SearchCommand(searchDate);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Ui.emptyDateInput());
        } catch (DateTimeParseException e) {
            throw new DukeException(Ui.invalidDateInput());
        }
    }

    /**
     * Parses the arguments for a "find" command.
     *
     * @param inputArr Array containing the arguments.
     * @return A FindCommand to be executed.
     * @throws DukeException if the user entered invalid arguments.
     */
    private static FindCommand parseFindCommand(String[] inputArr) throws DukeException {
        try {
            String keywords = inputArr[1];
            if (keywords.equals("")) {
                throw new DukeException(Ui.emptyFindInput());
            }

            return new FindCommand(keywords);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Ui.emptyFindInput());
        }
    }

    /**
     * Parses the arguments for a "tag" command.
     *
     * @param inputArr Array containing the arguments.
     * @return A TagCommand to be executed.
     * @throws DukeException if the user entered invalid arguments.
     */
    private static TagCommand parseTagCommand(String[] inputArr) throws DukeException {
        try {
            String tagInfo = inputArr[1];
            String[] taskAndTag = tagInfo.split("\\s+#", 2);

            int index = Integer.parseInt(taskAndTag[0]) - 1;
            //follow format of tag names since '#' was removed by .split()
            String tagName = "#" + taskAndTag[1];
            Tag tag = Tag.of(tagName);
            return new TagCommand(index, tag);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException(Ui.invalidTagInput());
        }
    }

    /**
     * Parses the arguments for an "untag" command.
     *
     * @param inputArr Array containing the arguments.
     * @return An UntagCommand to be executed.
     * @throws DukeException if the user entered invalid arguments.
     */
    private static UntagCommand parseUntagCommand(String[] inputArr) throws DukeException {
        try {
            String tagInfo = inputArr[1];
            String[] taskAndTag = tagInfo.split("\\s+#", 2);

            int index = Integer.parseInt(taskAndTag[0]) - 1;
            //follow format of tag names since '#' was removed by .split()
            String tagName = "#" + taskAndTag[1];
            Tag tag = Tag.of(tagName);
            return new UntagCommand(index, tag);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException(Ui.invalidTagInput());
        }
    }

    /**
     * Parses the arguments for a "tasktags" command.
     *
     * @param inputArr Array containing the arguments.
     * @return A TaskTagsCommand to be executed.
     * @throws DukeException if the user entered invalid arguments.
     */
    private static TaskTagsCommand parseTaskTagsCommand(String[] inputArr) throws DukeException {
        try {
            int index = Integer.parseInt(inputArr[1]) - 1;
            return new TaskTagsCommand(index);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException(Ui.invalidTaskTagsInput());
        }
    }

    /**
     * Parses the arguments for a "taggedby" command.
     *
     * @param inputArr Array containing the arguments.
     * @return A TaggedByCommand to be executed.
     * @throws DukeException if the user did not enter a tag to search for.
     */
    private static TaggedByCommand parseTaggedByCommand(String[] inputArr) throws DukeException {
        try {
            String tagName = inputArr[1];
            if (!tagName.startsWith("#")) { //if user forgot to input correct format for the tag
                tagName = "#" + tagName;
            }
            Tag tag = Tag.of(tagName);
            return new TaggedByCommand(tag);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Ui.invalidTaggedByInput());
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

    /**
     * Makes sense of the given string of tags and saves them.
     *
     * @param task Task to save the tags under.
     * @param allTags String of tags to parse.
     */
    private static void saveTags(Task task, String allTags) {
        String[] tagList = allTags.split(" ");

        for (String tagName : tagList) {
            Tag tag = Tag.of(tagName);
            task.addTag(tag);
        }
    }
}

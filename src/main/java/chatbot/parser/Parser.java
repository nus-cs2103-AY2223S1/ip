package chatbot.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import chatbot.commands.AddDeadline;
import chatbot.commands.AddEvent;
import chatbot.commands.AddTodo;
import chatbot.commands.Command;
import chatbot.commands.Delete;
import chatbot.commands.Exit;
import chatbot.commands.Find;
import chatbot.commands.FindWith;
import chatbot.commands.List;
import chatbot.commands.ListOn;
import chatbot.commands.ListTags;
import chatbot.commands.Mark;
import chatbot.commands.Unmark;
import chatbot.exceptions.DukeException;
import chatbot.tasks.Deadline;
import chatbot.tasks.Event;

/**
 * The class is responsible for parsing user input and determining the consequent action.
 */
public class Parser {
    public static final Pattern TODO_TAG_FORMAT = Pattern.compile("(?<taskName>.+?)\\s+#(?<tags>.+)");
    public static final Pattern EVENT_TAG_FORMAT = Pattern.compile(
            "(?<taskName>.+)\\s+/at\\s+(?<date>\\S+)\\s+#(?<tags>.+)");
    public static final Pattern EVENT_FORMAT = Pattern.compile("(?<taskName>.+)\\s+/at\\s+(?<date>\\S+)");
    public static final Pattern DEADLINE_TAG_FORMAT = Pattern.compile(
            "(?<taskName>.+)\\s+/by\\s+(?<date>\\S+)\\s+#(?<tags>.+)");
    public static final Pattern DEADLINE_FORMAT = Pattern.compile("(?<taskName>.+)\\s+/by\\s+(?<date>\\S+)");
    public static final Pattern TASK_INDEX_FORMAT = Pattern.compile("(?<taskIndex>\\d+)");
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<command>\\S+)(?<arguments>.*)");

    /**
     * The method parses the user input and determine the command that should be executed accordingly.
     *
     * @param userInput The user input to be parsed.
     * @return The command to be executed
     * @throws DukeException Raised shall the user input has format related error.
     */
    public static Command parse(String userInput) throws DukeException {
        Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw DukeException.INVALID_COMMAND;
        }

        String command = matcher.group("command");
        String args = matcher.group("arguments").trim();

        switch (command) {
        case "list":
            LocalDate date = parseList(args);
            if (date == null) {
                return List.LIST;
            } else {
                return new ListOn(date);
            }
        case "listtags":
            return ListTags.LIST_TAGS;
        case "delete":
            return new Delete(parseTargetIndex(args));
        case "mark":
            return new Mark(parseTargetIndex(args));
        case "unmark":
            return new Unmark(parseTargetIndex(args));
        case "find":
            return new Find(parseFind(args));
        case "findtag":
            if (args.isEmpty()) {
                throw DukeException.MISSING_TAG;
            }
            return new FindWith(args);
        case "todo":
            String[] todoSpecs = parseTodo(args);
            String[] aTags = Arrays.copyOfRange(todoSpecs, 1, todoSpecs.length);
            return new AddTodo(todoSpecs[0], aTags);
        case "event":
            String[] eventSpecs = parseTimedTask(Event.TYPE, args);
            LocalDate eventDate;
            try {
                eventDate = LocalDate.parse(eventSpecs[1]);
            } catch (DateTimeParseException e) {
                throw DukeException.INVALID_DATE_FORMAT;
            }

            String[] eTags = Arrays.copyOfRange(eventSpecs, 2, eventSpecs.length);
            return new AddEvent(eventSpecs[0], eventDate, eTags);
        case "deadline":
            String[] deadlineSpecs = parseTimedTask(Deadline.TYPE, args);
            LocalDate deadline;
            try {
                deadline = LocalDate.parse(deadlineSpecs[1]);
            } catch (DateTimeParseException e) {
                throw DukeException.INVALID_DATE_FORMAT;
            }

            String[] dTags = Arrays.copyOfRange(deadlineSpecs, 2, deadlineSpecs.length);
            return new AddDeadline(deadlineSpecs[0], deadline, dTags);
        case "bye":
            return Exit.EXIT;
        default:
            throw DukeException.INVALID_COMMAND;
        }
    }

    /**
     * The method parses the argument segment of the user input to look for an integer.
     *
     * @param args The argument to be parsed.
     * @return The index of the task to be operated on.
     * @throws DukeException Raised if the argument does not contain a valid index.
     */
    public static int parseTargetIndex(String args) throws DukeException {
        Matcher indexMatcher = TASK_INDEX_FORMAT.matcher(args);
        if (!indexMatcher.matches()) {
            throw DukeException.TASK_INDEX_MISSING;
        }

        String index = indexMatcher.group("taskIndex");
        if (index.length() != args.length()) {
            throw DukeException.INVALID_INDEX_FORMAT;
        }

        return Integer.parseInt(index);
    }

    /**
     * The method parses the argument segment of the user input
     * to look for the task name of a task and the associated tags if they exist.
     *
     * @param args The argument to be parsed.
     * @return The task name.
     * @throws DukeException Raised if the argument does not contain a task name.
     */
    public static String[] parseTodo(String args) throws DukeException {
        Matcher tagMatcher = TODO_TAG_FORMAT.matcher(args);
        if (!tagMatcher.matches()) {
            String taskName = args.trim();
            if (taskName.isEmpty()) {
                throw DukeException.INSUFFICIENT_TASK_SPECIFICATION;
            }
            return new String[] {taskName};
        } else {
            String[] allInfo;
            String taskName = tagMatcher.group("taskName").trim();
            String stickyTags = tagMatcher.group("tags").trim();
            String[] tags = stickyTags.split("#");

            allInfo = new String[1 + tags.length];
            allInfo[0] = taskName;
            for (int i = 0; i < tags.length; i++) {
                allInfo[i + 1] = tags[i].trim();
            }
            return allInfo;
        }
    }

    /**
     * The method parses the argument segment of the user input
     * to look for the task name and date of a time sensitive task and the associated tags if they exist.
     *
     * @param type The type of time sensitive task.
     * @param args The argument to be parsed.
     * @return An array containing the name and date of the task.
     * @throws DukeException Raised if the argument does not contain
     *      a task name and a date.
     */
    public static String[] parseTimedTask(String type, String args) throws DukeException {
        Matcher matcher;
        Matcher tagMatcher;
        if (type == Deadline.TYPE) {
            matcher = DEADLINE_FORMAT.matcher(args);
            tagMatcher = DEADLINE_TAG_FORMAT.matcher(args);
        } else if (type == Event.TYPE) {
            matcher = EVENT_FORMAT.matcher(args);
            tagMatcher = EVENT_TAG_FORMAT.matcher(args);
        } else {
            throw new RuntimeException("Internal Program Error");
        }

        if (!matcher.matches() && !tagMatcher.matches()) {
            throw DukeException.INSUFFICIENT_TASK_SPECIFICATION;
        }

        if (!tagMatcher.matches()) {
            String taskName = matcher.group("taskName").trim();
            String date = matcher.group("date");

            return new String[] {taskName, date};
        } else {
            String[] allInfo;
            String taskName = tagMatcher.group("taskName").trim();
            String date = tagMatcher.group("date");
            String stickyTags = tagMatcher.group("tags").trim();
            String[] tags = stickyTags.split("\\s*#");

            allInfo = new String[2 + tags.length];
            allInfo[0] = taskName;
            allInfo[1] = date;
            for (int i = 0; i < tags.length; i++) {
                allInfo[i + 2] = tags[i].trim();
            }

            return allInfo;
        }
    }

    /**
     * The method parses user input to determine the possible date the user
     *      will attach to a list command.
     *
     * @param args The arguments to be parsed.
     * @return The date that might be attached.
     * @throws DukeException
     */
    public static LocalDate parseList(String args) throws DukeException {
        String trimmed = args.trim();
        if (trimmed.isEmpty()) {
            return null;
        } else {
            try {
                return LocalDate.parse(trimmed);
            } catch (DateTimeParseException e) {
                throw DukeException.INVALID_DATE_FORMAT;
            }
        }
    }

    /**
     * The method parses user input to determine the keyword to use for the find command.
     *
     * @param args The arguments to be parsed.
     * @return The keyword to be included in the find command.
     * @throws DukeException
     */
    public static String parseFind(String args) throws DukeException {
        String trimmed = args.trim();
        if (trimmed.isEmpty()) {
            throw DukeException.MISSING_FIND_KEYWORD;
        }

        return trimmed;
    }
}

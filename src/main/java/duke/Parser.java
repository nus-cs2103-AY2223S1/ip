package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import duke.command.Action;
import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeRuntimeException;
import duke.exception.InvalidArgumentException;
import duke.exception.NoArgumentException;
import duke.exception.ReadAttributeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * The Parser for conversion between String and other types.
 */
public class Parser {
    /**
     * The String representation of the separator to divide a row of String into attributes.
     */
    private static final String ATTRIBUTE_SEPARATOR = "}";

    /**
     * Returns an Arraylist of all beginning indices of target in the whole String.
     *
     * @param str1 The targeted String.
     * @param str2 The whole String
     * @return The Arraylist of all beginning indices of target in the whole String.
     */
    public static ArrayList<Integer> getIndicesOfStr1InStr2(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();

        ArrayList<Integer> result = new ArrayList<>();
        Stream.iterate(0, x -> x + 1)
                .limit(len2 - len1 + 1)
                .filter(i -> str2.substring(i, i + len1).equals(str1))
                .forEach(result::add);
        return result;
    }

    /**
     * Returns the first index of all beginning indices of target in the whole String.
     *
     * @param str1 The targeted String.
     * @param str2 The whole String
     * @return The first index of all beginning indices of target in the whole String.
     */
    private static int getFirstIndexOfStr1InStr2(String str1, String str2) {
        ArrayList<Integer> indices = getIndicesOfStr1InStr2(str1, str2);
        return indices.size() == 0 ? -1 : indices.get(0);
    }

    /**
     * Returns an Arraylist of String separated as attributes from the given formatted String.
     *
     * @param string The given formatted String.
     * @return The Arraylist of String separated as attributes from the given formatted String.
     */
    public static ArrayList<String> separateAttributes(String string) {
        ArrayList<String> result = new ArrayList<>();
        Arrays.stream(string.split(ATTRIBUTE_SEPARATOR)).forEach(s -> result.add(s.trim()));
        return result;
    }

    /**
     * Returns the formatted String by combining attributes of Strings.
     *
     * @param strings The attributes of Array of Strings
     * @return The formatted String.
     */
    public static String combineAttributes(String... strings) {
        return Arrays.stream(strings)
                .reduce("", (x, y) -> x + " " + ATTRIBUTE_SEPARATOR + " " + y)
                .substring(ATTRIBUTE_SEPARATOR.length() + 2);
    }

    /**
     * Returns the Command indicated by given String.
     *
     * @param s The given String.
     * @return The Command.
     * @throws DukeException if the given String is not in correct format.
     */
    public static Command parseCommand(String s) throws DukeException {
        int indexOfName;
        int indexOfTime;
        String arg1;
        String arg2;

        s = s.trim();
        String[] words = s.split(" ");
        Action action = Action.getAction(words[0]);
        switch (action) {
        case GREET:
            return Command.greet();
        case EXIT:
            return Command.exit();
        case MARK:
            arg1 = s.substring(Action.getString(action).length()).trim();
            if (arg1.equals("")) {
                throw new NoArgumentException(action);
            } else if (!isInt(arg1)) {
                throw new InvalidArgumentException(action, "The argument should be an integer.");
            }
            return Command.mark(Integer.parseInt(arg1));
        case UNMARK:
            arg1 = s.substring(Action.getString(action).length()).trim();
            if (arg1.equals("")) {
                throw new NoArgumentException(action);
            } else if (!isInt(arg1)) {
                throw new InvalidArgumentException(action, "The argument should be an integer.");
            }
            return Command.unmark(Integer.parseInt(arg1));
        case LIST:
            return Command.list();
        case TODO:
            arg1 = s.substring(Action.getString(action).length()).trim();
            if (arg1.equals("")) {
                throw new NoArgumentException(action);
            } else if (!isValidString(arg1)) {
                throw new InvalidArgumentException(action,
                        "Todo details should not contain '}'.");
            }
            return Command.todo(arg1);
        case EVENT:
            String symbolEvent = " /at ";
            if (!s.contains(symbolEvent)) {
                throw new InvalidArgumentException(action, "Keyword: ["
                        + symbolEvent + " ] or [Time] is not found.");
            }
            indexOfName = Action.getString(action).length();
            indexOfTime = getFirstIndexOfStr1InStr2(symbolEvent, s);
            arg1 = s.substring(indexOfName, indexOfTime).trim();
            arg2 = s.substring(indexOfTime + symbolEvent.length()).trim();
            if (arg1.equals("") && arg2.equals("")) {
                throw new NoArgumentException(action);
            } else if (!isValidString(arg1)) {
                throw new InvalidArgumentException(action,
                        "Event [Name] is not found.");
            } else if (!isValidDate(arg2)) {
                throw new InvalidArgumentException(action,
                        "Event [Time] is not found.");
            }

            return Command.event(arg1, parseStringToDateTime(arg2));
        case DEADLINE:
            String symbolDeadline = " /by ";

            if (!s.contains(symbolDeadline)) {
                throw new InvalidArgumentException(action, "Keyword: ["
                        + symbolDeadline + " ] or [Time] is not found.");
            }

            indexOfName = Action.getString(action).length();
            indexOfTime = getFirstIndexOfStr1InStr2(symbolDeadline, s);
            arg1 = s.substring(indexOfName, indexOfTime).trim();
            arg2 = s.substring(indexOfTime + symbolDeadline.length()).trim();

            if (arg1.equals("") && arg2.equals("")) {
                throw new NoArgumentException(action);
            } else if (!isValidString(arg1)) {
                throw new InvalidArgumentException(action,
                        "Deadline [Name] is not found.");
            } else if (!isValidDate(arg2)) {
                throw new InvalidArgumentException(action,
                        "Deadline [Time] is not found.");
            }

            return Command.deadline(arg1, parseStringToDateTime(arg2));
        case DELETE:
            arg1 = s.substring(Action.getString(action).length()).trim();
            if (arg1.equals("")) {
                throw new NoArgumentException(action);
            } else if (!isInt(arg1)) {
                throw new InvalidArgumentException(action, "The argument should be an integer.");
            }
            return Command.delete(Integer.parseInt(arg1));
        case SAVE:
            return Command.save();
        case READ:
            return Command.read();
        case FIND:
            arg1 = s.substring(Action.getString(action).length()).trim();
            if (arg1.equals("")) {
                throw new NoArgumentException(action);
            } else if (!isValidString(arg1)) {
                throw new InvalidArgumentException(action, "The argument should be a String.");
            }
            return Command.find(arg1);
        default:
            return Command.doNothing();
        }
    }

    /**
     * Returns the Task represented by given formatted String.
     *
     * @param formattedString The given formatted String.
     * @return The Task represented by given formatted String.
     * @throws ReadAttributeException if the String is not in correct format.
     */
    public static Task parseTask(String formattedString) throws ReadAttributeException {
        ArrayList<String> attributes = Parser.separateAttributes(formattedString);
        switch (attributes.get(0)) {
        case Todo.SYMBOL:
            return parseTodo(formattedString);
        case Event.SYMBOL:
            return parseEvent(formattedString);
        case Deadline.SYMBOL:
            return parseDeadline(formattedString);
        default:
            throw new ReadAttributeException(
                    "Task", formattedString, "Task Symbol: [" + attributes.get(0) + "] is invalid.");
        }
    }

    /**
     * Returns the Event represented by given formatted String.
     *
     * @param formattedString The given formatted String.
     * @return The Event represented by given formatted String.
     * @throws ReadAttributeException if the String is not in correct format.
     */
    private static Event parseEvent(String formattedString) {
        ArrayList<String> attributes = Parser.separateAttributes(formattedString);
        if (attributes.size() < 4) {
            throw new ReadAttributeException("Event", formattedString, "Number of attributes less than 4");
        }
        Event result = Task.event(attributes.get(2), parseStringToDateTime(attributes.get(3)));
        if (convertIntToBool(Integer.parseInt(attributes.get(1))) == true) {
            result.markAsDone();
        }
        return result;
    }

    /**
     * Returns the Deadline represented by given formatted String.
     *
     * @param formattedString The given formatted String.
     * @return The Deadline represented by given formatted String.
     * @throws ReadAttributeException if the String is not in correct format.
     */
    private static Deadline parseDeadline(String formattedString) {
        ArrayList<String> attributes = Parser.separateAttributes(formattedString);
        if (attributes.size() < 4) {
            throw new ReadAttributeException("Deadline", formattedString, "Number of attributes less than 4");
        }
        Deadline result = Task.deadline(attributes.get(2), parseStringToDateTime(attributes.get(3)));
        if (convertIntToBool(Integer.parseInt(attributes.get(1))) == true) {
            result.markAsDone();
        }
        return result;
    }

    /**
     * Returns the Todo represented by given formatted String.
     *
     * @param formattedString The given formatted String.
     * @return The Todo represented by given formatted String.
     * @throws ReadAttributeException if the String is not in correct format.
     */
    private static Todo parseTodo(String formattedString) {
        ArrayList<String> attributes = Parser.separateAttributes(formattedString);
        if (attributes.size() < 3) {
            throw new ReadAttributeException("Todo", formattedString, "Number of attributes less than 3");
        }
        Todo result = Task.todo(attributes.get(2));
        if (Parser.convertIntToBool(Integer.parseInt(attributes.get(1))) == true) {
            result.markAsDone();
        }
        return result;
    }

    /**
     * Returns the TaskList represented by given formatted String.
     *
     * @param formattedString The given formatted String.
     * @return The TaskList represented by given formatted String.
     * @throws ReadAttributeException if the String is not in correct format.
     */
    public static TaskList parseTaskList(String formattedString) {
        TaskList result = new TaskList();
        Arrays.stream(formattedString.split(System.lineSeparator()))
                .filter(s -> !s.trim().equals(""))
                .map(s -> s.trim())
                .map(s -> Parser.parseTask(s))
                .forEach(task -> result.add(task));
        return result;
    }

    /**
     * Returns a boolean indicating whether the given String is a valid integer.
     *
     * @param s The given String
     * @return The boolean indicating whether the given String is a valid integer.
     */
    private static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Returns a boolean indicating whether the given String is a valid String.
     *
     * @param input The given String
     * @return The boolean indicating whether the given String is a valid String.
     */
    private static boolean isValidString(String input) {
        return input != null && !input.equals("") && !input.contains(ATTRIBUTE_SEPARATOR);
    }

    /**
     * Returns a boolean indicating whether the given String is a valid LocalDateTime.
     *
     * @param input The given String
     * @return The boolean indicating whether the given String is a valid LocalDateTime.
     */
    private static boolean isValidDate(String input) {
        try {
            LocalDateTime.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Returns the number representation of a given boolean.
     *
     * @param bool The given boolean.
     * @return The number representation of a given boolean.
     */
    public static int convertBoolToInt(boolean bool) {
        return bool ? 1 : 0;
    }

    /**
     * Returns the boolean from given number representation.
     *
     * @param i The given number representation.
     * @return The boolean.
     */
    public static boolean convertIntToBool(int i) {
        if (i == 1) {
            return true;
        } else if (i == 0) {
            return false;
        } else {
            throw new DukeRuntimeException(i + " is not defined when converting int to bool.");
        }
    }

    /**
     * Returns the String representation of the Attribute Separator in formatted String.
     *
     * @return The String representation of the Attribute Separator in formatted String.
     */
    public static String getAttributeSeparator() {
        return ATTRIBUTE_SEPARATOR;
    }

    /**
     * Returns the String representation of given LocalDateTime.
     *
     * @param localDateTime The given LocalDateTime.
     * @return The String representation of given LocalDateTime.
     */
    public static String parseDateTimeToString(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Returns the LocalDateTime from given String representation.
     *
     * @param string The given String representation.
     * @return The LocalDateTime from given String representation.
     */
    public static LocalDateTime parseStringToDateTime(String string) {
        return LocalDateTime.parse(string, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}

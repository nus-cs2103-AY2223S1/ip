package duke;

import duke.command.Action;
import duke.command.Command;
import duke.exception.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static duke.task.Task.*;

public class Parser {
    private static final String ATTRIBUTE_SEPARATOR = "}";

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

    private static int getFirstIndexOfStr1InStr2(String str1, String str2) {
        ArrayList<Integer> indices = getIndicesOfStr1InStr2(str1, str2);
        return indices.size() == 0 ? -1 : indices.get(0);
    }

    public static ArrayList<String> separateAttributes(String string) {
        ArrayList<String> result = new ArrayList<>();
        Arrays.stream(string.split(ATTRIBUTE_SEPARATOR)).forEach(s -> result.add(s.trim()));
        return result;
    }

    public static String combineAttributes(String... strings) {
        return Arrays.stream(strings)
                .reduce("", (x, y) -> x + " " + ATTRIBUTE_SEPARATOR + " " + y).substring(ATTRIBUTE_SEPARATOR.length() + 2);
    }

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
            arg1 = s.substring(Action.getString(Action.MARK).length()).trim();
            if (arg1.equals("")) {
                throw new NoArgumentException(Action.MARK);
            } else if (!isInt(arg1)) {
                throw new InvalidArgumentException(Action.MARK, "The argument should be an integer.");
            }
            return Command.mark(Integer.parseInt(arg1));
        case UNMARK:
            arg1 = s.substring(Action.getString(Action.UNMARK).length()).trim();
            if (arg1.equals("")) {
                throw new NoArgumentException(Action.UNMARK);
            } else if (!isInt(arg1)) {
                throw new InvalidArgumentException(Action.UNMARK, "The argument should be an integer.");
            }
            return Command.unmark(Integer.parseInt(arg1));
        case LIST:
            return Command.list();
        case TODO:
            arg1 = s.substring(Action.getString(Action.TODO).length()).trim();
            if (arg1.equals("")) {
                throw new NoArgumentException(Action.TODO);
            } else if (!isValidString(arg1)) {
                throw new InvalidArgumentException(Action.EVENT,
                        "Todo details should not contain '}'.");
            }
            return Command.todo(arg1);
        case EVENT:
            String START_OF_EVENT_TIME_SYMBOL = " /at ";
            if (!s.contains(START_OF_EVENT_TIME_SYMBOL)) {
                throw new InvalidArgumentException(Action.EVENT, "Keyword: [" +
                        START_OF_EVENT_TIME_SYMBOL + " ] or [Time] is not found.");
            }
            indexOfName = Action.getString(Action.EVENT).length();
            indexOfTime = getFirstIndexOfStr1InStr2(START_OF_EVENT_TIME_SYMBOL, s);
            arg1 = s.substring(indexOfName, indexOfTime).trim();
            arg2 = s.substring(indexOfTime + START_OF_EVENT_TIME_SYMBOL.length()).trim();
            if (arg1.equals("") && arg2.equals("")) {
                throw new NoArgumentException(Action.EVENT);
            } else if (!isValidString(arg1)) {
                throw new InvalidArgumentException(Action.EVENT,
                        "Event [Name] is not found.");
            } else if (!isValidDate(arg2)) {
                throw new InvalidArgumentException(Action.EVENT,
                        "Event [Time] is not found.");
            }

            return Command.event(arg1, parseStringToDateTime(arg2));
        case DEADLINE:
            String START_OF_DEADLINE_TIME_SYMBOL = " /by ";

            if (!s.contains(START_OF_DEADLINE_TIME_SYMBOL)) {
                throw new InvalidArgumentException(Action.DEADLINE, "Keyword: [" +
                        START_OF_DEADLINE_TIME_SYMBOL + " ] or [Time] is not found.");
            }

            indexOfName = Action.getString(Action.DEADLINE).length();
            indexOfTime = getFirstIndexOfStr1InStr2(START_OF_DEADLINE_TIME_SYMBOL, s);
            arg1 = s.substring(indexOfName, indexOfTime).trim();
            arg2 = s.substring(indexOfTime + START_OF_DEADLINE_TIME_SYMBOL.length()).trim();

            if (arg1.equals("") && arg2.equals("")) {
                throw new NoArgumentException(Action.DEADLINE);
            } else if (!isValidString(arg1)) {
                throw new InvalidArgumentException(Action.DEADLINE,
                        "Deadline [Name] is not found.");
            } else if (!isValidDate(arg2)) {
                throw new InvalidArgumentException(Action.DEADLINE,
                        "Deadline [Time] is not found.");
            }

            return Command.deadline(arg1, parseStringToDateTime(arg2));
        case DELETE:
            arg1 = s.substring(Action.getString(Action.DELETE).length()).trim();
            if (arg1.equals("")) {
                throw new NoArgumentException(Action.DELETE);
            } else if (!isInt(arg1)) {
                throw new InvalidArgumentException(Action.DELETE, "The argument should be an integer.");
            }
            return Command.delete(Integer.parseInt(arg1));
        case SAVE:
            return Command.save();
        case READ:
            return Command.read();
        default:
            return Command.doNothing();
        }
    }

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

    private static Event parseEvent(String formattedString) {
        ArrayList<String> attributes = Parser.separateAttributes(formattedString);
        if (attributes.size() < 4) {
            throw new ReadAttributeException("Event", formattedString, "Number of attributes less than 4");
        }
        Event result = event(attributes.get(2), parseStringToDateTime(attributes.get(3)));
        if (convertIntToBool(Integer.parseInt(attributes.get(1))) == true) {
            result.markAsDone();
        }
        return result;
    }

    private static Deadline parseDeadline(String formattedString) {
        ArrayList<String> attributes = Parser.separateAttributes(formattedString);
        if (attributes.size() < 4) {
            throw new ReadAttributeException("Deadline", formattedString, "Number of attributes less than 4");
        }
        Deadline result = deadline(attributes.get(2), parseStringToDateTime(attributes.get(3)));
        if (convertIntToBool(Integer.parseInt(attributes.get(1))) == true) {
            result.markAsDone();
        }
        return result;
    }

    private static Todo parseTodo(String formattedString) {
        ArrayList<String> attributes = Parser.separateAttributes(formattedString);
        if (attributes.size() < 3) {
            throw new ReadAttributeException("Todo", formattedString, "Number of attributes less than 3");
        }
        Todo result = todo(attributes.get(2));
        if (Parser.convertIntToBool(Integer.parseInt(attributes.get(1))) == true) {
            result.markAsDone();
        }
        return result;
    }

    public static TaskList parseTaskList(String formattedString) {
        TaskList result = new TaskList();
        Arrays.stream(formattedString.split(System.lineSeparator()))
                .filter(s -> !s.trim().equals(""))
                .map(s -> s.trim())
                .map(s -> Parser.parseTask(s))
                .forEach(task -> result.add(task));
        return result;
    }

    private static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isValidString(String input) {
        return input != null && !input.equals("") && !input.contains(ATTRIBUTE_SEPARATOR);
    }

    private static boolean isValidDate(String input) {
        try {
            LocalDateTime.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static int convertBoolToInt(boolean bool) {
        return bool ? 1 : 0;
    }

    public static boolean convertIntToBool(int i) {
        if (i == 1) {
            return true;
        } else if (i == 0) {
            return false;
        } else {
            throw new DukeRuntimeException(i + " is not defined when converting int to bool.");
        }
    }

    public static String getAttributeSeparator() {
        return ATTRIBUTE_SEPARATOR;
    }

    public static String parseDateTimeToString(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public static LocalDateTime parseStringToDateTime(String string) {
        return LocalDateTime.parse(string, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}

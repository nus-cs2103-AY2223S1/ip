import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

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

    public static int getFirstIndexOfStr1InStr2(String str1, String str2) {
        ArrayList<Integer> indices = getIndicesOfStr1InStr2(str1, str2);
        return indices.size() == 0 ? -1 : indices.get(0);
    }

    public static ArrayList<String> separateAttributes(String string) {
        ArrayList<String> result = new ArrayList<>();
        Arrays.stream(string.split(ATTRIBUTE_SEPARATOR)).forEach(s -> result.add(s.trim()));
        return result;
    }

    public static String combineAttributes(ArrayList<String> arrayList) {
        return arrayList.stream()
                .reduce("", (x, y) -> x + " " + ATTRIBUTE_SEPARATOR + " " + y).substring(ATTRIBUTE_SEPARATOR.length() + 2);
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
                throw new DukeException.NoArgumentException(Action.MARK);
            } else if (!isInt(arg1)) {
                throw new DukeException.InvalidArgumentException(Action.MARK, "The argument should be an integer.");
            }
            return Command.mark(Integer.parseInt(arg1));
        case UNMARK:
            arg1 = s.substring(Action.getString(Action.UNMARK).length()).trim();
            if (arg1.equals("")) {
                throw new DukeException.NoArgumentException(Action.UNMARK);
            } else if (!isInt(arg1)) {
                throw new DukeException.InvalidArgumentException(Action.UNMARK, "The argument should be an integer.");
            }
            return Command.unmark(Integer.parseInt(arg1));
        case LIST:
            return Command.list();
        case TODO:
            arg1 = s.substring(Action.getString(Action.TODO).length()).trim();
            if (arg1.equals("")) {
                throw new DukeException.NoArgumentException(Action.TODO);
            } else if (!isValidString(arg1)) {
                throw new DukeException.InvalidArgumentException(Action.EVENT,
                        "Todo details should not contain '}'.");
            }
            return Command.todo(arg1);
        case EVENT:
            String START_OF_EVENT_TIME_SYMBOL = " /at ";
            if (!s.contains(START_OF_EVENT_TIME_SYMBOL)) {
                throw new DukeException.InvalidArgumentException(Action.EVENT, "Keyword: [" +
                        START_OF_EVENT_TIME_SYMBOL + " ] or [Time] is not found.");
            }
            indexOfName = Action.getString(Action.EVENT).length();
            indexOfTime = getFirstIndexOfStr1InStr2(START_OF_EVENT_TIME_SYMBOL, s);
            arg1 = s.substring(indexOfName, indexOfTime).trim();
            arg2 = s.substring(indexOfTime + START_OF_EVENT_TIME_SYMBOL.length()).trim();
            if (arg1.equals("") && arg2.equals("")) {
                throw new DukeException.NoArgumentException(Action.EVENT);
            } else if (!isValidString(arg1)) {
                throw new DukeException.InvalidArgumentException(Action.EVENT,
                        "Event [Name] is not found.");
            } else if (!isValidDate(arg2)) {
                throw new DukeException.InvalidArgumentException(Action.EVENT,
                        "Event [Time] is not found.");
            }

            return Command.event(arg1, parseStringToDateTime(arg2));
        case DEADLINE:
            String START_OF_DEADLINE_TIME_SYMBOL = " /by ";

            if (!s.contains(START_OF_DEADLINE_TIME_SYMBOL)) {
                throw new DukeException.InvalidArgumentException(Action.DEADLINE, "Keyword: [" +
                        START_OF_DEADLINE_TIME_SYMBOL + " ] or [Time] is not found.");
            }

            indexOfName = Action.getString(Action.DEADLINE).length();
            indexOfTime = getFirstIndexOfStr1InStr2(START_OF_DEADLINE_TIME_SYMBOL, s);
            arg1 = s.substring(indexOfName, indexOfTime).trim();
            arg2 = s.substring(indexOfTime + START_OF_DEADLINE_TIME_SYMBOL.length()).trim();

            if (arg1.equals("") && arg2.equals("")) {
                throw new DukeException.NoArgumentException(Action.DEADLINE);
            } else if (!isValidString(arg1)) {
                throw new DukeException.InvalidArgumentException(Action.DEADLINE,
                        "Deadline [Name] is not found.");
            } else if (!isValidDate(arg2)) {
                throw new DukeException.InvalidArgumentException(Action.DEADLINE,
                        "Deadline [Time] is not found.");
            }

            return Command.deadline(arg1, parseStringToDateTime(arg2));
        case DELETE:
            arg1 = s.substring(Action.getString(Action.DELETE).length()).trim();
            if (arg1.equals("")) {
                throw new DukeException.NoArgumentException(Action.DELETE);
            } else if (!isInt(arg1)) {
                throw new DukeException.InvalidArgumentException(Action.DELETE, "The argument should be an integer.");
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
            LocalDate.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            return true;
        } catch (Exception e) {
            return false;
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

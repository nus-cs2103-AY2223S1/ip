import java.util.HashMap;
import java.util.Map;

public class CommandParser {

    private static final String BY_DATE_DELIMITER = "/by";
    private static final String AT_DATE_DELIMITER = "/at";

    static int getIndexOfFirstWhiteSpace(String input) {
        int indexOfFirstWhiteSpace = 0;
        for (; indexOfFirstWhiteSpace < input.length(); indexOfFirstWhiteSpace++) {
            if (input.charAt(indexOfFirstWhiteSpace) == ' ') {
                break;
            }
        }
        return indexOfFirstWhiteSpace;
    }

    static String getFirstWord(String input) {
        int indexOfFirstWhiteSpace = getIndexOfFirstWhiteSpace(input);
        return input.substring(0, indexOfFirstWhiteSpace);
    }

    static String getByDate(String input) {
        int indexOfDelimiter = input.indexOf(BY_DATE_DELIMITER);
        if (indexOfDelimiter == -1) {
            return null;
        }
        return input.substring(indexOfDelimiter + BY_DATE_DELIMITER.length(), input.length());
    }

    static String getAtDate(String input) {
        int indexOfDelimiter = input.indexOf(AT_DATE_DELIMITER);
        if (indexOfDelimiter == -1) {
            return null;
        }
        return input.substring(indexOfDelimiter + AT_DATE_DELIMITER.length(), input.length());
    }

    static String getTaskTitle(String input) {
        int indexOfFirstWhiteSpace = getIndexOfFirstWhiteSpace(input);
        int indexOfSecondWhiteSpace = indexOfFirstWhiteSpace
                + getIndexOfFirstWhiteSpace(input.substring(indexOfFirstWhiteSpace + 1, input.length()))
                + 1;
        return input.substring(indexOfFirstWhiteSpace + 1, indexOfSecondWhiteSpace);
    }

    private Map<String, CommandType> commandTypeMap;

    CommandParser() {
        commandTypeMap = new HashMap<>();
        CommandType[] commandTypes = CommandType.class.getEnumConstants();
        for (CommandType type: commandTypes) {
            commandTypeMap.put(type.getCommandString(), type);
        }
    }

    Actionable parse(String commandString) {
        return new Command(CommandType.EXIT) {
            @Override
            public void takeAction() {

            }
        };
    }
}

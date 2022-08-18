import java.util.HashMap;
import java.util.Map;

public class CommandParser {

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

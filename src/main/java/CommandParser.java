import java.util.HashMap;
import java.util.Map;

public class CommandParser {

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

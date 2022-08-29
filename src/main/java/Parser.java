import java.util.HashMap;
import java.util.Map;

class Parser {
    private Map<String, Command> commands = new HashMap<>();

    public void addCommand (String commandKey, Command command) {
        commands.put(commandKey, command);
    }

    public void executeCommand (String input) throws DukeException {
        String arr[] = input.split(" ", 2);
        if (!commands.containsKey(arr[0])) {
            throw DukeException.invalidCommand;
        }
        Command command = commands.get(arr[0]);
        if (arr.length < 2) {
            command.execute("");
        } else {
            command.execute(arr[1]);
        }
    }
}

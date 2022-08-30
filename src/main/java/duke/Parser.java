package duke;

import java.util.HashMap;
import java.util.Map;

/**
 * A parser class to insert in command keys with their respective command functions.
 */
class Parser {
    private Map<String, Command> commands = new HashMap<>();

    /**
     * Maps a command to a commandKey
     *
     * @param commandKey keyword or the first word of the command.
     * @param command A function to be executed when command is called.
     */
    public void addCommand(String commandKey, Command command) {
        commands.put(commandKey, command);
    }

    /**
     * Gets the first word from input and executes the command assigned to it
     *
     * @param input command made of commandKey and argument.
     * @throws DukeException during execution of the command or if commandKey does not exist.
     */
    public void executeCommand(String input) throws DukeException {
        String[] arr = input.split(" ", 2);
        if (!commands.containsKey(arr[0])) {
            throw DukeException.INVALIDCOMMAND;
        }
        Command command = commands.get(arr[0]);
        if (arr.length < 2) {
            command.execute("");
        } else {
            command.execute(arr[1]);
        }
    }
}

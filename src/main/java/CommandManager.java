import java.util.ArrayList;

/**
 * Utility class to manage the list of commands and provides augmenting operations to
 * add to the list of commands or to enumerate through the list of commands
 */
public class CommandManager {
    // The list of commands
    private final ArrayList<String> commandList;

    public CommandManager() {
        this.commandList = new ArrayList<>();
    }

    /**
     * Adds the received command into the command list
     *
     * @param command Command received from the caller
     */
    public void add(String command) {
        this.commandList.add(command);
    }

    /**
     * Return the list of commands
     *
     * @return List of commands
     */
    public String[] list() {
        String[] commands = new String[this.commandList.size()];
        for (int i = 0; i < this.commandList.size(); i++) {
            commands[i] = this.commandList.get(i);
        }
        return commands;
    }
}

import java.util.List;
import java.util.ArrayList;
/**
 * Stores a list of commands entered by the user
 */
public class CommandList {
    private final List<String> commands;

    CommandList() {
        commands = new ArrayList<>();
    }

    /**
     * Adds a command to the command list
     * @param newCommand the command to be added
     */
    public void add(String newCommand) {
        commands.add(newCommand);
    }

    /**
     * Gets a String representation of all commands in the list
     * @return a String representing all commands in the list
     */
    public String getAllCommands() {
        String result = "Choo choo! Here are the commands you've entered:";
        for (int i = 0; i < commands.size(); i++) {
            result += "\n  ";
            String command = commands.get(i);
            int commandNum = i + 1;
            result += commandNum + ". " + command;
        }
        return result;
    }
}

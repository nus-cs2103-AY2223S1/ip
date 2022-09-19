package commands;

import java.util.Map;
import java.util.StringJoiner;

import exceptions.DukeException;
import input.Input;



/**
 * Command to see help message for all or specific command
 */
public class HelpCommand extends Command {
    private Map<String, Command> commandMap;

    /**
     * Creates a Help command
     * @param commandMap Map of all command names to commands, possibly including help command itself
     */
    public HelpCommand(Map<String, Command> commandMap) {
        super("help", "Provides help");
        this.commandMap = commandMap;

    }

    @Override
    public CommandResponse run(Input input) throws DukeException {
        if (input.getNumberOfTokens() == 1) {
            StringJoiner joiner = new StringJoiner(", ");

            for (Command cmd : commandMap.values()) {
                joiner.add(cmd.commandName);
            }
            return new CommandResponse(joiner.toString());
        }

        String cmdName = input.getTokenAtIndex(1);
        assert cmdName != null;

        if (!commandMap.containsKey(cmdName)) {
            return new CommandResponse(String.format("I do not recognise the command '%s'", cmdName));
        }
        return new CommandResponse(commandMap.get(cmdName).getUsageDescription());
    }
    @Override
    public String getUsageDescription() {
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add(usageDescription);
        joiner.add("help: lists available commands");
        joiner.add("help <command name>: lists help for specific command e.g help todo");
        return joiner.toString();
    }
}

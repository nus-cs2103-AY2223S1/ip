package jarvis.command;

import java.util.Arrays;
import java.util.Optional;

import jarvis.JarvisException;
import jarvis.storage.Storage;
import jarvis.task.TaskList;

/**
 * Command --- handles the different types of commands.
 */
public abstract class Command {
    private final String keyCommand;
    private final Optional<String> arguments;

    /**
     * Constructor.
     *
     * @param command the command entered by the user.
     */
    public Command(String command) {
        String[] splitCommands = command.split(" ", 2);
        this.keyCommand = splitCommands[0];
        if (splitCommands.length > 1) {
            this.arguments = Optional.of(splitCommands[1]);
        } else {
            this.arguments = Optional.empty();
        }
    }

    String[] splitArguments() {
        return arguments.map(s -> Arrays.stream(s.split("/by|/at", 2)).map(String::trim)
                .toArray(String[]::new)).orElse(null);
    }

    /**
     * Get the main command entered by the user.
     *
     * @return the main command entered by the user.
     */
    public String getKeyCommand() {
        return this.keyCommand;
    }

    String getDescription() throws JarvisException {
        if (arguments.isEmpty()) {
            throw new JarvisException("Invalid. Please provide a description.");
        }
        return splitArguments()[0];
    }

    String getDate() throws JarvisException {
        String[] split = splitArguments();
        if (split.length == 1 || split[1].length() == 0) {
            throw new JarvisException("Invalid. Please provide a date.");
        }
        return split[1];
    }

    int getTaskIndex() throws JarvisException {
        if (arguments.isPresent()) {
            return Integer.parseInt(arguments.get()) - 1;
        } else {
            throw new JarvisException("No task found. Please enter a valid task number.");
        }
    }

    /**
     * Executes the command.
     *
     * @param tasks the list of tasks.
     * @param storage stores the tasks locally.
     * @return response after executing the command.
     * @throws JarvisException exception for invalid commands.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws JarvisException;
}

package jarvis.command;

import java.util.Arrays;
import java.util.Optional;

import jarvis.JarvisException;
import jarvis.storage.Storage;
import jarvis.task.TaskList;
import jarvis.ui.Ui;

/**
 * Command --- handles the different types of commands.
 */
public abstract class Command {
    private final String command;
    private final Optional<String> arguments;
    private final Optional<String> description;
    private final Optional<String> dateTime;

    /**
     * Constructor.
     *
     * @param command the command entered by the user.
     */
    public Command(String command) {
        assert command != null;
        this.command = command;
        String[] splitCommands = command.split(" ", 2);
        if (splitCommands.length == 2) {
            this.arguments = Optional.of(splitCommands[1]);
            String[] splitArguments = this.arguments.map(dateTime -> Arrays.stream(dateTime.split("/at|/by", 2))
                    .map(String::trim).toArray(String[]::new)).orElse(null);
            this.description = Optional.of(splitArguments[0]);
            if (splitArguments.length == 2) {
                this.dateTime = Optional.of(splitArguments[1]);
            } else {
                this.dateTime = Optional.empty();
            }
        } else {
            this.arguments = Optional.empty();
            this.description = Optional.empty();
            this.dateTime = Optional.empty();
        }
    }

    /**
     * Returns the main command entered by the user.
     *
     * @return main command entered by the user.
     */
    public String getKeyCommand() {
        String[] splitCommand = command.split(" ", 2);
        return splitCommand[0];
    }

    String getDescription() throws JarvisException {
        if (this.description.isPresent()) {
            return this.description.get();
        }
        throw new JarvisException("Invalid. Please provide a description.");
    }

    String getDateTime() throws JarvisException {
        if (this.dateTime.isPresent()) {
            return this.dateTime.get();
        }
        throw new JarvisException("Invalid. Please provide a date for deadlines or date and time for events.");
    }

    int getTaskIndex() throws JarvisException {
        if (this.arguments.isPresent()) {
            return Integer.parseInt(this.arguments.get()) - 1;
        } else {
            throw new JarvisException("No task found. Please enter a valid task number.");
        }
    }

    /**
     * Executes the command.
     *
     * @param storage stores the tasks locally.
     * @param tasks the list of tasks.
     * @param ui prints feedback.
     * @return response after executing the command.
     * @throws JarvisException exception for invalid commands.
     */
    public abstract String execute(Storage storage, TaskList tasks, Ui ui) throws JarvisException;
}

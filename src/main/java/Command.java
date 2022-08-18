import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public abstract class Command {
    private final String keyCommand;
    private final Optional<String> arguments;

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

    String getKeyCommand() {
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

    public abstract String execute(List<Task> tasks) throws JarvisException;
}

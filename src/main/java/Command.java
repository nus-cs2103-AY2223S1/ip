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

    String getDescription() {
        if (arguments.isEmpty()) {
            return null;
        }
        return splitArguments()[0];
    }

    String getDate() {
        String[] split = splitArguments();
        if (split.length == 1 || split[1].length() == 0) {
            return null;
        }
        return split[1];
    }

    int getTaskIndex() {
        if (arguments.isPresent()) {
            return Integer.parseInt(arguments.get()) - 1;
        } else {
            return -1;
        }
    }

    public abstract String execute(List<Task> tasks);
}

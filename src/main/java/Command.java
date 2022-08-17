import java.util.Arrays;
import java.util.Optional;

public class Command {
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
}

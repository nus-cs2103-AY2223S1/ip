package seedu.duke;
import java.util.ArrayList;
import java.util.Arrays;



public class Parser {
    private final ArrayList<String> stringOfCommands;
    public Parser() {
        this.stringOfCommands = new ArrayList<>(Arrays.asList("BYE",
        "DEADLINE", "DELETE", "EVENT", "FIND", "LIST", "MARK", "TODO", "UNMARK"));
    }

    public Command parse(String input) {
        String commandString = input.split(" ")[0].toUpperCase();
        Command command = Command.DEFAULT;
        if (stringOfCommands.contains(commandString)) {
            command = Command.valueOf(commandString);
        }
        return command;

    }
}

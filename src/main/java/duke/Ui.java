package duke;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents an interface that interacts with the user by printing the necessary messages
 */
public class Ui {

    static final List<String> COMMANDS = Stream.of(
            "todo", "deadline", "event",
            "list", "mark", "unmark",
            "find", "sort", "delete",
            "bye").collect(Collectors.toList());

    String showWelcome() {
        return "Hello! I'm Duke! What can I do for you?";
    }

    String showCommands() {
        String commandList = "List of commands possible:\n";
        for (String command : COMMANDS) {
            commandList += "-" +  command + "\n";
        }
        return commandList;
    }

    
    String showError(Exception e){
        return e.getMessage();
    }

}

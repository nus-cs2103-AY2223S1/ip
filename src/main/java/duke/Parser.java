package duke;

import duke.command.Command;

import java.time.LocalDate;

/**
 * Parses the user input and returns the specified command
 */
public class Parser {

    /**
     * Takes the command given by the user, extracts the verb in the
     * input and translates it into a command
     *
     * @param command
     * @return
     */
    public static void parse(String command) {
        // Idea from : https://stackoverflow.com/questions/70683058/using-startswith-in-switch-case-in-java
        String verb = command.split(" ")[0];
        switch (verb) {
            case Constants.EVENT_STRING:
                String eventDescription = command.split(" ")[1];
                LocalDate eventDate = LocalDate.parse(command.split(" ")[3]);
        }
    }
}

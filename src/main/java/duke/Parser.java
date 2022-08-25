package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.models.Event;
import duke.models.Task;

import java.time.LocalDate;

/**
 * This class returns a command based on the user input
 */
public class Parser {
    private Ui ui;

    public Parser(Ui ui) {
        this.ui = ui;
    }

    /**
     * Requirement: deals with making sense of the user command
     *
     * Takes the input given by the user, extracts the verb
     * and translates it into a command
     *
     * @param command
     * @return
     */
    public Command parse(String command) {
        // Idea from : https://stackoverflow.com/questions/70683058/using-startswith-in-switch-case-in-java
        String verb = command.split(Constants.EMPTY_SPACE)[0];
        switch (verb) {
            case Constants.EVENT_STRING:
                String eventDescription = command.split(Constants.EMPTY_SPACE)[1];
                LocalDate eventDate = LocalDate.parse(command.split(Constants.EMPTY_SPACE)[3]);
                Task t = new Event(eventDescription, eventDate);
                return new AddCommand(t);
            case Constants.BYE_STRING:
                this.ui.showByeMessage();
            default:
                return null;
        }
    }
}

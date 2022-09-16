package duke.parser;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

public class Parser {
    private static final String ASSERTION_INPUT_TOO_SHORT = "Invalid Input: Input is too short";

    private static void assertValidInput(String input) {
        assert input.length() > 0: ASSERTION_INPUT_TOO_SHORT;
    }

    /**
     * Returns a ToDo object based on parsed input
     *
     * @param input
     * @return
     */
    public static ToDo generateToDoFromInput(String input) {
        assertValidInput(input);
        String[] commands = input.split(" ");
        String description = "";
        for (int i = 1; i < commands.length; ++i) {
            description += commands[i] + " ";
        }
        description = description.substring(0, description.length() - 1);
        return new ToDo(description);
    }

    /**
     * Returns a Deadline object based on parsed input
     *
     * @param input
     * @return
     */
    public static Deadline generateDeadlineFromInput(String input) {
        assertValidInput(input);
        String[] commands = input.split(" ");
        String description = "";
        String timeQualifier = "";
        String timeDescription = "";

        int timeQualifierIndex = 0;

        for (int i = 1; i < commands.length; ++i) {
            if (commands[i].charAt(0) == '/') {
                timeQualifierIndex = i;
                break;
            }
            description += commands[i] + " ";
        }
        description = description.substring(0, description.length() - 1);

        timeQualifier = commands[timeQualifierIndex].substring(1);

        for (int i = timeQualifierIndex + 1; i < commands.length; ++i) {
            timeDescription += commands[i] + " ";
        }

        timeDescription = timeDescription.substring(0, timeDescription.length() - 1);

        return  new Deadline(description, timeQualifier, timeDescription);
    }

    /**
     * Returns an Event object based on parsed input
     *
     * @param input
     * @return
     */
    public static Event generateEventFromInput(String input) {
        assertValidInput(input);
        String[] commands = input.split(" ");
        String description = "";
        String timeQualifier = "";
        String timeDescription = "";

        int timeQualifierIndex = 0;

        for (int i = 1; i < commands.length; ++i) {
            if (commands[i].charAt(0) == '/') {
                timeQualifierIndex = i;
                break;
            }
            description += commands[i] + " ";
        }
        description = description.substring(0, description.length() - 1);

        timeQualifier = commands[timeQualifierIndex].substring(1);

        for (int i = timeQualifierIndex + 1; i < commands.length; ++i) {
            timeDescription += commands[i] + " ";
        }

        timeDescription = timeDescription.substring(0, timeDescription.length() - 1);

        return new Event(description, timeQualifier, timeDescription);
    }

}

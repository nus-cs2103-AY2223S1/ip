package duke;
import duke.command.Command;
import duke.command.ByeCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.ScheduleCommand;
import duke.command.FindCommand;
import duke.command.SnoozeCommand;

/*
Makes sense of user command
*/
public class Parser {

    /**
     * Extracts command word from user input
     *
     * @param userInput String of user input.
     * @return The extracted command word.
     */
    public static Command parseUserInput(String userInput) throws DukeException {
        String[] splitUserInput = userInput.split(" ");

        switch (splitUserInput[0]) {
            case "bye":
                return new ByeCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(getTaskName(userInput));
            case "unmark":
                return new UnmarkCommand(getTaskName(userInput));
            case "delete":
                return new DeleteCommand(getTaskName(userInput));
            case "schedule":
                return new ScheduleCommand(getDate(userInput));
            case "find":
                return new FindCommand(splitUserInput[1]);
            case "snooze":
                return new SnoozeCommand(getTaskName(userInput), getDate(userInput));
            case "todo":
                return new AddCommand(getTaskName(userInput), splitUserInput[0]);
            case "deadline":
            case "event":
                return new AddCommand(getTaskName(userInput), getDate(userInput), splitUserInput[0]);
        }

        throw new DukeException("i don't think i know this command :(");

    }

    /**
     * Extracts task name from userInput.
     *
     * @param userInput String of user input.
     * @return The extracted task name.
     */
    public static String getTaskName(String userInput) {
        assert(userInput.length() != 0);
        if (userInput.contains("/")) {
            return userInput.substring(userInput.indexOf(" ") + 1, userInput.indexOf("/") - 1).trim();
        }
        return userInput.substring(userInput.indexOf(" ") + 1).trim();
    }

    /**
     * Extracts date from userInput.
     *
     * @param userInput String of user input.
     * @return The extracted date.
     */
    public static String getDate(String userInput) throws DukeException {
        assert(userInput.length() != 0);

        String[] splitUserInput = userInput.split(" ");

        if (splitUserInput.length == 1) {
            throw new DukeException("please input a date in the format YYYY-MM-DD");
        }
        if (userInput.contains("/")) {
            return userInput.substring(userInput.indexOf("/") + 4);
        }
        return splitUserInput[1];
    }
}

package duke;

import duke.commands.Command;
import duke.commands.CommandsList;

/**
 * Parser to parse the text inputs from the user into Command objects.
 */
public class Parser {

    /**
     * Parse the text inputs from the user into Command objects.
     * @param rawInput Takes in the raw String provided by the user.
     * @return A Command object representing the parsed command.
     */
    public Command parse(String rawInput) {
        String[] input = rawInput.trim().split(" ", 2);
        switch (input[0].trim()) {
        case "list":
            return new Command(CommandsList.LIST);

        case "todo":
            try {
                return new Command(CommandsList.TODO, input[1].trim());
            } catch (ArrayIndexOutOfBoundsException e) {
                return new Command(CommandsList.ERROR,
                        "Whoops! todo needs a description of the task Dattebayo!\n    'todo <Task>'");
            }

        case "deadline":
            try {
                String[] splitArgs = input[1].split(" /by ", 2);
                String checkSecondArg = splitArgs[1];
                return new Command(CommandsList.DEADLINE, splitArgs);
            } catch (ArrayIndexOutOfBoundsException e) {
                return new Command(CommandsList.ERROR, "Whoops! deadline needs a description of the task "
                        + "and due date Dattebayo!" + "\n    'deadline <Task> /by <Due By>'");
            }

        case "event":
            try {
                String[] splitArgs = input[1].split(" /at ", 2);
                String checkSecondArg = splitArgs[1];
                return new Command(CommandsList.EVENT, splitArgs);
            } catch (ArrayIndexOutOfBoundsException e) {
                return new Command(CommandsList.ERROR, "Whoops! event needs a description of the task "
                        + "and time Dattebayo!" + "\n    'event <Task> /by <Time>'");
            }

        case "mark":
            try {
                return new Command(CommandsList.MARK, input[1].trim());
            } catch (NumberFormatException e) {
                return new Command(CommandsList.ERROR, "Whoops! it seems you your index is not an integer Dattebayo!"
                        + "\n'mark <Index>'");
            } catch (ArrayIndexOutOfBoundsException e) {
                return new Command(CommandsList.ERROR, "Whoops! mark needs the index of the item Dattebayo!"
                        + "\n    'mark <Index>'");
            }

        case "unmark":
            try {
                return new Command(CommandsList.UNMARK, input[1].trim());
            } catch (ArrayIndexOutOfBoundsException e) {
                return new Command(CommandsList.ERROR, "Whoops! unmark needs the index of the item Dattebayo!"
                        + "\n    'unmark <Index>'");
            }

        case "delete":
            try {
                return new Command(CommandsList.DELETE, input[1].trim());
            } catch (ArrayIndexOutOfBoundsException e) {
                return new Command(CommandsList.ERROR, "Whoops! delete needs the index of the item Dattebayo!"
                        + "\n    'delete <Index>'");
            }

        case "find":
            try {
                return new Command(CommandsList.FIND, input[1].trim());
            } catch (ArrayIndexOutOfBoundsException e) {
                String response = "Whoops! find needs a parameter to search for "
                        + "\n    'find <Parameter>'";
                return new Command(CommandsList.ERROR, response);
            }

        case "bye":
            return new Command(CommandsList.BYE);

        case "help":
            return new Command(CommandsList.HELP);

        default:
            return new Command(CommandsList.UNKNOWN);
        }
    }
}

package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.EventCommand;
import duke.commands.ExpenseCommand;
import duke.commands.FindCommand;
import duke.commands.HelpCommand;
import duke.commands.ListCommand;
import duke.commands.NumericCommand;
import duke.commands.TodoCommand;



/**
 * Class that manages the input text.
 */
public class Parser {

    /**
     * Default constructor of the Parser class.
     */
    public Parser() {}

    /**
     * Returns the Command object after processing an input text.
     * List of commands include: bye, list, mark, unmark, delete, todo, deadline, event.
     *
     * @param input Input text to be parsed into a command.
     * @return Command object to be executed.
     * @throws DukeException If input text is not in the correct form.
     */
    public Command parseInput(String input) throws DukeException {
        String[] inputStringArray = input.split(" ");
        String commandText = inputStringArray[0];
        switch (inputStringArray[0]) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "help":
            if (inputStringArray.length == 1) {
                return new HelpCommand("all");
            }
            String helpString = inputStringArray[1];
            return new HelpCommand(helpString);
        case "mark":
        case "unmark":
        case "delete":
            validateArgument(inputStringArray);
            int[] integerArgumentsArray = new int[inputStringArray.length - 1];
            for (int i = 0; i < integerArgumentsArray.length; i++) {
                integerArgumentsArray[i] = Integer.parseInt(inputStringArray[i+1]) - 1;
            }
            return new NumericCommand(commandText, integerArgumentsArray);
        case "todo":
            validateArgument(inputStringArray);
            String todoName = combineStringArray(inputStringArray, 1, inputStringArray.length);
            return new TodoCommand(todoName);
        case "deadline":
            validateArgument(inputStringArray);
            int bySplitter = Arrays.asList(inputStringArray).indexOf("/by");
            if (bySplitter == -1) {
                throw new DukeException("Please enter /by for your deadline!");
            }
            String deadlineName = combineStringArray(inputStringArray, 1, bySplitter);
            String deadlineDateString = combineStringArray(inputStringArray, bySplitter + 1, inputStringArray.length);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime date = LocalDateTime.parse(deadlineDateString, formatter);
            return new DeadlineCommand(deadlineName, date);

        case "event":
            validateArgument(inputStringArray);
            int atSplitter = Arrays.asList(inputStringArray).indexOf("/at");
            if (atSplitter == -1) {
                throw new DukeException("Please enter /at for your event!");
            }
            String eventName = combineStringArray(inputStringArray, 1, atSplitter);
            String eventLocationString = combineStringArray(inputStringArray, atSplitter + 1, inputStringArray.length);
            return new EventCommand(eventName, eventLocationString);

        case "expense":
            validateArgument(inputStringArray);
            int amtSplitter = Arrays.asList(inputStringArray).indexOf("/amt");
            if (amtSplitter == -1) {
                throw new DukeException("Please enter /amt for your expense!");
            }
            String expenseName = combineStringArray(inputStringArray, 1, amtSplitter);
            int expenseAmount = Integer.parseInt(inputStringArray[amtSplitter + 1]);
            return new ExpenseCommand(expenseName, expenseAmount);

        case "find":
            validateArgument(inputStringArray);
            String searchKeyword = inputStringArray[1];
            return new FindCommand(searchKeyword);

        default:
            throw new DukeException("No suitable command with that name!\nPlease type help for a list of available commands");
        }
    }

    private void validateArgument(String[] inputStringArray) throws DukeException {
        if (inputStringArray.length == 1) {
            throw new DukeException("Please enter an argument after the command");
        }
    }

    private String combineStringArray(String[] inputStringArray, int start, int end) {
        assert end <= inputStringArray.length && start >= 0;
        String[] subArray = Arrays.asList(inputStringArray).subList(start, end).toArray(new String[0]);
        String combinedString = Arrays.stream(subArray).reduce("", (a, b) -> a + " " + b).trim();
        return combinedString;
    }


}

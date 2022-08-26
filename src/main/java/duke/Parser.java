package duke;

import duke.commands.Command;
import duke.commands.ByeCommand;
import duke.commands.ListCommand;
import duke.commands.NumericCommand;
import duke.commands.TodoCommand;
import duke.commands.DeadlineCommand;
import duke.commands.EventCommand;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Parser {
    private boolean isAcceptingInput;

    public Parser(boolean isAcceptingInput) {
        this.isAcceptingInput = isAcceptingInput;
    }

    public boolean getIsAcceptingInput() {
        return this.isAcceptingInput;
    }

    public Command parseInput(String input) throws DukeException {
        String[] inputStringArray = input.split(" ");
        String commandText = inputStringArray[0];
        switch (inputStringArray[0]) {
        case "bye":
            this.isAcceptingInput = false;
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "mark":
        case "unmark":
        case "delete":
            validateArgument(inputStringArray);
            int index = Integer.parseInt(inputStringArray[1]) - 1;
            return new NumericCommand(commandText, index);
        case "todo":
            validateArgument(inputStringArray);
            String todoName = combineStringArray(inputStringArray, 1, inputStringArray.length);
            return new TodoCommand(todoName);
        case "deadline":
            validateArgument(inputStringArray);
            int bySplitter = Arrays.asList(inputStringArray).indexOf("/by");

            String deadlineName = combineStringArray(inputStringArray, 1, bySplitter);
            String deadlineDateString = combineStringArray(inputStringArray, bySplitter + 1, inputStringArray.length);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime date = LocalDateTime.parse(deadlineDateString, formatter);
            return new DeadlineCommand(deadlineName, date);

        case "event":
            validateArgument(inputStringArray);
            int atSplitter = Arrays.asList(inputStringArray).indexOf("/at");
            String eventName = combineStringArray(inputStringArray, 1, atSplitter);
            String eventLocationString = combineStringArray(inputStringArray, atSplitter + 1, inputStringArray.length);
            return new EventCommand(eventName, eventLocationString);

        default:
            throw new DukeException("No suitable name for that task");
        }
    }

    public void validateArgument(String[] inputStringArray) throws DukeException {
        if (inputStringArray.length == 1) {
            throw new DukeException("Please enter an argument (number) after mark!");
        }
    }

    public String combineStringArray(String[] inputStringArray, int start, int end) {
        String[] subArray =  Arrays.asList(inputStringArray).subList(start,end).toArray(new String[0]);
        String combinedString = Arrays.stream(subArray).reduce("", (a,b) -> a + " "+ b).trim();
        return combinedString;
    }


}

package duke;

import command.Command;
import command.ByeCommand;
import command.ListCommand;
import command.MarkCommand;
import command.SnoozeCommand;
import command.UnmarkCommand;
import command.FindCommand;
import command.DeleteCommand;
import command.DeadlineCommand;
import command.EventCommand;
import command.TodoCommand;
import exception.EmptyDescriptionException;
import exception.InvalidCommandException;
import java.util.ArrayList;

/**
 *  A class that handles parsing and processing of user inputs.
 *  @author  Chen Guanzhou
 *  @version v2
 */
public class Parser {
    private TaskList currList;

    public Parser(TaskList currList) {
        this.currList = currList;
    }

    /**
     * A method for Duke to process the input given by the user.
     * @param input The input string picked up by the scanner object.
     * @throws EmptyDescriptionException If the input is a recognized command but no appropriate description after that.
     * @throws InvalidCommandException If the input is an unrecognized command.
     */
    public void processInput(String input) throws EmptyDescriptionException, InvalidCommandException {
        ArrayList<String> acceptedKeywords = new ArrayList<>();
        acceptedKeywords.add("list");
        acceptedKeywords.add("bye");
        acceptedKeywords.add("deadline");
        acceptedKeywords.add("event");
        acceptedKeywords.add("todo");
        acceptedKeywords.add("delete");
        acceptedKeywords.add("find");
        acceptedKeywords.add("mark");
        acceptedKeywords.add("unmark");
        acceptedKeywords.add("snooze");
        String[] parts = input.split(" ", 2);
        String keyword = parts[0];
        if (input.equals("bye") || input.equals("list")) return;
        if (!acceptedKeywords.contains(keyword)) {
            throw new InvalidCommandException();
        }
        else if (parts.length == 1 || parts[1].equals("")) {
            throw new EmptyDescriptionException();
        }
    }


    /**
     * Parses the user input and returns the necessary command for execution.
     * @param input The user keyboard input after prompt from Duke.
     * @return The resulting Duke command.
     * @throws EmptyDescriptionException If the input is a recognized command but no appropriate description after that.
     * @throws InvalidCommandException If the input is an unrecognized command.
     */
    public Command parseUserInput(String input) throws EmptyDescriptionException, InvalidCommandException {
            String[] parts = input.split(" ", 2);
            String keyword = parts[0];
            processInput(input);
            switch (keyword) {
            case "bye":
                return new ByeCommand();
            case "list":
                return new ListCommand(currList);
            case "snooze":
                return new SnoozeCommand(currList.getTaskAt(Integer.parseInt(parts[1]) - 1));
            case "mark": //command is mark 2
                return new MarkCommand(currList.getTaskAt(Integer.parseInt(parts[1]) - 1));
            case "unmark":
                return new UnmarkCommand(currList.getTaskAt(Integer.parseInt(parts[1]) - 1));
            case "deadline":
                return new DeadlineCommand(input, currList);
            case "event":
                return new EventCommand(input, currList);
            case "todo":
                return new TodoCommand(input, currList);
            case "delete":
                return new DeleteCommand(Integer.parseInt(parts[1]) - 1, currList);
            case "find":
                return new FindCommand(parts[1], currList);
            default:
                assert false; //should not reach here
                return null;
            }
    }
}

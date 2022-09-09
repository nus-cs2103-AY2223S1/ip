package betago;

import java.util.Scanner;

import betago.commands.AddDeadlineCommand;
import betago.commands.AddEventCommand;
import betago.commands.AddTodoCommand;
import betago.commands.ByeCommand;
import betago.commands.Command;
import betago.commands.DeleteCommand;
import betago.commands.FindCommand;
import betago.commands.InvalidCommand;
import betago.commands.ListCommand;
import betago.commands.MarkUnmarkCommand;

/**
 * Parser class that reads respective input from user.
 */
public class Parser {
    private final TaskList tasks;
    private final Storage storage;

    /**
     * Constructor for Parser.
     * Initialises TaskList variable.
     */
    public Parser(TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Reads input from the user and returns the respective command.
     *
     * @param input Input from the user.
     * @return Respective command based on user input.
     */
    public Command readCommands(String input) {
        Scanner sc = new Scanner(input); //can check if can juz use input
        String str = sc.nextLine();
        String[] inputs = str.split(" ", 2);
        if (str.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (inputs[0].equalsIgnoreCase("mark") || inputs[0].equalsIgnoreCase("unmark")) {
            return new MarkUnmarkCommand();
        } else if (inputs[0].equalsIgnoreCase("todo")) {
            return new AddTodoCommand();
        } else if (inputs[0].equalsIgnoreCase("deadline")) {
            return new AddDeadlineCommand();
        } else if (inputs[0].equalsIgnoreCase("event")) {
            return new AddEventCommand();
        } else if (inputs[0].equalsIgnoreCase("delete")) {
            return new DeleteCommand();
        } else if (inputs[0].equalsIgnoreCase("find")) {
            return new FindCommand();
        } else if (str.equalsIgnoreCase("bye")) {
            return new ByeCommand();
        } else {
            return new InvalidCommand("Apologies Human. I do not understand that command.");
        }
    }

}

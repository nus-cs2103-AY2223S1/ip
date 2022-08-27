package parser;

import java.util.Scanner;

import command.Command;
import command.CommandType;
import task.TaskList;
import ui.Ui;

/**
 * <h1>Parser class</h1>
 * Parses the user's input and generates the appropriate command.
 */
public class Parser {
    private Scanner sc;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a Parser object.
     *
     * @param tasks list of Tasks the user currently has.
     * @param ui the User Interface that prints out the output.
     */
    public Parser(TaskList tasks, Ui ui) {
        this.sc = new Scanner(System.in);
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Returns the appropriate Command after parsing the input String.
     *
     * @param input String to be parsed.
     * @return Command to be executed.
     */
    public Command parse(String input) {
        if (input.equals("")) {
            return new Command(CommandType.EMPTY, tasks, input, ui);
        } else if (input.equals("bye")) {
            return new Command(CommandType.BYE, tasks, input, ui);
        } else if (input.equals("list")) {
            return new Command(CommandType.LIST, tasks, input, ui);
        } else if (input.length() >= 4 && input.substring(0, 4).equals("todo")) {
            return new Command(CommandType.TODO, tasks, input, ui);
        } else if (input.length() >= 8 && input.substring(0, 8).equals("deadline")) {
            return new Command(CommandType.DEADLINE, tasks, input, ui);
        } else if (input.length() >= 5 && input.substring(0, 5).equals("event")) {
            return new Command(CommandType.EVENT, tasks, input, ui);
        } else if (input.length() >= 4 && input.substring(0, 4).equals("mark")) {
            return new Command(CommandType.MARK, tasks, input, ui);
        } else if (input.length() >= 6 && input.substring(0, 6).equals("unmark")) {
            return new Command(CommandType.UNMARK, tasks, input, ui);
        } else if (input.length() >= 6 && input.substring(0, 6).equals("delete")) {
            return new Command(CommandType.DELETE, tasks, input, ui);
        } else if (input.length() >= 4 && input.substring(0, 4).equals("find")) {
            return new Command(CommandType.FIND, tasks, input, ui);
        } else {
            return new Command(CommandType.INVALID, tasks, input, ui);
        }
    }
}

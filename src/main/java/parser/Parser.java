package parser;

import command.Command;
import command.CommandType;
import task.TaskList;
import ui.Ui;

import java.util.Scanner;

public class Parser {
    private Scanner sc;
    private TaskList tasks;
    private Ui ui;

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
        }  else if (input.length() >= 5 && input.substring(0, 5).equals("event")) {
            return new Command(CommandType.EVENT, tasks, input, ui);
        } else if (input.length() >= 4 && input.substring(0, 4).equals("mark")) {
            return new Command(CommandType.MARK, tasks, input, ui);
        } else if (input.length() >= 6 && input.substring(0, 6).equals("unmark")) {
            return new Command(CommandType.UNMARK, tasks, input, ui);
        } else if (input.length() >= 6 && input.substring(0, 6).equals("delete")) {
            return new Command(CommandType.DELETE, tasks, input, ui);
        } else {
            return new Command(CommandType.INVALID, tasks, input, ui);
        }
    }
}

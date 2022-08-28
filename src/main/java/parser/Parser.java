package parser;

import command.AddCommand;
import command.ByeCommand;
import command.Command;
import command.DeleteCommand;
import command.EditCommand;
import command.FalseCommand;
import command.ListCommand;

public class Parser {
    protected String fullCommand;

    public Parser(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public Command parse(String fullCommand) {
        if (fullCommand.isEmpty() || fullCommand.equals("bye")) {
            return new ByeCommand(fullCommand);
        } else if (fullCommand.equals("list")) {
            return new ListCommand(fullCommand);
        } else if (fullCommand.startsWith("delete")) {
            return new DeleteCommand(fullCommand);
        } else if (fullCommand.startsWith("mark") || fullCommand.startsWith("unmark")) {
            return new EditCommand(fullCommand);
        } else if (fullCommand.startsWith("todo") || fullCommand.startsWith("deadline") ||
                fullCommand.startsWith("event")) {
            return new AddCommand(fullCommand);
        } else {
            return new FalseCommand(fullCommand);
        }
    }
}

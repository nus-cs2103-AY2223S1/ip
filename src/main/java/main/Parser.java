package main;

import command.ByeCommand;
import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.ListCommand;
import command.LoadCommand;
import command.MarkCommand;
import command.TodoCommand;
import command.UnmarkCommand;
import exception.InvalidCommandException;

public class Parser {

    private static final String COMMAND_LOAD = "load"; 
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";

    Parser() {
        ;
    }

    public Command parseCommand(String userCommand) throws InvalidCommandException {
        String splitUserStatement[] = userCommand.split(" ", 2);
        String command = splitUserStatement[0];
        String commandArgs = splitUserStatement[1];
        switch(command) { //no breaks as all cases lead to return
        case COMMAND_LOAD:
            return new LoadCommand(commandArgs);
        case COMMAND_LIST:
            return new ListCommand(commandArgs);
        case COMMAND_BYE:
            return new ByeCommand(commandArgs);
        case COMMAND_TODO:
            return new TodoCommand(commandArgs);
        case COMMAND_DEADLINE:            
            return new DeadlineCommand(commandArgs);
        case COMMAND_EVENT:          
            return new EventCommand(commandArgs);
        case COMMAND_MARK:
            return new MarkCommand(commandArgs);
        case COMMAND_UNMARK:
            return new UnmarkCommand(commandArgs);
        case COMMAND_DELETE:
            return new DeleteCommand(commandArgs);
        default:
            throw new InvalidCommandException("Command entered is invalid");
        }
    }
}

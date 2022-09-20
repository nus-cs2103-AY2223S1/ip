package meower;

import command.ByeCommand;
import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.FindCommand;
import command.ListCommand;
import command.LoadCommand;
import command.MarkCommand;
import command.TodoCommand;
import command.UnmarkCommand;
import exception.InvalidCommandException;

public class Parser {

    private final String MESSAGE_ERROR_UNNECESSARY_ARG = "Argument given for command not needing argument";
    private final String MESSAGE_ERROR_INVALID_COMMAND = "Command entered is invalid";

    private final String COMMAND_FIND = "find"; 
    private final String COMMAND_LOAD = "load"; 
    private final String COMMAND_LIST = "list";
    private final String COMMAND_BYE = "bye";
    private final String COMMAND_DELETE = "delete";
    private final String COMMAND_TODO = "todo";
    private final String COMMAND_DEADLINE = "deadline";
    private final String COMMAND_EVENT = "event";
    private final String COMMAND_MARK = "mark";
    private final String COMMAND_UNMARK = "unmark";

    public Parser() {
        ;
    }

    
    /** 
     * Parses a given userinput into the respective commands, throws InvalidCommandException when user gives invalid commands
     * @param userCommand the command inputted by the user
     * @return Command
     * @throws InvalidCommandException thrown when an invalid command is inputted by the user
     */
    public Command parse(String userCommand) throws InvalidCommandException {

        //split user command into command word and arguments
        String splitUserStatement[] = userCommand.split(" ", 2);
        String command = splitUserStatement[0];

        //initialise empty string to store arguments
        String commandArgs = "";
        if (splitUserStatement.length > 1){
            commandArgs = splitUserStatement[1].strip();
        }

        //switch-case for different command word
        switch(command) { //no breaks as all cases lead to return
        case COMMAND_FIND:
            return new FindCommand(commandArgs);
        case COMMAND_LOAD:
            if (!commandArgs.equals("")) {
                return new LoadCommand(commandArgs);
            }
            return new LoadCommand();
        case COMMAND_LIST:
            if (!commandArgs.equals("")) {
                throw new InvalidCommandException(MESSAGE_ERROR_UNNECESSARY_ARG);
            }
            return new ListCommand();
        case COMMAND_BYE:
            if (!commandArgs.equals("")) {
                return new ByeCommand(commandArgs);
            }
            return new ByeCommand();
        case COMMAND_TODO:
            return new TodoCommand(commandArgs);
        case COMMAND_DEADLINE:   
            //process arguments to pass into Command constructor
            String[] deadlineArgs = parseCommandArgs("/by",commandArgs); 
            String deadlineDescription = deadlineArgs[0];
            String deadlineDate = deadlineArgs[1].strip();  
            return new DeadlineCommand(deadlineDescription, deadlineDate);
        case COMMAND_EVENT:
            //process arguments to pass into Command constructor
            String[] eventArgs = parseCommandArgs("/at",commandArgs); 
            String eventDescription = eventArgs[0];
            String eventDate = eventArgs[1].strip();          
            return new EventCommand(eventDescription, eventDate);
        case COMMAND_MARK:
            return new MarkCommand(commandArgs);
        case COMMAND_UNMARK:
            return new UnmarkCommand(commandArgs);
        case COMMAND_DELETE:
            return new DeleteCommand(commandArgs);
        default:
            throw new InvalidCommandException(MESSAGE_ERROR_INVALID_COMMAND);
        }
    }

    
    /** 
     * Parses the arguments of the user inputted commands
     * @param delimiter the String that will be used to split the given arguments
     * @param args String representation of user inputted arguments
     * @return String[]
     */
    public String[] parseCommandArgs(String delimiter, String args) {
        String[] splitByDelimiter = args.split(delimiter, 2);
        return splitByDelimiter;
    }
}

package main;

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

    public String[] parseCommand(String userCommand) throws InvalidCommandException {
        String[] parsedCommand = {"","",""};
        String splitUserStatement[] = userCommand.split(" ", 2);
        if (!isValidCommand(splitUserStatement[0])) {
            throw new InvalidCommandException("Thats not an available command.");
        }
        String command = splitUserStatement[0];
        parsedCommand[0] = command;
        String userArgs = "";
        if (splitUserStatement.length > 1) {
            userArgs = splitUserStatement[1];
        }
        String[] splitUserArgs = {"",""};
        if (command.equals(COMMAND_DEADLINE)) {
            splitUserArgs = userArgs.split("/by", 2);
        }
        else if (command.equals(COMMAND_EVENT)) {
            splitUserArgs = userArgs.split("/at", 2);
        } else {
            splitUserArgs = userArgs.split("/", 2);
        }
        parsedCommand[1] = splitUserArgs[0];
        if (splitUserArgs.length > 1) {
            parsedCommand[2] = splitUserArgs[1];
        }
        return parsedCommand;

    }

    public boolean isValidCommand(String command) {
        switch(command) { //no breaks as all cases lead to return
            case COMMAND_LOAD:
                return true;
            case COMMAND_LIST:
                return true;
            case COMMAND_BYE:
                return true;
            case COMMAND_TODO:
                return true;
            case COMMAND_DEADLINE:            
                return true;
            case COMMAND_EVENT:          
                return true;
            case COMMAND_MARK:
                return true;
            case COMMAND_UNMARK:
                return true;
            case COMMAND_DELETE:
                return true;
            default:
                return false;
            }
    }    

}

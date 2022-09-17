public class Parser {

    public static String[] parseCommand(String userCommand) throws InvalidCommandException {
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
        if (command.equals(CommandType.DEADLINE.getCommand())) {
            splitUserArgs = userArgs.split("/by", 2);
        }
        else if (command.equals(CommandType.EVENT.getCommand())) {
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

    public static boolean isValidCommand(String command) {
        switch(command) { //no breaks as all cases lead to return
            case CommandType.LOAD.getCommand():
                return true;
            case CommandType.LIST.getCommand():
                return true;
            case CommandType.BYE.getCommand():
                return true;
            case CommandType.TODO.getCommand():
                return true;
            case CommandType.DEADLINE.getCommand():            
                return true;
            case CommandType.EVENT.getCommand():          
                return true;
            case CommandType.MARK.getCommand():
                return true;
            case CommandType.UNMARK.getCommand():
                return true;
            case CommandType.DELETE.getCommand():
                return true;
            default:
                return false;
            }
    }    

}

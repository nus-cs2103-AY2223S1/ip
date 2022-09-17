public class Parser {
    public Parser() {};

    public static Command parseCommand(String fullCommand) {
        String[] strArr = fullCommand.split(" ",2);
        String commandType = strArr[0];
        String commandDetails;

        if (strArr.length != 2) {
            commandDetails = null;
        } else {
            commandDetails = strArr[1];
        }

        switch (commandType) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(commandDetails);
            case "unmark":
                return new UnmarkCommand(commandDetails);
            case "delete":
                return new DeleteCommand(commandDetails);
            case "todo":
                return new AddTodoCommand(commandDetails);
            case "deadline":
                return new AddDeadlineCommand(commandDetails);
            case "event":
                return new AddEventCommand(commandDetails);
            default:
                return new InvalidCommand();
        }
    }

    public static int parseTaskIndex(String commandDetails) throws PonyException, NumberFormatException{
        if (commandDetails == null) {
            throw new PonyException(":( OOPS!!! Please provide the details!!");
        } else {
            int taskIndex = Integer.parseInt(commandDetails);
            return taskIndex;
        }
    }

    public static String parseTodoDetails(String commandDetails) throws PonyException {
        if (commandDetails == null) {
            throw new PonyException(":( OOPS!!! Please provide the details!!");
        } else {
            return commandDetails;
        }
    }

    public static String[] parseDeadlineDetails(String commandDetails, String format) throws PonyException {
        if (commandDetails == null) {
            throw new PonyException(":( OOPS!!! Please provide the details!!");
        } else {
            String[] taskInfoArr = commandDetails.split(" /by ", 2);
            if (taskInfoArr.length != 2) {
                throw new PonyException(":( OOPS!!! Please provide the details in the following format: " + format);
            }
            return taskInfoArr;
        }
    }

    public static String[] parseEventDetails(String commandDetails, String format) throws PonyException {
        if (commandDetails == null) {
            throw new PonyException(":( OOPS!!! Please provide the details!!");
        } else {
            String[] taskInfoArr = commandDetails.split(" /at ", 2);
            if (taskInfoArr.length != 2) {
                throw new PonyException(":( OOPS!!! Please provide the details in the following format: " + format);
            }
            return taskInfoArr;
        }
    }

}

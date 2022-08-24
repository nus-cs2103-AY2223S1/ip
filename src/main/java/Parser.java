public class Parser {

    public Command handleCommand(String userInput, TasksList tasksList) throws DukeException {
            String[] inputArray = userInput.trim().split("\\s+", 2);
            CommandType commandType = CommandType.parseToCommand(inputArray[0].toLowerCase());

            switch (commandType) {
                case BYE:
                    return new ExitCommand();
                case LIST:
                    return new ListCommand(tasksList);
                case MARK:
                    return new MarkCommand(tasksList, inputArray);
                case UNMARK:
                    return new UnmarkCommand(tasksList, inputArray);
                case TODO:
                    return new TodoCommand(tasksList, inputArray);
                case DEADLINE:
                    return new DeadlineCommand(tasksList, inputArray);
                case EVENT:
                    return new EventCommand(tasksList, inputArray);
                case DELETE:
                    return new DeleteCommand(tasksList, inputArray);
                default:
                    throw new DukeException("Invalid Command");
            }
    }

}

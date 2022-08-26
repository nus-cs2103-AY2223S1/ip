import java.util.Objects;

public abstract class Parser {
    // should return the command, or throw an exception if command is wrong
    public static Command parse(String commandString, TaskList taskList) throws IllegalArgumentException {
        String[] commandArr = commandString.split(" ");
        Command.Commands commandWord = null;
        Command command = null;
        try {
            commandWord = Command.Commands.valueOf(commandArr[0]);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("🙁 OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        }

        if (!Objects.isNull(commandWord)) {
            switch(commandWord) {
                case mark:
                    command = UpdateMarkCommand.of(commandString, taskList);
                    break;
                case unmark:
                    command = UpdateUnmarkCommand.of(commandString, taskList);
                    break;
                case todo:
                    command = AddTodoCommand.of(commandString);
                    break;
                case deadline:
                    command = AddDeadlineCommand.of(commandString);
                    break;
                case event:
                    command = AddEventCommand.of(commandString);
                    break;
                case delete:
                    command = DeleteCommand.of(commandString, taskList);
                    break;
                case bye:
                    command = new ByeCommand(commandString);
                    break;
                case list:
                    command = new ListCommand(commandString);
                    break;
            }
        }

        return command;
    }

    public static int getTaskIndex(String command, TaskList taskList) throws IllegalArgumentException {
        int i;
        String[] commandArr = command.split(" ");
        try {
            i = Integer.parseInt(commandArr[1]);
        } catch (NumberFormatException e) {     // if second word not integer
            throw new IllegalArgumentException();
        }

        return i;
    }
}

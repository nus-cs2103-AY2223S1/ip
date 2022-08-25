import java.time.LocalDate;

public class Parser {

    public static Command parse(String userInput) throws DukeException {
        String commandWord = userInput.trim().split(" ", 2)[0];
        String arguments;
        Task task;
        int taskNumber;

        switch (commandWord) {

            case AddCommand.ADD_TODO:
                arguments = userInput.trim().split(" ", 2)[1];
                task = new ToDo(arguments);
                return new AddCommand(task);

            case AddCommand.ADD_DEADLINE:
                arguments = userInput.trim().split(" ", 2)[1];
                String deadline = arguments.split(" /by ")[0];
                String by = arguments.split(" /by ")[1];
                LocalDate date = LocalDate.parse(by);
                task = new Deadline(deadline, date);
                return new AddCommand(task);

            case AddCommand.ADD_EVENT:
                arguments = userInput.trim().split(" ", 2)[1];
                String event = arguments.split(" /at ")[0];
                String at = arguments.split(" /at ")[1];
                task = new Event(event, at);
                return new AddCommand(task);

            case DeleteCommand.DELETE_COMMAND:
                arguments = userInput.trim().split(" ", 2)[1];
                taskNumber = Integer.parseInt(arguments);
                return new DeleteCommand(taskNumber - 1);

            case MarkCommand.MARK_COMMAND:
                arguments = userInput.trim().split(" ", 2)[1];
                taskNumber = Integer.parseInt(arguments);
                return new MarkCommand(taskNumber - 1);

            case UnmarkCommand.UNMARK_COMMAND:
                arguments = userInput.trim().split(" ", 2)[1];
                taskNumber = Integer.parseInt(arguments);
                return new UnmarkCommand(taskNumber - 1);

            case ListCommand.LIST_COMMAND:
                return new ListCommand();

            case ExitCommand.EXIT_COMMAND:
                return new ExitCommand();

            default:
                return new ListCommand();
        }
    }
}

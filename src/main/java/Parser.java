public class Parser {
    public static Command parse(String command, Storage storage, TasksList tasksList, Ui ui) throws DukeException {
        String[] words = command.split(" ", 2);
        if (ExitCommand.isCommand(command)) {
            return new ExitCommand(ui, storage, tasksList);
        } else if (ListCommand.isCommand(command)) {
            return new ListCommand(tasksList);
        } else if (MarkCommand.isCommand(words[0])) {
            return new MarkCommand(tasksList, storage, words);
        } else if (UnmarkCommand.isCommand(words[0])) {
            return new UnmarkCommand(tasksList, storage, words);
        } else if (AddCommand.isCommand(words[0])) {
            return new AddCommand(tasksList, storage, words);
        } else if (DeleteCommand.isCommand(words[0])) {
            return new DeleteCommand(tasksList, storage, words);
        } else {
            return new InvalidCommand();
        }
    }
}

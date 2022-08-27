public class Parser {

    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    public Parser(Storage storage, Ui ui, TaskList taskList) {
        this.storage = storage;
        this.ui = ui;
        this.taskList = taskList;
    }

    public static enum CommandName {
        bye, list, mark, unmark, delete, todo, deadline, event
    }

    public Command parse(String input) throws DukeException {
        String[] tokens = input.split(" ");
        if (tokens.length == 0) {
            throw new DukeException("I'm sorry but I don't know what that means.");
        }
        int taskIndex;
        switch (CommandName.valueOf(tokens[0])) {
        case mark:
            taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            return new ChangeStatusCommand(storage, ui, taskList, taskIndex, true);
        case unmark:
            taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            return new ChangeStatusCommand(storage, ui, taskList, taskIndex, false);
        case list:
            return new ListCommand(storage, ui, taskList);
        case delete:
            taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            return new DeleteCommand(storage, ui, taskList, taskIndex);
        case bye:
            return new ExitCommand(storage, ui, taskList);
        default:
            return new AddCommand(storage, ui, taskList, input);
        }

    }

}

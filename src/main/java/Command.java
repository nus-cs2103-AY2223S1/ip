public abstract class Command {

    protected String content;
    protected TaskList tasks;

    public Command() {}

    public Command(TaskList tasks) {
        this.tasks = tasks;
    }

    public Command(String content, TaskList tasks) {
        this.content = content;
        this.tasks = tasks;
    }

    public static Command of(String message, TaskList tasks) throws DukeException {
        String[] splitMessage = message.split("\\s+", 2);
        CommandType commandType = CommandType.parse(splitMessage[0]);
        switch (commandType) {
        case BYE:
            return new EndCommand();
        case LIST:
            return new ListCommand(tasks);
        case MARK:
            if (splitMessage.length < 2) {
                throw new DukeException("You forgot to tell me the task number!");
            }
            return new MarkCommand(splitMessage[1], tasks);
        case UNMARK:
            if (splitMessage.length < 2) {
                throw new DukeException("You forgot to tell me the task number!");
            }
            return new UnmarkCommand(splitMessage[1], tasks);
        case TODO:
            if (splitMessage.length < 2) {
                throw new DukeException("You forgot to add the description!");
            }
            return new ToDoCommand(splitMessage[1], tasks);
        case DEADLINE:
            if (splitMessage.length < 2) {
                throw new DukeException("You forgot to add the description!");
            }
            return new DeadlineCommand(splitMessage[1], tasks);
        case EVENT:
            if (splitMessage.length < 2) {
                throw new DukeException("You forgot to add the description!");
            }
            return new EventCommand(splitMessage[1], tasks);
        case DELETE:
            if (splitMessage.length < 2) {
                throw new DukeException("You forgot to tell me the task number!");
            }
            return new DeleteCommand(splitMessage[1], tasks);
        default:
            throw new DukeException("Sorry, there is no such command!");
        }
    }
    public abstract boolean run() throws DukeException;

}

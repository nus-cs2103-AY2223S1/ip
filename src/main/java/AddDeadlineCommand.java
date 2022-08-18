public class AddDeadlineCommand implements Command {

    private TaskList tasks;
    private Deadlines task;

    public AddDeadlineCommand(TaskList tasks, Deadlines task) {
        this.tasks = tasks;
        this.task = task;
    }

    public AddDeadlineCommand(TaskList tasks, String input) throws DaveException {
        if (input.equals("")) {
            throw new DaveException("( ; ω ; ) Oh nyo!!! The description of an event cannot be empty!");
        }
        String[] args = input.split("/by ");
        if (args.length > 2) {
            throw new DaveException("( ; ω ; ) Oh nyo!!! Too many deadlines, Dave's brain is fried!");
        } else if (args.length < 2) {
            throw new DaveException("( ; ω ; ) Oh nyo!!! Please provide the time of the deadline!");
        }
        this.task = new Deadlines(args[0], args[1]);
        this.tasks = tasks;
    }

    @Override
    public String execute() {
        return this.tasks.add(task);
    }
}

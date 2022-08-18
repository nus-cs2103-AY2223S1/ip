public class AddEventCommand implements Command {

    private TaskList tasks;
    private Events task;

    public AddEventCommand(TaskList tasks, Events task) {
        this.tasks = tasks;
        this.task = task;
    }

    public AddEventCommand(TaskList tasks, String input) throws DaveException {
        if (input.equals("")) {
            throw new DaveException("( ; ω ; ) Oh nyo!!! The description of an event cannot be empty!");
        }
        String[] args = input.split("/at ");
        if (args.length > 2) {
            throw new DaveException("( ; ω ; ) Oh nyo!!! Too many timings given, Dave's brain is fried!");
        } else if (args.length < 2) {
            throw new DaveException("( ; ω ; ) Oh nyo!!! Please provide a timing for the event!");
        }
        this.task = new Events(args[0], args[1]);
        this.tasks = tasks;
    }

    @Override
    public String execute() {
        return this.tasks.add(task);
    }
}

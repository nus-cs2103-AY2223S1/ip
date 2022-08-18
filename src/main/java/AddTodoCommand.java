public class AddTodoCommand implements Command {

    private TaskList tasks;
    private ToDo task;

    public AddTodoCommand(TaskList tasks, ToDo task) {
        this.tasks = tasks;
        this.task = task;
    }


    public AddTodoCommand(TaskList tasks, String input) throws DaveException {
        if (input.equals("")) {
            throw new DaveException("( ; ω ; ) Oh nyo!!! The description of a todo cannot be empty!");
        }
        this.task = new ToDo(input);
        this.tasks = tasks;
    }

    @Override
    public String execute() {
        return this.tasks.add(task);
    }
}


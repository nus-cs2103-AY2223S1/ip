public class TodoCommand extends Command{
    private String input;

    public TodoCommand(String input) {
        this.input = input;
    }

    @Override
    public void run(TaskList taskList) throws DukeException{
        if (input.strip().length() >= 1) {
            Todo todo = new Todo(input);
            taskList.createTask(todo);
        } else {
            throw new DukeException("Description of a todo cannot be empty");
        }
    };
}

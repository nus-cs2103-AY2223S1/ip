public class CommandAddTodo extends Command {

    private String name;
    private boolean done;

    public CommandAddTodo(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {

        try {
            Task task = new TaskTodo(this.name, this.done);
            taskList.add(task);
            ui.showText("I created a Todo " + task + " for you. You have " + taskList.size() + " task(s) now.");
        } catch (TaskNoNameException e) {
            ui.showText("Error in creating Todo. " + e.getMessage());
        }

    }
}
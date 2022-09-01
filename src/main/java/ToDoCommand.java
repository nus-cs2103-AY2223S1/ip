public class ToDoCommand extends Command {
    String desc;

    public ToDoCommand(String desc) {
        this.desc = desc;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Todo tmp = new Todo(desc);
        tasks.addTodo(tmp);
        storage.write(tasks.toStringWritable());
        ui.showOutput("Got it. I added this task:");
        ui.showOutput("\t" + tmp);
        ui.showOutput("Now you have " + tasks.getLength() + " tasks in the list.");
    }

    public boolean isExit() {
        return false;
    }
}

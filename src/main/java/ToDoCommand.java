public class ToDoCommand extends Command {

    public ToDoCommand(String content, TaskList tasks) {
        super(content, tasks);
    }

    @Override
    public boolean run() throws DukeException {
        Reply.printMessage(this.tasks.addTask(new ToDo(this.content)));
        return false;
    }
}
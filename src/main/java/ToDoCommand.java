public class ToDoCommand extends Command {

    public ToDoCommand(String content, TaskList tasks) {
        super(content, tasks);
    }

    @Override
    public String run() throws DukeException {
        return this.tasks.addTask(new ToDo(this.content));
    }
}
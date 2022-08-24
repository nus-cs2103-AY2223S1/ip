public class ListCommand extends Command {

    public static final String LIST = "Here are the tasks in your list:\n";


    public ListCommand(TaskList tasks) {
        super(tasks);
    }

    @Override
    public String run() throws DukeException {
        return LIST + this.tasks.toString();
    }

}
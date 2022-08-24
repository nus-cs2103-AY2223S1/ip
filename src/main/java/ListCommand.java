public class ListCommand extends Command {

    public static final String LIST = "Here are the tasks in your list:\n";


    public ListCommand(TaskList tasks) {
        super(tasks);
    }

    @Override
    public boolean run() throws DukeException {
        Reply.printMessage(LIST + this.tasks.toString());
        return false;
    }

}
/**
 * Represents the concept of a input command to the application.
 *
 * @author WR3nd3
 */
abstract public class Command {

    abstract public void execute(TaskList tasks, Ui ui, ListLoader storage);

    public boolean isExit() {
        return false;
    }
}

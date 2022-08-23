public class UnknownCommand extends Command {
    UnknownCommand() {

    }

    public void exec(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("Sorry, I don't know what that means.\n"
                + "Did you make a mistake? Please note that commands are case-sensitive.");
    }

    public boolean isTerminator() {
        return false;
    }
}

public class EmptyCommand extends Command {
    public static final boolean IS_EXIT = false;

    public EmptyCommand() {
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            throw new InvalidCommandException();
        }
        catch (InvalidCommandException ex) {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public boolean isExit() {
        return this.IS_EXIT;
    }
}
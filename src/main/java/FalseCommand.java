public class FalseCommand extends Command {
    protected String commandLine;

    public FalseCommand(String commandLine) {
        this.commandLine = commandLine;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList task, Ui ui, Storage storage) throws DukeException {
        try {
            throw new DukeException("I'm sorry, but I don't know what that means :-(\n");
        } catch (DukeException e) {
            System.out.println(e.toString());
            ui.nextCommand();
        }
    }
}

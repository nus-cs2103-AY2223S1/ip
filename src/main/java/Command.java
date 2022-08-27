public abstract class Command {
    boolean isExit = false;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return isExit;
    }

    protected void displayCommand(Ui ui, String header, Object body, String footer) {
        ui.show(header);
        ui.show(body);
        ui.show(footer);
    }
}

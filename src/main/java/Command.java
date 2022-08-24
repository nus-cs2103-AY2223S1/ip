abstract class Command {
    private String info;

    public Command (String info) {
        this.info = info;
    }

    public String getInfo () {
        return this.info;
    }
    abstract void execute (Ui ui, TaskList taskList) throws DukeException;
}

package duke;

abstract class Command {

    private static boolean isExit;
    abstract String execute(TaskList tasks, Ui ui, Storage storage);
    boolean getIsExit() {
        return this.isExit;
    }

}

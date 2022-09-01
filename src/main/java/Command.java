package duke;

abstract class Command {

    abstract boolean execute(TaskList tasks, Ui ui, Storage storage);

}

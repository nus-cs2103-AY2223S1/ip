package anya;

abstract class Command {

    abstract String execute(TaskList tasks, Ui ui) throws AnyaException;

}

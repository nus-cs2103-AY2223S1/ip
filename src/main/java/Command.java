package main.java;

public abstract class Command {

    public boolean isExit;
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    public abstract boolean isExit();
}

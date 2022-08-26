package duke;

public abstract class Command {

    public Command() {

    }

    public abstract TaskList execute(TaskList taskList, Ui ui, Storage storage);
    public abstract boolean isExit();
}


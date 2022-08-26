package commands;

import tasks.*;

public abstract class Command {
    public boolean isDone;
    public abstract void run(TaskList taskList);
    public Command() {
        this.isDone = false;
    }
}

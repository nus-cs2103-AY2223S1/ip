package commands;

import tasks.Deadlines;
import tasks.Events;
import tasks.Task;
import tasks.TaskList;
import tasks.Todos;

public abstract class Command {
    public boolean isDone;
    public abstract void run(TaskList taskList);
    public Command() {
        this.isDone = false;
    }
}

package commands;

import java.util.ArrayList;
import tasks.*;
public abstract class Command {
    public boolean isDone;
    public abstract void run(ArrayList<Task> taskList);
    public Command() {
        this.isDone = false;
    }
}

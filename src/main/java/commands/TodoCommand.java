package commands;

import java.util.ArrayList;
import tasks.*;
import duke.Statements;

public class TodoCommand extends Command {
    String descript;
    boolean isDone;

    public TodoCommand(String descript) {
        this.descript = descript;
        this.isDone = false;
    }

    @Override
    public void run(ArrayList<Task> taskList) {
        Task toAdd = new Todos(this.descript);
        taskList.add(toAdd);
        Statements.addStatement(toAdd.toString(), taskList.size());
    }
}

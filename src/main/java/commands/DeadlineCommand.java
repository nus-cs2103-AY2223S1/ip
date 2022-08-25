package commands;

import java.util.ArrayList;

import duke.Statements;
import tasks.*;

public class DeadlineCommand extends Command {
    String descript;
    String doBy;

    public DeadlineCommand(String descript, String doBy) {
        this.descript = descript;
        this.doBy = doBy;
    }

    @Override
    public void run(ArrayList<Task> taskList) {
        Task toAdd = new Deadlines(this.descript, this.doBy);
        taskList.add(toAdd);
        Statements.addStatement(toAdd.toString(), taskList.size());
    }
}

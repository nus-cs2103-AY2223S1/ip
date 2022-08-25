package commands;

import java.util.ArrayList;

import duke.Statements;
import tasks.*;

public class EventCommand extends Command {
    String descript;
    String atTime;

    public EventCommand(String descript, String atTime) {
        this.descript = descript;
        this.atTime = atTime;
    }

    @Override
    public void run(ArrayList<Task> taskList) {
        Task toAdd = new Events(this.descript, this.atTime);
        taskList.add(toAdd);
        Statements.addStatement(toAdd.toString(), taskList.size());
    }
}

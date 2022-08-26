package commands;

import duke.Ui;
import tasks.*;

public class EventCommand extends Command {
    String descript;
    String atTime;

    public EventCommand(String descript, String atTime) {
        this.descript = descript;
        this.atTime = atTime;
    }

    @Override
    public void run(TaskList taskList) {
        Task toAdd = new Events(this.descript, this.atTime);
        taskList.addTask(toAdd);
        Ui.addStatement(toAdd.toString(), taskList.getSize());
    }
}

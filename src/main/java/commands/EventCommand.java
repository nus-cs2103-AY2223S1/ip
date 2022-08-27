package commands;

import duke.Ui;
import tasks.Deadlines;
import tasks.Events;
import tasks.Task;
import tasks.TaskList;
import tasks.Todos;

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
        Ui.printAddStatement(toAdd.toString(), taskList.getSize());
    }
}

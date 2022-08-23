package roger.commands;

import roger.Storage;
import roger.TaskList;
import roger.Ui;
import roger.tasks.ToDo;

import java.time.LocalDate;

public class AddToDoCommand extends Command {
    protected String taskName;

    public AddToDoCommand(String taskName) {
        this.taskName = taskName;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage)  {
        ToDo toDo = new ToDo(taskName);
        tasks.add(toDo);
        ui.showcase("Nephew got new to-do:", toDo.toString());
        ui.show("Nephew now have " + Integer.toString(tasks.getLength()) + " tasks in the list.");
    }
}

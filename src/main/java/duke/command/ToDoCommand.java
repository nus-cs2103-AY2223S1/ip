package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.task.ToDo;
import duke.Ui;

public class ToDoCommand extends Command {
    private final String description;

    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ui.printBorder();
        ToDo toDo = new ToDo(description);
        taskList.add(toDo, storage);
        String message = "Nice! This task has been successfully added!";
        ui.displayCommandMessage(message, toDo, taskList.getSize());
        ui.printBorder();
    }
}

package duke.command;

import duke.task.ToDo;
import duke.task.TasksController;
import duke.Ui;
import duke.Storage;
import duke.exception.EmptyContentException;
/**
 * CreatToDoCommand will execute the command of creating a new todo.
 */
public class CreateToDoCommand extends Command {

    /**
     * Creates a new todo
     * @param controller Duke task controller
     * @param ui Duke UI
     * @param storage Duke IO processor
     */
    public void execute(TasksController controller, Ui ui, Storage storage) {
        ui.prompt("Please type in your ToDo task:");
        String todo = ui.inputText();
        try {
            controller.checkTaskContent(todo);
            ToDo todoTask = new ToDo(todo);
            controller.addToList(todoTask);
            ui.display(todoTask.toString(), false, false, false, false, false);
        } catch (EmptyContentException ece) {
            ui.reportError("No empty task is allowed! Please try again...");
            ui.showSplitLine();
        }
    }
}

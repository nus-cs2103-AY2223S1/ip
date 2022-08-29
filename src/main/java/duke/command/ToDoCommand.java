package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.EmptyDescException;
import duke.task.ToDo;

import java.io.IOException;

public class ToDoCommand extends Command {
    private String desc;

    public ToDoCommand(String desc) {
        this.desc = desc.replace("todo", "");
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            ToDo task = new ToDo(desc);
            tasks.addTask(task);
            storage.appendToFile(task.toSave() + System.lineSeparator());
            ui.printMessage("Got it. I've added this task:\n"
                    + task + "\n"
                    + "Now you have " + tasks.listSize() + " tasks in the list.");
        } catch (EmptyDescException e) {
            System.out.println(e.getMessage());
        } catch (IOException ioe) {
            System.out.println("Something went wrong: " + ioe.getMessage());
        }
    }
}

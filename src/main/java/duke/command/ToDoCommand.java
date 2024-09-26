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
        assert !this.desc.isEmpty() : "task description cannot be empty";
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
            ui.nextOutput("Got it. I've added this task:\n"
                    + task + "\n"
                    + "Now you have " + tasks.listSize() + " tasks in the list.\n"
                    + super.nextAction);
        } catch (EmptyDescException e) {
            ui.nextOutput(e.getMessage());
        } catch (IOException ioe) {
            ui.nextOutput("Something went wrong: " + ioe.getMessage());
        }
    }
}

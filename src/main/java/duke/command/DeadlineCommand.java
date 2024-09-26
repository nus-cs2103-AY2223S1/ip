package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import duke.task.Deadline;

import duke.exception.EmptyDescException;

import java.io.IOException;

public class DeadlineCommand extends Command {
    private String desc;
    private String byDate;

    public DeadlineCommand(String desc) {
        desc = desc.replace("deadline", "");
        int slashIndex = desc.lastIndexOf("/");
        byDate = desc.substring(slashIndex + 4);
        this.desc = desc.substring(0, slashIndex - 1);
        assert !this.desc.isEmpty() : "task description cannot be empty";
        assert !byDate.isEmpty() : "deadline cannot be empty";
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Deadline task = new Deadline(desc, byDate);
            tasks.addTask(task);
            storage.appendToFile(task.toSave() + System.lineSeparator() + "");
            ui.nextOutput("Understood. I've added this task:\n"
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

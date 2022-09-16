package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.EmptyDescException;
import duke.task.Event;

import java.io.IOException;

public class EventCommand extends Command {
    private String desc;
    private String atDate;

    public EventCommand(String desc) {
        desc = desc.replace("event", "");
        int slashIndex = desc.lastIndexOf("/");
        atDate = desc.substring(slashIndex + 4);
        this.desc = desc.substring(0, slashIndex - 1);
        assert !this.desc.isEmpty() : "task description cannot be empty";
        assert !atDate.isEmpty() : "event date cannot be empty";
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Event task = new Event(desc, atDate);
            tasks.addTask(task);
            storage.appendToFile(task.toSave() + System.lineSeparator() + "");
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

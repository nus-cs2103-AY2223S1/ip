package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.EmptyDescException;
import duke.task.Event;

import java.io.IOException;

public class EventCommand extends Command {
    private String desc;
    private String at;

    public EventCommand(String desc) {
        desc = desc.replace("event", "");
        int slash = desc.lastIndexOf("/");
        this.at = desc.substring(slash + 4);
        this.desc = desc.substring(0, slash - 1);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Event task = new Event(desc, at);
            tasks.addTask(task);
            storage.appendToFile(task.toSave() + System.lineSeparator() + "");
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

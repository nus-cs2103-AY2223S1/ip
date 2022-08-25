package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import duke.task.Deadline;

import duke.exception.EmptyDescException;

import java.io.IOException;

public class DeadlineCommand extends Command {
    private String desc;
    private String by;

    public DeadlineCommand(String desc) {
        desc = desc.replace("deadline", "");
        int slash = desc.lastIndexOf("/");
        this.by = desc.substring(slash + 4);
        this.desc = desc.substring(0, slash - 1);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Deadline task = new Deadline(desc, by);
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

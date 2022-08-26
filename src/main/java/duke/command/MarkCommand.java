package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to mark a task as completed or uncompleted.
 */
public class MarkCommand extends Command {
    /** The issued command: i.e. mark or unmark. */
    private String mark;

    /** The task to be marked as done or undone. */
    private int taskNumber;

    public MarkCommand(String mark, int taskNumber) {
        this.mark = mark;
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String task;
        if (mark.equals("mark")) {
            task = tasks.markTask(taskNumber, true);
            ui.reply("Nice! I've marked this duke.task as done:\n" + task);
        } else if (mark.equals("unmark")) {
            task = tasks.markTask(taskNumber, false);
            ui.reply("OK, I've marked this duke.task as not done yet:\n" + task);
        }
        storage.saveTasks(tasks);
    }
}
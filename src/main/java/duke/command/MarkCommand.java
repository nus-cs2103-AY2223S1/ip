package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class MarkCommand extends Command {
    private String mark;
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
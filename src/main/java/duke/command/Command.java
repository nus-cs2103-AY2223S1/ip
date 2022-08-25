package duke.command;
import duke.main.Ui;
import duke.main.TaskList;
import duke.main.Storage;

public abstract class Command {
//    multiple children classes that extend from command and can call run to do something

    /**
     * Execute will add a task into the tasklist, save the new tasklist in storage
     * and print out what was added
     *
     * @param task
     * @param ui
     * @param storage
     */
    public abstract void execute(TaskList task, Ui ui, Storage storage);
}

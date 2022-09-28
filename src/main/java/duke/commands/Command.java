package duke.commands;

import duke.tasks.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

public abstract class Command {

    public abstract String execute(TaskList tasks, Ui ui, Storage storage);



}
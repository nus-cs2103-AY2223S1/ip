package iana.command;

import iana.exception.IanaException;
import iana.main.Storage;
import iana.main.Ui;
import iana.tasks.TaskList;

public class ExitCommand extends Command {
    
    public ExitCommand() {

    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.write(tasks);
        } catch (IanaException e) {
            ui.echo(e.getMessage());
        }
        ui.sayBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
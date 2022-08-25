package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
        try {
            storage.save(tasks);
        } catch (IOException e) {
            System.out.println("Error saving file!!");
        }
    }

    @Override
    public String toString() {
        return "Exit command";
    }
}

package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class HelloCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String intro = "Hello! My name is GustavoBot, but you can call me Gus\n"
                + "How may I help you today?";
        ui.nextOutput(intro);
    }
}

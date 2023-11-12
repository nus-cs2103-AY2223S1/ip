package byu.commands;

import byu.util.Response;
import byu.util.TaskList;
import byu.util.Ui;

/**
 * A command to open the help window.
 */
public class HelpCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui) {
    }
    @Override
    public boolean isHelp() {
        return true;
    }

    @Override
    public Response generateResponse(TaskList tasks) {
        String output = "Forgot the commands? No worries!\nOpening the help page...";
        return new Response(output, false, true);
    }
}

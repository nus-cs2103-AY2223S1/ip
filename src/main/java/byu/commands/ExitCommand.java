package byu.commands;

import byu.util.Response;
import byu.util.TaskList;
import byu.util.Ui;

/**
 * A command to exit the program.
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui) {
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public Response generateResponse(TaskList tasks) {
        String output = "Awww, I'll miss you :(\nSee you soon!!\n";
        return new Response(output, true, false);
    }
}

package byu.commands;

import byu.util.TaskList;
import byu.util.Ui;

public class HelpCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui) {
        String response = generateResponse(tasks);
        ui.setOutput(response);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean isHelp() {
        return true;
    }

    @Override
    public String generateResponse(TaskList tasks) {
        return "";
    }
}

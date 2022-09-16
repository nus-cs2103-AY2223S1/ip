package anya;

public class HelpCommand extends Command {

    HelpCommand() {

    }

    String execute(TaskList tasks, Ui ui) {
        String response = ui.getHelp();
        return response;
    }

}


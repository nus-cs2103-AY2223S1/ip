package meowmeow.commands;

import meowmeow.Storage;
import meowmeow.TaskList;
import meowmeow.Ui;

public class FindCommand extends Command {

    private String userInput;

    public FindCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.findTask(userInput);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

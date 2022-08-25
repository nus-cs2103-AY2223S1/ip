package commands;
import byu.*;
import exceptions.InvalidIndex;

/**
 * Represents a command to exit the chatbot.
 */
public class ByeCommand extends Command {

    public void execute(ToDoList list, Ui ui) throws InvalidIndex {
        ui.exit();
        list.save();
    }

    public boolean isExit() {
        return true;
    }
}

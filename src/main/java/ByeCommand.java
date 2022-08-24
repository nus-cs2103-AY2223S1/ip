import java.util.ArrayList;

public class ByeCommand extends Command {
    public ByeCommand(ArrayList<String> rest) {
        super(true, rest);
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        if (rest.isEmpty()) {
            ui.say("Goodbye :(");
        } else {
            throw new IncompleteCommandException(String.join(" ", rest), "bye", "Were you trying to enter 'bye'?");
        }
    }
}

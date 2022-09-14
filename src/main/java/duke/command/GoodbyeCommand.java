package duke.command;

public class GoodbyeCommand extends Command {

    /**
     * A command to quit the chat box.
     */
    public GoodbyeCommand() {
        super((tasks, ui, storage) ->
                ui.goodBye(),
                true);
    }
}

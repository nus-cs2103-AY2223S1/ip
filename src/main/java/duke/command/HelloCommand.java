package duke.command;

public class HelloCommand extends Command {
    /**
     * A command to greet Duke.
     */
    public HelloCommand() {
        super((tasks, ui, storage) ->
                ui.showWelcome());
    }
}

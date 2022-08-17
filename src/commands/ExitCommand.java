package commands;

import input.Input;

public class ExitCommand extends Command {
    public ExitCommand() {
        super("bye");
    }

    @Override
    public CommandResponse run(Input input) {
        return new CommandResponse("Bye. See you again soon!", true);
    }
}

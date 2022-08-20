package duke.command;

public class ExitCommand extends Command {
    @Override
    public void execute() {
        System.out.println("Will that be all? Alright then.");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

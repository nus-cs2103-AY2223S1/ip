package duke.command;

public class ByeCommand extends Command {
    public ByeCommand() {
        super();
    }

    @Override
    public void run() {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
    }
}

public class ByeCommand extends Command {
    public ByeCommand(String command) {
        super(command);
    }

    @Override
    public void execute() {
        System.out.println("Bye. Hope to see you again soon!\n");
        System.exit(0);
    }
}

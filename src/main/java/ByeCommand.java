public class ByeCommand extends Command {
    public ByeCommand() {
        super();
        this.terminated = true;
    }

    @Override
    public void execCommand(TaskList list) {
        System.out.println("Goodbye! I hope to see you  again soon!");
    }
}

public class InvalidCommand extends Command{

    @Override
    public void execute (TaskList taskList) {

        System.out.println("Invalid command");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

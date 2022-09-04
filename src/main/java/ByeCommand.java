public class ByeCommand extends Command {
    public void execute(TaskList taskList, Storage storage) {
        System.out.println("Bye. Hope to see you again soon!");
        setIsExitToTrue();
    }
}

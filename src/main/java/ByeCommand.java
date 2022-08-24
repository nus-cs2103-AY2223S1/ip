public class ByeCommand extends Command{

    @Override
    void execute(Storage storage, UI ui, TaskList taskList) {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
    }

}

public class ByeCommand extends Command{

    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        isByeCommand = true;
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
    }

}

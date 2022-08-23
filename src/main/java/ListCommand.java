public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    @Override
    public void execCommand(TaskList list) {
        System.out.println("Here are your tasks:");
        for (int i = 0; i < list.getSize(); i++) {
            System.out.println(i + 1 + ". " + list.getTask(i));
        }
    }
}

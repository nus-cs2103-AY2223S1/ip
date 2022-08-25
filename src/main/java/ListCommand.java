public class ListCommand extends Command {
    private TaskList taskList;

    public ListCommand(String command, TaskList taskList) {
        super(command);
        this.taskList = taskList;
    }

    public void execute() {
        String s = this.taskList.toString();
        if (s.isEmpty()) {
            System.out.println("🙁 OOPS!!! There are no tasks in your list yet.");
        } else {
            System.out.printf("Here are the tasks in your list:\n%s", s);
        }
    }
}

import java.util.Map;

public class DeleteInstruction extends Instruction {
    public DeleteInstruction(String mainArgument, Map<String, String> flagArgumentsMap) {
        super(mainArgument, flagArgumentsMap);
    }

    @Override
    public void execute(TaskList taskList) {
        if (this.hasMainArgument()) {
            int taskId = Integer.parseInt(this.getMainArgument()) - 1;
            Task task = taskList.getTask(taskId);
            System.out.println("Ok, I've deleted this task:");
            System.out.println(task);
            taskList.deleteTask(taskId);
            if (taskList.isEmpty()) {
                System.out.println("You have no more tasks left in your list!");
            } else {
                System.out.printf("You have %d task(s) left in your list.%n", taskList.size());
            }
        } else {
            System.out.println("Sorry, you need to tell me which task to delete.");
        }
    }
}

import java.util.Map;

public class MarkInstruction extends Instruction {
    public MarkInstruction(String mainArgument, Map<String, String> flagArgumentsMap) {
        super(mainArgument, flagArgumentsMap);
    }

    @Override
    public void execute(TaskList taskList) {
        if (this.hasMainArgument()) {
            int taskNum = Integer.parseInt(this.getMainArgument());
            taskList.markTask(taskNum - 1);
            System.out.printf("Well done! I've marked task %d as done:%n", taskNum);
            System.out.println(taskList.getTask(taskNum - 1));
        } else {
            System.out.println("Sorry, you need to tell me which task to mark.");
        }
    }
}

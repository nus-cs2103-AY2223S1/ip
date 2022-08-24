import java.util.Map;

public class UnmarkInstruction extends Instruction {
    public UnmarkInstruction(String mainArgument, Map<String, String> flagArgumentsMap) {
        super(mainArgument, flagArgumentsMap);
    }

    @Override
    public void execute(TaskList taskList) {
        if (this.hasMainArgument()) {
            int taskNum = Integer.parseInt(this.getMainArgument());
            taskList.unmarkTask(taskNum - 1);
            System.out.printf("Sure, I've marked task %d as not done:%n", taskNum);
            System.out.println(taskList.getTask(taskNum - 1));
        } else {
            System.out.println("Sorry, you need to tell me which task to unmark.");
        }
    }
}

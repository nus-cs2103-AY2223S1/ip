import java.util.Map;

public class ListInstruction extends Instruction {
    public ListInstruction(String mainArgument, Map<String, String> flagArgumentsMap) {
        super(mainArgument, flagArgumentsMap);
    }

    @Override
    public void execute(TaskList taskList) {
        if (taskList.isEmpty()) {
            System.out.println("You have no tasks at the moment!");
        } else {
            int i = 1;
            for (Task task : taskList) {
                System.out.printf("%d. %s%n", i, task);
                i++;
            }
        }
    }
}

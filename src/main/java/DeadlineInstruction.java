import java.util.Map;

public class DeadlineInstruction extends Instruction {
    public DeadlineInstruction(String mainArgument, Map<String, String> flagArgumentsMap) {
        super(mainArgument, flagArgumentsMap);
    }

    @Override
    public void execute(TaskList taskList) {

    }
}

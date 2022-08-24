import java.util.Map;

public class UnmarkInstruction extends Instruction {
    public UnmarkInstruction(String mainArgument, Map<String, String> flagArgumentsMap) {
        super(mainArgument, flagArgumentsMap);
    }

    @Override
    public void execute(TaskList taskList) {

    }
}

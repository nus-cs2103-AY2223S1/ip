import java.util.Map;

public class MarkInstruction extends Instruction {
    public MarkInstruction(String mainArgument, Map<String, String> flagArgumentsMap) {
        super(mainArgument, flagArgumentsMap);
    }

    @Override
    public void execute(TaskList taskList) {

    }
}

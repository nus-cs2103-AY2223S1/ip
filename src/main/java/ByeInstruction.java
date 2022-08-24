import java.util.Map;

public class ByeInstruction extends Instruction {
    public ByeInstruction(String mainArgument, Map<String, String> flagArgumentsMap) {
        super(mainArgument, flagArgumentsMap);
    }

    @Override
    public void execute(TaskList taskList) {

    }
}

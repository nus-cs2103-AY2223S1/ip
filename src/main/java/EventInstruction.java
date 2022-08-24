import java.util.Map;

public class EventInstruction extends Instruction {
    public EventInstruction(String mainArgument, Map<String, String> flagArgumentsMap) {
        super(mainArgument, flagArgumentsMap);
    }

    @Override
    public void execute(TaskList taskList) {

    }
}

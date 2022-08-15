import java.util.ArrayList;
import java.util.Arrays;

public class Command {
    private Action action;
    private ArrayList<String> parameters;

    public Command(Action action, String... parameters) {
        this.action = action;
        this.parameters = new ArrayList<>();
        Arrays.stream(parameters).forEach(x -> this.parameters.add(x));
    }

    public Action getAction() {
        return this.action;
    }

    public ArrayList<String> getParameters() {
        ArrayList<String> result = new ArrayList<>();
        this.parameters.stream().forEach(x -> result.add(x));
        return result;
    }
}

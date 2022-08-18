import java.util.List;

public class ListCommand extends Command {
    public ListCommand(String command) {
        super(command);
    }

    @Override
    public String execute(List<Task> tasks) {
        StringBuilder output = new StringBuilder();
        output.append("Here are your tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            output.append(String.format("\t %d. %s", i + 1, tasks.get(i)));
            if (i + 1 < tasks.size()) {
                output.append("\n");
            }
        }
        if (tasks.size() == 0) {
            return "You do not have any tasks.";
        }
        return output.toString();
    }
}

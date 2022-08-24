import java.util.ArrayList;

public class DeleteCommand extends Command{
    public DeleteCommand(ArrayList<String> rest) {
        super(false, rest);
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        if (rest.isEmpty()) {
            throw new IncompleteCommandException(String.join(" ", rest), "delete", "Please enter 1 appropriate integer");
        } else {
            int pos = java.lang.Integer.parseInt(rest.get(0));
            Task t = tl.get(pos - 1);
            tl.deleteTask(pos);
            ui.say("Poolsheen has forgot: " + t.description + " and you now have " + tl.size() + " tasks left");
        }
    }
}

import java.util.ArrayList;

public class ToDoCommand extends Command{
    public ToDoCommand(ArrayList<String> rest) {
        super(false, rest);
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        if (rest.isEmpty()) {
            throw new IncompleteCommandException(String.join(" ", rest), "todo", "The description of a todo cannot be empty");
        } else {
            String descTD = String.join(" ", rest);
            ToDo t = new ToDo(descTD, false);
            tl.add(t);
            ui.say("Poolsheen now remembers: " + descTD);
        }
    }
}

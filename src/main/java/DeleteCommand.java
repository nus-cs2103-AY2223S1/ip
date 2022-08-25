import java.util.ArrayList;

public class DeleteCommand extends Command {
    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.say("Noted. I've removed this task:", true, false);
        ui.say(tasks.get(index).toString(), false, false);
        tasks.remove(index);
        storage.writeFile(tasks, ui);
        ui.say("Now you have " + tasks.size() + " tasks in the list.", false, true);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

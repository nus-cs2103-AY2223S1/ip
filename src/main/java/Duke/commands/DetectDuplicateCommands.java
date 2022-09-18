package Duke.commands;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Duke.*;

public class DetectDuplicateCommands extends Command {

    /**
     * Executes the command to check if the list has duplicates
     *
     * @param tasks The task list containing all the tasks before the command is executed.
     * @param ui Provides access to the UI of the program.
     * @param storage Provides access to local storage.
     * @throws IOException when there is a problem with the IO.
     * @throws DukeException when there is a wrong input or save file issues.
     */

    @Override
    public List<String> execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        int counter = 0;
        ArrayList<String> text = new ArrayList<>();
        for (int i = 0; i < tasks.getTaskListSize(); i++) {
            for (int j = i + 1; j < tasks.getTaskListSize(); j++) {
                if (tasks.getTask(i) == tasks.getTask(j)) {
                    text.add("There seems to be a duplicate task in your planner");
                    text.add("The duplicate task is " + tasks.getTaskToString(i));
                    counter++;
                    break;
                }
            }
        }
        text.add("You have " + counter + " duplicate tasks");
        return text;
    }
}


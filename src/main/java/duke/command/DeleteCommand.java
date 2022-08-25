package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.util.TaskList;

import java.util.ArrayList;

public class DeleteCommand extends Command {
    private final ArrayList<String> words;

    public DeleteCommand(ArrayList<String> words) {
        this.words = words;
    }

    public void execute(Storage storage, TaskList tasklist, Ui ui) throws DukeException {
        String input = String.join(" ", words);
        // Implement error for "delete hello"
        int taskNum = Integer.parseInt(input);
        if (taskNum > 0 && taskNum <= tasklist.tasks.size()) {
            String deletedTask = tasklist.tasks.get(taskNum - 1).toString();
            tasklist.deleteTask(taskNum);
            System.out.println(ui.SPACER + "\n"
                    + "Boo~ Don't be a quitter! I've removed this task for you. =3=\n"
                    + deletedTask + "\n"
                    + "You have " + tasklist.tasks.size()
                    + (tasklist.tasks.size() == 1 ? " task! :D\n" : " tasks! :D\n")
                    + ui.SPACER);
        } else if (tasklist.tasks.size() == 0) {
            throw new DukeException(ui.SPACER + "\n"
                    + "There's nothing in your list to delete! T^T\n"
                    + ui.SPACER);
        } else {
            throw new DukeException(ui.SPACER + "\n"
                    + "Please enter a valid task number to delete. T^T\n"
                    + ui.SPACER);
        }
    }
}

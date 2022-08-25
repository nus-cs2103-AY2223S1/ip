package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.util.TaskList;

public class DeleteCommand extends Command {
    private final String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    public void execute(Storage storage, TaskList tasks, Ui ui) throws DukeException {
        // Implement error for "delete hello"
        int taskNum = Integer.parseInt(input);
        if (taskNum > 0 && taskNum <= tasks.tasklist.size()) {
            String deletedTask = tasks.tasklist.get(taskNum - 1).toString();
            tasks.deleteTask(taskNum);
            System.out.println(ui.SPACER + "\n"
                    + "Boo~ Don't be a quitter! I've removed this task for you. =3=\n"
                    + deletedTask + "\n"
                    + "You have " + tasks.tasklist.size()
                    + (tasks.tasklist.size() == 1 ? " task! :D\n" : " tasks! :D\n")
                    + ui.SPACER);
        } else if (tasks.tasklist.size() == 0) {
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

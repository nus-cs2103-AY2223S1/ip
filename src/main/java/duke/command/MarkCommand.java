package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.util.TaskList;

import java.util.ArrayList;
import java.util.Arrays;

public class MarkCommand extends Command {
    private final String input;

    public MarkCommand(String input) {
        this.input = input;
    }

    public void execute(Storage storage, TaskList tasks, Ui ui) throws DukeException {
        ArrayList<String> words = new ArrayList<>(Arrays.asList(input.split(" ")));
        switch (input) {
        case "mark":
            // Implement error for empty mark argument
            int taskNum = Integer.parseInt(words.get(0));
            if (taskNum > 0 && taskNum <= tasks.tasklist.size()) {
                tasks.tasklist.get(taskNum - 1).markDone();
                System.out.println(ui.SPACER + "\n"
                        + "Great Job on completing this task! ^.^ :\n"
                        + tasks.printTaskStatus(taskNum - 1) + "\n"
                        + ui.SPACER);
            } else if (tasks.tasklist.size() == 0) {
                throw new DukeException(ui.SPACER + "\n"
                        + "There's nothing in your list to mark! T^T\n"
                        + ui.SPACER);
            } else {
                throw new DukeException(ui.SPACER + "\n"
                        + "Please enter a valid task number to mark. T^T\n"
                        + ui.SPACER);
            }
            break;
        case "unmark":
            // Implement error for empty unmark argument
            taskNum = Integer.parseInt(words.get(0));
            if (taskNum > 0 && taskNum <= tasks.tasklist.size()) {
                tasks.tasklist.get(taskNum - 1).markUndone();
                System.out.println(ui.SPACER + "\n"
                        + "Grrr, remember to finish your task! =3=:\n"
                        + tasks.printTaskStatus(taskNum - 1) + "\n"
                        + ui.SPACER);
            } else if (tasks.tasklist.size() == 0) {
                throw new DukeException(ui.SPACER + "\n"
                        + "There's nothing in your list to unmark! T^T\n"
                        + ui.SPACER);
            } else {
                throw new DukeException(ui.SPACER + "\n"
                        + "Please enter a valid task number to unmark. T^T\n"
                        + ui.SPACER);
            }
            break;
        default:
            throw new DukeException("Sorry, I don't understand. T^T\n"
                    + "Please start your command with list, mark, unmark, todo, deadline, event or bye. :')\n");
        }
    }
}

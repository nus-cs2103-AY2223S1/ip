package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import task.Task;
import task.Todo;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class FindCommand extends Command {

    String str;

    public FindCommand(String str) {
        this.str = str;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String sub = str.substring(5).trim();
        if (!sub.isEmpty()) {
            Predicate<Task> filter = task -> task.stringify().contains(sub);
            Consumer<Task> print = task -> {
                if (filter.test(task)) {
                    System.out.println((tasks.getTasks().indexOf(task) + 1)
                            + ". " + task.toString());
                }
            };
            System.out.println("Here are the matching tasks in your list:");
            tasks.getTasks().forEach(print);
        } else {
            throw new DukeException("Please put in an argument for the find command.");
        }
    }
}

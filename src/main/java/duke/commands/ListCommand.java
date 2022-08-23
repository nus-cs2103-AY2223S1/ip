package duke.commands;

import duke.Ui;
import duke.task.TaskList;

import java.util.stream.IntStream;

/**
 * Lists all existing tasks.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    @Override
    public void execute(TaskList tasks, Ui ui) {
        StringBuilder output = new StringBuilder();
        output.append("Here are the tasks in your list:\n");
        IntStream.range(0, tasks.size()).forEach(i -> output.append(String.format("%d. %s%n", i + 1, tasks.get(i))));
        ui.displayText(output.toString());
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

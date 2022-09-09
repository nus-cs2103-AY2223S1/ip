package duke.command;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import duke.internal.MessageBuilder;
import duke.internal.Parser;
import duke.internal.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Prints the tasks that contain a given search query in its description.
 * Usage: find [0]
 * [0]: search query
 *
 * @since Level-9
 */
public class FindCommand extends Command {
    private final String query;

    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public boolean isTerminal() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, MessageBuilder messageBuilder,
                        Parser parser) throws IOException {
        List<Task> results = tasks.stream()
                .filter(task -> task.getDescription().contains(query))
                .collect(Collectors.toList());
        if (results.isEmpty()) {
            messageBuilder.addLine("No tasks matched your query.");
            return;
        }
        String repr = new TaskList(results).toString();
        messageBuilder.addLine("Here are the tasks matching your query!").addLine(repr);
    }
}

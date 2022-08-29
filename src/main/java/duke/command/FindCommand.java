package duke.command;

import java.util.stream.IntStream;

import duke.Ui;
import duke.task.TaskList;

public class FindCommand implements Command {
    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Find tasks in the task list. Parameters: DESCRIPTION. Example: " + COMMAND_WORD + " book";

    private String description;

    public FindCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        TaskList filteredList = taskList.findTask(description);
        String[] strArray = IntStream.range(0, filteredList.size()).mapToObj(i -> taskList.get(i).toString())
                .toArray(String[]::new);
        ui.printMessage(strArray, "Here are the matching tasks in your list:");
    }
}

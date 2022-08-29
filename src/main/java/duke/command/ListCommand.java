package duke.command;

import java.util.stream.IntStream;

import duke.Ui;
import duke.task.TaskList;

public class ListCommand implements Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all the tasks in the task list as a list with index numbers. Example: " + COMMAND_WORD;

    @Override
    public void execute(TaskList taskList, Ui ui) {
        String[] strArray = IntStream.range(0, taskList.size())
                .mapToObj(i -> String.format("%d.%s", i + 1, taskList.get(i).toString())).toArray(String[]::new);
        ui.printMessage(strArray);
    }
}

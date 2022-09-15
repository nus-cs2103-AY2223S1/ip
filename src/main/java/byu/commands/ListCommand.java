package byu.commands;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import byu.util.TaskList;
import byu.util.Ui;

/**
 * A command to list all the tasks in the TaskList.
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui) {
        String response = generateResponse(tasks);
        ui.setOutput(response);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String generateResponse(TaskList tasks) {
        assert tasks.getNumOfTasks() >= 0 : "size of list should be non-negative";

        String heading = "Here is a list of your tasks:\n";
        IntStream intStream = IntStream.rangeClosed(1, tasks.getNumOfTasks());
        Stream<String> stringStream = intStream.mapToObj(
                i -> String.format("%d. %s\n", i, tasks.getTask(i)));
        String result = stringStream.reduce("", (x, y) -> x + y);
        return heading + result;
    }

}

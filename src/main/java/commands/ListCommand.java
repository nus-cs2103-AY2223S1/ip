package commands;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import byu.TaskList;
import byu.Ui;

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
        String output = "These are the tasks in your list:\n";
        IntStream intStream = IntStream.rangeClosed(1, tasks.getNumOfTasks());
        Stream<String> stringStream = intStream.mapToObj(
                i -> String.format("%d. %s\n", i, tasks.getTask(i).toString()));
        String result = stringStream.reduce("", (x, y) -> x + y);
        output += result;
        return output;
    }

}

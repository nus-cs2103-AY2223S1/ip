package dukeprogram.command;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import dukeprogram.DukeResponse;
import dukeprogram.InternalAction;
import dukeprogram.Widget;
import dukeprogram.facilities.TaskList;

/**
 * A ListTaskCommand specifies a command that allows the main program
 * to list out all the tasks presently stored in the task list.
 */
public class ListTasksCommand extends Command {
    @Override
    protected InternalAction onEnter() {
        TaskList currentTaskList = TaskList.current();

        String formattedTaskListString = IntStream.range(0, currentTaskList.getSize())
                        .mapToObj(i -> i + 1 + ". " + currentTaskList.get(i).toString())
                        .collect(Collectors.joining("\n"));

        return new InternalAction(new DukeResponse(
                "Here is your task list:",
                new Widget("Tasks:", formattedTaskListString)));
    }

    @Override
    protected InternalAction onStay() {
        return onEnter();
    }

    @Override
    public InternalAction onParse(String input) {
        return new InternalAction("Uh... I'm not sure what to do with that...");
    }

    @Override
    public Command onExit() {
        return null;
    }
}

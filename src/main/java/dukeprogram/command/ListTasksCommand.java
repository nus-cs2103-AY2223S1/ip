package dukeprogram.command;

import dukeprogram.InternalAction;
import dukeprogram.facilities.TaskList;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ListTasksCommand extends Command {
    @Override
    protected InternalAction onEnter() {
        TaskList currentTaskList = TaskList.current();

        return new InternalAction(
                "This is your task list: \n"
                        + IntStream.range(0, currentTaskList.getSize())
                        .mapToObj(i -> i + 1 + ". " + currentTaskList.get(i).toString())
                        .collect(Collectors.joining("\n"))
        );
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

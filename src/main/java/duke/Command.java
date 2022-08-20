package duke;

import duke.Exceptions.InvalidCommandException;
import duke.Exceptions.NoSuchTaskException;
import duke.Task.*;

abstract public class Command {
    abstract void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidCommandException;

    boolean isExit() {
        return this instanceof ExitCommand;
    }
}

class ExitCommand extends Command {
    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showGoodByeMessage();
    }
}

class ListTasksCommand extends Command {
    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) throws NoSuchTaskException {
        ui.showMessage("Here are the tasks in your list:");
        for (int i = 0; i < taskList.getNumTasks(); i++) {
            ui.showMessage(String.format("%d. %s", i+1, taskList.get(i)));
        }
    }
}

class DeleteTaskCommand extends Command {
    int index;
    DeleteTaskCommand(int index) {
        this.index = index;
    }
    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) throws NoSuchTaskException {
        Task task = taskList.deleteTaskAtIndex(index);
        ui.showMessage(
                String.format("Noted. I've removed this task:\n\t%s\n",
                        task));
        storage.save(taskList);
    }
}
class MarkCommand extends Command {
    int index;
    MarkCommand(int index) {
        this.index = index;
    }
    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) throws NoSuchTaskException {
        Task task = taskList.get(index);
        task.markAsCompleted();
        storage.save(taskList);
    }
}

class UnmarkCommand extends Command {
    int index;
    UnmarkCommand(int index) {
        this.index = index;
    }
    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) throws NoSuchTaskException {
        Task task = taskList.get(index);
        task.markAsIncomplete();
        storage.save(taskList);
    }
}

class CreateTaskCommand extends Command {
    Task task;
    CreateTaskCommand(Task task) {
        this.task = task;
    }
    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.store(task);
        ui.showMessage(
                String.format("Got it. I've added this task:\n\t%s\n",
                        task));
        storage.save(taskList);
    }
}

class CreateTodoCommand extends CreateTaskCommand {
    CreateTodoCommand(Todo todo) {
        super(todo);
    }
}

class CreateDeadlineCommand extends CreateTaskCommand {
    CreateDeadlineCommand(Deadline deadline) {
        super(deadline);
    }
}
class CreateEventCommand extends CreateTaskCommand {
    CreateEventCommand(Event event) {
        super(event);
    }
}

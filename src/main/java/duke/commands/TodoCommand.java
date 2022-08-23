package duke.commands;

import java.io.IOException;
import duke.TaskList;
import duke.DukeException;
import duke.Ui;
import duke.Storage;
import duke.tasks.*;

public class TodoCommand extends Command{
    private String input;

    public TodoCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        Todo todo = new Todo(this.input);
        taskList.append(todo);
        String todoMessage = "added: " + todo.toString() + "\n";
        todoMessage += String.format("Now, you have %d task(s) in the list.", taskList.length());
        ui.print(todoMessage);
        storage.saveTasks(taskList);
    }
}

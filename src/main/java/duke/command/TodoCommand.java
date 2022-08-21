package duke.command;

import duke.exception.InvalidDescriptionException;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.Ui;
import duke.Storage;

public class TodoCommand extends Command {
    String desc;

    public TodoCommand(String desc) {
        try {
            if (desc.isEmpty()) {
                throw new InvalidDescriptionException();
            }

            this.desc = desc;
        } catch (InvalidDescriptionException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(new ToDo(this.desc));
        storage.saveTaskFile(taskList);
    }
}

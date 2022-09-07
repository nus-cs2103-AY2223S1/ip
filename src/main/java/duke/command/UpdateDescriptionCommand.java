package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidInputException;

import java.time.LocalDate;

public class UpdateDescriptionCommand extends Command {
    private int updateIndex;
    private String updatedDescription;

    public UpdateDescriptionCommand(int updateIndex, String updatedDescription) {
        super();
        this.updateIndex = updateIndex;
        this.updatedDescription = updatedDescription;
    }

    @Override
    public String run(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException {
        if (updateIndex > tasks.getSize() || updateIndex < 0) {
            throw new InvalidInputException("The index provided is not within the list.");
        }
        String updatedTask = tasks.updateTaskDescription(this.updateIndex,this.updatedDescription);
        storage.save(tasks.getTaskListInString());
        return ui.printUpdate(updatedTask);
    }
}

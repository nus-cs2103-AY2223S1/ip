package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidInputException;

import java.time.LocalDate;

public class UpdateDateCommand extends Command {

    private int updateIndex;
    private LocalDate updatedDate;

    public UpdateDateCommand(int updateIndex, LocalDate updatedDate) {
        super();
        this.updateIndex = updateIndex;
        this.updatedDate = updatedDate;
    }

    @Override
    public String run(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException {
        if (updateIndex > tasks.getSize() || updateIndex < 0) {
            throw new InvalidInputException("The index provided is not within the list.");
        }
        String updatedTask = tasks.updateTaskDate(this.updateIndex, this.updatedDate);
        storage.save(tasks.getTaskListInString());
        return ui.printUpdate(updatedTask);
    }



}

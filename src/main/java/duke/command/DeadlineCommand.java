package duke.command;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import duke.exception.DeadlineException;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.util.Pair;

/**
 * Deadline command for Duke application
 *
 * @author Farrel Dwireswara Salim
 */
public class DeadlineCommand implements Command {
    private final String description;

    /**
     * Constructs a new instance of DeadlineCommand.
     *
     * @param description the description of the command.
     */
    public DeadlineCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the DeadlineCommand and returns the response pair.
     *
     * @param ui the Ui object to handle user interface.
     * @param storage the storage used by the DeadlineCommand.
     * @param taskList the task list used by the DeadlineCommand.
     * @return the response pair.
     * @throws DukeException If Duke fails to execute the DeadlineCommand.
     */
    @Override
    public Pair<Boolean, String> execute(Ui ui, Storage storage, TaskList taskList)
            throws DukeException {
        List<String> deadlineInfo = Arrays.stream(description.split("/by", 2))
                .map(String::trim)
                .filter(info -> !info.isEmpty())
                .collect(Collectors.toList());

        if (deadlineInfo.size() < 2) {
            throw new DeadlineException();
        }

        int tagsIndex = deadlineInfo.get(1).indexOf("#");
        String dateString = tagsIndex < 0
                ? deadlineInfo.get(1)
                : deadlineInfo.get(1).substring(0, tagsIndex).trim();
        String[] tags;

        if (tagsIndex < 0 || tagsIndex >= deadlineInfo.get(1).length() - 1) {
            tags = new String[0];
        } else {
            tags = Arrays.stream(deadlineInfo.get(1).substring(tagsIndex + 1).split(","))
                    .map(String::trim)
                    .filter(tag -> !tag.isEmpty())
                    .toArray(String[]::new);
        }

        Task newTask = new Deadline(deadlineInfo.get(0), dateString, tags);
        taskList.addTask(newTask);
        storage.saveTasksInStorage(taskList.toStorageRepresentation());

        String responseMessage = "This task is successfully added:\n " + newTask
                + "\nNow you have " + taskList.getTaskListSize() + " task(s) in the list";
        ui.printMessage(responseMessage);

        return new Pair<>(true, responseMessage);
    }
}

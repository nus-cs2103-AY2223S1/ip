package duke.commands;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import duke.TaskList;
import duke.DukeException;
import duke.Ui;
import duke.Storage;
import duke.tasks.*;

/**
 * The DeadlineCommand class encapsulates the execution of a deadline command.
 */
public class DeadlineCommand extends Command{
    private String input;

    public DeadlineCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the deadline command.
     * @param taskList List where a deadline is to be added to it.
     * @param ui Ui which sends a message to the user after a successful execution or when an error is thrown.
     * @param storage Storage which saves the modified tasklist to the hard disk after successful execution of command.
     * @throws DukeException
     * @throws IOException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        String[] splitDetailDeadline = this.input.split("/by", 2);
        if (splitDetailDeadline.length == 1) {
            throw new DukeException("Description of deadline must be followed by /by then followed by time of deadline.");
        } else {
            String deadlineAction = splitDetailDeadline[0].trim();
            String deadlineTime = splitDetailDeadline[1].trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime deadlineDateTime = LocalDateTime.parse(deadlineTime, formatter);
            Deadline deadline = new Deadline(deadlineAction, deadlineDateTime);
            taskList.append(deadline);
            String deadlineMessage = "added: " + deadline.toString() + "\n";
            ui.print(deadlineMessage, taskList);
        }
        storage.saveTasks(taskList);
    }
}

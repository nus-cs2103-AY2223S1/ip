import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineCommand extends Command{
    private String input;

    public DeadlineCommand(String input) {
        this.input = input;
    }

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
            deadlineMessage += String.format("Now, you have %d task(s) in the list.", taskList.length());
            ui.print(deadlineMessage);
        }
        storage.saveTasks(taskList);
    }
}

package duke.command;

import duke.DukeException;
import duke.task.Deadline;
import duke.util.TaskList;

import java.time.LocalDate;

public class DeadlineCommand extends Command{
    private String input;

    public DeadlineCommand(String input) {
        this.input = input;
    }

    @Override
    public void run(TaskList taskList) throws DukeException {
        String[] segments = input.split("/by");
        if (segments.length != 2) {
            throw new DukeException("Error with deadline input");
        } else {
            String time = segments[1].strip();
            LocalDate date = LocalDate.parse(time);
            Deadline deadline = new Deadline(segments[0], date);
            taskList.createTask(deadline);
        }
    };
}

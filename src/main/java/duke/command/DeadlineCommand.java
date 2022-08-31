package duke.command;

import duke.InvalidDateException;
import duke.task.Deadline;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.time.DateTimeException;

/**
 * DeadlineCommand adds a deadline to tasks.
 */
public class DeadlineCommand extends Command {
    private String deadlineDescription;
    private String by;

    /**
     * Constructor for DeadlineCommand
     * @param deadlineDescription deadline description.
     * @param by due date of deadline.
     */
    public DeadlineCommand(String deadlineDescription, String by) {
        super();
        this.deadlineDescription = deadlineDescription;
        this.by = by;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidDateException {
        try {
            Deadline deadline = new Deadline(deadlineDescription, by);
            tasks.addToTaskList(deadline);
            storage.save(tasks.getTasks());
            return String.format("Got it. I've added this task:\n" +
                            "added: %s\n" +
                            "Now you have %s task%s in the list.",
                    deadline.toString(),
                    String.valueOf(tasks.getSize()),
                    tasks.getSize() == 1 ? "" : "s");
        } catch (DateTimeException e) {
            throw new InvalidDateException();
        }
    }
}

package zupey.handlers;

import java.time.format.DateTimeParseException;

import zupey.entities.Deadline;
import zupey.entities.Task;
import zupey.exceptions.ZupeyException;
import zupey.service.Service;


/**
 * Handles the user command for creating a new Deadline.
 */
public class DeadlineHandler implements IHandler {
    private String deadlineName;
    private String flag;
    private String flagOption;

    /**
     * Constructs new DeadlineHandler from a HandlerFactory.
     * @param factory
     */
    public DeadlineHandler(HandlerFactory factory) {
        this.deadlineName = factory.getTaskName();
        this.flag = factory.getFlag();
        this.flagOption = factory.getFlagOption();
    }

    /**
     * Handles the "deadline" command by adding a new deadline to service's tasklist
     *
     * @param s Service object of the application
     * @throws ZupeyException
     */
    @Override
    public String handle(Service s) throws ZupeyException {
        if (this.deadlineName == null) {
            throw new ZupeyException("Please enter a task name!\nUsage:`deadline return book /by Sunday`");
        }
        if (this.flag == null) {
            throw new ZupeyException("Please enter a /by time!\nUsage:`deadline return book /by Sunday`");
        }
        if (!flag.equals("by")) {
            throw new ZupeyException("Incorrect option flag!\nUsage:`deadline return book /by Sunday`");
        }
        if (this.flagOption == null) {
            throw new ZupeyException("Please enter a deadline!\nUsage:`deadline return book /by Sunday`");
        }
        try {
            Task deadline = new Deadline(this.deadlineName, this.flagOption);
            s.saveTasks();
            s.addToList(deadline);
            int size = s.getList().size();
            assert size != 0;
            return String.format("Got it. I've added this task:\n  "
                    + deadline
                    + "\nNow you have %d task%s in the list.", size, size != 1 ? "s" : "");
        } catch (DateTimeParseException ex) {
            throw new ZupeyException("Invalid Date/Time!\nUsage: `deadline return book /at 2/12/2019 1800`");
        }
    }
}

package duke.logic.task;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.storage.DukeEncoder;


/**
 * Represents a deadline task.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructor for deadline.
     *
     * @param detail String
     */
    public Deadline(String detail, String by) {
        super(detail);
        this.by = LocalDateTime.parse(by);
    }

    /**
     * Constructor for deadline.
     *
     * @param detail
     * @param isDone
     * @param by
     */
    public Deadline(String detail, boolean isDone, String by) {
        super(detail, isDone);
        this.by = LocalDate.parse(by);
    }

    /**
     * Add a deadline task
     *
     * @param userInput text the user typed
     * @param workList
     */
    public static String add(ArrayList<Task> workList, String userInput) {
        try {
            // Error when deadline followed by a blank space
            userInput.substring(10);
            // Error when just deadline
            String[] commandSplit = userInput.substring(9).split(" /by ");
            Deadline deadline = new Deadline(commandSplit[0], commandSplit[1]);
            workList.add(deadline);
            // Update Storage
            DukeEncoder.rewriteList(workList);
            return Task.add(workList, userInput) + deadline + "\n"
                    + updateNumOfTask(workList);
        } catch (StringIndexOutOfBoundsException e) {
            return new DukeException.EmptyDeadlineException().throwDukeException();
        } catch (ArrayIndexOutOfBoundsException e) {
            return new DukeException.DeadlineWithoutByException().throwDukeException();
        }

    }

    /**
     * Returns String form of the task
     * @return String
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns String to be stored in the hardware list.
     * @return String
     */
    @Override
    public String storedData() {
        return "D" + "|" + super.storedData() + "|" + by;
    }
}

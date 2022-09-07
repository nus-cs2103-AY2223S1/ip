package duke.logic.task;

import duke.exception.DukeException;
import duke.storage.DukeEncoder;

import java.util.ArrayList;

/**
 * Represents a event task.
 */
public class Event extends Task {

    protected String at;
    /**
     * Constructor for event.
     *
     * @param detail String
     */
    public Event(String detail, String at) {
        super(detail);
        this.at = at;
    }

    /**
     * Constructor for event.
     * @param detail
     * @param isDone
     * @param at
     */
    public Event(String detail, boolean isDone, String at) {
        super(detail, isDone);
        this.at = at;
    }

    /**
     * Add a event task
     *
     * @param userInput text the user typed
     * @param workList
     */
    public static String add(ArrayList<Task> workList, String userInput) {
        try {
            // Error when event followed by a blank space
            userInput.substring(7);
            // Error when just event
            String[] commandSplit = userInput.substring(6).split(" /at ");
            Event event = new Event(commandSplit[0], commandSplit[1]);
            workList.add(event);
            // Update Storage
            DukeEncoder.rewriteList(workList);
            return Task.add(workList, userInput) + event + "\n"
                    + updateNumOfTask(workList);
        } catch (StringIndexOutOfBoundsException e) {
            return new DukeException.EmptyEventException().throwDukeException();

        } catch (ArrayIndexOutOfBoundsException e) {
            return new DukeException.EventWithoutAtException().throwDukeException();
        }

    }
    /**
     * Returns String form of the task
     * @return String
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Returns String to be stored in the hardware list.
     * @return String
     */
    @Override
    public String storedData() {
        return "E" + "|" + super.storedData() + "|" + at;
    }
}

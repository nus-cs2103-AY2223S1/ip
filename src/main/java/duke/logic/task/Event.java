package duke.logic.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.storage.DukeEncoder;


/**
 * Represents a event task.
 */
public class Event extends Task {

    protected LocalDate at;
    /**
     * Constructor for event.
     *
     * @param detail String.
     */
    public Event(String detail, String at) {
        super(detail);
        this.at = LocalDate.parse(at);
    }

    /**
     * Constructor for event.
     * @param detail
     * @param isDone
     * @param at
     */
    public Event(String detail, boolean isDone, String at) {
        super(detail, isDone);
        this.at = LocalDate.parse(at);
    }

    /**
     * Add a event task.
     *
     * @param userInput text the user typed.
     * @param workList
     */
    public static String add(ArrayList<Task> workList, String userInput) {
        try {
            userInput.substring(7);
            String[] commandSplit = userInput.substring(6).split(" /at ");
            Event event = new Event(commandSplit[0], commandSplit[1]);
            workList.add(event);
            DukeEncoder.rewriteList(workList);
            return Task.add(workList, userInput) + event + "\n"
                    + updateNumOfTask(workList);
        } catch (StringIndexOutOfBoundsException e) {
            return new DukeException.EmptyEventException().throwDukeException();
        } catch (ArrayIndexOutOfBoundsException e) {
            return new DukeException.EventWithoutAtException().throwDukeException();
        } catch (DateTimeParseException e) {
            return new DukeException.WrongDateFormat().throwDukeException();
        }
    }
    /**
     * Returns String form of the task.
     * @return String.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns String to be stored in the hardware list.
     * @return String.
     */
    @Override
    public String storedData() {
        return "E" + "|" + super.storedData() + "|" + at;
    }
}

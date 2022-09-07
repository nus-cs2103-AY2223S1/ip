package duke.logic.task;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.storage.DukeEncoder;
import duke.ui.Constants;


/**
 * Represents a general task.
 */
public class Task {
    private String detail;
    private boolean isDone;

    /**
     * Constructor for task.
     * @param detail String
     */
    public Task(String detail) {
        this.detail = detail;
        this.isDone = false;
    }

    /**
     * Constructor for task.
     * @param detail String
     */
    public Task(String detail, boolean isDone) {
        this.detail = detail;
        this.isDone = isDone;
    }
    /**
     * Get icon for status done or not
     * @return String
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    /**
     * Mark task as Done and Print acknowledge message.
     */
    public static String markAsDone(ArrayList<Task> workList, String userInput) {
        try {
            userInput.substring(6);
            int index = Integer.parseInt(userInput.split(" ")[1]);
            Task task = workList.get(index - 1);
            task.isDone = true;
            DukeEncoder.rewriteList(workList);
            return Constants.MARK_AS_DONE_MESSAGE + task;
        } catch (StringIndexOutOfBoundsException e) {
            return new DukeException.EmptyMarkingException().throwDukeException();
        } catch (NumberFormatException e) {
            return new DukeException.EmptyMarkingException().throwDukeException();
        } catch (IndexOutOfBoundsException e) {
            return new DukeException.EmptyMarkingException().throwDukeException();
        }
    }

    /**
     * Mark task as not Done and Print acknowledge message.
     */
    public static String markAsNotDone(ArrayList<Task> workList, String userInput) {
        try {
            userInput.substring(8);
            int index = Integer.parseInt(userInput.split(" ")[1]);
            Task task = workList.get(index - 1);
            task.isDone = false;
            DukeEncoder.rewriteList(workList);
            return Constants.UNMARK_MESSAGE + task;
        } catch (StringIndexOutOfBoundsException e) {
            return new DukeException.EmptyMarkingException().throwDukeException();
        } catch (NumberFormatException e) {
            return new DukeException.EmptyMarkingException().throwDukeException();
        } catch (IndexOutOfBoundsException e) {
            return new DukeException.EmptyMarkingException().throwDukeException();
        }
    }

    /**
     * Returns String form of the task
     * @return String
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.detail;
    }

    protected String statusIcon() {
        return (this.isDone ? "1" : "0");
    }

    /**
     * Returns String to be stored in the hardware list.
     * @return String
     */
    public String storedData() {
        return statusIcon() + "|" + detail;
    }

    public boolean contain(String s) {
        return detail.contains(s);
    }
}

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
     * Print all item in the word list
     */
    public static String listItems(ArrayList<Task> workList) {
        String toPrint = Constants.LISTING_MESSAGE + "\n";
        for (int i = 0; i < workList.size(); i++) {
            toPrint += ((i + 1) + ") " + workList.get(i).toString()) + "\n";
        }
        return toPrint;
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
     * Add text that user typed to the word list
     *
     * @param userInput text the user typed
     * @param workList
     */
    public static String add(ArrayList<Task> workList, String userInput) {
        return Constants.ARROW + "Added task: ";
    }

    /**
     * Delete a task
     *
     * @param userInput text the user typed
     * @param workList
     */
    public static String delete(ArrayList<Task> workList, String userInput) {
        try {
        userInput.substring(8);
        int index = Integer.parseInt(userInput.split(" ")[1]);
        Task task = workList.get(index - 1);
        workList.remove(task);
        DukeEncoder.rewriteList(workList);
        // Update data
        return (Constants.ARROW + "Deleted task: " + task.toString()) + "\n"
                + Task.updateNumOfTask(workList);
    } catch (StringIndexOutOfBoundsException e) {
        return new DukeException.EmptyDeleteException().throwDukeException();

    } catch (NumberFormatException e) {
        return new DukeException.EmptyDeleteException().throwDukeException();

    } catch (IndexOutOfBoundsException e) {
        return new DukeException.EmptyDeleteException().throwDukeException();

    }
    }


    /**
     * Updates number of task in the list
     * @return String
     */
    public static String updateNumOfTask(ArrayList<Task> workList) {
        return "Now you have " + workList.size() + " task(s) on your list.";
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

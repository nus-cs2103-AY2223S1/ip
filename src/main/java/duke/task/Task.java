package duke.task;

import java.util.ArrayList;
import java.util.Arrays;

import duke.DukeException;

/**
 * Represents a task.
 */
public class Task {

    private static final String ALREADY = "This task is already marked as ";
    private static final int MONTH_POSITION = 0;
    private static final int DAY_POSITION = 1;
    private static final int YEAR_POSITION = 2;
    private static final int CORRECT_MONTH_LENGTH = 3;
    private static final int CORRECT_DAY_LENGTH = 2;
    private static final int CORRECT_YEAR_LENGTH = 4;
    private static final String[] TASK_TYPES_ARRAY = new String[] {"D", "T", "E"};
    private static final ArrayList<String> TASK_TYPES_LIST = new ArrayList<>(Arrays.asList(TASK_TYPES_ARRAY));
    public static final String LOAD_DATE_FORMAT = "MMM dd yyyy";
    public static final String SAVE_DATE_FORMAT = "yyyy-mm-dd";
    private static final String MARK_DONE_MARKER = "X";
    private static final String MARK_UNDONE_MARKER = " ";

    private final String taskDescription;
    private boolean isDone;

    Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    Task(String taskDescription, boolean isDone) {
        this.taskDescription = taskDescription;
        this.isDone = isDone;
    }

    /**
     * Checks date input for correct format.
     *
     * @param date date data.
     * @return boolean.
     */
    public static boolean isCorrectFormat(String date) {
        String [] dateDetails = date.split("");
        boolean monthCorrectFormat = (dateDetails[MONTH_POSITION].length() == CORRECT_MONTH_LENGTH);
        boolean dayCorrectFormat = (dateDetails[DAY_POSITION].length() == CORRECT_DAY_LENGTH);
        boolean yearCorrectFormat = (dateDetails[YEAR_POSITION].length() == CORRECT_YEAR_LENGTH);
        return monthCorrectFormat && dayCorrectFormat && yearCorrectFormat;
    }

    /**
     * Checks whether task type is an existing type.
     *
     * @param type task type data.
     * @return boolean.
     */
    public static boolean isCorrectTaskType(String type) {
        return TASK_TYPES_LIST.contains(type);
    }

    /**
     * Marks task as done.
     *
     * @throws DukeException If task is done.
     */
    public void doing() throws DukeException {
        if (!this.isDone) {
            this.isDone = true;
        } else {
            throw new DukeException(ALREADY + "done");
        }
    }

    /**
     * Unmarks task as not done.
     *
     * @throws DukeException If task is not done.
     */
    public void undo() throws DukeException {
        if (this.isDone) {
            this.isDone = false;
        } else {
            throw new DukeException(ALREADY + "not done");
        }
    }

    /**
     * Returns task description.
     *
     * @return task description.
     */
    public String getDescription() {
        return this.taskDescription;
    }

    /**
     * Returns task status.
     *
     * @return task status.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns task.
     *
     * @return task.
     */
    @Override
    public String toString() {
        String marker;
        if (this.isDone) {
            marker = MARK_DONE_MARKER;
        } else {
            marker = MARK_UNDONE_MARKER;
        }
        return "[" + marker + "] " + this.taskDescription;
    }
}

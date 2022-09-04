package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a task list.
 */
public class TaskList {

    private ArrayList<Task> tasks = new ArrayList<>();

    private static final String INDEX_OUT_OF_RANGE_MESSAGE = "Index given is out of range";
    private static final String SAVE_MARK_DONE = "1";
    private static final String SAVE_MARK_UNDONE = "0";
    private static final String SAVE_DETAILS_SEPERATOR = ",";
    private static final String DATE_SEPERATOR = ":";
    private static final int LENGTH_WITH_DATE_IN_STRING = 2;
    private static final int POSITION_OF_DATE_IN_STRING = 1;
    private static final int START_POSITION_OF_TYPE_IN_STRING = 1;
    private static final int END_POSITION_OF_TYPE_IN_STRING = 2;
    private static final int START_POSITION_OF_DATE_IN_STRING = 1;
    private static final int TASK_CONTAINS_DATE = 4;
    private static final int POSITION_OF_TASK_TYPE_IN_TASK = 1;
    private static final int POSITION_OF_MARK_IN_TASK = 1;
    private static final int POSITION_OF_TASK_DESCRIPTION_IN_TASK = 2;
    private static final int POSITION_OF_DATE_IN_TASK = 3;
    /**
     * Constructs an empty task list.
     */
    TaskList() {

    }

    /**
     * Constructs a task list.
     *
     * @param data Data containing tasks in the list.
     */
    TaskList(String data) {
        if (!data.equals("")) {
            String[] tasksArray = data.split("\n");
            for (String task : tasksArray) {
                this.tasks.add(stringToTask(task));
            }
        }
    }

    private TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    private static Task stringToTask(String input) {
        String [] taskDetails = input.split(SAVE_DETAILS_SEPERATOR);
        String taskDescription = taskDetails[POSITION_OF_TASK_DESCRIPTION_IN_TASK];
        boolean isDone;
        isDone = taskDetails[POSITION_OF_MARK_IN_TASK].equals(SAVE_MARK_DONE);
        if (taskDetails.length == TASK_CONTAINS_DATE) {
            String time = taskDetails[POSITION_OF_DATE_IN_TASK];
            LocalDate date = LocalDate.parse(time, DateTimeFormatter.ofPattern(Task.LOAD_DATE_FORMAT));
            if (taskDetails[POSITION_OF_TASK_TYPE_IN_TASK].equals(Deadline.TASK_TYPE)) {
                return new Deadline(taskDescription, isDone, date);
            } else {
                return new Event(taskDescription, isDone, date);
            }
        }
        return new Todo(taskDescription, isDone);
    }

    private static String taskToString(Task task) {
        String taskDescription = task.getDescription();
        String completed = (task.isDone()) ? SAVE_MARK_DONE : SAVE_MARK_UNDONE;
        String type = task.toString().substring(START_POSITION_OF_TYPE_IN_STRING, END_POSITION_OF_TYPE_IN_STRING);
        String [] splitTime = task.toString().split(DATE_SEPERATOR);
        if (splitTime.length == LENGTH_WITH_DATE_IN_STRING) {
            String time = splitTime[POSITION_OF_DATE_IN_STRING];
            return String.join(SAVE_DETAILS_SEPERATOR, type, completed, taskDescription,
                    time.substring(START_POSITION_OF_DATE_IN_STRING, time.length() - 1));
        }
        return String.join(SAVE_DETAILS_SEPERATOR, type, completed, taskDescription);
    }

    /**
     * Adds task to task list.
     *
     * @param task Target task.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes task from task list.
     *
     * @param ind Index of targeted task.
     * @return deleted task.
     * @throws DukeException if ind >= tasks.size() or ind < 0.
     */
    public Task delete(int ind) throws DukeException {
        try {
            Task deletingTask = this.tasks.get(ind);
            this.tasks.remove(ind);
            return deletingTask;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(INDEX_OUT_OF_RANGE_MESSAGE);
        }
    }

    /**
     * Gets target task from task list.
     *
     * @param i Index of target task.
     * @return target task.
     * @throws DukeException if i >= tasks.size() or i < 0.
     */
    public Task get(int i) throws DukeException {
        try {
            return this.tasks.get(i);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(INDEX_OUT_OF_RANGE_MESSAGE);
        }
    }

    /**
     * Returns a task list filtered by a keyword.
     *
     * @param keyword Keyword phrase.
     * @return Filtered task list.
     */
    public TaskList find(String keyword) {
        String[] keywords = keyword.split(" ");
        ArrayList<Task> filteredTasks = new ArrayList<>();
        ArrayList<Boolean> added = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            added.add(false);
        }
        for (String word : keywords) {
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                if (task.getDescription().contains(word) && !added.get(i)) {
                    filteredTasks.add(task);
                    added.set(i, true);
                }
            }
        }
        return new TaskList(filteredTasks);
    }

    /**
     * Returns size of task list.
     *
     * @return size.
     */
    protected int size() {
        return this.tasks.size();
    }

    /**
     * Changes task list to a string format.
     *
     * @return data.
     */
    public String toString() {
        StringBuilder data = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            if (i != 0) {
                data.append(System.lineSeparator());
            }
            data.append(taskToString(this.tasks.get(i)));
        }
        return data.toString();
    }
}

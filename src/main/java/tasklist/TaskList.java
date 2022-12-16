package tasklist;

import java.time.LocalDate;
import java.util.ArrayList;

import exception.FredException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

/**
 * TaskList that Fred uses to store the tasks of the current session
 */
public class TaskList {

    protected ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns taskList so that it can be modified.
     * @return Reference to the ArrayList in TaskList.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Returns size of taskList.
     * @return Size of taskList.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Retrieve a task with the specified index from taskList.
     * @param index Index of the wanted task.
     * @return Task that was wanted.
     * @throws FredException
     */
    public Task getTask(int index) throws FredException {
        if (index > taskList.size()) {
            throw new FredException("Your list has only " + taskList.size() + " items!");
        } else if (index <= 0) {
            throw new FredException("Input an integer that is greater than 0!");
        }

        return taskList.get(index - 1);
    }

    /**
     * Lists out all the tasks in taskList.
     * @return String containing a list of the tasks in taskList.
     * @throws FredException
     */
    public String list() throws FredException {
        if (taskList.size() == 0) {
            throw new FredException("There are no items in your list!");
        }

        String list = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            list += (i + 1) + "." + taskList.get(i) + "\n";
        }
        return list;
    }

    /**
     * Mark given task as done.
     * @param index Index of task to be marked.
     * @throws FredException
     */
    public void mark(int index) throws FredException {
        if (index > taskList.size()) {
            throw new FredException("Your list has only " + taskList.size() + " items!");
        } else if (index <= 0) {
            throw new FredException("Input an integer that is greater than 0!");
        }

        taskList.get(index - 1).setStatus(true);
    }

    /**
     * Unmark given task.
     * @param index Index of task to be unmarked.
     * @throws FredException
     */
    public void unmark(int index) throws FredException {
        if (index > taskList.size()) {
            throw new FredException("Your list has only " + taskList.size() + " items!");
        } else if (index <= 0) {
            throw new FredException("Input an integer that is greater than 0!");
        }

        taskList.get(index - 1).setStatus(false);
    }

    /**
     * Add given task to taskList.
     * @param task Task to be added to taskList.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Delete given task from taskList.
     * @param index Index of task to be deleted.
     * @throws FredException
     */
    public void delete(int index) throws FredException {
        if (index > taskList.size()) {
            throw new FredException("Your list has only " + taskList.size() + " items!");
        } else if (index <= 0) {
            throw new FredException("Input an integer that is greater than 0!");
        }

        taskList.remove(index - 1);
    }

    /**
     * Find all tasks containing given keyword and list them.
     * @param keyword Keyword to be found in tasks.
     * @return String containing a list of tasks that contain the given keyword.
     * @throws FredException
     */
    public String find(String keyword) throws FredException {
        String list = "Here are matching tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getDescription().contains(keyword)) {
                list += (i + 1) + "." + taskList.get(i) + "\n";
            }

        }

        if (list.equals("Here are matching tasks in your list:\n")) {
            throw new FredException("Could not find task with this keyword!");
        }
        return list;
    }

    /**
     * Snooze task with given taskIndex to given date
     * @param taskIndex taskIndex of task to be snoozed
     * @param date date for task to be snoozed to
     * @return String containing snoozed task
     */
    public String snooze(int taskIndex, LocalDate date) throws FredException {

        String message = "Here is the new snoozed task:\n";
        Task task = getTask(taskIndex);

        if (task instanceof ToDo) {
            throw new FredException("ToDo cannot be snoozed!");
        } else if (task instanceof Event) {
            Event event = (Event) task;
            event.setAt(date);
            message += event.toString();
            message += "\n";
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            deadline.setBy(date);
            message += deadline.toString();
            message += "\n";
        } else {
            assert (!(task instanceof ToDo) && !(task instanceof Event) && !(task instanceof Deadline));
        }

        return message;
    }
}

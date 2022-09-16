package duke.tasklist;

import duke.UI.UIText;
import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    protected static final UIText UIText = new UIText();
    protected ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Prints list of tasks related to description
     *
     * @param description
     * @return
     */
    public String find(String description) {
        ArrayList<Task> relatedTaskList = new ArrayList<Task>();

        for (int i = 0; i < taskList.size(); ++i) {
            Task task = taskList.get(i);
            if (task.getDescription().toLowerCase().matches(("(.*)" + description + "(.*)").toLowerCase())) {
                relatedTaskList.add(task);
            }
        }

        String response = UIText.FINDING_RELATED_TASK;

        if (relatedTaskList.size() <= 0) {
            response += UIText.NO_RELATED_TASK_FOUND;
        } else {
            for (int i = 1; i <= relatedTaskList.size(); ++i) {
                response += (i + ". " + relatedTaskList.get(i - 1).toString()) + "\n";
            }
        }
        return response;
    }


    /**
     * Adds task to an array list of task
     *
     * @param task
     * @return
     */
    public String add(Task task) {
        taskList.add(task);
        return UIText.ADD_TASK + task.toString() + "\n" + UIText.NUMBER_OF_TASK_LEFT + taskList.size() + "\n";
    }

    /**
     * Prints each task in the array list of tasks
     *
     * @return
     */
    public String printTaskList() {
        String response = UIText.TASK_LIST_OPENING;

        if (taskList.size() <= 0) {
            response += UIText.NO_LIST_FOUND;
        } else {
            for (int i = 1; i <= taskList.size(); ++i) {
                response += (i + ". " + taskList.get(i - 1).toString()) + "\n";
            }
        }
        return response;
    }


    /**
     * Sets a task in the array list of tasks as done
     *
     * @param index
     * @return
     * @throws DukeException
     */
    public String markTaskAsDone(int index) throws DukeException {
        if (index < 0) {
            throw new DukeException(UIText.INDEX_IS_INVALID);
        } else if (index >= taskList.size()) {
            throw new DukeException(UIText.TASK_DOES_NOT_EXIST);
        }

        taskList.get(index).markAsDone();
        return UIText.MARK_AS_DONE + taskList.get(index).toString();
    }

    /**
     * Sets a task in the array list of tasks as !done
     *
     * @param index
     * @return
     * @throws DukeException
     */
    public String markTaskAsNotDone(int index) throws DukeException{
        if (index < 0) {
            throw new DukeException(UIText.INDEX_IS_INVALID);
        } else if (index >= taskList.size()) {
            throw new DukeException(UIText.TASK_DOES_NOT_EXIST);
        }

        taskList.get(index).markAsNotDone();
        return UIText.MARK_AS_NOT_DONE + taskList.get(index).toString();
    }

    /**
     * Removes a task in the array list of tasks
     *
     * @param index
     * @return
     * @throws DukeException
     */
    public String deleteTask(int index) throws DukeException {
        String response = "";

        if (index < 0) {
            throw new DukeException(UIText.INDEX_IS_INVALID);
        } else if (index >= taskList.size()) {
            throw new DukeException(UIText.TASK_DOES_NOT_EXIST);
        }

        response = UIText.REMOVE_TASK + taskList.get(index).toString() + "\n" + UIText.NUMBER_OF_TASK_LEFT
                + (taskList.size() - 1) + "\n";

        taskList.remove(index);

        return response;
    }

    @Override
    public String toString() {
        return taskList.toString();
    }
}

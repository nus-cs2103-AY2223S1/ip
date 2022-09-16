package duke;

import java.util.ArrayList;

public class TaskList {

    protected static final UI UI = new UI();
    protected ArrayList<Task> taskList;

    TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Prints list of tasks related to description
     *
     * @param description
     */
    public String find(String description) {
        ArrayList<Task> relatedTaskList = new ArrayList<Task>();

        for (int i = 0; i < taskList.size(); ++i) {
            Task task = taskList.get(i);
            if (task.getDescription().toLowerCase().matches(("(.*)" + description + "(.*)").toLowerCase())) {
                relatedTaskList.add(task);
            }
        }

        String response = UI.FINDING_RELATED_TASK;

        if (relatedTaskList.size() <= 0) {
            response += UI.NO_RELATED_TASK_FOUND;
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
     */
    public String add(Task task) {
        taskList.add(task);
        return UI.ADD_TASK + task.toString() + "\n" + UI.NUMBER_OF_TASK_LEFT + taskList.size() + "\n";
    }

    /**
     * Prints each task in the array list of tasks
     */
    public String printTaskList() {
        String response = UI.TASK_LIST_OPENING;

        if (taskList.size() <= 0) {
            response += UI.NO_LIST_FOUND;
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
     * @throws DukeException
     */
    public String markTaskAsDone(int index) throws DukeException {
        if (index < 0) {
            throw new DukeException(UI.INDEX_IS_INVALID);
        } else if (index >= taskList.size()) {
            throw new DukeException(UI.TASK_DOES_NOT_EXIST);
        }

        taskList.get(index).markAsDone();
        return UI.MARK_AS_DONE + taskList.get(index).toString();
    }

    /**
     * Sets a task in the array list of tasks as !done
     *
     * @param index
     * @throws DukeException
     */
    public String markTaskAsNotDone(int index) throws DukeException{
        if (index < 0) {
            throw new DukeException(UI.INDEX_IS_INVALID);
        } else if (index >= taskList.size()) {
            throw new DukeException(UI.TASK_DOES_NOT_EXIST);
        }

        taskList.get(index).markAsNotDone();
        return UI.MARK_AS_NOT_DONE + taskList.get(index).toString();
    }

    /**
     * Removes a task in the array list of tasks
     * @param index
     * @throws DukeException
     */
    public String deleteTask(int index) throws DukeException {
        String response = "";

        if (index < 0) {
            throw new DukeException(UI.INDEX_IS_INVALID);
        } else if (index >= taskList.size()) {
            throw new DukeException(UI.TASK_DOES_NOT_EXIST);
        }

        response = UI.REMOVE_TASK + taskList.get(index).toString() + "\n" + UI.NUMBER_OF_TASK_LEFT
                + (taskList.size() - 1) + "\n";

        taskList.remove(index);

        return response;
    }

    @Override
    public String toString() {
        return taskList.toString();
    }
}

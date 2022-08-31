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
    public void find(String description) {
        ArrayList<Task> relatedTaskList = new ArrayList<Task>();

        for (int i = 0; i < taskList.size(); ++i) {
            Task task = taskList.get(i);
            if (task.getDescription().toLowerCase().matches(("(.*)" + description + "(.*)").toLowerCase())) {
                relatedTaskList.add(task);
            }
        }

        String response = UI.findingRelatedTask;

        if (relatedTaskList.size() <= 0) {
            response += UI.noRelatedTaskFound;
        } else {
            for (int i = 1; i <= relatedTaskList.size(); ++i) {
                response += (i + ". " + relatedTaskList.get(i - 1).toString()) + "\n";
            }
        }
        UI.printResponse(response);
    }


    /**
     * Adds task to an array list of task
     *
     * @param task
     */
    public void add(Task task) {
        taskList.add(task);
        UI.printResponse(UI.addTask + task.toString() + "\n" + UI.numberOfTaskLeft + taskList.size() + "\n");
    }

    /**
     * Prints each task in the array list of tasks
     */
    public void printTaskList() {
        String response = UI.taskListOpening;

        if (taskList.size() <= 0) {
            response += UI.noListFound;
        } else {
            for (int i = 1; i <= taskList.size(); ++i) {
                response += (i + ". " + taskList.get(i - 1).toString());
            }
        }
        UI.printResponse(response);
    }

    /**
     * Sets a task in the array list of tasks as done
     *
     * @param index
     * @throws DukeException
     */
    public void markTaskAsDone(int index) throws DukeException {
        if (index < 0) {
            throw new DukeException("I cannot mark this task because your index is invalid");
        } else if (index >= taskList.size()) {
            throw new DukeException("I cannot mark this task because task does not exist");
        }

        taskList.get(index).markAsDone();
        UI.printResponse(UI.markAsDone + taskList.get(index).toString());
    }

    /**
     * Sets a task in the array list of tasks as !done
     *
     * @param index
     * @throws DukeException
     */
    public void markTaskAsNotDone(int index) throws DukeException{
        if (index < 0) {
            throw new DukeException("I cannot unmark this task because your index is invalid");
        } else if (index >= taskList.size()) {
            throw new DukeException("I cannot unmark this task because task does not exist");
        }

        taskList.get(index).markAsNotDone();
        UI.printResponse(UI.markAsNotDone + taskList.get(index).toString());
    }

    /**
     * Removes a task in the array list of tasks
     * @param index
     * @throws DukeException
     */
    public void deleteTask(int index) throws DukeException {
        if (index < 0) {
            throw new DukeException("I cannot delete this task because your index is invalid");
        } else if (index >= taskList.size()) {
            throw new DukeException("I cannot delete this task because task does not exist");
        }

        UI.printResponse(UI.removeTask + taskList.get(index).toString() + UI.numberOfTaskLeft + (taskList.size() - 1) + "\n");

        taskList.remove(index);
    }

    @Override
    public String toString() {
        return taskList.toString();
    }
}

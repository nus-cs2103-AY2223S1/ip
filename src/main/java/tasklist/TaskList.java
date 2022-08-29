package tasklist;

import exception.FredException;

import task.Task;

import java.util.ArrayList;

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
        int counter = 1;
        String list = "Here are matching tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getDescription().contains(keyword)) {
                list += counter++ + "." + taskList.get(i) + "\n";
            }

        }

        if (list.equals("Here are matching tasks in your list:\n")) {
            throw new FredException("Could not find task with this keyword!");
        }
        return list;
    }

}

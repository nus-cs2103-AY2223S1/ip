package duke;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents the list of task managed by the ChatBot.
 */
public class TaskList {

    private List<Task> list;
    private static int noOfTasks;
    private Ui ui;
    private static HashMap<String, List<Task>> taskByKeyword;

    /**
     * Create a tasklist.
     * @param pastList
     */
    TaskList(List<Task> pastList) {
        this.list = pastList;
        this.noOfTasks = pastList.size();
        this.ui = new Ui();
        this.taskByKeyword = new HashMap<String, List<Task>>();
        for (int i = 0; i < pastList.size(); i++) {
            Task pastTask = pastList.get(i);
            String stringDescription = pastTask.getTaskDescription();
            updateKeyword(stringDescription, pastTask);
        }
    }

    List<Task> getList() {
        return this.list;
    }

    /**
     * Add new task from the users into the existing taskList.
     * Keep track of the number of tasks in the list.
     * Update the keyword hashtable.
     * @param Task task: Task given by the users to add into the list.
     * @return int noOfTasks: the current number of tasks in the list after adding it to the list.
     */
    int add(Task task) {
        this.list.add(task);
        this.noOfTasks++;
        updateKeyword(task.getTaskDescription(), task);
        return this.noOfTasks;
    }

    private void updateKeyword(String s, Task newTask) {
        String[] strarr = s.split(" ");
        for (int i = 0; i < strarr.length; i++) {
            if (taskByKeyword.containsKey(strarr[i])) {
                taskByKeyword.get(strarr[i]).add(newTask);
            } else {
                List<Task> listForHashMap = new ArrayList<Task>();
                listForHashMap.add(newTask);
                taskByKeyword.put(strarr[i], listForHashMap);
            }
        }
    }

    /**
     * Delete the task with this taskNo from the list.
     * Keep track of the number of tasks in the list.
     * Update the keyword hashtable.
     * @param int taskNo: TaskNo given by the users for deletion.
     * @return String[] task info: which contains the deleted task description and the current number of tasks in the list after deletion.
     */
    String[] delete(int taskNo) {
        Task currentTask = this.list.get(taskNo - 1);
        String currentTaskDescription = currentTask.getTaskDescription();
        removeTaskFromKeyword(currentTaskDescription, currentTask);
        this.list.remove(taskNo - 1);
        this.noOfTasks--;
        String[] tasklistInfo = {currentTask.toString(), String.valueOf(noOfTasks)};
        return tasklistInfo;
    }

    private void removeTaskFromKeyword(String s, Task deletedTask) {
        String[] strarr = s.split(" ");
        for (int i = 0; i < strarr.length; i++) {
            if (taskByKeyword.get(strarr[i]).size() == 1) {
                taskByKeyword.remove(strarr[i]);
            } else {
                taskByKeyword.get(strarr[i]).remove(deletedTask);
            }
        }
    }

    /**
     * Find tasks that contain the keyword s and print it out.
     * @param String keyword: Keyword given by the users.
     */
    void findTaskWithThisKeyword(String keyword) {
        System.out.println("Here are the matching tasks in your list:");
        List<Task> tasks = taskByKeyword.get(keyword);
        for (int i = 1; i < tasks.size() + 1; i++) {
            String matchingTask = tasks.get(i - 1).toString();
            System.out.println(i + ". " + matchingTask);
        }
    }

    /**
     * Print out the existing list of tasks for the users.
     */
    void getPrintedList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < list.size() + 1; i++) {
            String currentTask = list.get(i - 1).toString();
            System.out.println(i + ". " + currentTask);
        }
    }

    /**
     * Mark the task with this taskNo as done.
     * @param int taskNo: TaskNo given by the users to mark it as done.
     * @return String markedTask: the string version of the task that is marked.
     */
    String mark(int taskNo) {
        Task taskToBeModify = list.get(taskNo - 1);
        taskToBeModify.markAsDone();
        String markedTask = taskToBeModify.toString();
        return markedTask;
    }

    /**
     * Unmark the task with this taskNo.
     * @param int taskNo: TaskNo given by the users to mark it as undone.
     * @return String unmarkedTask: the string version of the task that is unmarked.
     */
    String unmark(int taskNo) {
        Task taskToBeModify = list.get(taskNo - 1);
        taskToBeModify.markAsUndone();
        String unmarkedTask = taskToBeModify.toString();
        return unmarkedTask;
    }

}

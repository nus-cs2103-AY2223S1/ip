package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the task list where all the tasks of the user are stored. A <code>TaskList</code> is the
 * list of all tasks of the user.
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();
    private static final String filepath = "./data/duke.txt";

    public TaskList() {
    }

    /**
     * Constructor for TaskList, given a list of tasks
     *
     * @param tasks the list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Creates a file the filepath, where all the tasks are saved for easy retrieval.
     */
    public void createFile() {
        new File("./data").mkdir();
        File taskList = new File(filepath);
    }

    /**
     * Add a task (to-do,event or deadline) to the tasklist.
     *
     * @param taskName description of the task to be added.
     * @param ui       ui to print the confirmation messages after task is added
     *                 to the list.
     */
    public String addToList(String taskName, Ui ui) {
        try {
            if (taskName.matches("\\btodo\\s.*\\b")) {
                assert taskName.length() >= 6 : "addToList function is not working for todo";
                Task newTask = new ToDo(taskName.substring(5), false, 0);
                tasks.add(newTask);
                return ui.taskAddMsg(newTask, tasks.size());
            }
            if (taskName.matches("\\bevent\\s.*\\s/at\\s.*\\b")) {
                assert taskName.length() >= 13 : "addToList function is not working for event";
                String des = taskName.substring(6, taskName.indexOf("/") - 1);
                String at = taskName.substring(taskName.indexOf("/") + 4, taskName.length());
                Task newTask = new Event(des, false, at, 0);
                tasks.add(newTask);
                return ui.taskAddMsg(newTask, tasks.size());
            }
            if (taskName.matches("\\bdeadline\\s.*\\s/by\\s.*\\b")) {
                String des = taskName.substring(9, taskName.indexOf("/") - 1);
                String by = taskName.substring(taskName.indexOf("/") + 4);
                Task newTask = new Deadline(des, false, by, 0);
                tasks.add(newTask);
                return ui.taskAddMsg(newTask, tasks.size());
            }
            if (taskName.matches("\\btodo\\s+") || taskName.matches("\\btodo\\b")) {
                throw new DukeException("Sorry please provide a task to be done!");
            } else {
                throw new DukeException("I'm sorry, I don't know what that means!");
            }
        } catch (Exception e) {
            String msg = e.getMessage();
            return msg;
        }
    }

    /**
     * View the list of all tasks.
     *
     * @param ui ui to display the list of tasks to the user.
     */
    public String viewList(Ui ui) {
        return ui.viewListMsg(tasks);
    }

    /**
     * Mark a task as done/completed.
     *
     * @param num the index of task to be marked as done.
     * @param ui  ui to display confirmatory message.
     */
    public String taskDone(int num, Ui ui) {
        Task task = tasks.get(num - 1);
        tasks.get(num - 1).markAsDone();
        return ui.taskDoneMsg(task);
    }

    public String editPriority(int num, String priority, Ui ui) {
        Task task = tasks.get(num - 1);
        task.setPriority(priority);
        return ui.editPriorityMsg(priority);
    }

    /**
     * Mark a task as undone.
     *
     * @param num index of task to be marked as undone.
     * @param ui  ui to display confirmatory message.
     */
    public String taskUndone(int num, Ui ui) {
        Task task = tasks.get(num - 1);
        tasks.get(num - 1).markAsUndone();
        return ui.taskUndoneMsg(task);
    }

    /**
     * Delete a task from the list
     *
     * @param num index of task to be deleted
     * @param ui  ui to display confirmatory message
     */
    public String deleteTask(int num, Ui ui) {
        Task task = tasks.get(num - 1);
        tasks.remove(num - 1);
        return ui.deleteTaskMsg(task, tasks.size());
    }

    public String findTask(String keyword, Ui ui) {
        return ui.viewFoundTasks(keyword, tasks);
    }

    /**
     * Save all the tasks from the list to the local file.
     */
    public void addTasksToFile() {
        try {
            FileWriter fw = new FileWriter(filepath);
            for (int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.get(i).toStore() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int size() {
        return tasks.size();
    }
}


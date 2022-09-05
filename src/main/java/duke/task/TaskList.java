package duke.task;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Tasklist class to store task objects.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for tasklist class.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Method to add a task into tasklist and
     * save it into the file.
     *
     * @param t The task to be added.
     */
    public void addTask(Task t)  {
        this.tasks.add(t);
//        try {
//            Storage.save(this.tasks);
//        } catch (IOException e) {
//            Ui.showError(e);
//        }
    }

//    /**
//     * Method to add a task but without printing
//     * the show message, this is for loading the
//     * data from storage file.
//     *
//     * @param t The task to be added.
//     */
//    public void addTaskWithoutPrinting(Task t)  {
//        this.tasks.add(t);
////        try {
////            Storage.save(this.tasks);
////        } catch (IOException e) {
////            Ui.showError(e);
////        }
//    }

    /**
     * Gets the task based on task number.
     *
     * @param taskNo The task number.
     * @return The task in the taskList at taskNo position.
     */
    public Task getTask(int taskNo) {
        return tasks.get(taskNo - 1);
    }

    /**
     * Gets all the tasks
     *
     * @return All the tasks in task list
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Gets the size of the tasks
     *
     * @return The size of the tasks
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Marks the task as done.
     *
     * @param taskNo The corresponding task ID.
     */
    public void markTask(int taskNo) {
        Task task = this.getTask(taskNo);
        task.mark();
    }

    /**
     * Marks the task as undone.
     *
     * @param taskNo The corresponding task ID.
     */
    public void unMarkTask(int taskNo) {
        Task task = this.getTask(taskNo);
        task.unMark();
    }

    /**
     * Deletes the task from tasklist.
     *
     * @param taskNo The corresponding task ID.
     */
    public void deleteTask(int taskNo) {
        this.tasks.remove(taskNo - 1);
    }

    /**
     * Finds task based on keyword.
     *
     * @param keyword The keyword to find.
     */
    public void findTask(String keyword) {
        int i = 0;
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                i = i + 1;
                System.out.println(i + "." + task);
            }
        }
    }

    /**
     * Gets the task type.
     *
     * @param task The task to check.
     * @return Returns the task type.
     */
    public String getTaskType(Task task) {
        if (task instanceof Deadline) {
            return "Deadlines";
        } else if (task instanceof Event) {
            return "Events";
        } else if (task instanceof ToDo) {
            return "ToDos";
        }
        return "Task";
    }

//    /**
//     * Prints all tasks in tasklist.
//     */
//    public void printList() {
//        Ui.showPrintListMessage();
//        int ListLength = tasks.size();
//        for (int i = 0; i < ListLength; i++) {
//            System.out.println((i + 1) + ". " + tasks.get(i));
//        }
//    }
}

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
        try {
            Storage.save(this.tasks);
        } catch (IOException e) {
            Ui.showError(e);
        }
        Ui.showAddTaskMessage(t, this.tasks.size());
    }

    /**
     * Method to add a task but without printing
     * the show message, this is for loading the
     * data from storage file.
     *
     * @param t The task to be added.
     */
    public void addTaskWithoutPrinting(Task t)  {
        this.tasks.add(t);
        try {
            Storage.save(this.tasks);
        } catch (IOException e) {
            Ui.showError(e);
        }
    }

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
     * Marks the task as done.
     *
     * @param taskNo The corresponding task ID.
     */
    public void markTask(int taskNo) {
        Task task = this.getTask(taskNo);
        task.mark();
        try {
            Storage.save(this.tasks);
        } catch (IOException e) {
            Ui.showError(e);
        }
        Ui.markTaskMessage(task);
    }

    /**
     * Marks the task as undone.
     *
     * @param taskNo The corresponding task ID.
     */
    public void unMarkTask(int taskNo) {
        Task task = this.getTask(taskNo);
        task.unMark();
        try {
            Storage.save(this.tasks);
        } catch (IOException e) {
            Ui.showError(e);
        }
        Ui.unMarkTaskMessage(task);
    }

    /**
     * Deletes the task from tasklist.
     *
     * @param taskNo The corresponding task ID.
     */
    public void deleteTask(int taskNo) {
        Task t = this.getTask(taskNo);
        this.tasks.remove(taskNo - 1);
        try {
            Storage.save(this.tasks);
        } catch (IOException e) {
            Ui.showError(e);
        }
        Ui.showDeleteTaskMessage(t, this.tasks.size());
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
     * Prints tasks based on date.
     *
     * @param date Date to find.
     */
    public void printTasksOnSpecificDate(LocalDate date) {
        Ui.showPrintTasksOnSpecificDateMessage(date);
        int i = 0;
        for (Task task : tasks) {
            if (this.getTaskType(task).equals("Deadlines")) {
                Deadline d = (Deadline) task;
                if (d.getDate().equals(date)) {
                    i = i + 1;
                    System.out.println(i + "." + d);
                }
            } else if (this.getTaskType(task).equals("Events")) {
                Event e = (Event) task;
                if (e.getDate().equals(date)) {
                    i = i + 1;
                    System.out.println(i + "." + e);
                }
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

    /**
     * Prints all tasks in tasklist.
     */
    public void printList() {
        Ui.showPrintListMessage();
        int ListLength = tasks.size();
        for (int i = 0; i < ListLength; i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
}

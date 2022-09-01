package duke;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;


/**
 * Represents a list of tasks.
 * Chatbot users can perform add/delete/mark operations on the tasks in the list.
 */
public class TaskList {
    private List<Task> tasks;

    public TaskList(List<Task> l) {
        this.tasks = l;
    }

    public TaskList(TaskList l) {
        this.tasks = l.tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Prints all tasks in the task list.
     * Each task printed consists of task type, status, description, (date);
     * task types (todo, event, deadline) are represented by [T], [E], [D],
     * task status (done, undone) are represented by [X], [ ].
     */
    public void printTaskList() {
        int count = 1;
        for (Task t : tasks) {
            System.out.println(String.format("%d.%s %s", count, t.getStatusIcon(), t.getDescription()));
            count += 1;
        }
    }

    /**
     * Returns the string format of task list to be saved to storage.
     * Each task string consists of task type, status, description, (date);
     * The format is | task type | status | description | time
     *
     * @return string format of task list.
     */
    public String saveTaskList() {
        String result = "Task Type | Status | Description & Time\n";
        for (Task t : tasks) {
            result += t.toString() + "\n";
        }
        return result;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return the number of tasks.
     */
    public Integer getSize() {
        return this.tasks.size();
    }

    /**
     * Returns the task at an index in the task list (the index method starts from 0).
     * The task at index is deleted from the task list.
     *
     * @param index task index.
     * @return Task object.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the task at an index in the task list (the index method starts from 1).
     *
     * @param index task index.
     * @return Task object.
     */
    public Task deleteTaskAtIndex(int index) {
        return this.tasks.remove(index - 1);
    }

    /**
     * Adds the task at the end of the task list.
     *
     * @param t task to add to list.
     */
    public void addTask(Task t) {
        this.tasks.add(t);
    }

    /**
     * Marks a task at task index as done and returns the marked task.
     *
     * @param index task index.
     * @return marked task.
     */
    public Task markAsDone(int index) {
        Task task = this.tasks.get(index - 1);
        task.markAsDone();
        return task;
    }

    /**
     * Marks a task at task index as undone and returns the marked task.
     *
     * @param index task index.
     * @return marked task.
     */
    public Task markAsUndone(int index) {
        Task task = this.tasks.get(index - 1);
        task.markAsUndone();
        return task;
    }

    /**
     * Returns a task list containing unfinished tasks on the date.
     * The task list includes unfinished deadline tasks with end date after/on the specified date,
     * and unfinished event tasks with the specified date between start and end date.
     *
     * @param date LocalDate object.
     * @return task list.
     */
    public TaskList searchByDate(LocalDate date) {
        List<Task> tasksAtDate = new ArrayList<Task>();
        for (Task t : tasks) {
            if (!t.getStatus() && t.taskType().equals("event")) {
                Event e = (Event) t;
                if ((date.isAfter(e.getStartDate()) && date.isBefore(e.getEndDate()))
                        || date.isEqual(e.getStartDate()) || date.isEqual(e.getEndDate())) {
                    tasksAtDate.add(e);
                }
            } else if (!t.getStatus() && t.taskType().equals("deadline")) {
                Deadline d = (Deadline) t;
                if (!date.isAfter(d.getEnd())) {
                    tasksAtDate.add(d);
                }
            }
        }
        return new TaskList(tasksAtDate);
    }

    /**
     * Returns a task list containing tasks that have task description related to keyword.
     *
     * @param keyword task description keyword.
     * @return task list.
     */
    public TaskList searchByKeyword(String keyword) {
        TaskList tasksByKeyword = new duke.TaskList();
        for (Task t : tasks) {
            if (t.getDescription().contains(keyword)) {
                tasksByKeyword.addTask(t);
            }
        }
        return tasksByKeyword;
    }

}

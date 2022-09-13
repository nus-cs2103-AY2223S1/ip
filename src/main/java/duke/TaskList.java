package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * Represents a list of tasks.
 * TaskDive chatbot users can perform add/delete/mark operations on the tasks in the list.
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
     * Returns a String representation of all tasks in the task list.
     * String representation of each task consists of task type, status, description, (date), tags.
     *
     * Task types (todo, event, deadline) are represented by [T], [E], [D],
     * task status (done, undone) are represented by [X], [ ],
     * task date is represented as the format yyyy-MM-dd, task tags are represented as {tag1, tag2, tag3} (if any).
     *
     * @return String representation of TaskList.
     */
    public String printTaskList() {
        if (this.getSize() == 0) {
            return "Oops! There's no matching tasks found :-(";
        }
        int count = 1;
        String listString = "";
        for (Task t : tasks) {
            //listString += String.format("%d.%s %s %s\n", count, t.getStatusIcon(), t.getDescription(), t.printTags());
            listString += String.format("%d.%s\n", count, t.printTask());
            count += 1;
        }
        return listString;
    }

    /**
     * Returns the string format of task list to be saved in storage.
     * String representation of each task consists of task type, status, description, (date), tags;
     * The format is | task type | status | description | time | tags
     *
     * @return string format of task list.
     */
    public String saveTaskList(){
        String result = "Task Type | Status | Description & Time | Tags\n";
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
     * Returns the task at an index in the task list (0-indexed).
     *
     * @param index task index.
     * @return Task object.
     */
    public Task getTask(int index) {
        assert index >= 0 && index < this.getSize() : "index out of bound." ;
        return tasks.get(index);
    }

    /**
     * Returns the task at an index in the task list (1-indexed).
     * The task at index is then deleted from the task list.
     *
     * @param index task index.
     * @return Task object.
     */
    public Task deleteTaskAtIndex(int index) {
        assert index >= 1 && index <= this.getSize() : "index out of bound." ;
        return this.tasks.remove(index - 1);
    }

    /**
     * Adds a new task at the end of the task list.
     *
     * @param task a new task.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Marks the status of the task at task index as done and returns the marked task (1-indexed).
     *
     * @param index task index.
     * @return marked task.
     */
    public Task markAsDone(int index) {
        assert index >= 1 && index <= this.getSize() : "index out of bound." ;
        Task task = this.tasks.get(index - 1);
        task.markAsDone();
        return task;
    }

    /**
     * Marks the status of the task at task index as undone and returns the marked task.
     *
     * @param index task index.
     * @return marked task.
     */
    public Task markAsUndone(int index) {
        assert index >= 1 && index <= this.getSize() : "index out of bound." ;
        Task task = this.tasks.get(index - 1);
        task.markAsUndone();
        return task;
    }

    /**
     * Searches taskList and returns a TaskList containing unfinished tasks on the date.
     * The task list includes unfinished deadline tasks with end date after/on the date,
     * and unfinished event tasks with the specified date between start and end date (inclusive).
     *
     * @param date LocalDate object.
     * @return task list.
     */
    public TaskList searchByDate(LocalDate date) {
        List<Task> tasksAtDate = new ArrayList<Task>();
        for (Task t : tasks) {
            assert t.equals("deadline") || t.equals("todo") || t.equals("event") : "Invalid task type.";
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
     * Searches taskList and returns a new TaskList of tasks that has the tag.
     *
     * @param tag task tag.
     * @return task list.
     */
    public TaskList searchByTag(String tag) {
        TaskList tasksByTag = new duke.TaskList();
        for (Task t : tasks) {
            if (t.containsTag(tag)) {
                tasksByTag.addTask(t);
            }
        }
        return tasksByTag;
    }

    /**
     * Searches taskList and returns a TaskList of tasks whose task description contains keyword.
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

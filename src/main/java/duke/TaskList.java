package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


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
    public String printTaskList() {
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
     * Returns the string format of task list to be saved to storage.
     * Each task string consists of task type, status, description, (date);
     * The format is | task type | status | description | time
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
     * Returns the task at an index in the task list (the index method starts from 0).
     * The task at index is deleted from the task list.
     *
     * @param index task index.
     * @return Task object.
     */
    public Task getTask(int index) {
        assert index >= 0 && index < this.getSize() : "index out of bound." ;
        return tasks.get(index);
    }

    /**
     * Returns the task at an index in the task list (the index method starts from 1).
     *
     * @param index task index.
     * @return Task object.
     */
    public Task deleteTaskAtIndex(int index) {
        assert index >= 1 && index <= this.getSize() : "index out of bound." ;
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
        assert index >= 1 && index <= this.getSize() : "index out of bound." ;
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
        assert index >= 1 && index <= this.getSize() : "index out of bound." ;
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

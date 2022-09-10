package duke;

import java.time.LocalDate;

import java.util.ArrayList;


public class TaskList {

    private ArrayList<Task> tasks;
    private Ui ui;

    /**
     * A constructor to initialize a TaskList Object.
     *
     * @param tasks An ArrayList object containing Task Objects to do.
     * @param ui An Ui Object dealing with user interface of the application.
     */
    public TaskList(ArrayList<Task> tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Returns number of tasks in the TaskList.
     *
     * @return An integer representing number of tasks in TaskList.
     */
    public int getLength() {
        return tasks.size();
    }

    /**
     * Returns the Task Object at the given index.
     *
     * @param index The index of the task object.
     * @return A Task Object with the given index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns a String with details of ToDo Object being added into TaskList.
     *
     * @param task The String task description of the ToDo.
     * @return A String with the details of ToDo Object added.
     */
    public String addToDo(String task) {
        ToDo newToDo = new ToDo(false, task, tasks.size() + 1, "");
        tasks.add(newToDo);
        assert tasks.size() >= 1 : "TaskList should have at least one task"
                + "since at least one task has been added.";

        return newToDo.printAdded();
    }

    /**
     * Returns a string with details of Deadline Object being added into TaskList.
     *
     * @param task The String task description of the Deadline.
     * @param d1 The LocalDate Object representing date of the Deadline.
     * @param newTime The String representation of time of the Deadline.
     * @return A String with the details of Deadline Object added.
     */
    public String addDeadline(String task, LocalDate d1, String newTime) {
        Deadline newDeadline = new Deadline(false, task, tasks.size() + 1, "", d1, newTime);
        tasks.add(newDeadline);
        assert tasks.size() >= 1 : "TaskList should have at least one task"
                + "since at least one task has been added.";

        return newDeadline.printAdded();
    }

    /**
     * Returns a string with details of Event Object being added into TaskList.
     *
     * @param task The String task description of the Event.
     * @param d1 The LocalDate Object representing date of the Event.
     * @param newTime The String representation of time of the Event.
     * @return A string with the details of Event Object added.
     */
    public String addEvent(String task, LocalDate d1, String newTime) {
        Event newEvent = new Event(false, task, tasks.size() + 1, "", d1, newTime);
        tasks.add(newEvent);
        assert tasks.size() >= 1 : "TaskList should have at least one task"
                + "since at least one task has been added.";

        return newEvent.printAdded();
    }

    /** Deletes a task from the TaskList at the given index.
     *
     * @param index an Integer representing the position of the task in the TaskList.
     * @return A string representation of the Task deleted.
     */
    public String deleteTask(int index) {
        try {
            Task t = tasks.get(index - 1);
            String deleted = t.printDeleted();
            tasks.remove(index - 1);
            return deleted + "\n  Now you have " + tasks.size()+ " left\n";
        } catch (StringIndexOutOfBoundsException e) {
            return ui.printInsufficientInfoException(Ui.Keywords.delete);
        } catch (IndexOutOfBoundsException e) {
            return ui.printIndexOutOfBoundsException(Ui.Keywords.delete);
        }
    }

    /**
     * Marks the task at the given index as done.
     *
     * @param index an Integer representing position of the task in the TaskList.
     * @return A string representation of the Task marked done.
     */
    public String markDone(int index) {
        Task t = tasks.get(index - 1);

        return t.markDone();
    }

    /**
     * Unmarks the task at the given index as done.
     *
     * @param index an Integer representing position of the task in the TaskList.
     * @return A string representation of the Task marked undone.
     */
    public String markUndone(int index) {
        Task t = tasks.get(index - 1);

        return t.markUndone();
    }

    /**
     * Finds matching tasks in the TaskList using the keyword provided.
     *
     * @param keyword A string representing a possible word in task description.
     * @return A String representation of the list of matching tasks.
     */
    public String findMatchingTasks(String keyword) {

        String matches = "";

        if (tasks.isEmpty()) {
            return ui.printNoMatchingTask();
        }

        for (Task task: tasks) {
            if (task.getDescription().contains(keyword)) {
                String taskStr = task.printTask();
                matches += taskStr + "\n";
            }
        }

        return matches;
    }

    /**
     * Returns a String representation of the TaskList.
     *
     * @return a String representation of the TaskList.
     */
    public String printList() {
        if (tasks.isEmpty()) {
            return ui.printTaskListEmpty();
        } else {

            String list = "";
            int freshIndex = 1;

            for (Task task : tasks) {
                task.setIndex(freshIndex);
                freshIndex++;
            }

            for (Task task : tasks) {
                String newTask = task.printTask();
                list += newTask + "\n";
            }

            return list;
        }
    }

    public String tagTask(int index, String hashtag) {
        Task t = tasks.get(index - 1);

        return t.tag(hashtag);
    }

}

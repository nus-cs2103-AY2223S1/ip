package duke.tasks;

import duke.dukeexception.DateTimeFormatException;

import java.lang.StringBuilder;

public class Task {
    protected String name;
    static protected Integer totalNumber = 0;
    protected Integer index;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        totalNumber++;
        this.index = totalNumber;
        this.isDone = false;
    }

    /**
     * Helper function to call while adding a new task.
     * @param name the new task description.
     * @return the duke.Tasks.Task class being created.
     */
    public static Task addTask(String name) throws DateTimeFormatException {
        Task newTask = new Task(name);
        System.out.println("       " + newTask.printSelf());
        return newTask;
    }

    /**
     * Helper function to call while deleting a task, to keep track of the total number of tasks.
     * @param task The task being deleted.
     */
    public static void removeTask(Task task) throws DateTimeFormatException {
        totalNumber--;
        System.out.println("       " + task.printSelf());
    }

    /**
     * Update the index of a particular task to be index -1 after removal.
     */
    public void updateRemoval() {
        index--;
    }

    /**
     * Gets the icon depending on isDone status.
     * @return [X] if not done, [ ] is done.
     */
    public String getStatusIcon() {
        return (this.isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Gets the "1" or "0" depending on isDone status.
     * @return 1 if not done, 0 is done.
     */
    public String getStatusString() {
        return (this.isDone ? "1" : "0");
    }

    /**
     * Flips the status of isDone
     */
    public void changeStatus() {
        this.isDone = !this.isDone;
    }

    /**
     * Returns whether the task is completed.
     * @return True if done, False if have not yet done.
     */
    public boolean checkDone() {
        return this.isDone;
    }

    /**
     * Prints this line at the end of task adding/deleting for alert.
     */
    public static void countTaskLeft() {
        System.out.println("     Now you have " + totalNumber + " tasks in the list.");
    }

    /**
     * Returns an indicator of whether some string is a substring of task name.
     * @param str the substring for searching.
     * @return The indicator.
     */
    public boolean findName(String str) {
        return this.name.contains(str);
    }

    /**
     * helper func for toString method
     * @return string like: [icon] task name
     */
    public String printSelf() throws DateTimeFormatException {
        StringBuilder output = new StringBuilder();
        output.append(this.getStatusIcon() + " " + this.name);
        return output.toString();
    }

    public String recordString() {
        return getStatusString() + " | " + name;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("     ");
        try {
            output.append(this.index + "." + this.printSelf());
        } catch (DateTimeFormatException e) {
            throw new RuntimeException(e);
        }
        return output.toString();
    }

}

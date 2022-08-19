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

    public static Task addTask(String name) {
        Task newTask = new Task(name);
        System.out.println("     " + newTask.printSelf());
        return newTask;
    }

    /**
     * Get the icon depending on isDone status.
     * @return [X] if not done, [ ] is done.
     */
    public String getStatusIcon() {
        return (this.isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * To flip the status of isDone
     */
    public void changeStatus() {
        this.isDone = !this.isDone;
    }

    /**
     * Print this line at the end of task adding/deleting for alert.
     */
    public static void countTaskLeft() {
        System.out.println("     Now you have " + totalNumber + " tasks in the list.");
    }

    /**
     * helper func for toString method
     * @return string like: [icon] task name
     */
    public String printSelf() {
        StringBuilder output = new StringBuilder();
        output.append(this.getStatusIcon() + " " + this.name);
        return output.toString();
    }
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("     ");
        output.append(this.index + "." + this.printSelf());
        return output.toString();
    }
}

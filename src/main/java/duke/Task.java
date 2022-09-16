package duke;

import javafx.scene.layout.Priority;

import java.time.LocalDateTime;

/**
 * Represents a task.
 */
public class Task {

    private String status;
    private String type;
    private String name;
    private LocalDateTime time;
    private char priority;
    private Duke duke = new Duke();

    /**
     * Constructs a task.
     */
    public Task() {
    };

    /**
     * Constructs a task given a name.
     *
     * @param name the task name.
     */
    public Task(String name) {
        this.status = "[ ]";
        this.name = name;
        this.priority = 'L';
    }

    /**
     * Sets the priority of the task.
     * @param priority the priority of the task.
     */
    public void setPriority(char priority) {
        this.priority = priority;
    }

    /**
     * Returns the priority of the task.
     * @return the priority of the task.
     */
    public char getPriority() {
        return this.priority;
    }

    /**
     * Returns a string representation of the type of the task.
     *
     * @return the task type.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Returns a string representation of the name of the task.
     *
     * @return the task name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns a string representation of the status of the task.
     *
     * @return the task status.
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * Returns the time of the task.
     *
     * @return the task time.
     */
    public LocalDateTime getTime() {
        return this.time;
    }

    /**
     * Sets the status of the task as specified.
     *
     * @param status the status of the task. [X] for done, [ ] for undone.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Prints the task list.
     */
    public void print() {
        TaskList t = new TaskList();
        System.out.println(Duke.LINE);
        for (int i = 0, j = 1; i < duke.getCount(); i++, j++) {
            System.out.println(j + ". " + t.getList().get(i).status + t.getList().get(i).name);
        }
        System.out.println(Duke.LINE + "\n");
    }

    /**
     * Returns the task list.
     * @return the task list.
     */
    public String printGui() {
        TaskList t = new TaskList();
        String output = Duke.LINE;
        for (int i = 0, j = 1; i < duke.getCount(); i++, j++) {
            output += j + ". " + t.getList().get(i).status + t.getList().get(i).name + "\n";
        }
        output += "\n";
        return output;
    }

    /**
     * Lists the description of the task.
     */
    public void list() {
    };

    /**
     * Lists the description of the task.
     * @return the description of the task.
     */
    public String listGui() {
        return "";
    }

    /**
     * Returns the String representation of the task.
     *
     * @return the String representation of the task.
     */
    public String toString() {
        return " ";
    };

    /**
     * Returns the description of the task.
     *
     * @return the description of the task.
     */
    public String printDescription() {
        return "";
    }
}

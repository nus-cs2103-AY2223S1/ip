package dukeprogram.tasks;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import dukeprogram.userinterface.WidgetTaskLabel;

/**
 * A serializable Task class that describes a task in a tasklist
 */
public abstract class Task implements Serializable {

    @JsonProperty("name")
    private String name;
    @JsonProperty("isComplete")
    private boolean isComplete;

    /**
     * Creates a new task with the given name
     * @param name task name
     */
    public Task(String name) {
        this.name = name;
        this.isComplete = false;
    }

    public Task() {

    }

    /**
     * Returns the name of this task
     * @return the name of this task
     */
    public String getName() {
        return name;
    }

    public abstract WidgetTaskLabel createLabelWidget();

    protected boolean getTaskState() {
        return isComplete;
    }

    /**
     * Annotates this task as either complete or incomplete
     * @param isComplete the state to annotate this task with
     */
    public void markJobState(boolean isComplete) {
        this.isComplete = isComplete;
    }

    /**
     * Returns a string describing the state of completion of this task followed
     * by the name of the task
     * @return a string formatted as "[state] task_name"
     */
    @Override
    public String toString() {
        return String.format("[%s] ", isComplete ? "X" : " ") + name;
    }
}

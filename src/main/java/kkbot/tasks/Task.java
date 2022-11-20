package kkbot.tasks;

/**
 * Tasks are added by the user for KKBot to store and keep track of.
 *
 * @author AkkFiros
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a task
     * @param description Description of the task that the user inputs
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Obtains and returns the task description
     * @return Task description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Obtains and returns the completion status of the task
     * @return The completion status
     */
    public boolean getCompletion() {
        return this.isDone;
    }

    /**
     * Obtains and returns the icon representation
     * of the completion status of the task
     * @return the icon representing the completion status of the task
     */
    public String getStatusIcon() {
        if (isDone) {
            return "X";
        } else {
            return " ";
        }
    }

    /**
     * Sets a task to complete
     */
    public void changeStatus(boolean status) {
        this.isDone = status;
    }

    /**
     * Method to check if a task's description contains
     * a user input phrase (for FindCommand).
     * @param phrase user-input phrase to search for
     * @return a boolean if the Task's description contains the phrase
     */
    public boolean hasPhrase(String phrase) {
        if (phrase.isEmpty()) {
            return false;
        }

        return description.contains(phrase);
    }

    /**
     * toString method for a task when KKBot is listing out stored tasks
     * @return string representation of a task stored.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s",
                getStatusIcon(), description);
    }

    /**
     * Method to retrieve a task's type
     * @return Symbol representation of the Task's type
     */
    public abstract String getType();

    /**
     * Method to retrieve a task's date
     * @return the date of a task (if any)
     */
    public abstract String getDate();
}

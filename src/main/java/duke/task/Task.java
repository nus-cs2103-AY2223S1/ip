package duke.task;

/**
 * Abstract duke.task.Task class encapsulates all the different types of tasks
 * @author Shaune Ang
 */
abstract public class Task {
    private String name;
    private Boolean isCompleted = false;
    private Boolean isPrioritised = false;
    private PriorityLevel priorityLevel = PriorityLevel.NONE;

    // enum of all priority levels
    public enum PriorityLevel {
        NONE,
        LOW,
        MEDIUM,
        HIGH
    }

    /**
     * duke.task.Task constructor for tasks created by user
     * @param name name of task
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * duke.task.Task constructor for tasks loaded from saved file
     * @param name name of task
     * @param isCompleted Completion status of task
     */
    public Task(String name, boolean isCompleted, PriorityLevel priority) {
        this.name = name;
        this.isCompleted = isCompleted;
        this.priorityLevel = priority;
    }

    /**
     * Changes isCompleted to done
     */
    public String mark() {
        String message;
        if (isCompleted) {
            message = "This task is already marked:";
        } else {
            isCompleted = true;
            message = "Good Job on completing the task! I've marked this task as done:";
        }
        return message + "\n" + this;
    }

    /**
     * Changes isCompleted to not done
     */
    public String unmark() {
        String message;
        if (!isCompleted) {
            message = "This task is already unmarked:";
        } else {
            isCompleted = false;
            message = "OK, I've marked this task as not done yet:";
        }
        return message + "\n" + this;
    }

    /**
     * Returns name of task and its status to be used when list is called
     * @return Returns String of name of task formatted with status showing
     */
    @Override
    public String toString() {
        String mark = isCompleted ? "X" : "  ";
        if (isPrioritised()) {
            return String.format("[%s] [%s] %s", mark, priorityToSymbol(), name);
        } else {
            return String.format("[%s] %s", mark, name);
        }
    }

    /**
     * Returns completion status of task
     * @return completion status of task
     */
    public boolean getStatus() {
        return isCompleted;
    }

    /**
     * Returns name of task
     * @return name of task
     */
    public String getName() {
        return name;
    }

    /**
     * Returns priority in string form for storage
     * @return priority of task in symbol form
     */
    public String getPriority() {
        return priorityToSymbol();
    }

    /**
     * Returns true if task name contains the searchString
     * @param searchString
     * @return true if task name contains searchString
     */
    public boolean nameContains(String searchString) {
        return name.contains(searchString);
    }

    /**
     * Returns true if task has priority stated
     * @param priority priority user is searching for
     * @return true if task has priority stated
     */
    public boolean hasPriority(PriorityLevel priority) {
        return this.priorityLevel == priority;
    }

    /**
     * Converts priority levels into string
     * @return string of priority levels
     */
    public String priorityToString() {
        switch (priorityLevel) {
            case LOW:
                return "low";
            case MEDIUM:
                return "medium";
            case HIGH:
                return "high";
            default:
                return "none";
        }
    }

    /**
     * Converts priority levels into string
     * @return string of priority levels
     */
    public String priorityToSymbol() {
        switch (priorityLevel) {
            case LOW:
                return "!";
            case MEDIUM:
                return "!!";
            case HIGH:
                return "!!!";
            default:
                return "_";
        }
    }

    /**
     * Converts symbol to priority level
     * @return priority level
     */
    public static PriorityLevel symbolToPriority(String symbol) {
        switch (symbol) {
            case "!":
                return PriorityLevel.LOW;
            case "!!":
                return PriorityLevel.MEDIUM;
            case "!!!":
                return PriorityLevel.HIGH;
            case "_":
            default:
                return PriorityLevel.NONE;
        }
    }

    /**
     * Change priority of task
     * @return Response to the command
     */
    public String setPriority(PriorityLevel priorityLevel) {
        String message;
        if (this.priorityLevel == priorityLevel) {
            message = String.format("This task is marked as %s:", priorityToString());
        } else {
            this.priorityLevel = priorityLevel;
            message = String.format("Noted, changed priority of this task to %s:", priorityToString());
        }
        return message + "\n" + this;
    }

    /**
     * Converts command line priority to Enum PriorityLevel
     * @param command priority in command input by user
     * @return returns Enum PriorityLevel
     * @throws Exception
     */
    public static PriorityLevel commandToPriorityLevel(String command) throws Exception{
        switch(command) {
            case "l":
                return PriorityLevel.LOW;
            case "m":
                return PriorityLevel.MEDIUM;
            case "h":
                return PriorityLevel.HIGH;
            case "-":
                return PriorityLevel.NONE;
            default:
                throw new Exception("Unable to read priority command");
        }
    }

    /**
     * Checks if task is of priority
     * @return true if task is low medium or high priority, else false
     */
    private boolean isPrioritised() {
        switch(priorityLevel) {
            case LOW:
            case MEDIUM:
            case HIGH:
                return true;
            case NONE:
            default:
                return false;

        }
    }
}

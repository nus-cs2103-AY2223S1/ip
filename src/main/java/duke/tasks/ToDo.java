package duke.tasks;

/**
 * Represents a ToDo, which has the task in String, if done as boolean
 */
public class ToDo extends Task {
    public ToDo(String input) {
        super(input, "", "T");
    }

    public ToDo(String input, boolean done, String dummy) {
        super(input, done, "", "T");
    }

    /**
     * @return new ToDo object, with deadline toggled to True
     */
    public ToDo markDone() {
        return new ToDo(this.getVal(), true, "");
    }

    /**
     * @return new ToDo object, with deadline toggled to False
     */
    public ToDo markUndone() {
        return new ToDo(this.getVal(), false, "");
    }

    /**
     * @return string of method as [T][deadline] task (date) format
     */
    @Override
    public String toString() {
        String format;
        if(this.getDone()) {
            format = "[T][X] %s";
        }
        else {
            format = "[T][ ] %s";
        }
        return String.format(format, this.getVal());
    }

    /**
     * @return string format T | done | task | date, as specified in duke.txt
     */
    @Override
    public String toText() {
        var isDone = this.getDone() ? 1 : 0;
        String format = "T | %s | %s";
        return String.format(format, isDone, this.getVal());
    }
}

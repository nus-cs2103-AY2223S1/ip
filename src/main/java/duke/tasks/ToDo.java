package duke.tasks;

/**
 * Represents a ToDo, which has the task in String, if done as boolean
 */
public class ToDo extends Task {
    public ToDo(String input) {
        super(input, "");
    }

    public ToDo(String input, boolean done, String dummy) {
        super(input, done, "");
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
        if(this.getDone()) {
            return String.format("[T][X] %s", this.getVal());
        }
        else {
            return String.format("[T][ ] %s", this.getVal());
        }
    }

    /**
     * @return string format T | done | task | date, as specified in duke.txt
     */
    @Override
    public String toText() {
        return String.format("T | %s | %s", this.getDone() ? 1 : 0, this.getVal());
    }
}

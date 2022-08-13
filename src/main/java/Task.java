public class Task {
    private boolean done;
    private String title;
    private types type;
    private enum types{TODO, DEADLINE, EVENT}

    public Task(String title, String type) {
        switch(type) {
        case "todo":
            this.type = types.TODO;
            break;

        case "deadline":
            this.type = types.DEADLINE;
            break;

        case "event":
            this.type = types.EVENT;
            break;
        }
        this.done = false;
        this.title = title;
    }

    @Override
    public String toString() {
        return "[" + getType() + "]" + "[" + (isDone()?"X":" ") + "] " + title;
    }

    /**
     * This marks the task as being done.
     * Changing the boolean done to true.
     */
    public void mark() {
        this.done = true;
    }

    /**
     * This marks the task as not done.
     * Changing the boolean done to false.
     */
    public void unmark() {
        this.done = false;
    }

    public boolean isDone() {
        return this.done;
    }

    public String getType() {
        switch(this.type) {
            case TODO:
                return "T";
            case DEADLINE:
                return"D";
            case EVENT:
                return "E";
            default:
                //Should catch erroneous types
                return "T";
        }
    }

}

package duke.task;

abstract public class Task {
    private String description;
    private Status status;
    private enum Status {
        DONE,
        NOTDONE
    }

    public Task(String description) {
        this.description = description;
        //for now, new tasks added assumed to be undone
        this.status = Status.NOTDONE;
    }


    public void mark() {
        this.status = Status.DONE;
    }

    public void unmark() {
        this.status = Status.NOTDONE;
    }

    @Override
    public String toString() {
        switch (this.status) {
        case DONE:
            return "[X] " + this.description;
        case NOTDONE:
            return "[ ] " + this.description;
        default:
            return "";  //should not come here
        }
    }

    public String toData() {
        switch (this.status) {
        case DONE:
            return "1" + this.description.length() + "_" + this.description;
        case NOTDONE:
            return "0" + this.description.length() + "_" + this.description;
        default:
            return "";  //should not come here
        }
    }

    public boolean contains(String keyword) {
        return this.description.contains(keyword);
    }
}

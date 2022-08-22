public class Task {
    protected String description;
    protected boolean isDone;
    protected enum Type {TODO, EVENT, DEADLINE}
    protected Type type;

    public Task(String description, String type) {
        this.description = description;
        this.isDone = false;
        if ("todo".equals(type)) {
            this.type = Type.TODO;
        } else if ("event".equals(type)) {
            this.type = Type.EVENT;
        } else if ("deadline".equals(type)) {
            this.type = Type.DEADLINE;
        } else {
            System.out.println("Invalid choice, please choose either todo, event or deadline");
        }
    }

    public void setStatusIcon(boolean b) {
        this.isDone = b;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getType() {
        switch (this.type) {
            case TODO:
                return "T";
            case EVENT:
                return "E";
            case DEADLINE:
                return "D";
        };
        return "";
    }

    public String getTypeLong() {
        switch (this.type) {
            case TODO:
                return "todo";
            case EVENT:
                return "event";
            case DEADLINE:
                return "deadline";
        };
        return "";
    }

    public String getStatus() {
        if (this.isDone) {
            return "1";
        }
        return "0";
    }

    public String toString() {
        return "["+ this.getType() + "]" + "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String getData() {
        return getTypeLong() + " " + this.description + "|" + this.getStatus();
    }
}

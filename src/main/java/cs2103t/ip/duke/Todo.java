package cs2103t.ip.duke;

public class Todo extends Task {

    protected String by;
    private final String LINE = "_______________________________\n";

    public Todo(String description) {
        super(description);
        this.by = by;
    }

    @Override
    public String addString(int i) {
        String gotIt = "Got it. I've added this task: \n";
        String task = this.toString() + "\n";
        String now = String.format("Now you have %d tasks in the list \n", i);
        return LINE + gotIt + task + now + LINE;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String saveString() {
        if (this.isDone) {
            return "T" + "X " + this.description + "\n";
        } else {
            return "T  " + this.description + "\n";
        }
    }
}

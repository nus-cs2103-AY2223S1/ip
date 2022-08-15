public class Todo extends Task {

    protected String by;

    public Todo(String description) {
        super(description);
        this.by = by;
    }

    @Override
    public String addString(int i) {
        String line = "_______________________________\n";
        String gotIt = "Got it. I've added this task: \n";
        String task = this.toString() + "\n";
        String now = String.format("Now you have %d tasks in the list \n", i);
        return line + gotIt + task + now + line;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

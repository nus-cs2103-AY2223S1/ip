public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
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
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    public void setAt(String date) {
        at = date;
    }
}

public class ToDos extends Task {
    public String command;
    public int num;

    public ToDos(String description, int num) {
        super(description);
        this.command = description;
        this.num = num;
    }

    @Override
    public String toString() {
        return "Got it. I've added this task:\n" + "[T][ ] " + this.command + "\n" + "Now you have " + this.num + " tasks in the list.";
    }
}

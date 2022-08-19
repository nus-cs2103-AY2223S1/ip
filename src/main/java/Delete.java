public class Delete {
    public int num;
    public String command;

    public Delete(String description, int num) {
        this.command = description;
        this.num = num - 1;
    }

    @Override
    public String toString() {
        return "Noted. I've removed this task:\n" + this.command + "\n" + "Now you have " + this.num + " tasks in the list.";
    }
}

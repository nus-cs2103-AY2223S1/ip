public class Task {
    boolean completed = false;
    String taskName;
    int index;

    public Task(String name, int value) {
        taskName = name;
        index = value;

        System.out.printf(
                "    ____________________________________________________________\n" +
                "     added: %s\n" +
                "    ____________________________________________________________\n", taskName);
    }

    public void mark() {
        completed = true;
        System.out.printf(
                "    ____________________________________________________________\n" +
                "     Nice! I've marked task %s as done:\n" +
                "     " + this.toString() + "\n" +
                "    ____________________________________________________________\n", index);
    }

    public void unMark() {
        completed = false;
        System.out.printf(
                "    ____________________________________________________________\n" +
                "     Ok, I've marked task %s as not done yet:\n" +
                "     " + this.toString() + "\n" +
                "    ____________________________________________________________\n", index);
    }

    public String toString() {
        String marked = "[ ]";
        if (completed) {
            marked = "[X]";
        }
        return index + ". " + marked + " " + taskName;
    }
}

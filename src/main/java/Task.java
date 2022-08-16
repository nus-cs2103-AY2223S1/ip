public class Task {
    String title;
    boolean isDone;

    public Task(String title) {
        this.title = title;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
        System.out.println(String.format("Nice! I've marked this task as done:\n%s", this.print()));
    }

    public void unmark() {
        this.isDone = false;
        System.out.println(String.format("Ok, I've marked this task as not done yet:\n%s", this.print()));
    }

    public String print() {
        String isDoneCheck = isDone ? "X" : " ";
        return String.format("[%s] %s", isDoneCheck, title);
    }
}
public class Task {
    private String title;
    private Boolean isDone;

    public Task(String title) {
        this.title = title;
        this.isDone = false;
    }

    public void markIsDone() {
        if (isDone) {
            System.out.println("Error: Task is already done");
        } else {
            System.out.println("OK, I've marked this task as done: ");
            this.isDone = true;
            System.out.println(this);
        }
    }

    public void unmarkIsDone() {
        if (isDone) {
            System.out.println("OK, I've marked this task as not done yet: ");
            this.isDone = false;
            System.out.println(this);
        } else {
            System.out.println("Error: Task is not done");
        }
    }

    @Override
    public String toString() {
        String newString = String.format("[%s] %s", isDone ? "X" : " " , title);
        return newString;
    }
}

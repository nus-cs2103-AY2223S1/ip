public class Task {
    private String name;

    private String type;

    private int status;

    public static int numberTasks = 0;

    public Task(String name, String type) {
        this.name = name;
        this.type = type;
        this.status = 0;
        numberTasks++;
    }

    public void mark() {
        this.status = 1;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[" + this.type + "][X] " + this.name);
    }

    public void unMark() {
        this.status = 0;
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println("[" + this.type + "][ ] " + this.name);
    }

    public static int getNumberTasks() {
        return numberTasks;
    }

    public int getStatus() {
        return status;
    }

    public String output() {
        if (this.status == 0) {
            return "[" + this.type + "][ ] " + this.name;
        } else {
            return "[" + this.type + "][X] " + this.name;
        }
    }
}
